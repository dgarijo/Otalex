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
public class ETPPrimaveraUpdate {
    public static void run(){
        String graphName = "ETP_PRIMAVERA";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("<160  mm."), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.ETPPrimavera,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel(">160 mm."), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.ETPPrimavera,"3"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        ETPPrimaveraUpdate.run();
    }
}
