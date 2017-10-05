/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Controllers;

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josip
 */
public class DefaultController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }  
    @FXML
    public void issueBookAction(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/app/Views/Issue.fxml")); //postavljamo main view u parent element
        Scene home_page_scene = new Scene(home_page_parent); //kreiramo novu scenu i prosljedujemo joj view
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_page_scene.getStylesheets().add(getClass().getResource("/app/Styles/issue.css").toExternalForm());
        app_stage.setScene(home_page_scene);
        app_stage.show(); 
    }
    
    
    @FXML
    public void returnAction (ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/app/Views/ReturnBook.fxml")); //postavljamo main view u parent element
        Scene home_page_scene = new Scene(home_page_parent); //kreiramo novu scenu i prosljedujemo joj view
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_page_scene.getStylesheets().add(getClass().getResource("/app/Styles/returnbook.css").toExternalForm());
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    public void newStudentAction (ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/app/Views/NewStudent.fxml")); //postavljamo main view u parent element
        Scene home_page_scene = new Scene(home_page_parent); //kreiramo novu scenu i prosljedujemo joj view
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_page_scene.getStylesheets().add(getClass().getResource("/app/Styles/newstudent.css").toExternalForm());
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    
    @FXML
    public void manageBooksAction (ActionEvent event) throws IOException{
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/app/Views/NewBook.fxml")); //postavljamo main view u parent element
        Scene home_page_scene = new Scene(home_page_parent); //kreiramo novu scenu i prosljedujemo joj view
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_page_scene.getStylesheets().add(getClass().getResource("/app/Styles/newbook.css").toExternalForm());
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    
}
