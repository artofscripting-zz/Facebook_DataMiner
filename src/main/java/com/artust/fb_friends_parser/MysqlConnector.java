/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artust.fb_friends_parser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Art
 */
public class MysqlConnector {
 Connection con;
 ResultSet rs = null;
     public MysqlConnector () {
     try {
         Class.forName("com.mysql.jdbc.Driver");
         con  = DriverManager.getConnection(
                 "jdbc:mysql://127.0.0.1:3306/piss", "root", "root");
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     }
         
}
     public MysqlConnector (String host) {
     try {
         Class.forName("com.mysql.jdbc.Driver");
         con  = DriverManager.getConnection(
                 "jdbc:mysql://"+host+":3306/piss", "root", "root");
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     }
         
}

    MysqlConnector(String host, String port) {
      
          try {
         Class.forName("com.mysql.jdbc.Driver");
         con  = DriverManager.getConnection(
                 "jdbc:mysql://"+host+":"+port+"/piss", "root", "root");
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
        
    }
      MysqlConnector(String host, String port, String db) {
      
          try {
         Class.forName("com.mysql.jdbc.Driver");
         con  = DriverManager.getConnection(
                 "jdbc:mysql://"+host+":"+port+"/"+db+"", "root", "root");
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
        
    }
            MysqlConnector(String host, String port, String db, String pw, String un) {
      
          try {
         Class.forName("com.mysql.jdbc.Driver");
         con  = DriverManager.getConnection(
                 "jdbc:mysql://"+host+":"+port+"/"+db+"", pw, un);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
        
    }
      public int insert(String sql) {
    int success = 0;
     try {
       
         
         con.prepareStatement(sql);
         PreparedStatement pstmt = con.prepareStatement(sql) ;
  
         success = pstmt.executeUpdate();
     } catch (SQLException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     }
         return success;
     }
   
    public ResultSet runQuery(String q){
             
           
     try {
         Statement stmt = con.createStatement();
         rs = stmt.executeQuery(q);
         con.close();
     } catch (SQLException ex) {
         Logger.getLogger(MysqlConnector.class.getName()).log(Level.SEVERE, null, ex);
     }
        
      return rs;  
        
        
    }
    
    
    
    
}
