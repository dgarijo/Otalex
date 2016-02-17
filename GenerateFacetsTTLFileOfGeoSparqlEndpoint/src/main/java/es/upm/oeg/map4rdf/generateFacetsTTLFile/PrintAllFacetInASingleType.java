package es.upm.oeg.map4rdf.generateFacetsTTLFile;

import java.io.PrintStream;
import java.util.List;

import es.upm.oeg.map4rdf.generateFacetsTTLFile.util.Facet;

public class PrintAllFacetInASingleType {
	
	private List<Facet> facets;
	
	public PrintAllFacetInASingleType(List<Facet> facets) {
		this.facets = facets;
	}
	
	public void printTTL(PrintStream out){
		printHeaderFacetsTTL(out);
		for(Facet facet: facets){
			if(!facet.getLabels().isEmpty()){
				out.println("<"+facet.getURI()+">");
				for(String locale: facet.getLabels().keySet()){
					out.println("\trdfs:label \""+facet.getLabels().get(locale)+"\"@"+locale+";");
				}
				out.println(".");
				out.println("");
			}else{
				System.err.println("No labels for uri:"+facet.getURI());
			}
		}
	}
	
	private void printHeaderFacetsTTL(PrintStream out){
		out.println("@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>.");
		out.println("@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .");
		out.println("@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .");
		out.println("@prefix foaf: <http://xmlns.com/foaf/0.1/> .");
		out.println("@prefix map4rdf: <http://code.google.com/p/map4rdf/wiki/ontology#> .");
		out.println();
		out.println("_:rdfTypeFacet");
		out.println("\ta map4rdf:FacetGroup;");
		out.println("\tmap4rdf:facetPredicate rdf:type;");
		out.println("\trdfs:label \"Type\"@en;");
		out.println("\trdfs:label \"Tipo\"@es;");
		for(Facet facet:facets){
			out.println("\tmap4rdf:facet <"+facet.getURI()+">;");
		}
		out.println(".");
		out.println();
	}
}
