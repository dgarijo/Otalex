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
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.update.GraphStoreFactory;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateProcessor;
import com.hp.hpl.jena.update.UpdateRequest;

public class App {
	
	private static RDFDatatype wktLiteral = new WKTLiteral();
	
	public static void main(String args[]) {
		if (args.length < 1) {
			System.out.println("Not exists param 1.");
			return;
		}
		long msStart = System.currentTimeMillis();
		File file = new File(args[0]);
		if (!file.exists()) {
			System.err.println("File not exists");
			return;
		}
		if (!file.canRead()) {
			System.err.println("Can read file");
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
			int fixedGeometries = 0;
			for (String uri : geometries.keySet()) {
				String toReplace = FixMultipleSerialization.getSerializationOfWKTGeometries(geometries.get(uri));
				int beforeDelete = countRemovedStatements(model);
				removeMultipleSerializations(model, uri);
				int afterDelete = countRemovedStatements(model);
				int deleteWKTs = beforeDelete-afterDelete;
				if(deleteWKTs!=geometries.get(uri).size()){
					System.err.println("Delete old geometries doesn't work");
					System.err.println("Error in the geometry:"+uri);
					System.err.println("Try to delete "+geometries.get(uri).size() +" WKTs. Only deleted: "+deleteWKTs);
				}
				model.add(model.createResource(uri),
						ResourceFactory.createProperty("http://www.opengis.net/ont/geosparql#asWKT"),
						ResourceFactory.createTypedLiteral(toReplace,wktLiteral));// + "^^http://www.opengis.net/ont/geosparql#wktLiteral"));
				fixedGeometries++;
				if (fixedGeometries % 10 == 0) {
					System.out.print(fixedGeometries);
				} else {
					System.out.print("|");
				}
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

	private static int countRemovedStatements(Model model){
		int countStatements = 0;
		StmtIterator listOfStatements = model.listStatements();
		while (listOfStatements.hasNext()) {
			listOfStatements.next();
			countStatements++;
		}
		return countStatements;
	}
	
	private static void removeMultipleSerializations(Model model, String uri) {
		UpdateRequest request = UpdateFactory.create(getDeleteQuery(model, uri));
		UpdateProcessor proc = UpdateExecutionFactory.create(request, GraphStoreFactory.create(model));
		proc.execute();
	}

	private static String getDeleteQuery(Model model, String uri) {
		String query = "";
		query += "DELETE { <" + uri + "> <http://www.opengis.net/ont/geosparql#asWKT> ?wkt} ";
		query += "WHERE { <" + uri + "> <http://www.opengis.net/ont/geosparql#asWKT> ?wkt } ";
		return query;
	}

	private static String getQuery() {
		String query = "PREFIX geosparql: <http://www.opengis.net/ont/geosparql#> ";
		query += "select ?geometry ?wkt where {";
		query += "	?r geosparql:hasGeometry ?geometry.";
		query += "	?geometry geosparql:asWKT ?wkt.";
		query += "}";
		return query;
	}
	
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
