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
public class TemperaturaMinimaAbsoluta {
    public static void run(){
        String graphName = "TMIN_ABSOLUTA";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("<-8 ºC"), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.TempMinAbs,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("-8 / -7 ºC"), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.TempMinAbs,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("-7/-6 ºC"), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.TempMinAbs,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("-6 / -5 ºC"), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.TempMinAbs,"6"),
                Queries.graphPrefixName+graphName);
        //7
        q.performUpdateQuery(Queries.getInsertStatementsForLabel("-5 / -4 ºC"), 
                Queries.getDeleteStatementsForLabel("7"), 
                Queries.getWhereClauseForLabel(Queries.TempMinAbs,"7"),
                Queries.graphPrefixName+graphName);
        //8
        q.performUpdateQuery(Queries.getInsertStatementsForLabel(">-4 ºC"), 
                Queries.getDeleteStatementsForLabel("8"), 
                Queries.getWhereClauseForLabel(Queries.TempMinAbs,"8"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        TemperaturaMinimaAbsoluta.run();
    }
}
