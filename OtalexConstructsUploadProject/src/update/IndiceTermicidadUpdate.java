/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update;

import otalex.Constants;
import otalex.Queries;
import otalex.QueryOperations;

/**
 *
 * @author dgarijo
 */
public class IndiceTermicidadUpdate {
    public static void run(){
        String graphName = "IND_TERMICIDAD";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("Mesotemplado superior"), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.INDTermicidad,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("Mesomediterráneo superior"), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.INDTermicidad,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("Mesomediterráneo inferior"), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.INDTermicidad,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("Termomediterráneo inferior"), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.INDTermicidad,"4"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        IndiceTermicidadUpdate.run();
    }
}
