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
public class ETPInviernoUpdate {
    public static void run(){
        String graphName = "ETP_INVIERNO";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //0
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("< 40 mm."), 
                Queries.getDeleteStatementsForLabel("0"), 
                Queries.getWhereClauseForLabel(Queries.ETPInvierno,"0"),
                Queries.graphPrefixName+graphName);
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("40-60  mm."), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.ETPInvierno,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("60-80  mm."), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.ETPInvierno,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("80-100 mm."), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.ETPInvierno,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("> 100 mm."), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.ETPInvierno,"4"),
                Queries.graphPrefixName+graphName);
    }
    public static void main (String[] args){
        ETPInviernoUpdate.run();
    }
}
