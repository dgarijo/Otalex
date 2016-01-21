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
public class AmplitudTermicaMediaAnualUpdate {
    public static void run(){
        String graphName = "AMPL_TERM_MEDIA_ANUAL";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("<10 ºC."), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaMediaAnual,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("10-12 ºC."), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaMediaAnual,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel(">12 ºC."), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaMediaAnual,"4"),
                Queries.graphPrefixName+graphName);
    }
    public static void main (String[] args){
        AmplitudTermicaMediaAnualUpdate.run();
    }
}
