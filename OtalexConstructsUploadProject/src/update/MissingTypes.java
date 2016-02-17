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
 * This script fixes the missing types of RIo, Embalse, Arroyo, Abufeira. Ribeira. Regato and Riveira
 * Query: select distinct ?g ?c ?l ?a where {GRAPH ?g{?a a ?c. ?a <http://www.w3.org/2000/01/rdf-schema#label> ?l} FILTER regex(?l, "Regato") } LIMIT 1000
 * @author dgarijo
 */
public class MissingTypes {
    //small method to fix the other one in case errors are detected
/*    public static void run2(){
       QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
       //EMBALSE
       String[] graphNames = {"http://otalex.linkeddata.es/graph/I8SUPERFICIESACUATICAS_ETRS89_H29","http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29"};
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Embalse>"),
                "?resource a <http://geo.linkeddata.es/ontology/Embalse>", 
                graph);
        }
        //RIO, RÍO
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/SUPERFICIES_AQUATICAS_PT",
            "http://otalex.linkeddata.es/graph/CURSOS_DE_AGUA_PT",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/DIRECTIVA_HABITATS",
            "http://otalex.linkeddata.es/graph/I8CURSOSDEAGUA_ETRS89"};
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Rio>"),
                "?resource a <http://geo.linkeddata.es/ontology/Rio>", 
                graph);
            
        }
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29",
            "http://otalex.linkeddata.es/graph/I8CURSOSDEAGUA_ETRS89"};
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Rio>"),
                "?resource a <http://geo.linkeddata.es/ontology/Rio>", 
                graph);
            
        }
        //ALBUFEIRA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/I8SUPERFICIESACUATICAS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/SUPERFICIES_AQUATICAS_PT",
            "http://otalex.linkeddata.es/graph/UNIDADES_PAISAGEM_OTALEX_ETRS"};
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Albufera>"),
                "?resource a <http://geo.linkeddata.es/ontology/Albufera>", 
                graph);
            
        }
        //LAGOA 
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/AREAS_PROTEGIDAS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/CLC_06_OTALEX_C_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/CLC_00_OTALEX_C_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I8CURSOSDEAGUA_ETRS89",
            "http://otalex.linkeddata.es/graph/PT_EMBALSES",
            "http://otalex.linkeddata.es/graph/RAMSAR_PT_ETRS89_H29_REC",
            "http://otalex.linkeddata.es/graph/SUPERFICIES_AQUATICAS_PT"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Laguna>"),
                "?resource a <http://geo.linkeddata.es/ontology/Laguna>", 
                graph);  
        }
        
        //LAGUNA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29"};
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Laguna>"),
                "?resource a <http://geo.linkeddata.es/ontology/Laguna>", 
                graph);  
        }
        
        //ARROYO
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Arroyo>"),
                "?resource a <http://geo.linkeddata.es/ontology/Arroyo>", 
                graph);  
        }
        //RIBEIRA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/CURSOS_DE_AGUA_PT",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I8CURSOSDEAGUA_ETRS89",
            "http://otalex.linkeddata.es/graph/RAMSAR_PT_ETRS89_H29_REC",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Rivera>"),
                "?resource a <http://geo.linkeddata.es/ontology/Rivera>", 
                graph);  
        }
        
        //RIVERA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Rivera>"),
                Queries.getInsertStatementsForLabelRegExp("Rivera "), 
                graph);  
        }
        
        //REGATO
        
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Regato>"),
                "?resource a <http://geo.linkeddata.es/ontology/Regato>", 
                graph);  
        }
        
        //GARGANTA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/AREAS_PROTEGIDAS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Garganta>"),
                "?resource a <http://geo.linkeddata.es/ontology/Garganta>", 
                graph);  
        }
        
        //BARRANCO
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Barranco>"),
                "?resource a <http://geo.linkeddata.es/ontology/Barranco>", 
                graph);
        }
        
        //REGAJO
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Regajo>"),
                "?resource a <http://geo.linkeddata.es/ontology/Regajo>", 
                graph);
        }
        
        //PAUL
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/AREAS_PROTEGIDAS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/RAMSAR_PT_ETRS89_H29_REC",
            "http://otalex.linkeddata.es/graph/DIRECTIVA_HABITATS"
        };
        for(String graph:graphNames){
        q.performDeleteQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Paul>"),
                "?resource a <http://geo.linkeddata.es/ontology/Paul>", 
                graph);
        }
            
    }*/ 
    
    public static void run(){
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        /**
         * Query done to extract the graphs: select distinct ?g  where {GRAPH ?g{?a a ?c. ?a <http://www.w3.org/2000/01/rdf-schema#label> ?l} FILTER regex(?l, "Rio ") } LIMIT 1000
         */
        //EMBALSE
        String[] graphNames = {"http://otalex.linkeddata.es/graph/I8SUPERFICIESACUATICAS_ETRS89_H29","http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29"};
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Embalse>"),
                Queries.getInsertStatementsForLabelRegExp("Embalse "), 
                graph);
            
        }
        //RIO, RÍO
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/SUPERFICIES_AQUATICAS_PT",
            "http://otalex.linkeddata.es/graph/CURSOS_DE_AGUA_PT",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/DIRECTIVA_HABITATS",
            "http://otalex.linkeddata.es/graph/I8CURSOSDEAGUA_ETRS89"};
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Rio>"),
                Queries.getInsertStatementsForLabelRegExp("Rio "), 
                graph);
            
        }
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29",
            "http://otalex.linkeddata.es/graph/I8CURSOSDEAGUA_ETRS89"};
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Rio>"),
                Queries.getInsertStatementsForLabelRegExp("Río "), 
                graph);
            
        }
        //ALBUFEIRA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/I8SUPERFICIESACUATICAS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/SUPERFICIES_AQUATICAS_PT",
            "http://otalex.linkeddata.es/graph/UNIDADES_PAISAGEM_OTALEX_ETRS"};
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Albufera>"),
                Queries.getInsertStatementsForLabelRegExp("Albufeira "), 
                graph);
            
        }
        //LAGOA 
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/AREAS_PROTEGIDAS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/CLC_06_OTALEX_C_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/CLC_00_OTALEX_C_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I8CURSOSDEAGUA_ETRS89",
            "http://otalex.linkeddata.es/graph/PT_EMBALSES",
            "http://otalex.linkeddata.es/graph/RAMSAR_PT_ETRS89_H29_REC",
            "http://otalex.linkeddata.es/graph/SUPERFICIES_AQUATICAS_PT"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Laguna>"),
                Queries.getInsertStatementsForLabelRegExp("Lagoa "), 
                graph);  
        }
        
        //LAGUNA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29"};
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Laguna>"),
                Queries.getInsertStatementsForLabelRegExp("Laguna "), 
                graph);  
        }
        
        //ARROYO
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Arroyo>"),
                Queries.getInsertStatementsForLabelRegExp("Arroyo "), 
                graph);  
        }
        //RIBEIRA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/CURSOS_DE_AGUA_PT",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I8CURSOSDEAGUA_ETRS89",
            "http://otalex.linkeddata.es/graph/RAMSAR_PT_ETRS89_H29_REC",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Rivera>"),
                Queries.getInsertStatementsForLabelRegExp("Ribeira "), 
                graph);  
        }
        
        //RIVERA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Rivera>"),
                Queries.getInsertStatementsForLabelRegExp("Rivera "), 
                graph);  
        }
        
        //REGATO
        /**
         * http://otalex.linkeddata.es/graph/PT_RIOS
         */
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Regato>"),
                Queries.getInsertStatementsForLabelRegExp("Regato "), 
                graph);  
        }
        
        //GARGANTA
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/AREAS_PROTEGIDAS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/I_06_EMBALSES_ETRS89H29",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Garganta>"),
                Queries.getInsertStatementsForLabelRegExp("Garganta "), 
                graph);  
        }
        
        //BARRANCO
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/PT_RIOS",
            "http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29",
            "http://otalex.linkeddata.es/graph/RIOS_ETRS89_H29"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Barranco>"),
                Queries.getInsertStatementsForLabelRegExp("Barranco "), 
                graph);
        }
        
        //REGAJO
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/I_06_RIOS_ETRS89H29"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Regajo>"),
                Queries.getInsertStatementsForLabelRegExp("Regajo "), 
                graph);
        }
        
        //PAUL
        graphNames = new String[]{"http://otalex.linkeddata.es/graph/AREAS_PROTEGIDAS_ETRS89_H29",
            "http://otalex.linkeddata.es/graph/RAMSAR_PT_ETRS89_H29_REC",
            "http://otalex.linkeddata.es/graph/DIRECTIVA_HABITATS"
        };
        for(String graph:graphNames){
        q.performInsertQuery(Queries.getInsertStatementsForType("<http://geo.linkeddata.es/ontology/Paul>"),
                Queries.getInsertStatementsForLabelRegExp("Paul "), 
                graph);
        }
        
    }
    public static void main (String[] args){
        MissingTypes.run();
    }
}
