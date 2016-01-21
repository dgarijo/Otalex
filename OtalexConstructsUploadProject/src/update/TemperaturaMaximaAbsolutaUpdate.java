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
public class TemperaturaMaximaAbsolutaUpdate {
    public static void run(){
        String graphName = "TMAX_ABSOLUTA";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("< 38 ºC"), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxAbsoluta,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("38- 40 ºC"), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxAbsoluta,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("40 - 42 ºC"), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxAbsoluta,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("42 - 44 ºC"), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxAbsoluta,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("> 44 ºC"), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxAbsoluta,"6"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        TemperaturaMaximaAbsolutaUpdate.run();
    }
}
