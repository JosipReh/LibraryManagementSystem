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
public class StudentModel {
    Connection Conn = DBConn.Connector();
    ResultSet rs;
    PreparedStatement pst;
    
    
    public Integer getLastId() {
        int lastID = 0;
        try {
            String sql = "SELECT MAX(Student_ID) FROM Student";
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
    
    public void registerStudent(String id,String name,String lastname,String course,String year,String semester) {
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("lastname = " + lastname);
        System.out.println("course = " + course);
        System.out.println("year = " + year);
        System.out.println("semester = " + semester);
        try {
            String sql = "INSERT INTO Student (Student_ID,Student_Name,Student_Lastname,Student_Course,Student_Year,Student_Semester) VALUES (?,?,?,?,?,?)";
            pst=Conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, lastname);
            pst.setString(4, course);
            pst.setString(5, year);
            pst.setString(6, semester);
            pst.execute();
            pst.close();
            
            
        }catch(Exception e) {
            System.out.println("e = " + e);
        }
        
    }
    
}
