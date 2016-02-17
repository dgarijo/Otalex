package es.upm.oeg.map4rdf.generateFacetsTTLFile;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.upm.oeg.map4rdf.generateFacetsTTLFile.util.Facet;
import es.upm.oeg.map4rdf.generateFacetsTTLFile.util.FacetSuperClass;

public class PrintAllFacetInAMultipleType {
	private List<Facet> facets;
	private List<ObtainMoreDataOfFacets> ontsMoreData;

	public PrintAllFacetInAMultipleType(List<Facet> facets, List<ObtainMoreDataOfFacets> ontsMoreData) {
		this.facets = facets;
		this.ontsMoreData = ontsMoreData;
	}

	public void printTTL(PrintStream out) {
		printHeaderFacetsTTL(out);
		List<FacetSuperClass> facetsSuperClass = getFacetsSuperClass(facets);
		for (FacetSuperClass facetSuperClass : facetsSuperClass) {
			printSuperClass(facetSuperClass, out);
		}
		for (Facet facet : facets) {
			if (!facet.getLabels().isEmpty()) {
				out.println("<" + facet.getURI() + ">");
				for (String locale : facet.getLabels().keySet()) {
					out.println("\trdfs:label \"" + facet.getLabels().get(locale) + "\"@" + locale + ";");
				}
				out.println(".");
				out.println("");
			} else {
				System.err.println("No labels for uri:" + facet.getURI());
			}
		}
	}

	private List<FacetSuperClass> getFacetsSuperClass(List<Facet> facets) {
		Map<String, FacetSuperClass> toReturn = new HashMap<String, FacetSuperClass>();
		FacetSuperClass generic = new FacetSuperClass("http://code.google.com/p/map4rdf/wiki/ontology#rdfTypeFacet");
		generic.addLabel("en", "Type");
		generic.addLabel("es", "Tipo");
		for (Facet facet : facets) {
			if (!facet.getSuperclassUris().isEmpty()) {
				for (String superClassUri : facet.getSuperclassUris()) {
					if (!toReturn.containsKey(superClassUri)) {
						FacetSuperClass superClass = new FacetSuperClass(superClassUri);
						superClass.addFacet(facet);
						toReturn.put(superClassUri, superClass);
					} else {
						toReturn.get(superClassUri).addFacet(facet);
					}
				}
			} else {
				generic.addFacet(facet);
			}
		}
		List<String> urisToRemove = new ArrayList<String>();
		for(String superClassUri:toReturn.keySet()){
			FacetSuperClass facetSuperClass = toReturn.get(superClassUri);
			if(facetSuperClass.getFacets().size()<=1){
				for(Facet facet:facetSuperClass.getFacets()){
					generic.addFacet(facet);
				}
				urisToRemove.add(superClassUri);
			}
		}
		for(String uriToRemove: urisToRemove){
			toReturn.remove(uriToRemove);
		}
		for (ObtainMoreDataOfFacets ontMoreData : ontsMoreData) {
			for (String uri : toReturn.keySet()) {
				toReturn.put(uri, ontMoreData.getFacetSuperClassMoreData(toReturn.get(uri)));
			}
		}
		toReturn.put(generic.getUri(), generic);
		return new ArrayList<FacetSuperClass>(toReturn.values());
	}

	private void printHeaderFacetsTTL(PrintStream out) {
		out.println("@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>.");
		out.println("@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .");
		out.println("@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .");
		out.println("@prefix foaf: <http://xmlns.com/foaf/0.1/> .");
		out.println("@prefix map4rdf: <http://code.google.com/p/map4rdf/wiki/ontology#> .");
		out.println();
	}

	private void printSuperClass(FacetSuperClass facetSuperClass, PrintStream out) {
		out.println("<" + facetSuperClass.getUri() + ">");
		out.println("\ta map4rdf:FacetGroup;");
		out.println("\tmap4rdf:facetPredicate rdf:type;");
		for (String locale : facetSuperClass.getLabels().keySet()) {
			out.println("\trdfs:label \"" + facetSuperClass.getLabels().get(locale) + "\"@" + locale + ";");
		}
		for (Facet facet : facetSuperClass.getFacets()) {
			out.println("\tmap4rdf:facet <" + facet.getURI() + ">;");
		}
		out.println(".");
		out.println();
	}
}
