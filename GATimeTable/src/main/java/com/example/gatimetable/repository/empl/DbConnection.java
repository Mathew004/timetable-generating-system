package com.example.gatimetable.repository.empl;

import java.sql.*;

public class DbConnection {
     public static Connection getConnection(){
         Connection con;
         try{
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetabledb","root","");
             return con;
         }catch (Exception ex){
             System.out.println(ex.getMessage());
         }
         return null;
     }
     public static ResultSet getResultSet(String query){
         Connection con = null;
         PreparedStatement st = null;
         ResultSet rs = null;
         try {
             con = getConnection();
             st = con.prepareStatement(query);
             rs = st.executeQuery();
             return rs;
         }catch (Exception ex){
             System.out.println(ex.getMessage());
         }
         return null;
     }
}
