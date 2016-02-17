/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otalex;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;
import java.io.InputStream;
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoUpdateFactory;
import virtuoso.jena.driver.VirtuosoUpdateRequest;

/**
 * Class for performing the typical operations in Virtuoso/SPARQL
 * @author dgarijo
 */
public class QueryOperations {
    String user;
    String password;
    String repo;

    public QueryOperations(String user, String password, String repo) {
        this.user = user;
        this.password = password;
        this.repo = repo;
    }
    
    /**
     * Deletes a graph from the repository
     * @param graphName 
     */
    public void deleteGraph (String graphName){
        String queryString = "CLEAR GRAPH <"+graphName+">";
	this.executeQuery(queryString, graphName);
    }
    
    private OntModel loadModel(String file, String modeFile){
        OntModel modelo = ModelFactory.createOntologyModel();//ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(file);
        if (in == null) {
            throw new IllegalArgumentException("File: " + file + " not found");
        }
        // read the RDF/XML file
        modelo.read(in, null, modeFile);
//        System.out.println("File "+file+" loaded");
        return modelo;
    }
    
    /**
     * Loads an RDF file to the repository, into graph graphName
     * @param pathToFile
     * @param graphName 
     */
    public void loadFileToRepositoryGraph (String pathToFile, String graphName){
        try{
            OntModel modelo = loadModel(pathToFile, "TTL");
            VirtGraph set = new VirtGraph(Queries.graphPrefixName+graphName, Constants.repository, Constants.user,Constants.password);
            set.getBulkUpdateHandler().add(modelo.getGraph());
            set.close();
            System.out.println("file loaded: "+pathToFile+ " in "+ graphName);
//            vur.exec();
        }catch(Exception e){
            System.out.println("Error when uploading the file "+pathToFile+ " in "+ graphName);
        }
    }
    
    //method that updates a label with the content in newLabels
    public void performUpdateQuery(String insert, String delete,String where, String graphName){
        performInsertQuery(insert, where, graphName);
        performDeleteQuery(delete, where, graphName);
    }
    
    public void performDeleteQuery(String statementsToDelete, String whereClause, String graphName){
        String query = Queries.prefixOtalex+"\n"+Queries.prefixRDFS+"\n"+
                "WITH GRAPH <"+graphName+">\n"
                + "DELETE{\n" +statementsToDelete+
                       "}\n" +
                        "WHERE{\n" +whereClause+                
                        "}";
        System.out.println(query);
        this.executeQuery(query, graphName);
    }
    
    public void performInsertQuery(String statementsToInsert, String whereClause, String graphName){
        String query = Queries.prefixOtalex+"\n"+Queries.prefixRDFS+"\n"+
                "WITH GRAPH <"+graphName+">\n"
                + "INSERT{\n" +statementsToInsert+
                       "}\n" +
                        "WHERE{\n" +whereClause+                
                        "}";
        System.out.println(query);
        this.executeQuery(query, graphName);
    }
    
    /**
     * Given a query and a graph, this method executes the query
     * @param query
     * @param graph 
     */
    public void executeQuery (String query, String graph){
        VirtGraph set = new VirtGraph(graph, this.repo, this.user,this.password);
        VirtuosoUpdateRequest request = VirtuosoUpdateFactory.create(query, set);
        request.exec();
    }
    
    public static void main (String[] args){
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //q.loadFromFile("C:\\Users\\dgarijo\\Desktop\\Ampl_Term_Media_Anual.ttl", "AmplitudTermicaMediaAnual");
        //q.deleteGraph("http://otalex.linkeddata.es/graph/I2HOJAS50_ETRS89");
        q.deleteGraph("http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29");
//        q.insertQuery("	?resource rdfs:label '<10 ºC.'@es.\n" +
//"	?resource rdfs:label '<10 ºC.'@pt", "?resource a otalex:AmplitudTermicaMediaAnual.\n" +
//"	?resource rdfs:label '2'.", "http://otalex.linkeddata.es/graph/AmplitudTermicaMediaAnual");
//        q.performUpdateQuery("	?resource rdfs:label '<10 ºC.'@es.\n" +
//"	?resource rdfs:label '<10 ºC.'@pt", "	?resource rdfs:label '2'.", "?resource a otalex:AmplitudTermicaMediaAnual.\n" +
//"	?resource rdfs:label '2'.", "http://otalex.linkeddata.es/graph/AmplitudTermicaMediaAnual");
    }
}
