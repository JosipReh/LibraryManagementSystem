/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Database;
import java.sql.*;
/**
 *
 * @author Josip
 */
public class DBConn {
    
    public static Connection Connector(){
    Connection Conn = null;
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        
        String host = "jdbc:mysql://localhost:3306/librarymanagementsystem";
        
        String username = "root";
        
        String password = "";
	Conn = DriverManager.getConnection(host, username, password);
    }catch(Exception e) {
        System.out.println("e = " + e);
        return null;
    }
    return Conn;
    }
}


