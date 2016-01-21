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
public class PrecipitacionMax24HUpdate {
    public static void run(){
        String graphName = "PREC_MAX_24H";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("< 80 mm"), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.PrecMax24H,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("80-95 mm."), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.PrecMax24H,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("95-110 mm."), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.PrecMax24H,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("110-125 mm."), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.PrecMax24H,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("125-140 mm."), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.PrecMax24H,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel(">140 mm."), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.PrecMax24H,"6"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        PrecipitacionMax24HUpdate.run();
    }
}
