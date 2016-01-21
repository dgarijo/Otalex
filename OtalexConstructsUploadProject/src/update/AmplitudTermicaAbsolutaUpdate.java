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
public class AmplitudTermicaAbsolutaUpdate {
    //graph name: AMPL_TERM_ABS
    public static void run(){
        String graphName = "AMPL_TERM_ABS";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("<44 ºC."), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaAbsoluta,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("44-46 ºC."), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaAbsoluta,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("46-48 ºC."), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaAbsoluta,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("48-50 ºC."), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaAbsoluta,"6"),
                Queries.graphPrefixName+graphName);
        //7
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("50-52 ºC."), 
                Queries.getDeleteStatementsForLabel("7"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaAbsoluta,"7"),
                Queries.graphPrefixName+graphName);
        //8
        q.performUpdateQuery(Queries.getInsertStatementsForLabel(">52 ºC."), 
                Queries.getDeleteStatementsForLabel("8"), 
                Queries.getWhereClauseForLabel(Queries.AmplitudTermicaAbsoluta,"8"),
                Queries.graphPrefixName+graphName);
       
    }
    public static void main (String[] args){
        AmplitudTermicaAbsolutaUpdate.run();
    }
}
