package es.upm.oeg.map4rdf.generateFacetsTTLFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import es.upm.oeg.map4rdf.generateFacetsTTLFile.util.Facet;

public class ObtainFacets {

	private final String endpoint;

	public ObtainFacets(String endpoint) {
		this.endpoint = endpoint;
	}

	public List<Facet> getFacets() {
		Map<String, Facet> toReturn = new HashMap<String, Facet>();
		QueryExecution execution = QueryExecutionFactory.sparqlService(endpoint, createGetFacetsQuery());
		ResultSet queryResult = execution.execSelect();
		while (queryResult.hasNext()) {
			QuerySolution solution = queryResult.next();
			String uri = solution.getResource("facet").getURI();
			Facet facet = null;
			if(!toReturn.containsKey(uri)){
				facet = new Facet(uri);
				toReturn.put(uri, facet);
			}else{
				facet = toReturn.get(facet);
			}
			if(solution.contains("label")){
				String locale = solution.getLiteral("label").getLanguage();
				String label = solution.getLiteral("label").getString();
				facet.addLabel(locale, label);
			}
			if(solution.contains("superclass")){
				facet.addSuperclassUri(solution.getResource("superclass").getURI());
			}
		}
		return new ArrayList<Facet>(toReturn.values());
	}

	private String createGetFacetsQuery() {
		return "PREFIX geosparql: <http://www.opengis.net/ont/geosparql#> "
				+"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+"Select DISTINCT ?facet ?label ?superclass where {" 
				+	"?r geosparql:hasGeometry ?geometry."
				+	"?r rdf:type ?facet." 
				+	"OPTIONAL {?facet rdfs:label ?label}"
				+	"OPTIONAL {?facet rdfs:subClassOf ?superclass}"
				+ "}";
	}
}
