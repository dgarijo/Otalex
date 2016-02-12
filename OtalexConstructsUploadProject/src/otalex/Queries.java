/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otalex;

/**
 * Class for creating the different type of queries we will insert into Otalex
 * @author dgarijo
 */
public class Queries {
    public static final String prefixOtalex = "prefix otalex: <http://vocab.linkeddata.es/datosabiertos/def/medio-ambiente/otalex#>";
    public static final String prefixRDFS = "prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#>";
    
    public static final String graphPrefixName = "http://otalex.linkeddata.es/graph/";
    
    /**
     * Add the label in portuguese and spanish (both with the same label)
     * @param label
     * @return 
     */
    public static final String getInsertStatementsForLabel(String label){
        return"?resource rdfs:label '"+label+"'@es."+
	"?resource rdfs:label '"+label+"'@pt";
    }
    
    /**
     * Add the label in spanish and protuguse
     * @param labelEs
     * @param labelPt
     * @return 
     */
    public static final String getInsertStatementsForLabels(String labelEs, String labelPt){
        return"?resource rdfs:label '"+labelEs+"'@es."+
	"?resource rdfs:label '"+labelPt+"'@pt";
    }
    
    //Generic delete statement for legends
    public static final String getDeleteStatementsForLabel(String label){
        return"?resource rdfs:label '"+label+"'";
    }
    //Generic where clause
    public static final String getWhereClauseForLabel(String feature, String labelValue){
        return "?resource a "+feature+".\n" +
                "	?resource rdfs:label '"+labelValue+"'.";
    }
    
    //insert type1 where type2
    public static final String getInsertStatementsForType(String type){
        return "?resource a "+type+".\n";
    }
    
    //delete (where statements are equal)
    public static final String getDeleteStatementsForType(String type){
        return "?resource otalex:auxType "+type+".\n";
    }
    
    //insert ?resource a ?type where ?resource rdfs:label ?label
    //?a a ?c. ?a <http://www.w3.org/2000/01/rdf-schema#label> ?l} FILTER regex(?l, "Laguna ") 
    public static final String getInsertStatementsForLabelRegExp(String label){
        return "?resource a ?type.\n ?resource <http://www.w3.org/2000/01/rdf-schema#label> ?l. FILTER regex(?l, \""+label+"\")";
    }
   
    
    //AMPL_TERM_ABS
    public static final String AmplitudTermicaAbsoluta = "otalex:AmplitudTermicaAbsoluta";
    //AMPL_TERM_MEDIA_ANUAL
    public static final String AmplitudTermicaMediaAnual = "otalex:AmplitudTermicaMediaAnual";
    //ETP_ANUAL
    public static final String ETPAnual = "otalex:ETPAnual";
    //ETP_INVIERNO
    public static final String ETPInvierno = "otalex:ETPInvierno";
    //ETP_OTONO
    public static final String ETPOtono = "otalex:ETPOtono";
    //ETP_PRIMAVERA
    public static final String ETPPrimavera = "otalex:ETPPrimavera";
    //ETP_VERANO
    public static final String ETPVerano = "otalex:ETPVerano";
    //IND_ARIDEZ
    public static final String INDAridez = "otalex:IndiceAridez";
    //IND_CONTINENTALIDAD
    public static final String INDContinentalidad = "otalex:IndiceContinentalidad";
    //IND_OMBROTERMICO
    public static final String INDOmbrotermico = "otalex:IndiceOmbrotermico";
    //IND_TERMICIDAD
    public static final String INDTermicidad = "otalex:IndiceTermicidad";
    //PREC_MAX_24H
    public static final String PrecMax24H = "otalex:PrecipitacionMaxima24Horas";
    //PREC_MEDIA_ANUAL
    public static final String PrecMediaAnual = "otalex:PrecipitacionMediaAnual";
    //TMAX_ABSOLUTA
    public static final String TempMaxAbsoluta = "otalex:TemperaturaMaximaAbsoluta";
    //TMAX_MEDIA_ANUAL
    public static final String TempMaxMediaAnual = "otalex:TemperaturaMaximaMediaAnual";
    //TMIN_ABSOLUTA
    public static final String TempMinAbs = "otalex:TemperaturaMinimaAbsoluta";
    //TMIN_MEDIA_ANUAL
    public static final String TempMinMediaAnual = "otalex:TemperaturaMinimaMediaAnual";
    //T_MEDIA_ANUAL
    public static final String TempMediaAnual = "otalex:TemperaturaMediaAnual";
    //UNIDADES_LOCALES_PAISAJE
    public static final String UnidadLocalPaisaje = "otalex:UnidadLocalPaisaje";
}
