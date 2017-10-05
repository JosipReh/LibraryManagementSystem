/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Models;

import app.Database.DBConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josip
 */
public class UserModel {
    Connection Conn = DBConn.Connector();
    ResultSet rs;
    PreparedStatement pst;


    public void Register(String firstname, String lastname, String username, String password, String sec_q, String answer) {

        
        // \/ \/ \/
           try {
            String sql = "INSERT INTO Users (Firstname,Lastname,Username,Password,Security_Question,Answer) VALUES(?,?,?,?,?,?)";
            pst=Conn.prepareStatement(sql);
            pst.setString(1, firstname);
            pst.setString(2, lastname);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, sec_q);
            pst.setString(6, answer);
            pst.execute();
            pst.close();
            System.out.println("Account created = ");
            
        }catch(Exception e) {
            System.out.println("Query error -> "+e);
        }
    }

    public Integer checkIfUserExists(String username) {
        //String insertRegisterDataQuery = "insert into users (username,email,password) values ('"+username+"','"+email+"','"+password+"')";
        
        int usernameCounter = 0;

        try {
            String checkIfUsernameExistsQuery = ("SELECT username from Users WHERE username=?");
            pst=Conn.prepareStatement(checkIfUsernameExistsQuery);
            pst.setString(1,username);
            rs = pst.executeQuery();


            //broji koliko je username-ova u bazi isto kao i uneseni username
            while (rs.next()) {
                ++usernameCounter;
                // Get data from the current row and use it
            }
            System.out.println("usernameCounter = " + usernameCounter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usernameCounter;
    }
    
    public boolean login(String username, String password) {
        boolean isLoggedInSuccessfuly = false;
        try {
        String SelectQuery = "SELECT * FROM Users WHERE username=? AND password =?";
        pst=Conn.prepareStatement(SelectQuery);
        pst.setString(1, username);
        pst.setString(2, password);
        rs = pst.executeQuery();
            while (rs.next()) {
                String dbUsername = rs.getString("username");
                String dbPassword = rs.getString("password");

                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                System.out.println("username : " + dbUsername + " Password : " + dbPassword);
                isLoggedInSuccessfuly = true;
                }else {
                    isLoggedInSuccessfuly = false;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return isLoggedInSuccessfuly;
    }
    
    
    public String recover_account(String username,String question,String answer) {
        String password = null;
        try {
        String sql = "SELECT * FROM Users WHERE Username=? AND Answer=?";
        pst=Conn.prepareStatement(sql);
        pst.setString(1,username);
        pst.setString(2,answer);
        rs = pst.executeQuery();
        if(rs.next()) {
            password = rs.getString("Password");
        }
        }catch(Exception e) {
            System.out.println("e = " + e);
        }
        return password;
        
    }


}
