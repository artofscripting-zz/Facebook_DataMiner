/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artust.fb_friends_parser;

import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Art
 */
public class Main {

    public static String dataFolder = "C:\\Users\\Art\\Desktop\\selenium-friends-scraper-master\\dist\\main\\data";
public static MysqlConnector ms = new MysqlConnector();
public static  List sqls = new ArrayList();
         
        
public static void main(String[] args) throws IOException {
        String json = "{test:1}";

             
        
        List<File> files = getFileNames(dataFolder);
        readFiles(files);

    }

    public static void readFiles(List<File> jsonFiles) {
        int cnt = 0;
        List<String> nodes = new ArrayList();
        List<String> links = new ArrayList();

        Map<String, Integer> counter = new HashMap();
        for (File file : jsonFiles) {
            String firstname = "";
            try {
                // System.out.println(file);
                JsonReader jsonReader = new JsonReader(new FileReader(file));

                jsonReader.beginArray();
                
                
                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextString();
                    if (firstname == "") {
                        firstname = name;
                    }
                    if (!name.equals(firstname)) {

                        String one = "{\"source\": \"" + firstname + "\", \"target\": \"" + name + "\", \"value\": 1}";
                        
                        String sqlLink = "INSERT INTO `facebook`.`friends` (`source`,`target`) VALUES (\""+firstname+"\" , \""+name+"\");";
                      //  ms.insert(sqlLinks);
                        links.add(one);
                          sqls.add(sqlLink +"\n");

                    }

                    if (counter.containsKey(name)) {
                        int integ = counter.get(name);
                        integ++;
                        counter.put(name, integ);
                        cnt++;
                    } else {
                        counter.put(name, 1);
                        //   System.out.println(name);
                        String one = "{\"id\": \"" + name + "\", \"group\": 1}";
                        nodes.add(one);
                        
                       
                      String sql = "INSERT INTO `facebook`.`users`\n" +
"(`username`)\n" +
"VALUES\n (\"" +
name+ "\");"
        + "";
                      
                       // System.out.println(sql);
                      //  ms.insert(sql);
                        sqls.add(sql);
                        

                    }

//System.out.println(name);
                }

                jsonReader.endArray();
                jsonReader.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("running data counts");
        String outSqlFile = String.join("\n", sqls);
        System.out.println(nodes.size());
       // String nodeData = String.join(",", nodes);
        System.out.println(links.size());
      //  String linkData = String.join(",", links);

        String flareData = "";
        flareData += "{\n  \"nodes\": [";
      //  flareData += nodeData;
        flareData += " ],\n \"links\": [";
     //   flareData += linkData;

        flareData += " ]\n}";
   try (FileWriter file = new FileWriter("Friends.sql")) {
            file.write(outSqlFile);
            System.out.println("Successfully Copied JSON Object to File...");
            //System.out.println("\nJSON Object: " + obj);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //   System.out.println(flareData);
        try (FileWriter file = new FileWriter("Friends3.json")) {
        //    file.write(flareData);
        //    System.out.println("Successfully Copied JSON Object to File...");
            //System.out.println("\nJSON Object: " + obj);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(cnt);
    }

    public static void countFiles(List<File> jsonFiles) {
        int cnt = 0;

        Map<String, Integer> counter = new HashMap();
        for (File file : jsonFiles) {

            try {
                // System.out.println(file);
                JsonReader jsonReader = new JsonReader(new FileReader(file));

                jsonReader.beginArray();

                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextString();

                    if (counter.containsKey(name)) {
                        int integ = counter.get(name);
                        integ++;
                        counter.put(name, integ);
                        cnt++;
                    } else {
                        counter.put(name, 1);

                    }

//System.out.println(name);
                }

                jsonReader.endArray();
                jsonReader.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //   System.out.println(flareData);
        System.out.println(cnt);

    }

    public static void readApp(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            //        System.out.println(name);

        }
        jsonReader.endObject();
    }

    public static List<File> getFileNames(String dataFolder) {
        try {
            List<File> filesInFolder = Files.walk(Paths.get(dataFolder))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            return filesInFolder;

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
