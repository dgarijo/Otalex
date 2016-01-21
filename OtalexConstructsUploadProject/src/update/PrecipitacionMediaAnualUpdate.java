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
public class PrecipitacionMediaAnualUpdate {
    public static void run(){
        String graphName = "PREC_MEDIA_ANUAL";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //0
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("< 500 mm"), 
                Queries.getDeleteStatementsForLabel("0"), 
                Queries.getWhereClauseForLabel(Queries.PrecMediaAnual,"0"),
                Queries.graphPrefixName+graphName);
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("500 - 600 mm"), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.PrecMediaAnual,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("600 - 700 mm"), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.PrecMediaAnual,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("700 - 800 mm."), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.PrecMediaAnual,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("800 - 900 mm."), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.PrecMediaAnual,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("900 - 1000 mm."), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.PrecMediaAnual,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("1000- 1100 mm."), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.PrecMediaAnual,"6"),
                Queries.graphPrefixName+graphName);
        //7
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("> 1100 mm."), 
                Queries.getDeleteStatementsForLabel("7"), 
                Queries.getWhereClauseForLabel(Queries.PrecMediaAnual,"7"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        PrecipitacionMediaAnualUpdate.run();
    }
}
