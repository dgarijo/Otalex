package es.upm.oeg.map4rdf.generateFacetsTTLFile.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacetSuperClass {
	private String uri;
	private Map<String,String> labels;
	private List<Facet> facets;
	
	public FacetSuperClass(String uri) {
		this.uri = uri;
		this.labels = new HashMap<String, String>();
		this.facets = new ArrayList<Facet>();
	}

	public String getUri() {
		return uri;
	}

	public Map<String, String> getLabels() {
		return labels;
	}

	public List<Facet> getFacets() {
		return facets;
	}
	
	public String addLabel(String locale,String label){
		return this.labels.put(locale, label);
	}
	
	public void addFacet(Facet facet){
		if(!this.facets.contains(facet)){
			this.facets.add(facet);
		}
		
	}
}
