/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;





    public class NewFXMain extends Application {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    @Override
    public void start(Stage primaryStage) {
        StackPane rootMain = new StackPane();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML.fxml"));
        Pane rootJuegosView;
       
            
        try {
            rootJuegosView = fxmlLoader.load();
            rootMain.getChildren().add(rootJuegosView);
            Scene scene = new Scene(rootMain, 650, 400);
            primaryStage.setScene(scene);
            emf = Persistence.createEntityManagerFactory("zonalerosPU");
            em = emf.createEntityManager();
            
            FXMLController fxmlController = (FXMLController) fxmlLoader.getController(); 
            fxmlController.setEntityManager(em);
            fxmlController.cargarJuego();
        } catch (IOException ex) {
            
        }
            primaryStage.setTitle("JUEGOS");
            primaryStage.show();
    }
    
    @Override
    public void stop() throws Exception {
        em.close(); 
        emf.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:database;create=true");
            
        } catch (SQLException ex) { 
        }        
    }
    
    public static void main(String[] args) {
    launch(args);
    }
    
}  
     
