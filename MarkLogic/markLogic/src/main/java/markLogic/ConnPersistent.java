package markLogic;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.GraphQuery;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;

import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.semantics.SPARQLRuleset;
import com.marklogic.semantics.rdf4j.MarkLogicRepository;
import com.marklogic.semantics.rdf4j.MarkLogicRepositoryConnection;
import com.marklogic.semantics.rdf4j.config.MarkLogicRepositoryFactory;
import com.marklogic.semantics.rdf4j.query.MarkLogicTupleQuery;

import queryes.VarieQuery;
import queryes.VarieQueryPerGraphDB;
import queryes.VarieQueryPerGraphDBPropertyPath;

public class ConnPersistent {

	private VarieQuery vq = new VarieQuery();
	private VarieQueryPerGraphDB vqpgdb = new VarieQueryPerGraphDB();
	private VarieQueryPerGraphDBPropertyPath vqpgdbPP = new VarieQueryPerGraphDBPropertyPath();
	private String queryString = null;
	private TupleQuery tupleQuery = null;
	private TupleQueryResult results = null;
	private long inizio = 0;
	private long fine = 0;

	public static MarkLogicRepositoryConnection repCon;

	public ConnPersistent() {
		this.createConnection();
	}

	public RepositoryConnection createConnection() {

		Repository rep = null;
		long inizio = 0;
		rep = new MarkLogicRepository("localhost", 8000, "niccocorsa", "grifone", "DIGEST");
		rep.initialize();
		repCon = (MarkLogicRepositoryConnection) rep.getConnection();
		System.out.println("Connessione creata : " + repCon);
		return repCon;

	}

	public String[] FileNameOfFolder(String nameDir) {

		File folder = new File(nameDir);
		File[] listOfFiles = folder.listFiles();
		String[] nameOfFile = new String[listOfFiles.length];
		int i = 0;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				System.out.println(file.getName());
				nameOfFile[i++] = file.getName();

			}
		}
		return nameOfFile;

	}

	public void insertFileToRepository(String fileName) throws RDFParseException, RepositoryException, IOException {

		File f = null;
		System.err.println("Inserendo i file ");
		f = new File(fileName);
		repCon.add(f, fileName, RDFFormat.TURTLE, (Resource) null);

	}

	public void insertFilesProfondita2(String nameDir) {

		File folder = new File(nameDir);
		File[] listOfFiles = folder.listFiles();
		File folder1 = new File(nameDir);
		File[] listOfFiles1 = null;
		String[] nameOfFile = new String[100];
		int i = 0;
		for (File file : listOfFiles) {
			listOfFiles1 = file.listFiles();
			for (File file1 : listOfFiles1) {
				System.out.println(file1.getAbsolutePath());
				try {
					this.insertFileToRepository(file1.getAbsolutePath());
				} catch (Exception e) {
					System.err.println("Errore su file: " + file1.getAbsolutePath());
				}
			}
			nameOfFile[i++] = file.getName();
		}
	}

	public Method[] sortArray(Object[] objArr) throws InterruptedException {

		Method[] methods = (Method[]) objArr;

		for (int o = 0; o < 100; o++) {
			for (int i = 0; i < objArr.length - 1; i++) {
				String method1 = methods[i].getName();
				String method2 = methods[i + 1].getName();
				if (Integer.valueOf((methods[i].getName().replace("getQuery", ""))) > Integer
						.valueOf((methods[i + 1].getName().replace("getQuery", "")))) {
					Method method = methods[i];
					methods[i] = methods[i + 1];
					methods[i + 1] = method;
				}

			}
			Thread.sleep(10);

		}

		System.out.println("\n\n\n\n");
		for (Method m : methods)
			System.out.println(m.getName() + "  iii");
		return methods;

	}

	@SuppressWarnings("deprecation")
	public void callAll() throws Exception {

		Method[] methods = vq.getClass().getDeclaredMethods();

		methods = this.sortArray(methods);
		Thread.sleep(1000);

		for (Method m : methods) {

			this.repCon.close();
			this.createConnection();

			try {

				System.out.println(m.getName());
				Thread.sleep(1000);
				queryString = (String) m.invoke(vq, null);
				tupleQuery = repCon.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
				// tupleQuery.setIncludeInferred(false);
				System.err.println("Inizio valutazione query numero " + m.getName());

				try {

					TimeLimitedCodeBlock.runWithTimeout(new Runnable() {

						@Override
						public void run() {
							inizio = System.currentTimeMillis();
							results = tupleQuery.evaluate();
							fine = System.currentTimeMillis();
						}
					}, 5, TimeUnit.SECONDS);
				} catch (TimeoutException e) {
					System.err.println("Time Out eccedeuto");
					FileUtils.writeStringToFile(new File("Tempi"),
							"\nQuery numero " + m.getName().replace("getQuery", "").replace("()", "") + " timeOut",
							true);
					continue;
				}

				System.err.println("fine valutazione normale query numero" + m.getName());
				try {
					while (results.hasNext()) {
						BindingSet bindings = results.next();
						for (String ss : results.getBindingNames()) {
							System.err.println(bindings.getValue(ss));

							FileUtils.writeStringToFile(new File(m.getName() + "_outPut"),
									"\n" + bindings.getValue(ss).toString(), true);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("output Null");
				}

				FileUtils.writeStringToFile(new File("Tempi"), "\nQuery numero "
						+ m.getName().replace("getQuery", "").replace("()", "") + " " + (fine - inizio), true);
				System.err.println(fine - inizio);
			} catch (QueryEvaluationException e) {
				e.printStackTrace();
				FileUtils.writeStringToFile(new File("Tempi"),
						"\nQuery numero " + m.getName().replace("getQuery", "").replace("()", "") + " errore", true);
				System.err.println("Query numero" + m.getName() + " non valutata");
			}

		}

	}

	public void callGraphDBQuery() throws Exception {

		Method[] methods = vqpgdb.getClass().getDeclaredMethods();

		methods = this.sortArray(methods);
		Thread.sleep(1000);

		for (Method m : methods) {

			this.repCon.close();
			this.createConnection();
			if (m.getName().contains("10") || m.getName().contains("11") || m.getName().contains("12")
					|| m.getName().contains("13") || m.getName().contains("14") || m.getName().contains("15")) {
				try {

					System.out.println(m.getName());
					Thread.sleep(1000);
					queryString = (String) m.invoke(vqpgdb, null);
					tupleQuery = repCon.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
					// tupleQuery.setIncludeInferred(false);
					System.err.println("Inizio valutazione query GraphDB numero " + m.getName());

					try {

						TimeLimitedCodeBlock.runWithTimeout(new Runnable() {

							@Override
							public void run() {
								inizio = System.currentTimeMillis();
								results = tupleQuery.evaluate();
								fine = System.currentTimeMillis();
							}
						}, 5, TimeUnit.SECONDS);
					} catch (TimeoutException e) {
						System.err.println("Time Out eccedeuto");
						FileUtils.writeStringToFile(new File("Tempi"), "\nQuery GraphDB numero "
								+ m.getName().replace("getQuery", "").replace("()", "") + " timeOut", true);
						continue;
					}

					System.err.println("fine valutazione normale query numero" + m.getName());
					try {
						while (results.hasNext()) {
							BindingSet bindings = results.next();
							for (String ss : results.getBindingNames()) {
								System.err.println(bindings.getValue(ss));

								FileUtils.writeStringToFile(new File(m.getName() + "GraphDB" + "_outPut"),
										"\n" + bindings.getValue(ss).toString(), true);
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("output Null");
					}

					FileUtils.writeStringToFile(
							new File("Tempi"), "\nQuery GraphDB numero "
									+ m.getName().replace("getQuery", "").replace("()", "") + " " + (fine - inizio),
							true);
					System.err.println(fine - inizio);
				} catch (QueryEvaluationException e) {
					e.printStackTrace();
					FileUtils.writeStringToFile(new File("Tempi"), "\nQuery GraphDB numero "
							+ m.getName().replace("getQuery", "").replace("()", "") + " errore", true);
					System.err.println("Query GraphDB numero" + m.getName() + " non valutata");
				}
			}
		}
	}

	public void callpropertyPath() throws Exception {

		Method[] methods = vqpgdbPP.getClass().getDeclaredMethods();

		methods = this.sortArray(methods);
		Thread.sleep(1000);

		for (Method m : methods) {

			this.repCon.close();
			this.createConnection();

			if (m.getName().contains("10") || m.getName().contains("11") || m.getName().contains("12")
					|| m.getName().contains("13") || m.getName().contains("14") || m.getName().contains("15")) {
				try {

					System.out.println(m.getName());
					Thread.sleep(1000);
					queryString = (String) m.invoke(vqpgdbPP, null);
					tupleQuery = repCon.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
					// tupleQuery.setIncludeInferred(false);
					System.err.println("Inizio valutazione query PropertPath numero " + m.getName());

					try {

						TimeLimitedCodeBlock.runWithTimeout(new Runnable() {

							@Override
							public void run() {
								inizio = System.currentTimeMillis();
								results = tupleQuery.evaluate();
								fine = System.currentTimeMillis();
							}
						}, 5, TimeUnit.SECONDS);
					} catch (TimeoutException e) {
						System.err.println("Time Out eccedeuto");
						FileUtils.writeStringToFile(new File("Tempi"), "\nQuery PropertyPath numero "
								+ m.getName().replace("getQuery", "").replace("()", "") + " timeOut", true);
						continue;
					}

					System.err.println("fine valutazione PropertyPath query numero" + m.getName());
					try {
						while (results.hasNext()) {
							BindingSet bindings = results.next();
							for (String ss : results.getBindingNames()) {
								System.err.println(bindings.getValue(ss));

								FileUtils.writeStringToFile(new File(m.getName() + "PropertyPath" + "_outPut"),
										"\n" + bindings.getValue(ss).toString(), true);
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("output Null");
					}

					FileUtils.writeStringToFile(
							new File("Tempi"), "\nQuery PropertyPath numero "
									+ m.getName().replace("getQuery", "").replace("()", "") + " " + (fine - inizio),
							true);
					System.err.println(fine - inizio);
				} catch (QueryEvaluationException e) {
					e.printStackTrace();
					FileUtils.writeStringToFile(new File("Tempi"), "\nQuery PropertyPath numero "
							+ m.getName().replace("getQuery", "").replace("()", "") + " errore", true);
					System.err.println("Query numero" + m.getName() + " non valutata");
				}
			}

		}

	}

	public static void main(String[] args) throws Exception {

		ConnPersistent cp = new ConnPersistent();

		cp.callAll();
		cp.callpropertyPath();
		cp.callGraphDBQuery();

	}

}
