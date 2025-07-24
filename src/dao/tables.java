/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
/**  
 *
 * @author muzammil
 */
public class tables {
    
    public static void main (String[] args){
       Connection con =  null;
       Statement  st = null;
       
       try {
    con = ConnectionProvider.getCon();
    
    if (con == null) {
        JOptionPane.showMessageDialog(null, "Failed to establish a database connection. Check your database server and credentials.");
        return;
    }

    st = con.createStatement();
    
    if (!tableExists(st, "userdetails")) {
        st.executeUpdate("CREATE TABLE userdetails("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255) NOT NULL, "
                + "gender VARCHAR(50) NOT NULL, "
                + "email VARCHAR(255) NOT NULL, "
                + "contact VARCHAR(20) NOT NULL, "
                + "address VARCHAR(500) NOT NULL, "
                + "state VARCHAR(100), "
                + "country VARCHAR(100), "
                + "uniqueregid VARCHAR(100) NOT NULL, "
                + "imagename VARCHAR(100)"
                + ");");
    }
   if (!tableExists(st, "userattendence")){
       st.executeUpdate("CREATE TABLE userattendence(userid INT NOT NULL,date DATE NOT NULL, checkin DATETIME, check"
               + "out DATETIME, workduration VARCHAR(100));");
   }
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, ex);
}finally{
           try{
               if(con!=null){
                   con.close();
               }
               if(st!=null){
                   st.close();
               }
           }catch(SQLException ex){
           }
       }
          
    }
    
    private static boolean tableExists(Statement st, String tableName )throws Exception{
        ResultSet resultSet = st.executeQuery("SHOW TABLES LIKE '" +tableName+ "'");
        return resultSet.next();
    }
}
