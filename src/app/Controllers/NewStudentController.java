/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Controllers;

import app.Models.StudentModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josip
 */
public class NewStudentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField student_id_input;
    @FXML private TextField student_name_input;
    @FXML private TextField student_lastname_input;
    @FXML private ComboBox student_course_input;
    @FXML private ComboBox student_year_input;
    @FXML private ComboBox student_semester_input;
    
    
    private final StudentModel studentModel = new StudentModel();
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        student_course_input.getItems().addAll(
            "Informatika",
            "Računarstvo",
            "Elektrotehnika"
        );
        student_course_input.getSelectionModel().select("Informatika");
        
        student_year_input.getItems().addAll(
                 "1",
                 "2",
                 "3",
                 "4"
        );
        student_year_input.getSelectionModel().select("1");
        
        student_semester_input.getItems().addAll(
                "1",
                "2"
        );
        student_semester_input.getSelectionModel().select("1");
        
        int lastId = studentModel.getLastId();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(lastId);
        String lastIdString = sb.toString();
        
        
        student_id_input.setText(lastIdString);
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
    public void registerAction (ActionEvent event) {
        
        String student_id = student_id_input.getText();
        String student_name = student_name_input.getText();
        String student_lastname = student_lastname_input.getText();
        
        
        String student_course = student_course_input.getSelectionModel().getSelectedItem().toString();

        String student_year= student_year_input.getSelectionModel().getSelectedItem().toString();
        
        String student_semester = student_semester_input.getSelectionModel().getSelectedItem().toString();
        
        studentModel.registerStudent(student_id, student_name, student_lastname, student_course, student_year, student_year);
        
        
        //add dialog box
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dodavanje Studenata");
            alert.setHeaderText("Student ("+student_name+") je uspješno dodan u bazu podataka");
            alert.setContentText(
                                 "ID Studenta: "+student_id+"\n"+
                                 "Ime Studenta: "+student_name+"\n"+
                                 "Prezime Studenta: "+student_lastname+"\n"+
                                 "Smjer Studenta: "+student_course+"\n"+
                                 "Godina studenta: "+student_year+"\n"+
                                 "Semestar: "+student_semester+"\n");
            alert.showAndWait();
    }
    
}
