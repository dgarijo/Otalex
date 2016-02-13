/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otalex;

import java.io.File;

/**
 * Script designed to load the files of a folder in the repository. 
 * Each file is assigned a different graph, which corresponds to its name.
 * Note that some files might fail due to their size. They will be uploaded separately
 * @author dgarijo
 */
public class LoadFilesInRepo {
    public static void run(){
       File f = new File(Constants.folderOfFilesToUpload);
       if(f.isDirectory()){
           //load all the ttl files
           File[] list;
           list = f.listFiles((File dir, String name) -> name.endsWith(".ttl"));
           QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
           for (File file:list){
               String graphName = file.getName().replace(".ttl", "").replace("_fixed","").toUpperCase().replace("-", "_").replace(" ", "").trim();
//               System.out.println(graphName);
               q.loadFileToRepositoryGraph(file.getPath(), graphName);
           }
       }
    }
    public static void runSingleFile(String fileName){
        File file = new File(Constants.folderOfFilesToUpload+File.separator+fileName);
        QueryOperations q = new QueryOperations(Constants.user, Constants.password, Constants.repository);
        String graphName = file.getName().replace(".ttl", "").replace("_fixed","").toUpperCase().replace("-", "_").replace(" ", "").trim();
        q.loadFileToRepositoryGraph(file.getPath(), graphName);
    }
    
    public static void main (String[] args){
//        LoadFilesInRepo.run();
        LoadFilesInRepo.runSingleFile("I2hojas50-etrs89_fixed.ttl");
//        LoadFilesInRepo.runSingleFile("I_06_EBALSES_ETRS89H29_fixed.ttl");
    }
}
