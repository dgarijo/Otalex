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
public class ETPVeranoUpdate {
    public static void run(){
        String graphName = "ETP_VERANO";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("< 300  mm."), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("300-320  mm."), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("320-340 mm."), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("340-360 mm."), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("360-380 mm."), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("380-400 mm."), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"6"),
                Queries.graphPrefixName+graphName);
        //7
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("400-420 mm."), 
                Queries.getDeleteStatementsForLabel("7"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"7"),
                Queries.graphPrefixName+graphName);
        //8
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("420-440 mm."), 
                Queries.getDeleteStatementsForLabel("8"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"8"),
                Queries.graphPrefixName+graphName);
        //9
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("440-460 mm."), 
                Queries.getDeleteStatementsForLabel("9"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"9"),
                Queries.graphPrefixName+graphName);
        //10
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("460-480 mm."), 
                Queries.getDeleteStatementsForLabel("10"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"10"),
                Queries.graphPrefixName+graphName);
        //11
        q.performUpdateQuery(Queries.getInsertStatementsForLabel(">480 mm."), 
                Queries.getDeleteStatementsForLabel("11"), 
                Queries.getWhereClauseForLabel(Queries.ETPVerano,"11"),
                Queries.graphPrefixName+graphName);
    }
    public static void main (String[] args){
        ETPVeranoUpdate.run();
    }
}
