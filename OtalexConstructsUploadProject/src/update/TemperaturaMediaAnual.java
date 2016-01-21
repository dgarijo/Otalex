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
public class TemperaturaMediaAnual {
    public static void run(){
        String graphName = "T_MEDIA_ANUAL";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("<13 ºC"), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.TempMediaAnual,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("13 - 14 ºC"), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.TempMediaAnual,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("14 - 15 ºC"), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.TempMediaAnual,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("15 - 16 ºC"), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.TempMediaAnual,"6"),
                Queries.graphPrefixName+graphName);
        //7
        q.performUpdateQuery(Queries.getInsertStatementsForLabel(">16 ºC"), 
                Queries.getDeleteStatementsForLabel("7"), 
                Queries.getWhereClauseForLabel(Queries.TempMediaAnual,"7"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        TemperaturaMediaAnual.run();
    }
}
