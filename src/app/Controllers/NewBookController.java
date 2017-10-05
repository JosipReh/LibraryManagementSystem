/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.Controllers;

import app.Models.BookModel;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josip
 */
public class NewBookController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField book_id_input;
    
    @FXML private TextField book_name_input;
    
    @FXML private ComboBox book_edition_input;
    
    @FXML private TextField book_publisher_input;
    
    @FXML private TextField book_price_input;
    
    @FXML private TextField book_pages_input;
    
    @FXML private Button addBookButton;
    
    @FXML private Button backButton;
    
    private final BookModel bookModel = new BookModel();
         
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        book_edition_input.getItems().addAll(
                 "1",
                 "2",
                 "3",
                 "4"
        );
        
        book_edition_input.getSelectionModel().select("1");
      
        int lastId = bookModel.getLastId();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(lastId);
        String lastIdString = sb.toString();
        
        
        book_id_input.setText(lastIdString);
        
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
    public void addBook(ActionEvent event) {
        System.out.println("event = " + event);
        
        String book_id = book_id_input.getText();
        String book_name = book_name_input.getText();
        String book_edition = book_edition_input.getSelectionModel().getSelectedItem().toString();
        String book_publisher = book_publisher_input.getText();
        String book_price = book_price_input.getText();
        String book_pages = book_pages_input.getText();
        
        
        bookModel.addNewBook(book_id, book_name, book_edition, book_publisher, book_price, book_pages);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dodavanje knjige");
            alert.setHeaderText("Knjiga ("+book_name+") je uspješno dodana u bazu podataka");
            alert.setContentText("ID Knjige: "+book_id+"\n"+
                                 "Ime Knjige: "+book_name+"\n"+
                                 "Izdanje Knjige: "+book_edition+"\n"+
                                 "Izdavač Knjige: "+book_publisher+"\n"+
                                 "Cijena Knjige: "+book_price+"\n"+
                                 "Broj stranica knjige: "+book_price+"\n"+
                                 "\n");
            alert.showAndWait();
        
    }
    
}
