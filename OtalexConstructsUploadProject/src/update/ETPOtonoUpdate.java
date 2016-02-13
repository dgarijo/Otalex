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
public class ETPOtonoUpdate {
    public static void run(){
        String graphName = "ETP_OTONO";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("< 180  mm."), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.ETPOtono,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("180-200  mm."), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.ETPOtono,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel(">200 mm."), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.ETPOtono,"3"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        ETPOtonoUpdate.run();
    }
}
