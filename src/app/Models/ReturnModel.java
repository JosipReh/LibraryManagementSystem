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
import java.util.ArrayList;

/**
 *
 * @author Josip
 */
public class ReturnModel {
    Connection Conn = DBConn.Connector();
    ResultSet rs;
    PreparedStatement pst;
    
    public ArrayList<String> StudentSearch(String student_id) {
        ArrayList<String> Result = new ArrayList<String>();
        try {
            String sql = "SELECT * FROM Issue WHERE Student_ID =?";
            pst=Conn.prepareStatement(sql);
            pst.setString(1, student_id);
            rs = pst.executeQuery();
            if(rs.next()) {
                
                Result.add(rs.getString("Student_Name"));
                Result.add(rs.getString("Student_Lastname"));
                Result.add(rs.getString("Student_Course"));
                Result.add(rs.getString("Student_Year"));
                Result.add(rs.getString("Student_Semester"));
                Result.add(rs.getString("Book_ID"));
                Result.add(rs.getString("Book_Name"));
                Result.add(rs.getString("Book_Edition"));
                Result.add(rs.getString("Book_Publisher"));
                Result.add(rs.getString("Book_Price"));
                Result.add(rs.getString("Book_Pages"));
                Result.add(rs.getString("Date_Of_Issue"));
                
                
            /*
            String book_name = rs.getString("Book_Name");
            String book_edition = rs.getString("Book_Edition");
            String book_publisher = rs.getString("Book_Publisher");
            String book_price = rs.getString("Book_Price");
            String book_pages = rs.getString("Book_Pages");
                    
                    */
                
            pst.close();
            rs.close();
            }

            
            
           
            
        }catch(Exception e) {
            System.out.println("e = " + e);
        }
        
        
        return Result;
    }
    
    public void deleteIssue(String id) {
        try {
            String sql = "DELETE FROM Issue WHERE Student_ID =?";
            pst=Conn.prepareStatement(sql);
            pst.setString(1,id);
            pst.execute();
            
        }catch(Exception e) {
        System.out.println("this = " + this);
        }
    }
    
    public void updateReturn(String book_id,String book_name,String book_edition,String book_publisher,String book_price,
            String book_pages,String student_id,String student_name,String student_lastname,String student_course,
            String student_year,String student_semester,String return_date) {
        
        try {
            String sql = "INSERT INTO Return (Book_ID,Book_Name,Book_Edition,Book_Publisher,Book_Price,Book_Pages,"
                    + "Student_ID,Student_Name,Student_Lastname,Student_Course,Student_Year,Student_Semester,Return_Date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=Conn.prepareStatement(sql);
            pst.setString(1,book_id);
            pst.setString(2,book_name);
            pst.setString(3,book_edition);
            pst.setString(4,book_publisher);
            pst.setString(5,book_price);
            pst.setString(6,book_pages);
            pst.setString(7,student_id);
            pst.setString(8,student_name);
            pst.setString(9,student_lastname);
            pst.setString(10,student_course);
            pst.setString(11,student_year);
            pst.setString(12,student_semester);
            pst.setString(13,return_date);
            pst.execute();
            pst.close();
            
            
        }catch(Exception e) {
            System.out.println("e = " + e);
        }
    
    }
    
}
