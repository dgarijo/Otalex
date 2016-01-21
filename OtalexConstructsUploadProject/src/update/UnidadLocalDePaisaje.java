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
public class UnidadLocalDePaisaje {
    public static void run(){
        String graphName = "UNIDADES_LOCALES_PAISAJE";
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        
        //updates: Where clause are equal to delete clauses in this case!
        q.performUpdateQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Albufera>"), 
                Queries.getDeleteStatementsForType("'Albufeiras'@pt"), 
                Queries.getDeleteStatementsForType("'Albufeiras'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:AluvialYColuvial"), 
                Queries.getDeleteStatementsForType("'Aluviais e coluviais'@pt"), 
                Queries.getDeleteStatementsForType("'Aluviais e coluviais'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:Caliza"), 
                Queries.getDeleteStatementsForType("'Calcários'@pt"), 
                Queries.getDeleteStatementsForType("'Calcários'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:Cuarcita"), 
                Queries.getDeleteStatementsForType("'Quartzitos'@pt"), 
                Queries.getDeleteStatementsForType("'Quartzitos'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:Duna"), 
                Queries.getDeleteStatementsForType("'Dunas'@pt"), 
                Queries.getDeleteStatementsForType("'Dunas'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:Granito"), 
                Queries.getDeleteStatementsForType("'Granitos'@pt"), 
                Queries.getDeleteStatementsForType("'Granitos'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:Pendiente"), 
                Queries.getDeleteStatementsForType("'Zonas de encosta'@pt"), 
                Queries.getDeleteStatementsForType("'Zonas de encosta'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:Pizarra"), 
                Queries.getDeleteStatementsForType("'Xistos'@pt"), 
                Queries.getDeleteStatementsForType("'Xistos'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:Planicie"), 
                Queries.getDeleteStatementsForType("'Planície'@pt"), 
                Queries.getDeleteStatementsForType("'Planície'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:PlutonicaEIgnea"), 
                Queries.getDeleteStatementsForType("'Rochas plutónicas e igneas'@pt"), 
                Queries.getDeleteStatementsForType("'Rochas plutónicas e igneas'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:RelieveAbrupto"), 
                Queries.getDeleteStatementsForType("'Relevo abrupto'@pt"), 
                Queries.getDeleteStatementsForType("'Relevo abrupto'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:ValleAplanado"), 
                Queries.getDeleteStatementsForType("'Vales aplanados'@pt"), 
                Queries.getDeleteStatementsForType("'Vales aplanados'@pt"),
                Queries.graphPrefixName+graphName);
        q.performUpdateQuery(Queries.getInsertStatementsForType("otalex:ValleCerrado"), 
                Queries.getDeleteStatementsForType("'Vales encaixados'@pt"), 
                Queries.getDeleteStatementsForType("'Vales encaixados'@pt"),
                Queries.graphPrefixName+graphName);
        //Just inserts: agua continental, albufera, humedal.
        q.performInsertQuery(Queries.getInsertStatementsForType("otalex:UnidadLocalPaisaje"),
                Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/AguasContinentales>"), 
                Queries.graphPrefixName+graphName);
        q.performInsertQuery(Queries.getInsertStatementsForType("otalex:UnidadLocalPaisaje"),
                Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Albufera>"), 
                Queries.graphPrefixName+graphName);
        q.performInsertQuery(Queries.getInsertStatementsForType("otalex:UnidadLocalPaisaje"),
                Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Humedal>"), 
                Queries.graphPrefixName+graphName);
        
    }
    public static void main (String[] args){
        UnidadLocalDePaisaje.run();
    }
}
