/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Controllers;

import app.Models.IssueModel;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josip
 */
public class IssueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private final IssueModel issueModel = new IssueModel();
    
    @FXML private TextField book_id_input;
    @FXML private TextField book_name_input;
    @FXML private TextField book_edition_input;
    @FXML private TextField book_publisher_input;
    @FXML private TextField book_price_input;
    @FXML private TextField book_pages_input;
    
    @FXML private TextField student_id_input;
    @FXML private TextField student_name_input;
    @FXML private TextField student_lastname_input;
    @FXML private TextField student_course_input;
    @FXML private TextField student_year_input;
    @FXML private TextField student_semester_input;
    
    
    @FXML private DatePicker issue_date_input;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
        
    @FXML
    public void backToHome (ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/app/Views/Default.fxml")); //postavljamo main view u parent element
        Scene home_page_scene = new Scene(home_page_parent); //kreiramo novu scenu i prosljedujemo joj view
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_page_scene.getStylesheets().add(getClass().getResource("/app/Styles/default.css").toExternalForm());
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    @FXML
    public void issueBookAction (ActionEvent event) {

        
        
        if (book_id_input.getText() == null || book_id_input.getText().trim().isEmpty() || student_id_input.getText() == null || student_id_input.getText().trim().isEmpty()) {
            System.out.println("Enter both ids");
        }else {
            String book_id = book_id_input.getText();
            String book_name = book_name_input.getText();
            String book_edition = book_edition_input.getText();
            String book_publisher = book_publisher_input.getText();
            String book_price = book_price_input.getText();
            String book_pages = book_pages_input.getText();

            String student_id = student_id_input.getText();
            String student_name = student_name_input.getText();
            String student_lastname = student_lastname_input.getText();
            String student_course = student_course_input.getText();
            String student_year = student_year_input.getText();
            String student_semester = student_semester_input.getText();


            LocalDate localDate = issue_date_input.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));

            String date_of_issue = instant.toString();
            
            
            issueModel.issueBook(book_id, book_name, book_edition, book_publisher, book_price, book_pages, student_id, student_name, student_lastname, student_course, student_year, student_semester, date_of_issue);
            
            //dialog box
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Posudba");
            alert.setHeaderText("Knjiga ("+book_name+") je posuđena studentu ("+student_name+")");
            alert.setContentText("ID Knjige: "+book_id+"\n"+
                                 "Ime Knjige: "+book_name+"\n"+
                                 "Izdanje Knjige: "+book_edition+"\n"+
                                 "Izdavač Knjige: "+book_publisher+"\n"+
                                 "Cijena Knjige: "+book_price+"\n"+
                                 "Broj stranica knjige: "+book_price+"\n"+
                                 "\n"+
                                 "ID Studenta: "+student_id+"\n"+
                                 "Ime Studenta: "+student_name+"\n"+
                                 "Prezime Studenta: "+student_lastname+"\n"+
                                 "Smjer Studenta: "+student_course+"\n"+
                                 "Godina studenta: "+student_year+"\n"+
                                 "Semestar"+student_semester+"\n"+
                                 "\n"+
                                 "Datum posudbe: "+date_of_issue);
            alert.showAndWait();
        }
        
        
        
        

                
        
    }
    
    @FXML 
    public void searchForBook (ActionEvent event) {
        
        String book_id = book_id_input.getText();
        
        ArrayList<String> books = new ArrayList<String>();
        books = issueModel.BookSearch(book_id);
        
        System.out.println("books = " + books);
        
        book_name_input.setText(books.get(0));
        book_edition_input.setText(books.get(1));
        book_publisher_input.setText(books.get(2));
        book_price_input.setText(books.get(3));
        book_pages_input.setText(books.get(4));
        
        
    }
    
    @FXML 
    public void searchForStudent (ActionEvent event) {
        
        String student_id = student_id_input.getText();
        
        ArrayList<String> students = new ArrayList<String>();
        
        students = issueModel.StudentSearch(student_id);
        
        
        student_name_input.setText(students.get(0));
        student_lastname_input.setText(students.get(1));
        student_course_input.setText(students.get(2));
        student_year_input.setText(students.get(3));
        student_semester_input.setText(students.get(4));
        
        
        
    }
    
}
