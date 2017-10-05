/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Controllers;

import app.Models.UserModel;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 *
 * @author Josip
 */
public class LRController implements Initializable {
    
    //REGISTER INPUT FIELDS
    @FXML private TextField firstname_Input;
    @FXML private TextField lastname_Input;
    @FXML private TextField username_Input;
    @FXML private TextField password_Input;
    @FXML private TextField answer_Input;
    @FXML private ComboBox security_q_Input;
    
    //REGISTER ALERT MESSAGES
    @FXML 
    public Label firstname_alert;
    @FXML 
    private Label lastname_alert;
    @FXML 
    private Label username_alert;
    @FXML 
    private Label password_alert;
    @FXML 
    private Label answer_alert;
    
    
    //LOGIN INPUT FIELDS
    @FXML private TextField login_username_input;
    @FXML private TextField login_password_input;
    
    //LOGIN ALERT MESSAGES
    @FXML private Label login_username_alert;
    @FXML private Label login_password_alert;
    
    //Tab selector
    @FXML private TabPane login_register_selector;
    
    
    //recovery tab
    @FXML private TextField recovery_username;
    
    @FXML private ComboBox recovery_question;
    
    @FXML private TextField recovery_answer;
    
    //Instance of userModel Object
    private final UserModel userModel = new UserModel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //combobox options
    security_q_Input.getItems().removeAll(security_q_Input.getItems());
    security_q_Input.getItems().addAll("Ime vašeg prvog kućnog ljubimca?", "Ime vaše srednje škole?", "Vaš nadimak?", "Grad u kojem su se vaši roditelji upoznali?");
    security_q_Input.getSelectionModel().select("Ime vašeg prvog kućnog ljubimca?");
    
    
    recovery_question.getItems().removeAll(security_q_Input.getItems());
    recovery_question.getItems().addAll("Ime vašeg prvog kućnog ljubimca?", "Ime vaše srednje škole?", "Vaš nadimak?", "Grad u kojem su se vaši roditelji upoznali?");
    recovery_question.getSelectionModel().select("Ime vašeg prvog kućnog ljubimca?");
    
    
    }  
    
    @FXML
    public void loginAction (ActionEvent event) throws IOException {
        String username = login_username_input.getText();
        String password = login_password_input.getText();
        
        //set all alert messages equals to null
        login_username_alert.setText(null);
        login_password_alert.setText(null);
        
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Korisničko ime nedostaje!");
            login_username_alert.setText("Molimo unesite korisničko ime!");
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Lozinka nedostaje!");
            login_password_alert.setText("Molimo unesite lozinku!");
        }
        
        if (!username.trim().isEmpty() && !password.trim().isEmpty()) {
            if(userModel.login(username,password)){

                
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/app/Views/Default.fxml")); //postavljamo main view u parent element
                Scene home_page_scene = new Scene(home_page_parent); //kreiramo novu scenu i prosljedujemo joj view
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                home_page_scene.getStylesheets().add(getClass().getResource("/app/Styles/default.css").toExternalForm());
                app_stage.setScene(home_page_scene);
                app_stage.show(); 
                       
            
            }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Greška u prijavi");
            alert.setHeaderText("Unijeli ste krivo korisničko ime i lozinku");
            alert.showAndWait();
            }
        }
        
        
        
    }
    
    @FXML
    public void registerAction (ActionEvent event) throws IOException {
        
        String firstname = firstname_Input.getText();
        String lastname = lastname_Input.getText();
        String username = username_Input.getText();
        String password = password_Input.getText();
        //String sec_question = security_q_Input.getText();
        String sec_question = (String) security_q_Input.getValue();

        String answer = answer_Input.getText();
        
        //set all alert messages equals to null
        firstname_alert.setText(null);
        lastname_alert.setText(null);
        username_alert.setText(null);
        password_alert.setText(null);
        answer_alert.setText(null);
        
        if (firstname == null || firstname.trim().isEmpty()) {
            System.out.println("Ime nedostaje!");
            firstname_alert.setText("Molimo unesite ime!");
        }
        if (lastname == null || lastname.trim().isEmpty()) {
            System.out.println("Prezime nedostaje!");
            lastname_alert.setText("Molimo unesite prezime!");
        }
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Korisničko ime nedostaje!");
            username_alert.setText("Molimo unesite korisničko ime!");
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Lozinka nedostaje!");
            password_alert.setText("Molimo unesite lozinku!");
        }
        if (answer == null || answer.trim().isEmpty()) {
            System.out.println("Nedostaje odgovor na sigurnosno pitanje");
            answer_alert.setText("Molimo odgovorite na sigurnostno pitanje");
        }

        if (!firstname.trim().isEmpty() && !lastname.trim().isEmpty() && !username.trim().isEmpty() && !password.trim().isEmpty() && !answer.trim().isEmpty()) {
            if (userModel.checkIfUserExists(username) == 0 ) {
                
                userModel.Register(firstname,lastname,username,password,sec_question,answer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Uspješna registracija");
                alert.setHeaderText("Uspješno ste se registrirali!");
                alert.showAndWait();
                
                
                //promjena taba u login
                login_register_selector.getSelectionModel().select(0);
                
                
                
            }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Greška u registraciji");
            alert.setHeaderText("Korisničko ime je već zauzeto!");
            alert.showAndWait();
            }
        }
        
       
        
    }
    
    @FXML 
    public void recoveryAction (ActionEvent event) {
        String username = recovery_username.getText();
        String recovery_q = (String) recovery_question.getValue();
        String recovery_a = recovery_answer.getText();
        
        if (username == null || username.trim().isEmpty() || recovery_q == null || recovery_q.trim().isEmpty() || recovery_a == null || recovery_a.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška prilikom obnove korisničkog računa!");
            alert.setContentText("Molimo unesite podatke u sva zadana polja.");
            alert.showAndWait();
        }else {
            String password = userModel.recover_account(username, recovery_q, recovery_a);
            if (password != null) {
            TextInputDialog dialog = new TextInputDialog(password);
            dialog.setTitle("Obnova Korisničkog računa");
            dialog.setHeaderText("");
            dialog.setContentText("Vaša izgubljena lozinka je: ");
            dialog.showAndWait();
            }else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Greška");
            alert.setHeaderText("Greška prilikom obnove korisničkog računa!");
            alert.setContentText("Podaci koje ste unijeli ne odgovaraju vašem korisničkom računu.");

            alert.showAndWait();
            }
            
        }

    }
    
}
