package es.upm.oeg.FixedMultipleSerializationsOnGeosparqlGeometries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.datatypes.BaseDatatype;
import com.hp.hpl.jena.datatypes.DatatypeFormatException;
import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.update.GraphStoreFactory;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateProcessor;
import com.hp.hpl.jena.update.UpdateRequest;
import com.hp.hpl.jena.vocabulary.RDF;

public class App {
	
	private static RDFDatatype wktLiteral = new WKTLiteral();
	private enum FixMethod {simpleGeo,multipleGeo};
	private static FixMethod fixMethod = FixMethod.simpleGeo;
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.out.println("Not exists param 1. Need a file on ttl format");
			printUSAGE();
			return;
		}
		if(args.length == 2 && args[1] != null && !args[1].isEmpty()){
			boolean validType = false;
			String param1 = args[1].substring(1, args[1].length());
			for(FixMethod toCheck : FixMethod.values()){
				if(param1.equals(toCheck.toString())){
					validType = true;
					fixMethod = FixMethod.valueOf(param1);
					break;
				}
			}
			if(!validType){
				System.out.println("Bad param: "+args[1]);
				printUSAGE();
				return;
			}
		}
		if(args.length > 2){
			System.out.println("More than 2 params");
			printUSAGE();
		}
		long msStart = System.currentTimeMillis();
		File file = new File(args[0]);
		if (!file.exists()) {
			System.err.println("File not exists");
			return;
		}
		if (!file.canRead()) {
			System.err.println("Can not read file");
			return;
		}
		if (!file.isFile()) {
			System.err.println("Isn't a File. Is a directory?");
			return;
		}

		try {
			Model model = ModelFactory.createDefaultModel();
			System.out.println("+Read File and Generate Model");
			TypeMapper.getInstance().registerDatatype(wktLiteral);
			// GET MODEL AND GEOMETRIES
			InputStream is = new FileInputStream(file);
			String charset = "UTF-8";
			InputStreamReader reader = new InputStreamReader(is, charset);
			model.read(reader, null, "TTL");
			QueryExecution qe = QueryExecutionFactory.create(getQuery(), model);
			ResultSet results = qe.execSelect();
			Map<String, List<String>> geometries = new HashMap<String, List<String>>();
			while (results.hasNext()) {
				QuerySolution sres = results.next();
				String uri = sres.getResource("geometry").getURI();
				String wkt = sres.getLiteral("wkt").getString();
				if (!geometries.containsKey(uri)) {
					geometries.put(uri, new ArrayList<String>());
				}
				geometries.get(uri).add(wkt);
			}
			System.out.println("end");
			System.out.println("+Ignore geometries with 1 serialization");
			// Remove from Map the geometries with 1 or less serializations
			List<String> toRemove = new ArrayList<String>();
			for (String uri : geometries.keySet()) {
				if (geometries.get(uri).size() <= 1) {
					toRemove.add(uri);
				}
			}
			for (String uri : toRemove) {
				geometries.remove(uri);
			}
			System.out.println("++Total of ignored geometries:" + toRemove.size());
			System.out.println("end");
			System.out.println("+Fix geometries with multiple serializations");
			System.out.println("++Total of geometries to fix:" + geometries.size());
			System.out.print("FixedGeometries:");
			// FIX GEOMETRIES
			switch (fixMethod) {
			case multipleGeo:
				fixInMultipleGeo(geometries, model);
				break;
			case simpleGeo:
			default:
				fixInSimpleGeo(geometries, model);
				break;
			}
			reader.close();
			System.out.println("");
			System.out.println("end");
			System.out.println("+Save to file the new model");
			// SAVE new MODEL
			String absolutePath = file.getAbsolutePath();
			int lastIndex = absolutePath.lastIndexOf(".");
			String newStringFile = absolutePath.substring(0, lastIndex) + "_fixed"
					+ absolutePath.substring(lastIndex, absolutePath.length());
			File fileToSave = new File(newStringFile);
			model.write(new FileOutputStream(fileToSave), "TTL");
			long msStop = System.currentTimeMillis();
			System.out.println("end");
			System.out.println("Time: " + (msStop - msStart) / 1000 + " seconds.");
		} catch (Exception e) {
			System.err.println("An unexpected exception: " + e);
			e.printStackTrace();
			return;
		}
	}
	
	
	
	//Main helper methods
	
	private static void fixInSimpleGeo(Map<String, List<String>> geometries,Model model){
		int fixedGeometries = 0;
		for (String uri : geometries.keySet()) {
			String toReplace = FixMultipleSerializationSimpleGeo.getSerializationOfWKTGeometries(geometries.get(uri));
			int beforeDelete = countRemovedStatements(model);
			removeSimpleGeoMultipleSerializations(model, uri);
			int afterDelete = countRemovedStatements(model);
			int deleteWKTs = beforeDelete-afterDelete;
			if(deleteWKTs!=geometries.get(uri).size()){
				System.err.println("Delete old geometries doesn't work");
				System.err.println("Error in the geometry:"+uri);
				System.err.println("Try to delete "+geometries.get(uri).size() +" WKTs. Only deleted: "+deleteWKTs);
			}
			removeSimpleGeoRDFType(model, uri);
			model.add(model.createResource(uri),
					ResourceFactory.createProperty("http://www.opengis.net/ont/geosparql#asWKT"),
					ResourceFactory.createTypedLiteral(toReplace,wktLiteral));// + "^^http://www.opengis.net/ont/geosparql#wktLiteral"));
			model.add(model.createResource(uri),RDF.type,ResourceFactory.createResource(FixMultipleSerializationSimpleGeo.getRdfTypeOfWKT(toReplace)));
			fixedGeometries++;
			if (fixedGeometries % 10 == 0) {
				System.out.print(fixedGeometries);
			} else {
				System.out.print("|");
			}
		}
		
	}
	
	private static void fixInMultipleGeo(Map<String, List<String>> geometries,Model model){
		int fixedGeometries = 0;
		for(String uri: geometries.keySet()){
			List<String> parents = getResourcesOfGeometry(uri, model);
			Map<String,List<RDFNode>> propertiesAndObjects = getOtherPropertiesAndObjectsOfGeometry(uri, model);
			List<String> wkts = geometries.get(uri);
			removeMultipleGeoMultipleSerializations(model, uri);
			for(int i=0;i<wkts.size();i++){
				String newUri = uri+"_"+i;
					model.add(ResourceFactory.createResource(newUri),
							ResourceFactory.createProperty("http://www.opengis.net/ont/geosparql#asWKT"),
							ResourceFactory.createTypedLiteral(wkts.get(i),wktLiteral));// + "^^http://www.opengis.net/ont/geosparql#wktLiteral"));
					model.add(ResourceFactory.createResource(newUri),RDF.type,ResourceFactory.createResource(FixMultipleSerializationSimpleGeo.getRdfTypeOfWKT(wkts.get(i))));
					for(String resource:parents){
						model.add(ResourceFactory.createResource(resource),ResourceFactory.createProperty("http://www.opengis.net/ont/geosparql#hasGeometry"),ResourceFactory.createResource(newUri));
					}
					for(String prop: propertiesAndObjects.keySet()){
						for(RDFNode obj: propertiesAndObjects.get(prop)){
							model.add(ResourceFactory.createStatement(
									ResourceFactory.createResource(newUri), 
									ResourceFactory.createProperty(prop), obj));
						}
					}
			}
			fixedGeometries++;
			if (fixedGeometries % 10 == 0) {
				System.out.print(fixedGeometries);
			} else {
				System.out.print("|");
			}
		}
	}
	
	
	
	//SPARQL Queries methods
	
	private static Map<String, List<RDFNode>> getOtherPropertiesAndObjectsOfGeometry(String uri, Model model) {
		Map<String,List<RDFNode>> toReturn = new HashMap<String,List<RDFNode>>();
		QueryExecution qe = QueryExecutionFactory.create(getOtherPropertiesAndObjectsOfGeometryQuery(uri), model);
		ResultSet results = qe.execSelect();
		while (results.hasNext()) {
			QuerySolution sres = results.next();
			String p = sres.get("p").toString();
			RDFNode o = sres.get("o");
			if(!toReturn.containsKey(p)){
				toReturn.put(p, new ArrayList<RDFNode>());
			}
			toReturn.get(p).add(o);
		}
		return toReturn;
	}

	private static List<String> getResourcesOfGeometry(String uri, Model model){
		List<String> resources = new ArrayList<String>();
		QueryExecution qe = QueryExecutionFactory.create(getResourcesOfGeometryQuery(uri), model);
		ResultSet results = qe.execSelect();
		while (results.hasNext()) {
			QuerySolution sres = results.next();
			String resourceUri = sres.getResource("resource").getURI();
			if (!resources.contains(resourceUri)) {
				resources.add(resourceUri);
			}
		}
		return resources;
	}
	
	private static void removeSimpleGeoMultipleSerializations(Model model, String uri) {
		UpdateRequest request = UpdateFactory.create(getSimpleGeoDeleteQuery(uri));
		UpdateProcessor proc = UpdateExecutionFactory.create(request, GraphStoreFactory.create(model));
		proc.execute();
	}
	
	private static void removeSimpleGeoRDFType(Model model, String uri){
		UpdateRequest request = UpdateFactory.create(getSimpleGeoDeleteRdfTypeQuery(uri));
		UpdateProcessor proc = UpdateExecutionFactory.create(request, GraphStoreFactory.create(model));
		proc.execute();
	}
	
	private static void removeMultipleGeoMultipleSerializations(Model model, String uri) {
		UpdateRequest request = UpdateFactory.create(getMultipleGeoDeleteQuery(uri));
		UpdateProcessor proc = UpdateExecutionFactory.create(request, GraphStoreFactory.create(model));
		proc.execute();
		
		request = UpdateFactory.create(getMultipleGeoParentDeleteQuery(uri));
		proc = UpdateExecutionFactory.create(request, GraphStoreFactory.create(model));
		proc.execute();
	}

	private static int countRemovedStatements(Model model){
		int countStatements = 0;
		StmtIterator listOfStatements = model.listStatements();
		while (listOfStatements.hasNext()) {
			listOfStatements.next();
			countStatements++;
		}
		return countStatements;
	}
	

	
	//STRING QUERYS
	
	private static String getSimpleGeoDeleteRdfTypeQuery(String uri) {
		String query = "";
		query += "DELETE { <" + uri + "> <"+RDF.type+"> ?type} ";
		query += "WHERE { <" + uri + "> <"+RDF.type+"> ?type } ";
		return query;
	}

	private static String getResourcesOfGeometryQuery(String uri){
		String query = "PREFIX geosparql: <http://www.opengis.net/ont/geosparql#> SELECT ?resource WHERE{"
				+ "  ?resource geosparql:hasGeometry <"+uri+">.}";
		return query;	
	}
	
	private static String getSimpleGeoDeleteQuery(String uri) {
		String query = "";
		query += "DELETE { <" + uri + "> <http://www.opengis.net/ont/geosparql#asWKT> ?wkt} ";
		query += "WHERE { <" + uri + "> <http://www.opengis.net/ont/geosparql#asWKT> ?wkt } ";
		return query;
	}

	private static String getOtherPropertiesAndObjectsOfGeometryQuery(String uri) {
		return "PREFIX geosparql: <http://www.opengis.net/ont/geosparql#> "
				+ "Select DISTINCT ?p ?o where { "
				+ "<"+uri+"> ?p ?o. "
				+ "FILTER(?p != <"+RDF.type+">). "
				+ "FILTER(?p != geosparql:asWKT).}";
	}

	
	private static String getQuery() {
		String query = "PREFIX geosparql: <http://www.opengis.net/ont/geosparql#> ";
		query += "select ?geometry ?wkt where {";
		query += "	?r geosparql:hasGeometry ?geometry.";
		query += "	?geometry geosparql:asWKT ?wkt.";
		query += "}";
		return query;
	}
	
	private static String getMultipleGeoDeleteQuery(String uri) {
		String query = "";
		query += "DELETE { <" + uri + "> ?p ?o} ";
		query += "WHERE { <" + uri + "> ?p ?o} ";
		return query;
	}
	
	private static String getMultipleGeoParentDeleteQuery(String uri) {
		String query = "PREFIX geosparql: <http://www.opengis.net/ont/geosparql#> ";
		query += "DELETE {?r geosparql:hasGeometry <" + uri + ">} ";
		query += "WHERE { ?r geosparql:hasGeometry <" + uri + ">} ";
		return query;
	}

	
	
	// APP PRINT INFO methods
	
	private static void printUSAGE(){
		System.out.println("Usage: fixMultipleSerialization file.ttl [-simpleGeo | -multipleGeo]");
	}
	
	//AUXILIAR CLASSes
	private static class WKTLiteral extends BaseDatatype{
		
		public WKTLiteral() {
			super("http://www.opengis.net/ont/geosparql#wktLiteral");
		}

	    @Override
	    public Class<?> getJavaClass() {
	        return String.class;
	    }

	    @Override
	    public String unparse(Object value) {
	        return value.toString();
	    }

	    @Override
	    public Object parse(String lexicalForm) throws DatatypeFormatException {
	        try {
	            return new String(lexicalForm); 
	        } catch (Exception ex) {
	            throw new DatatypeFormatException(lexicalForm, this, ex.getMessage());
	        }
	    }
	    
	   

	    @Override
	    public String toString() {
	        return "OPENGIS WKTLiteral";
	    }

		
	}
}
