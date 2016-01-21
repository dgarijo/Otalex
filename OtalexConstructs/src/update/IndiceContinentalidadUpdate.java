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
public class IndiceContinentalidadUpdate {
    public static void run(){
        String graphName = "IND_CONTINENTALIDAD";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        //0
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Euhiperoceánico", "Euhiperoceánico"), 
                Queries.getDeleteStatementsForLabel("0"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"0"),
                Queries.graphPrefixName+graphName);
        //1
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Subhiperoceánico acusado", "Subhiperoceánico acusado."), 
                Queries.getDeleteStatementsForLabel("1"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"1"),
                Queries.graphPrefixName+graphName);
        //2
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Subhiperoceánico atenuado", "Subhiperoceánico atenuada"), 
                Queries.getDeleteStatementsForLabel("2"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"2"),
                Queries.graphPrefixName+graphName);
        //3
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Semihiperoceánico acusado", "Semihiperoceánico acusado"), 
                Queries.getDeleteStatementsForLabel("3"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"3"),
                Queries.graphPrefixName+graphName);
        //4
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Semihiperoceánico atenuado", "Semihiperoceánico atenuada"), 
                Queries.getDeleteStatementsForLabel("4"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"4"),
                Queries.graphPrefixName+graphName);
        //5
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Euoceánico acusado", "Euoceánico acusado"), 
                Queries.getDeleteStatementsForLabel("5"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"5"),
                Queries.graphPrefixName+graphName);
        //6
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Euoceánico atenuado.", "Euoceánico atenuado."), 
                Queries.getDeleteStatementsForLabel("6"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"6"),
                Queries.graphPrefixName+graphName);
        //7
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Semicontinental atenuado", "Semicontinental atenuada"), 
                Queries.getDeleteStatementsForLabel("7"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"7"),
                Queries.graphPrefixName+graphName);
        //8
        q.performUpdateQuery(Queries.getInsertStatementsForLabels("Semicontinental acusado", "Semicontinental acusado"), 
                Queries.getDeleteStatementsForLabel("8"), 
                Queries.getWhereClauseForLabel(Queries.INDContinentalidad,"8"),
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        IndiceContinentalidadUpdate.run();
    }
}
