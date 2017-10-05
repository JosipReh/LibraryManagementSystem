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

/**
 *
 * @author Josip
 */
public class BookModel {
    Connection Conn = DBConn.Connector();
    ResultSet rs;
    PreparedStatement pst;
    
    public Integer getLastId() {
        int lastID = 0;
        try {
            String sql = "SELECT MAX(Book_ID) FROM Book";
            pst=Conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()) {
                lastID = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("lastID = " + lastID);
        if (lastID == 0) {
            lastID = 1;
        }else {
            lastID += 1;
        }
                
        return lastID;
      
    }
    
    public void addNewBook(String id, String name,String edition,String publisher,String price,String pages) {
        try {
            String sql = "INSERT INTO Book (Book_ID,Book_Name,Book_Edition,Book_Publisher,Book_Price,Book_Pages) VALUES (?,?,?,?,?,?)";
            pst=Conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, edition);
            pst.setString(4, publisher);
            pst.setString(5, price);
            pst.setString(6, pages);
            pst.execute();
            pst.close();
            
            
        }catch(Exception e) {
            System.out.println("e = " + e);
        }
        
    }

}
