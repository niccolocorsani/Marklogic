package queryes;

public class VarieQueryPerGraphDBPropertyPath  {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	/*Find-Adress*/		private String query1="#ID:find-address\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX omgeo:<http://www.ontotext.com/owlim/geo#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX vcard:<http://www.w3.org/2006/vcard/ns#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"SELECT DISTINCT ?via ?numero ?comune WHERE {\r\n" + 
			" ?entry rdf:type km4c:Entry.\r\n" + 
			" ?nc km4c:hasExternalAccess ?entry.\r\n" + 
			"?nc km4c:extendNumber ?numero.\r\n" + 
			" ?nc km4c:belongToRoad ?road.\r\n" + 
			" ?road km4c:extendName ?via.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			" ?road km4c:inMunicipalityOf ?com.\r\n" + 
			" ?com foaf:name ?comune. "
			+ "#VOS ?entry geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=0.1)\r\n" + 
			"#VOS BIND( bif:st_distance(?geo, bif:st_point(11.255836486816406, 43.778159022442345)) AS ?dist)\r\n" + 
			" ?entry omgeo:nearby(43.778159022442345 11.255836486816406 \"0.1km\").\r\n" +    ///da levare il commento
			" BIND( omgeo:distance(?elat, ?elong, 43.778159022442345, 11.255836486816406) AS ?dist)\r\n" + 
			"} ORDER BY ?dist LIMIT 1";
	
	
	
	
	
	
	
	
	/*Municipalities-Florence*/		private	String query2= "#ID:municipalities-florence\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"SELECT DISTINCT ?mun ?nomeComune WHERE {\r\n" + 
			"?mun rdf:type km4c:Municipality.\r\n" + 
			"?mun km4c:isPartOfProvince ?prov.\r\n" + 
			"?prov foaf:name \"FIRENZE\".\r\n" + 
			"?mun foaf:name ?nomeComune.\r\n" + 
			"}\r\n" + 
			"ORDER BY ?nomeComune";
	
	
	
	
	
	/*Bus-Lines*/	private	String query3 = "#ID:bus-lines\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"select distinct ?n where {\r\n" + 
			"?x rdf:type km4c:PublicTransportLine;\r\n" + 
			"dcterms:identifier ?n.\r\n" + 
			"bind(xsd:integer(?n) as ?d)\r\n" + 
			"} order by ?d "   ;
	
	
	
	
	
	/*BusStops-Of-Line*/	private	String query4 = "#ID:bus-stops-of-line\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"SELECT DISTINCT ?bs ?bslat ?bslong ?nomeFermata ?x WHERE {\r\n" + 
			"?tpll rdf:type km4c:PublicTransportLine.\r\n" + 
			"?tpll dcterms:identifier \"10\".\r\n" + 
			"?tpll km4c:hasRoute ?route.\r\n" + 
			"?route km4c:hasSection ?rs.\r\n" + 
			"?rs km4c:startsAtStop ?bs.\r\n" + 
			"?bs foaf:name ?nomeFermata.\r\n" + 
			"?bs geo:lat ?bslat.\r\n" + 
			"?bs geo:long ?bslong.\r\n" + 
			"} ORDER BY ?nomeFermata";
	
	
	
	/*Lines-Of-BusStop*/	private		String query5="#ID:lines-of-stop\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX km4cr:<http://www.disit.org/km4city/resource/>\r\n" + 
			"PREFIX schema:<http://schema.org/#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX vcard:<http://www.w3.org/2006/vcard/ns#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"SELECT DISTINCT ?id WHERE{\r\n" + 
			"?tpll rdf:type km4c:PublicTransportLine.\r\n" + 
			"?tpll dcterms:identifier ?id.\r\n" + 
			"?tpll km4c:hasRoute ?route.\r\n" + 
			"?route km4c:hasSection ?rs.\r\n" + 
			"?rs km4c:endsAtStop ?bs1.\r\n" + 
			"?rs km4c:startsAtStop ?bs2.\r\n" + 
			"{?bs1 foaf:name \"SAN ZANOBI\".}\r\n" + 
			"UNION\r\n" + 
			"{?bs2 foaf:name \"SAN ZANOBI\".}\r\n" + 
			"} ORDER BY ?id\r\n" + 
			"" ;
	
	
	/*Bus-stop-latlng*/private	String query6 = "#ID:bus-stop-latlng-%DISTkm\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX omgeo:<http://www.ontotext.com/owlim/geo#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"SELECT DISTINCT ?bs (STR(?nome) AS ?bsName) ?bslat ?bslong ?x WHERE {\r\n" + 
			"?bs rdf:type km4c:BusStop.\r\n" + 
			"?bs foaf:name ?nome.\r\n" + 
			"?bs geo:lat ?bslat.\r\n" + 
			"?bs geo:long ?bslong.\r\n" + 
			"#VOS ?bs geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=%DIST)\r\n" + 
			" ?bs omgeo:nearby(43.778159022442345 11.255836486816406 \"%DISTkm\").\r\n" + 
			"}\r\n" + 
			"";
	/*Bus-stop-florence*/private	String query7= "#ID:bus-stop-florence\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"SELECT DISTINCT ?bs ?nomeFermata ?bslat ?bslong ?x WHERE {\r\n" + 
			"?bs rdf:type km4c:BusStop.\r\n" + 
			"?bs foaf:name ?nomeFermata.\r\n" + 
			"?bs geo:lat ?bslat.\r\n" + 
			"?bs geo:long ?bslong.\r\n" + 
			"?bs km4c:isInMunicipality ?com.\r\n" + 
			"?com foaf:name \"FIRENZE\".\r\n" + 
			"}";
	
	
	
	/*Bus-stop-forecast*/	private String query8="#ID:bus-stop-forecast\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX time:<http://www.w3.org/2006/time#>\r\n" + 
			"SELECT DISTINCT ?avmr ?line ?state ?arrivalTime ?idRide WHERE {\r\n" + 
			"{\r\n" + 
			" SELECT ?ride (MAX(?avmr) AS ?avmrLast) WHERE{\r\n" + 
			" ?bs rdf:type km4c:BusStop.\r\n" + 
			" ?bs foaf:name \"SAN ZANOBI\".\r\n" + 
			" ?bs km4c:hasForecast ?bsf.\r\n" + 
			" ?avmr km4c:includeForecast ?bsf.\r\n" + 
			" ?ride km4c:hasAVMRecord ?avmr.\r\n" + 
			" }GROUP BY ?ride ORDER BY DESC (?avmrLast) LIMIT 15\r\n" + 
			"}\r\n" + 
			"?bs rdf:type km4c:BusStop.\r\n" + 
			"?bs foaf:name \"SAN ZANOBI\".\r\n" + 
			"?bs km4c:hasForecast ?forecast.\r\n" + 
			"?avmrLast km4c:includeForecast ?forecast.\r\n" + 
			"?forecast km4c:expectedTime ?arrivalTime.\r\n" + 
			"?avmrLast km4c:concernLine ?line.\r\n" + 
			"?avmrLast km4c:rideState ?state.\r\n" + 
			"?ride km4c:hasAVMRecord ?avmrLast.\r\n" + 
			"?ride dcterms:identifier ?idRide.\r\n" + 
			"FILTER(?arrivalTime>=xsd:dateTime(\"2015-05-06T09:00:00+02:00\"))\r\n" + 
			"} ORDER BY ?arrivalTime\r\n" + 
			"";

	/*AVM-distribution*/private	String query9="#ID:avm-distribution\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"SELECT ?yy ?mm ?dd (count(*) as ?c) where {\r\n" + 
			"?x a km4c:AVMRecord.\r\n" + 
			"?x dcterms:created ?d.\r\n" + 
			"BIND( year(?d) as ?yy)\r\n" + 
			"BIND( month(?d) as ?mm)\r\n" + 
			"BIND( day(?d) as ?dd)\r\n" + 
			"} GROUP BY ?yy ?mm ?dd ORDER BY ?yy ?mm ?dd\r\n" + 
			"";
	
	
	
	/// propertyPath
	
	/*Service-florence*/private	String query10="#ID:service-florence\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX dc:<http://purl.org/dc/elements/1.1/>\r\n" + 
			"PREFIX omgeo:<http://www.ontotext.com/owlim/geo#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"SELECT DISTINCT ?ser ?serAddress ?serNumber ?elat ?elong ?sName ?sType ?email\r\n" + 
			"?note ?labelIta ?multimedia ?description ?identifier ?x WHERE {\r\n" + 
			"?ser rdf:type/rdfs:subClassOf* km4c:Service.\r\n" + 
			"#VOS .\r\n" + 
			"OPTIONAL{?ser schema:name ?sName. }\r\n" + 
			"?ser schema:streetAddress ?serAddress.\r\n" + 
			"OPTIONAL {?ser km4c:houseNumber ?serNumber}.\r\n" + 
			"OPTIONAL {?ser dc:description ?description FILTER(LANG(?description) = \"it\")}\r\n" + 
			"OPTIONAL {?ser km4c:multimediaResource ?multimedia }\r\n" + 
			"OPTIONAL { ?ser dcterms:identifier ?identifier }\r\n" + 
			"OPTIONAL {?ser skos:note ?note }\r\n" + 
			"OPTIONAL {?ser schema:email ?email }\r\n" + 
			"{\r\n" + 
			" ?ser km4c:hasAccess ?entry.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			" ?nc km4c:hasExternalAccess ?entry.\r\n" + 
			" ?nc km4c:belongToRoad ?road.\r\n" + 
			" ?road km4c:inMunicipalityOf ?mun.\r\n" + 
			" ?mun foaf:name \"FIRENZE\".\r\n" + 
			"} UNION {\r\n" + 
			" ?ser km4c:isInRoad ?road .\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			" ?road km4c:inMunicipalityOf ?mun.\r\n" + 
			" ?mun foaf:name \"FIRENZE\".\r\n" + 
			"}\r\n" + 
			"#VOS ?ser a ?sType. FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			" ?ser <http://www.openrdf.org/schema/sesame#directType> ?sType.\r\n" + 
			"FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			"?sType rdfs:label ?labelIta. FILTER(LANG(?labelIta)=\"it\")\r\n" + 
			"}";
	
	
	
	
	/////propertyPath
	/*2.11 Service-Acc-Clt-Trs-W&F-florence*/private	String query11 = "#ID:service-acc-clt-trs-w&f-florence\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX km4cr:<http://www.disit.org/km4city/resource#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX dc:<http://purl.org/dc/elements/1.1/>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"SELECT DISTINCT ?ser ?serAddress ?serNumber ?elat ?elong ?sName ?sType ?email\r\n" + 
			"?note ?labelIta ?multimedia ?description ?identifier ?x WHERE {\r\n" + 
			"?ser rdf:type/rdfs:subClassOf* km4c:Service.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"OPTIONAL{?ser schema:name ?sName. }\r\n" + 
			"?ser schema:streetAddress ?serAddress.\r\n" + 
			"OPTIONAL {?ser km4c:houseNumber ?serNumber}.\r\n" + 
			"OPTIONAL {?ser dc:description ?description FILTER(LANG(?description) = \"it\")}\r\n" + 
			"OPTIONAL {?ser km4c:multimediaResource ?multimedia }\r\n" + 
			"OPTIONAL {?ser dcterms:identifier ?identifier }\r\n" + 
			"OPTIONAL {?ser skos:note ?note }\r\n" + 
			"OPTIONAL {?ser schema:email ?email }\r\n" + 
			"{\r\n" + 
			" ?ser km4c:hasAccess ?entry.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			" ?nc km4c:hasExternalAccess ?entry.\r\n" + 
			" ?nc km4c:belongToRoad ?road.\r\n" + 
			" ?road km4c:inMunicipalityOf ?mun.\r\n" + 
			" ?mun foaf:name \"FIRENZE\".\r\n" + 
			"} UNION {\r\n" + 
			" ?ser km4c:isInRoad ?road .\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			" ?road km4c:inMunicipalityOf ?mun.\r\n" + 
			" ?mun foaf:name \"FIRENZE\".\r\n" + 
			"}\r\n" + 
			"{ ?ser  rdf:type/rdfs:subClassOf* km4c:Accommodation\r\n" + 
			" .\r\n" +     ////////// urn:ontology da caricare       http://www.disit.org/km4city/schema  ( qui sta  rdf file da caricare con urn:ontology)
			"}\r\n" + 
			"UNION { ?ser rdf:type/rdfs:subClassOf* km4c:CulturalActivity\r\n" +   ///// rdf:type/rdfs:subClassOf*  <----->" rdf:type/rdfs:subClassOf*. per fare il property path
			" .\r\n" + 
			"}\r\n" +                                           ////rdfs_rule_set('urn:ontolgy','http://www.disit.org/km4city/resource/ontology')----> questo � il comando da fare su virtuoso per impostre il coso come ontologia
			"UNION { ?ser  rdf:type/rdfs:subClassOf* km4c:TourismService\r\n" + 
			".\r\n" + 
			"}"
			+ "UNION { ?ser rdf:type/rdfs:subClassOf* km4c:WineAndFood\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"}\r\n" + 
			"#VOS ?ser a ?sType. FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			" ?ser <http://www.openrdf.org/schema/sesame#directType> ?sType.\r\n" + 
			"FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			"?sType rdfs:label ?labelIta. FILTER(LANG(?labelIta)=\"it\")\r\n" + 
			"}\r\n" + 
			"";
	
	
	//PropertyPath
	/*Service-Htl-B&B-florence*/	 private String query12= "#ID:service-htl-b&b-florence\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX dc:<http://purl.org/dc/elements/1.1/>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"SELECT DISTINCT ?ser ?serAddress ?serNumber ?elat ?elong ?sName ?sType ?email\r\n" + 
			"?note ?labelIta ?multimedia ?description ?identifier ?x WHERE {\r\n" + 
			"?ser rdf:type km4c:Service\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"OPTIONAL{?ser schema:name ?sName. }\r\n" + 
			"?ser schema:streetAddress ?serAddress.\r\n" + 
			"OPTIONAL {?ser km4c:houseNumber ?serNumber}.\r\n" + 
			"OPTIONAL {?ser dc:description ?description FILTER(LANG(?description) = \"it\")}\r\n" + 
			"OPTIONAL {?ser km4c:multimediaResource ?multimedia }\r\n" + 
			"OPTIONAL {?ser dcterms:identifier ?identifier }\r\n" + 
			"OPTIONAL {?ser skos:note ?note }\r\n" + 
			"OPTIONAL {?ser schema:email ?email }\r\n" + 
			"{\r\n" + 
			" ?ser km4c:hasAccess ?entry.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			" ?nc km4c:hasExternalAccess ?entry.\r\n" + 
			" ?nc km4c:belongToRoad ?road.\r\n" + 
			" ?road km4c:inMunicipalityOf ?mun.\r\n" + 
			" ?mun foaf:name \"FIRENZE\".\r\n" + 
			"} UNION {\r\n" + 
			" ?ser km4c:isInRoad ?road .\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			" ?road km4c:inMunicipalityOf ?mun.\r\n" + 
			" ?mun foaf:name \"FIRENZE\".\r\n" + 
			"}\r\n" + 
			"{ ?ser rdf:type/rdfs:subClassOf* km4c:Hotel\r\n" + 
			" .\r\n" + 
			"}\r\n" + 
			"UNION { ?ser rdf:type/rdfs:subClassOf* km4c:Bed_and_breakfast."
			+ "#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"}\r\n" + 
			"#VOS ?ser a ?sType. FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			" ?ser <http://www.openrdf.org/schema/sesame#directType> ?sType.\r\n" + 
			"FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			"?sType rdfs:label ?labelIta. FILTER(LANG(?labelIta)=\"it\")\r\n" + 
			"}";
	/*2.13 Service-latlng*/private	String query13= "#ID:service-latlng-%DISTkm\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX omgeo:<http://www.ontotext.com/owlim/geo#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"PREFIX dc:<http://purl.org/dc/elements/1.1/>\r\n" + 
			"SELECT DISTINCT ?ser ?serAddress ?elat ?elong ?sType ?sTypeIta ?sName ?email\r\n" + 
			"?note ?multimedia ?description ?x WHERE {\r\n" + 
			"?ser rdf:type km4c:Service.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"OPTIONAL {?ser <http://schema.org/name> ?sName}\r\n" + 
			"?ser <http://schema.org/streetAddress> ?serAddress.\r\n" + 
			"OPTIONAL {?ser skos:note ?note}\r\n" + 
			"OPTIONAL {?ser dc:description ?description FILTER(LANG(?description) = \"it\") }\r\n" + 
			"OPTIONAL {?ser km4c:multimediaResource ?multimedia}\r\n" + 
			"OPTIONAL {?ser schema:email ?email }\r\n" + 
			"{\r\n" + 
			" ?ser km4c:hasAccess ?entry.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			"#VOS ?entry geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=%DIST)\r\n" + 
			" ?entry omgeo:nearby(43.778159022442345 11.255836486816406 \"%DISTkm\") .\r\n" + 
			"} UNION {\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			"#VOS ?ser geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=%DIST)\r\n" + 
			" ?ser omgeo:nearby(43.778159022442345 11.255836486816406 \"%DISTkm\") .\r\n" + 
			"}\r\n" + 
			"#VOS ?ser a ?sType. FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			" ?ser <http://www.openrdf.org/schema/sesame#directType> ?sType.\r\n" + 
			"FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			"?sType rdfs:label ?sTypeIta. FILTER(LANG(?sTypeIta)=\"it\")\r\n" + 
			"}";
	/* Service-Acc-Clt-Trs-W&F-latlng*/private	String query14= "#ID:service-acc-clt-trs-w&f-latlng-%DISTkm\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX omgeo:<http://www.ontotext.com/owlim/geo#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"PREFIX dc:<http://purl.org/dc/elements/1.1/>\r\n" + 
			"SELECT DISTINCT ?ser ?serAddress ?elat ?elong ?sType ?sTypeIta ?sName ?email\r\n" + 
			"?note ?multimedia ?description ?x WHERE {\r\n" + 
			"?ser rdf:type km4c:Service\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"OPTIONAL {?ser <http://schema.org/name> ?sName}\r\n" + 
			"?ser <http://schema.org/streetAddress> ?serAddress.\r\n" + 
			"OPTIONAL {?ser skos:note ?note}\r\n" + 
			"OPTIONAL {?ser dc:description ?description FILTER(LANG(?description) = \"it\") }\r\n" + 
			"OPTIONAL {?ser km4c:multimediaResource ?multimedia}\r\n" + 
			"OPTIONAL {?ser schema:email ?email }\r\n" + 
			"{\r\n" + 
			" ?ser km4c:hasAccess ?entry.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			"#VOS ?entry geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=%DIST)\r\n" + 
			" ?entry omgeo:nearby(43.778159022442345 11.255836486816406 \"%DISTkm\") .\r\n" + 
			"} UNION {\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			"#VOS ?ser geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=%DIST)\r\n" + 
			" ?ser omgeo:nearby(43.778159022442345 11.255836486816406 \"%DISTkm\") .\r\n" + 
			"}\r\n" + 
			"{ ?ser rdf:type/rdfs:subClassOf* km4c:Accommodation.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"}\r\n" + 
			"UNION { ?ser rdf:type/rdfs:subClassOf* km4c:CulturalActivity.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"}\r\n" + 
			"UNION { ?ser rdf:type/rdfs:subClassOf* km4c:TourismService.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"}\r\n" + 
			"UNION { ?ser rdf:type/rdfs:subClassOf* km4c:WineAndFood.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"}\r\n" + 
			"#VOS ?ser a ?sType. FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			" ?ser <http://www.openrdf.org/schema/sesame#directType> ?sType.\r\n" + 
			"FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)"
			+ "?sType rdfs:label ?sTypeIta. FILTER(LANG(?sTypeIta)=\"it\")\r\n" + 
			"}";
	/*Service-Htl-B&B-latlng*/private	String query15= "#ID: service-htl-b&b-latlng-%DISTkm\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX omgeo:<http://www.ontotext.com/owlim/geo#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"PREFIX dc:<http://purl.org/dc/elements/1.1/>\r\n" + 
			"SELECT DISTINCT ?ser ?serAddress ?elat ?elong ?sType ?sTypeIta ?sName ?email\r\n" + 
			"?note ?multimedia ?description ?x WHERE {\r\n" + 
			"?ser rdf:type/rdfs:subClassOf* km4c:Service.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"OPTIONAL {?ser <http://schema.org/name> ?sName}\r\n" + 
			"?ser <http://schema.org/streetAddress> ?serAddress.\r\n" + 
			"OPTIONAL {?ser skos:note ?note}\r\n" + 
			"OPTIONAL {?ser dc:description ?description FILTER(LANG(?description) = \"it\") }\r\n" + 
			"OPTIONAL {?ser km4c:multimediaResource ?multimedia}\r\n" + 
			"OPTIONAL {?ser schema:email ?email }\r\n" + 
			"{\r\n" + 
			" ?ser km4c:hasAccess ?entry.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			"#VOS ?entry geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=%DIST)\r\n" + 
			" ?entry omgeo:nearby(43.778159022442345 11.255836486816406 \"%DISTkm\") .\r\n" + 
			"} UNION {\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			"#VOS ?ser geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=%DIST)\r\n" + 
			" ?ser omgeo:nearby(43.778159022442345 11.255836486816406 \"%DISTkm\") .\r\n" + 
			"}\r\n" + 
			"{ ?ser rdf:type/rdfs:subClassOf* km4c:Hotel.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"}\r\n" + 
			"UNION { ?ser rdf:type/rdfs:subClassOf* km4c:Bed_and_breakfast.\r\n" + 
			"#VOS rdf:type/rdfs:subClassOf*.\r\n" + 
			"}\r\n" + 
			"#VOS ?ser a ?sType. FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			" ?ser <http://www.openrdf.org/schema/sesame#directType> ?sType.\r\n" + 
			"FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			"?sType rdfs:label ?sTypeIta. FILTER(LANG(?sTypeIta)=\"it\")\r\n" + 
			"}";
	/*Service-text-florence*/private	String query16 = "#ID:service-text-firenze\r\n" + 
			"PREFIX luc: <http://www.ontotext.com/owlim/lucene#>\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX schema:<http://schema.org/#>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"SELECT DISTINCT ?ser ?elong ?elat ?sTypeIta WHERE {\r\n" + 
			"?ser ?p ?txt.\r\n" + 
			" ?txt luc:myIndex \"casa\".\r\n" + 
			" ?txt luc:score ?sc.\r\n" + 
			"#VOS ?txt bif:contains \"casa\" OPTION (score ?sc).\r\n" + 
			"{\r\n" + 
			" ?ser km4c:hasAccess ?entry.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			" ?nc km4c:hasExternalAccess ?entry.\r\n" + 
			" ?nc km4c:belongToRoad ?road.\r\n" + 
			" ?road km4c:inMunicipalityOf ?mun.\r\n" + 
			" ?mun foaf:name \"FIRENZE\".\r\n" + 
			"} UNION {\r\n" + 
			" ?ser km4c:isInRoad ?road.\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			" ?road km4c:inMunicipalityOf ?mun.\r\n" + 
			" ?mun foaf:name \"FIRENZE\".\r\n" + 
			"}UNION {\r\n" + 
			" ?ser km4c:isInMunicipality ?com.\r\n" + 
			" ?com foaf:name \"FIRENZE\".\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			"}\r\n" + 
			"#VOS ?ser a ?sType. FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			" ?ser <http://www.openrdf.org/schema/sesame#directType> ?sType.\r\n" + 
			"FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			"?sType rdfs:label ?sTypeIta. FILTER(LANG(?sTypeIta)=\"it\")\r\n" + 
			"}\r\n" + 
			"";


	
	
	/*Service-text-latlng*/private	String query17 = "#ID:service-text-latlng\r\n" + 
			"PREFIX luc: <http://www.ontotext.com/owlim/lucene#>\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX schema:<http://schema.org/#>\r\n" + 
			"PREFIX omgeo:<http://www.ontotext.com/owlim/geo#>"
			+ "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"SELECT DISTINCT ?ser ?elong ?elat ?sTypeIta WHERE {\r\n" + 
			"?ser ?p ?txt.\r\n" + 
			" ?txt luc:myIndex \"casa\".\r\n" + 
			" ?txt luc:score ?sc.\r\n" + 
			"#VOS ?txt bif:contains \"casa\" OPTION (score ?sc).\r\n" + 
			"{\r\n" + 
			" ?ser km4c:hasAccess ?entry.\r\n" + 
			" ?entry geo:lat ?elat.\r\n" + 
			" ?entry geo:long ?elong.\r\n" + 
			" ?entry omgeo:nearby(43.778159022442345 11.255836486816406 \"0.5km\").\r\n" + 
			"#VOS ?entry geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=0.5)\r\n" + 
			"}UNION{\r\n" + 
			" ?ser geo:lat ?elat.\r\n" + 
			" ?ser geo:long ?elong.\r\n" + 
			" ?ser omgeo:nearby(43.778159022442345 11.255836486816406 \"0.5km\").\r\n" + 
			"#VOS ?ser geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=0.5)\r\n" + 
			"}\r\n" + 
			"#VOS ?ser a ?sType. FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			" ?ser <http://www.openrdf.org/schema/sesame#directType> ?sType.\r\n" + 
			"FILTER(?sType!=km4c:RegularService && ?sType!=km4c:Service)\r\n" + 
			"?sType rdfs:label ?sTypeIta. FILTER(LANG(?sTypeIta)=\"it\")\r\n" + 
			"}\r\n" + 
			"";
	/*Sensor-florence*/	private String query18 = "#ID:sensor-florence\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"SELECT DISTINCT ?sensor ?idSensore ?lat ?long ?address ?x WHERE {\r\n" + 
			"?sensor rdf:type km4c:SensorSite.\r\n" + 
			"?sensor geo:lat ?lat.\r\n" + 
			"?sensor geo:long ?long.\r\n" + 
			"?sensor dcterms:identifier ?idSensore.\r\n" + 
			"?sensor km4c:placedOnRoad ?road.\r\n" + 
			"?road km4c:inMunicipalityOf ?mun.\r\n" + 
			"?mun foaf:name \"FIRENZE\".\r\n" + 
			"?sensor schema:streetAddress ?address.\r\n" + 
			"}";
	/*Sensor-latlng*/private	String query19 = "#ID:sensor-latlng-%DISTkm\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX dct:<http://purl.org/dc/terms/#>\r\n" + 
			"PREFIX omgeo:<http://www.ontotext.com/owlim/geo#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\r\n" + 
			"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
			"SELECT DISTINCT ?sensor ?idSensore ?lat ?long ?address ?x WHERE {\r\n" + 
			"?sensor rdf:type km4c:SensorSite.\r\n" + 
			"?sensor geo:lat ?lat.\r\n" + 
			"?sensor geo:long ?long.\r\n" + 
			"?sensor dcterms:identifier ?idSensore.\r\n" + 
			" ?sensor omgeo:nearby(43.778159022442345 11.255836486816406 \"%DISTkm\").\r\n" + 
			"#VOS ?sensor geo:geometry ?geo.\r\n" + 
			"#VOS filter(bif:st_distance (?geo, bif:st_point (11.255836486816406,43.778159022442345))<=%DIST)\r\n" + 
			"?sensor schema:streetAddress ?address.\r\n" + 
			"}\r\n" + 
			"";
	/* Sensor-status*/private	String query20 = "#ID:sensor-status\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"SELECT ?avgDistance ?avgTime ?occupancy ?concentration ?vehicleFlow\r\n" + 
			"?averageSpeed ?thresholdPerc ?speedPercentile ?timeInstant WHERE{\r\n" + 
			"?sensor rdf:type km4c:SensorSite.\r\n" + 
			"?sensor dcterms:identifier \"EM0100401\".\r\n" + 
			"?sensor km4c:hasObservation ?obs.\r\n" + 
			"?obs km4c:measuredTime ?time.\r\n" + 
			"?time dcterms:identifier ?timeInstant.\r\n" + 
			"OPTIONAL {?obs km4c:averageDistance ?avgDistance}\r\n" + 
			"OPTIONAL {?obs km4c:averageTime ?avgTime}\r\n" + 
			"OPTIONAL {?obs km4c:occupancy ?occupancy}\r\n" + 
			"OPTIONAL {?obs km4c:concentration ?concentration}\r\n" + 
			"OPTIONAL {?obs km4c:vehicleFlow ?vehicleFlow}\r\n" + 
			"OPTIONAL {?obs km4c:averageSpeed ?averageSpeed}\r\n" + 
			"OPTIONAL {?obs km4c:thresholdPerc ?thresholdPerc}\r\n" + 
			"OPTIONAL {?obs km4c:speedPrecentile ?speedPercentile}\r\n" + 
			"} ORDER BY DESC (?timeInstant) LIMIT 1";
	/*Sensor-distribution*/private	String query21 = "#ID:sensor-distribution\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"SELECT ?yy ?mm ?dd (COUNT(*) AS ?c) where {\r\n" + 
			"#VOS {\r\n" + 
			"#VOS SELECT DISTINCT * where {\r\n" + 
			"?x a km4c:Observation.\r\n" + 
			"?x km4c:measuredTime/dcterms:identifier ?d.\r\n" + 
			"#VOS }\r\n" + 
			"#VOS }\r\n" + 
			"BIND( year(?d) as ?yy)\r\n" + 
			"BIND( month(?d) as ?mm)\r\n" + 
			"BIND( day(?d) as ?dd)\r\n" + 
			"}\r\n" + 
			"GROUP BY ?yy ?mm ?dd ORDER BY ?yy ?mm ?dd";
	
	
	
	/*Parking-status*/private	String query22 = "#ID:parking-status\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX time:<http://www.w3.org/2006/time#>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"SELECT DISTINCT ?situationRecord ?instantDateTime ?occupancy ?free ?occupied\r\n" + 
			"?capacity WHERE {\r\n" + 
			" ?park rdf:type km4c:Car_park.\r\n" + 
			" ?park schema:name \"G. Boccaccio\".\r\n" + 
			" ?cps km4c:observeCarPark ?park.\r\n" + 
			" ?cps km4c:capacity ?capacity.\r\n" + 
			" ?situationRecord km4c:relatedToSensor ?cps.\r\n" + 
			" ?situationRecord km4c:observationTime ?time.\r\n" + 
			" ?time dcterms:identifier ?instantDateTime.\r\n" + 
			" ?situationRecord km4c:parkOccupancy ?occupancy.\r\n" + 
			" ?situationRecord km4c:free ?free.\r\n" + 
			" ?situationRecord km4c:occupied ?occupied.\r\n" + 
			"} ORDER BY DESC (?instantDateTime) LIMIT 1\r\n" + 
			"";
	/*Parking-distribution*/private	String query23 = "#ID:parking-distribution\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"SELECT ?yy ?mm ?dd (COUNT(*) AS ?c) where {\r\n" + 
			"#VOS {\r\n" + 
			"#VOS SELECT DISTINCT * where {\r\n" + 
			" ?x a km4c:SituationRecord.\r\n" + 
			" ?x km4c:observationTime/dcterms:identifier ?d.\r\n" + 
			"#VOS }\r\n" + 
			"#VOS }\r\n" + 
			"BIND( year(?d) as ?yy)\r\n" + 
			"BIND( month(?d) as ?mm)\r\n" + 
			"BIND( day(?d) as ?dd)\r\n" + 
			"}\r\n" + 
			"GROUP BY ?yy ?mm ?dd ORDER BY ?yy ?mm ?dd";

	/*Weather-florence*/private	String query24 = "#ID:weather-florence\r\n" + 
			"PREFIX geo:<http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
			"PREFIX foaf:<http://xmlns.com/foaf/0.1/>\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\r\n" + 
			"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
			"PREFIX schema:<http://schema.org/>\r\n" + 
			"PREFIX time:<http://www.w3.org/2006/time#>\r\n" + 
			"SELECT ?giorno ?descrizione ?minTemp ?maxTemp ?instantDateTime ?wPred WHERE {\r\n" + 
			"{\r\n" + 
			" SELECT DISTINCT ?wRep ?instantDateTime WHERE {\r\n" + 
			" ?comune rdf:type km4c:Municipality.\r\n" + 
			" ?comune foaf:name \"FIRENZE\".\r\n" + 
			" ?comune km4c:hasWeatherReport ?wRep.\r\n" + 
			" ?wRep km4c:updateTime ?instant.\r\n" + 
			" ?instant schema:value ?instantDateTime.\r\n" + 
			" } ORDER BY DESC (?instantDateTime) LIMIT 1\r\n" + 
			"}\r\n" + 
			"?wRep km4c:hasPrediction ?wPred.\r\n" + 
			"?wPred dcterms:description ?descrizione.\r\n" + 
			"?wPred km4c:day ?giorno.\r\n" + 
			"?wPred km4c:hour \"giorno\"^^xsd:string.\r\n" + 
			"OPTIONAL { ?wPred km4c:minTemp ?minTemp.}\r\n" + 
			"OPTIONAL { ?wPred km4c:maxTemp ?maxTemp.}\r\n" + 
			"}";
	/* Weather-distribution*/private	String query25 = " #ID:meteo-distribution\r\n" + 
			"PREFIX dcterms:<http://purl.org/dc/terms/>\r\n" + 
			"PREFIX km4c:<http://www.disit.org/km4city/schema#>\r\n" + 
			"SELECT ?yy ?mm ?dd (COUNT(*) AS ?c) where {\r\n" + 
			"?x a km4c:WeatherReport.\r\n" + 
			"?x km4c:updateTime/<http://schema.org/value> ?d.\r\n" + 
			"BIND( year(?d) as ?yy)\r\n" + 
			"BIND( month(?d) as ?mm)\r\n" + 
			"BIND( day(?d) as ?dd)\r\n" + 
			"}\r\n" + 
			"GROUP BY ?yy ?mm ?dd ORDER BY ?yy ?mm ?dd";
	public String getQuery1() {
		return query1;
	}
	public String getQuery2() {
		return query2;
	}
	public String getQuery3() {
		return query3;
	}
	public String getQuery4() {
		return query4;
	}
	public String getQuery5() {
		return query5;
	}
	public String getQuery6() {
		return query6;
	}
	public String getQuery7() {
		return query7;
	}
	public String getQuery8() {
		return query8;
	}
	public String getQuery9() {
		return query9;
	}
	public String getQuery10() {
		return query10;
	}
	public String getQuery11() {
		return query11;
	}
	public String getQuery12() {
		return query12;
	}
	public String getQuery13() {
		return query13;
	}
	public String getQuery14() {
		return query14;
	}
	public String getQuery15() {
		return query15;
	}
	public String getQuery16() {
		return query16;
	}
	public String getQuery17() {
		return query17;
	}
	public String getQuery18() {
		return query18;
	}
	public String getQuery19() {
		return query19;
	}
	public String getQuery20() {
		return query20;
	}
	public String getQuery21() {
		return query21;
	}
	public String getQuery22() {
		return query22;
	}
	public String getQuery23() {
		return query23;
	}
	public String getQuery24() {
		return query24;
	}
	public String getQuery25() {
		return query25;
	}

	
	/////da mettere l'elenco delle query in propertyPath

}
