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
public class TemperaturaMinimaMediaAnual {
    public static void run(){
        String graphName = "TMIN_MEDIA_ANUAL";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("< 8 ºC"), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.TempMinMediaAnual,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("8 - 9 ºC"), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.TempMinMediaAnual,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("9 - 10 ºC"), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.TempMinMediaAnual,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("10 - 11 ºC"), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.TempMinMediaAnual,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("> 11 ºC"), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.TempMinMediaAnual,"6"),
                Queries.graphPrefixName+graphName);
    }
    public static void main (String[] args){
        TemperaturaMinimaMediaAnual.run();
    }
}
