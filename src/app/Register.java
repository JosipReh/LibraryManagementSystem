/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Josip
 */
public class Register extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        
        Parent root = FXMLLoader.load(Register.class.getResource("/app/Views/LRView.fxml"));
        
        Scene scene = new Scene(root);
        URL url = this.getClass().getResource("/app/Styles/LRStyle.css");
            if (url == null) {
                System.out.println("Resource not found. Aborting.");
                System.exit(-1);
            }else {
                System.out.println("CSS file is loaded url: ( " + url+" )");
            }
        String css = url.toExternalForm(); 
        scene.getStylesheets().add(css);
        // set icon
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/app/images/appicon.png")));
        //Program Title
        primaryStage.setTitle("Library Management System");
        

        
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
