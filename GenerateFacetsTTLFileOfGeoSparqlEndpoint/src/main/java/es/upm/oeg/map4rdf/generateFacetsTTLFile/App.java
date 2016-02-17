package es.upm.oeg.map4rdf.generateFacetsTTLFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import es.upm.oeg.map4rdf.generateFacetsTTLFile.util.Facet;
import es.upm.oeg.map4rdf.generateFacetsTTLFile.util.TTLType;

public class App 
{

    public static void main( String[] args )
    {
    	//Validate and get params
        if(args.length<2){
        	printUSAGE();
        	return;
        }
        TTLType ttlType = null;
        String typeFormated = args[0].substring(1, args[0].length());
        for(TTLType type:TTLType.values()){
        	if(typeFormated.equals(type.name())){
        		ttlType = type;
        		break;
        	}
        }
        if(ttlType == null){
        	System.err.println("Invalid param: "+args[0]);
        	printUSAGE();
        	return;
        }
        String endpointUri = args[1];
        List<String> ontURLs = new ArrayList<String>();
        List<ObtainMoreDataOfFacets> ontsMoreData = new ArrayList<ObtainMoreDataOfFacets>();
        if(args.length>2){
        	for(int i=2;i<args.length;i++){
        		if(!ontURLs.contains(args[i])){
        			ontURLs.add(args[i]);
        			ontsMoreData.add(new ObtainMoreDataOfFacets(args[i]));
        		}
        	}
        }
        ontURLs.clear();
        
        //PROCESS
        List<Facet> facetsInit;
        List<Facet> completeFacets = new ArrayList<Facet>();
        ObtainFacets obtainFacets = new ObtainFacets(endpointUri);
        facetsInit = obtainFacets.getFacets();
        for(ObtainMoreDataOfFacets obtainMDoF:ontsMoreData){
        	completeFacets.clear();
        	for(Facet facet: facetsInit){
        		completeFacets.add(obtainMDoF.getFacetMoreData(facet));
        	}
        	facetsInit.clear();
        	facetsInit.addAll(completeFacets);
        }
        facetsInit.clear();
        switch (ttlType) {
		case multipleType:
			PrintAllFacetInAMultipleType printM = new PrintAllFacetInAMultipleType(completeFacets, ontsMoreData);
			try {
				printM.printTTL(new PrintStream(new File("facets.ttl")));
			} catch (FileNotFoundException e) {
				System.err.println("Error in print file");
				e.printStackTrace();
			}
			return;
		default:
			PrintAllFacetInASingleType printS = new PrintAllFacetInASingleType(completeFacets);
			try {
				printS.printTTL(new PrintStream(new File("facets.ttl")));
			} catch (FileNotFoundException e) {
				System.err.println("Error in print file");
				e.printStackTrace();
			}
			break;
		}
    }
    
    private static void printUSAGE(){
    	System.out.println("generateFacetsTTLFileOfGeoSparqlEndpoint -singleType|-multipleType endpointUri [ontURLs ... ]");
    }
    

}
