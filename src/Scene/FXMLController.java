/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import newpackage.sql.Juego;

/**
 * FXML Controller class
 *
 * @author PC02
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    

    private EntityManager entityManager;
    @FXML
    private TableView<Juego> tabla;
    @FXML
    private TableColumn<Juego, String> nombre;
    @FXML
    private TableColumn<Juego, String> genero;
    @FXML
    private TableColumn<Juego, String> codigo;
    @FXML
    private TableColumn<Juego, String> Codigo_Desarrolladora;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldGenero;
    
    private Juego juegoSeleccionado;
    @FXML
    private AnchorPane rootJuegosView;
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
    codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
//    Codigo_Desarrolladora.setCellValueFactory(new PropertyValueFactory<>("codigoDesarrolladora")); 
    
    Codigo_Desarrolladora.setCellValueFactory(
    cellData -> {
        SimpleStringProperty property = new SimpleStringProperty();
        if (cellData.getValue().getCodigoDesarrolladora() != null) {
            property.setValue(cellData.getValue().getCodigoDesarrolladora().getNombre());
        }
        return property;
    });
    tabla.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            juegoSeleccionado = newValue;
            if (juegoSeleccionado != null) {
                textFieldNombre.setText(juegoSeleccionado.getNombre());
                textFieldGenero.setText(juegoSeleccionado.getGenero());
            } else {
                textFieldNombre.setText("");
                textFieldGenero.setText("");
            }
           
        });
    }  
    public void cargarJuego() {
    Query queryJuegoFindAll = entityManager.createNamedQuery("Juego.findAll");
    List<Juego> listJuego = queryJuegoFindAll.getResultList();
    tabla.setItems(FXCollections.observableArrayList(listJuego));
} 

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        try {  
            if (juegoSeleccionado != null) {
            juegoSeleccionado.setNombre(textFieldNombre.getText());
            juegoSeleccionado.setGenero(textFieldGenero.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(juegoSeleccionado);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tabla.getSelectionModel().getSelectedIndex();
            tabla.getItems().set(numFilaSeleccionada, juegoSeleccionado);
            TablePosition pos = new TablePosition(tabla, numFilaSeleccionada, null);
            tabla.getFocusModel().focus(pos);
            tabla.requestFocus();
        }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No hay nada que guardar");
            alert.showAndWait();
        }
        
        
    
    }

    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDetalle.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     

            // Ocultar la vista de la lista
            rootJuegosView.setVisible(false);
            FXMLDetalleController fxmlDetalleController = (FXMLDetalleController) fxmlLoader.getController();  
            fxmlDetalleController.setRootJuegosView(rootJuegosView);
            fxmlDetalleController.setTableViewPrevio(tabla);
            // Para el botón Nuevo:
            juegoSeleccionado = new Juego();
            fxmlDetalleController.setJuego(entityManager, juegoSeleccionado, true);
            fxmlDetalleController.mostrarDatos();
            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootJuegosView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionButtonEditar(ActionEvent event) {
      try {
        if(juegoSeleccionado.getId() != null) {
            try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDetalle.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     

            // Ocultar la vista de la lista
            rootJuegosView.setVisible(false);
            FXMLDetalleController fxmlDetalleController = (FXMLDetalleController) fxmlLoader.getController();  
            fxmlDetalleController.setRootJuegosView(rootJuegosView);
            fxmlDetalleController.setTableViewPrevio(tabla);
            // Para el botón Editar:
            fxmlDetalleController.setJuego(entityManager, juegoSeleccionado, false);
            fxmlDetalleController.mostrarDatos();
            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootJuegosView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
     
            ////////////////////////////////////////////////////////////////////////
            
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } else {
          try {
            // Cargar la vista de detalle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDetalle.fxml"));
            Parent rootDetalleView = fxmlLoader.load();     

            // Ocultar la vista de la lista
            rootJuegosView.setVisible(false);
            FXMLDetalleController fxmlDetalleController = (FXMLDetalleController) fxmlLoader.getController();  
            fxmlDetalleController.setRootJuegosView(rootJuegosView);
            fxmlDetalleController.setTableViewPrevio(tabla);
            // Para el botón Nuevo:
            juegoSeleccionado = new Juego();
            fxmlDetalleController.setJuego(entityManager, juegoSeleccionado, true);
            fxmlDetalleController.mostrarDatos();
            // Añadir la vista de detalle al StackPane principal para que se muestre
            StackPane rootMain = (StackPane)rootJuegosView.getScene().getRoot();
            rootMain.getChildren().add(rootDetalleView);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        }
      }catch (Exception ex) {}
    }

    @FXML
    private void onActionButtonSuprimir(ActionEvent event) {
        try {
            if(juegoSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suprimir");
            alert.setHeaderText("¿Desea suprimir el siguiente registro?");
            alert.setContentText(juegoSeleccionado.getNombre());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                entityManager.getTransaction().begin();
                entityManager.remove(juegoSeleccionado);
                entityManager.getTransaction().commit();
                tabla.getItems().remove(juegoSeleccionado);
                tabla.getFocusModel().focus(null);
                tabla.requestFocus();
            } else {
                int numFilaSeleccionada = tabla.getSelectionModel().getSelectedIndex();
                tabla.getItems().set(numFilaSeleccionada, juegoSeleccionado);
                TablePosition pos = new TablePosition(tabla, numFilaSeleccionada, null);
                tabla.getFocusModel().focus(pos);
                tabla.requestFocus();            
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Debe seleccionar un registro");
            alert.showAndWait();
        }
        
       }catch (Exception ex) {}
    }
    
}
