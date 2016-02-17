package es.upm.oeg.map4rdf.generateFacetsTTLFile.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facet {
	
	private String uri;
	private Map<String,String> labels;
	private List<String> superclassUris;
	
	public Facet(String uri){
		this.uri = uri;
		this.labels = new HashMap<String, String>();
		this.superclassUris = new ArrayList<String>();
	}
	
	public String getURI(){
		return this.uri;
	}
	
	public Map<String,String> getLabels(){
		return this.labels;
	}
	
	public List<String> getSuperclassUris() {
		return superclassUris;
	}

	public void addSuperclassUri(String superclassUri) {
		if(!this.superclassUris.contains(superclassUri)){
			this.superclassUris.add(superclassUri);
		}
	}

	public String addLabel(String locale,String label){
		return this.labels.put(locale, label);
	}
}
