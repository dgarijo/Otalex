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
public class IndiceAridezUpdate {
    public static void run(){
        String graphName = "IND_ARIDEZ";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Subhúmedo seco", "Sub-úmido seco"), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.INDAridez,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Subhúmedo húmedo", "Sub-úmido úmido"), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.INDAridez,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Húmedo", "Úmido"), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.INDAridez,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Perhúmedo", "Perúmido"), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.INDAridez,"4"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        IndiceAridezUpdate.run();
    }
}
