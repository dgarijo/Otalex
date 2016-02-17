package es.upm.oeg.map4rdf.generateFacetsTTLFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import es.upm.oeg.map4rdf.generateFacetsTTLFile.util.Facet;
import es.upm.oeg.map4rdf.generateFacetsTTLFile.util.FacetSuperClass;

public class ObtainMoreDataOfFacets {

	private String ontURL;
	private Model model;
	public ObtainMoreDataOfFacets(String ontURL) {
		this.ontURL = ontURL;
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		try {
			InputStream in = new URL(ontURL).openStream();
			model.read(in, null);
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Facet getFacetMoreData(Facet facet) {
		Facet toReturn = new Facet(facet.getURI());
		for (String superClassUri : facet.getSuperclassUris()) {
			toReturn.addSuperclassUri(superClassUri);
		}
		for (String locale : facet.getLabels().keySet()) {
			toReturn.addLabel(locale, facet.getLabels().get(locale));
		}
		toReturn = addNewData(toReturn);
		return toReturn;
	}
	
	public FacetSuperClass getFacetSuperClassMoreData(FacetSuperClass facetSuperClass){
		FacetSuperClass toReturn = new FacetSuperClass(facetSuperClass.getUri());
		for (String locale : facetSuperClass.getLabels().keySet()) {
			toReturn.addLabel(locale, facetSuperClass.getLabels().get(locale));
		}
		for(Facet facet:facetSuperClass.getFacets()){
			toReturn.addFacet(facet);
		}
		toReturn = addNewSuperclassData(toReturn);
		return toReturn;
	}

	private Facet addNewData(Facet facet) {
		try {
			QueryExecution execution = QueryExecutionFactory.create(getFacetMoreDataQuery(facet), model);
			ResultSet queryResult = execution.execSelect();
			while (queryResult.hasNext()) {
				QuerySolution solution = queryResult.next();
				if(solution.contains("label")){
					String locale = solution.getLiteral("label").getLanguage();
					String label = solution.getLiteral("label").getString();
					facet.addLabel(locale, label);
				}
				if(solution.contains("superclass") && solution.getResource("superclass") != null 
						&& solution.getResource("superclass").getURI() != null
						&& !solution.getResource("superclass").getURI().isEmpty()
						&& !solution.getResource("superclass").getURI().equals("null")){
					facet.addSuperclassUri(solution.getResource("superclass").getURI());
				}
			}
		} catch (Exception e) {
			System.err.println("Proble with URL:"+ontURL);
			e.printStackTrace();
			System.exit(1);
		}
		return facet;
	}
	
	private FacetSuperClass addNewSuperclassData(FacetSuperClass facetSuperClass) {
		try {
			QueryExecution execution = QueryExecutionFactory.create(getFacetSuperClassMoreDataQuery(facetSuperClass), model);
			ResultSet queryResult = execution.execSelect();
			while (queryResult.hasNext()) {
				QuerySolution solution = queryResult.next();
				if(solution.contains("label")){
					String locale = solution.getLiteral("label").getLanguage();
					String label = solution.getLiteral("label").getString();
					facetSuperClass.addLabel(locale, label);
				}
			}
		} catch (Exception e) {
			System.err.println("Proble with URL:"+ontURL);
			e.printStackTrace();
			System.exit(1);
		}
		return facetSuperClass;
	}

	private String getFacetMoreDataQuery(Facet facet) {
		return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+"Select DISTINCT ?label ?superclass where {" 
				+	"OPTIONAL {<"+facet.getURI()+"> rdfs:label ?label}"
				+	"OPTIONAL {<"+facet.getURI()+"> rdfs:subClassOf ?superclass}"
				+ "}";
	}
	
	private String getFacetSuperClassMoreDataQuery(FacetSuperClass facetSuperClass) {
		return "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+"Select DISTINCT ?label where {" 
				+	"OPTIONAL {<"+facetSuperClass.getUri()+"> rdfs:label ?label}"
				+ "}";
	}
}
