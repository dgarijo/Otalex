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
public class TemperaturaMaximaMediaAnualUpdate {
    public static void run(){
        String graphName = "TMAX_MEDIA_ANUAL";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("< 18 ºC"), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxMediaAnual,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("18-19 ºC"), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxMediaAnual,"6"),
                Queries.graphPrefixName+graphName);
        //7
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("19-20 ºC"), 
                Queries.getDeleteStatementsForLabel("7"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxMediaAnual,"7"),
                Queries.graphPrefixName+graphName);
        //8
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("20 - 21 ºC"), 
                Queries.getDeleteStatementsForLabel("8"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxMediaAnual,"8"),
                Queries.graphPrefixName+graphName);
        //9
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("21 - 22 ºC"), 
                Queries.getDeleteStatementsForLabel("9"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxMediaAnual,"9"),
                Queries.graphPrefixName+graphName);
        //10
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("> 22 ºC"), 
                Queries.getDeleteStatementsForLabel("10"), 
                Queries.getWhereClauseForLabel(Queries.TempMaxMediaAnual,"10"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        TemperaturaMaximaMediaAnualUpdate.run();
    }
}
