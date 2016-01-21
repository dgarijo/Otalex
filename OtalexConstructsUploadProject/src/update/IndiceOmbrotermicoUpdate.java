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
public class IndiceOmbrotermicoUpdate {
    public static void run(){
        String graphName = "IND_OMBROTERMICO";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //0
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Seco inferior", "Seco inferior"), 
                Queries.getDeleteStatementsForLabel("0"), 
                Queries.getWhereClauseForLabel(Queries.INDOmbrotermico,"0"),
                Queries.graphPrefixName+graphName);
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Seco superior", "Seco superior"), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.INDOmbrotermico,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Subhúmedo inferior", "Subúmido inferior"), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.INDOmbrotermico,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Subhúmedo superior", "Subúmido superior"), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.INDOmbrotermico,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Húmedo inferior", "Úmido inferior"), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.INDOmbrotermico,"4"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        IndiceOmbrotermicoUpdate.run();
    }
}
