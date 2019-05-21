/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scene;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import newpackage.sql.Desarrolladora;
import newpackage.sql.Juego;

/**
 * FXML Controller class
 *
 * @author PC02
 */
public class FXMLDetalleController implements Initializable {
    
    private Pane rootJuegosView;
    private TableView tableViewPrevio;
    private Juego juego;
    private EntityManager entityManager;
    private Character Stock;
    private boolean nuevoJuego;
    public static final char SI = 'S';
    public static final char NO = 'N';
    
    
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldGenero;
    @FXML
    private TextField textFieldCodigo;
    
    @FXML
    private TextField textFieldNumDesarrolladores;
    @FXML
    private TextField textFieldDescripcion;
    @FXML
    private TextField textFieldPresupuesto;
    @FXML
    private DatePicker datePickerFecha;
    @FXML
    private ComboBox<Desarrolladora> comboBoxCodDesarrolladora;
    @FXML
    private AnchorPane rootDetalleView;
    
    private TextField textFieldPais;
    @FXML
    private ToggleGroup estadoCivilGroup;
    @FXML
    private RadioButton radioButtonSi;
    @FXML
    private RadioButton radioButtonNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setTableViewPrevio(TableView tableViewPrevio) {
    // establece el table previo
    this.tableViewPrevio = tableViewPrevio;
    }
    
    public void setJuego(EntityManager entityManager, Juego juego, boolean nuevoJuego) {
    this.entityManager = entityManager;
    entityManager.getTransaction().begin();
    if(!nuevoJuego) {
        this.juego = entityManager.find(Juego.class, juego.getId());
    } else {
        this.juego = juego;
    }
    this.nuevoJuego = nuevoJuego;
    }
    
    public void mostrarDatos() {
    // muestra los datos de la tabla juego en los campos
    textFieldNombre.setText(juego.getNombre());
    textFieldGenero.setText(juego.getGenero());
    textFieldCodigo.setText(juego.getCodigo());
    textFieldNumDesarrolladores.setText(String.valueOf(juego.getNumDesarrolladores()));
    textFieldDescripcion.setText(juego.getDescripcion());
    textFieldPresupuesto.setText(String.valueOf(juego.getPresupuesto()));
    
     Query queryDesarrolladoraFindAll = entityManager.createNamedQuery("Desarrolladora.findAll");
        List listDesarrolladora = queryDesarrolladoraFindAll.getResultList();
     comboBoxCodDesarrolladora.setItems(FXCollections.observableList(listDesarrolladora));
    
    if (juego.getCodigoDesarrolladora() != null) {
    comboBoxCodDesarrolladora.setValue(juego.getCodigoDesarrolladora());
    }
    comboBoxCodDesarrolladora.setCellFactory((ListView<Desarrolladora> l) -> new ListCell<Desarrolladora>() {
    @Override
    protected void updateItem(Desarrolladora desarrolladora, boolean empty) {
        super.updateItem(desarrolladora, empty);
        if (desarrolladora == null || empty) {
            setText("");
        } else {
            setText(desarrolladora.getCodigoDesarrolladora() + "-" + desarrolladora.getNombre());
        }
    }
});
    comboBoxCodDesarrolladora.setConverter(new StringConverter<Desarrolladora>() {
    @Override
    public String toString(Desarrolladora desarrolladora) {
        if (desarrolladora == null) {
            return null;
        } else {
            return desarrolladora.getCodigoDesarrolladora() + "-" + desarrolladora.getNombre();
        }
    }
    @Override
    public Desarrolladora fromString(String userId) {
        return null;
    }
});
    if (juego.getFechaSalida() != null) {
    Date date = juego.getFechaSalida();
    Instant instant = date.toInstant();
    ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
    LocalDate localDate = zdt.toLocalDate();
    datePickerFecha.setValue(localDate);
}
    try {  
    if (juego.getStock()){
        radioButtonSi.setSelected(true);
    } else if (juego.getStock()==false){
        radioButtonNo.setSelected(true);
    }
    }catch (Exception e){}
    
    
}

    public void setRootJuegosView(Pane rootJuegoView) {
    //establece el root
    this.rootJuegosView = rootJuegoView;
    }

    

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        // guarda la transicion  que estemos haciendo y sale a la pagina anterior a la de nuevo
        try { 
            int numFilaSeleccionada;
            //convierte la fecha al formato 
             String fecha = (datePickerFecha.getValue().getDayOfMonth() + "/" + datePickerFecha.getValue().getMonthValue() + "/" + datePickerFecha.getValue().getYear());
            try{
                Date date = new SimpleDateFormat ("dd/MM/yy").parse(fecha);
                juego.setFechaSalida(date);

            } catch (Exception e){

            }
    //        juego.setNombre(textFieldPais.getText());
            juego.setNombre(textFieldNombre.getText());
            juego.setGenero(textFieldGenero.getText());
            juego.setCodigo(textFieldCodigo.getText());
            juego.setNumDesarrolladores(Short.valueOf(textFieldNumDesarrolladores.getText()));
            juego.setDescripcion(textFieldDescripcion.getText());
            juego.setPresupuesto(BigDecimal.valueOf(Double.valueOf(textFieldPresupuesto.getText())));
            juego.setCodigoDesarrolladora(comboBoxCodDesarrolladora.getValue());


            try {  
            if (radioButtonSi.isSelected()){
                juego.setStock(true);
            } else if (radioButtonNo.isSelected()){
                juego.setStock(false);
            }
            }catch (Exception e){}


            if(nuevoJuego) {
                entityManager.persist(juego);
            } else {
                entityManager.merge(juego);
            }



            entityManager.getTransaction().commit();

                if(nuevoJuego) {
                tableViewPrevio.getItems().add(juego);
                numFilaSeleccionada = tableViewPrevio.getItems().size() - 1;
                tableViewPrevio.getSelectionModel().select(numFilaSeleccionada);
                tableViewPrevio.scrollTo(numFilaSeleccionada);
            } else {
                numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
                tableViewPrevio.getItems().set(numFilaSeleccionada, juego);
            }
            TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
            tableViewPrevio.getFocusModel().focus(pos);
            tableViewPrevio.requestFocus();
            StackPane rootMain = (StackPane)rootJuegosView.getScene().getRoot();
            rootMain.getChildren().remove(rootDetalleView);  
            rootJuegosView.setVisible(true);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Debe rellenar los campos obligatorios antes de guardar");
            alert.showAndWait();
        }
     }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
    // cancela la transicion y sale a la pagina anterior a la de nuevo
    try {      
    
        entityManager.getTransaction().rollback();

        int numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
        TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
        tableViewPrevio.getFocusModel().focus(pos);
        tableViewPrevio.requestFocus();
    }catch (Exception e){} 
        StackPane rootMain = (StackPane)rootJuegosView.getScene().getRoot();
        rootMain.getChildren().remove(rootDetalleView);  
        rootJuegosView.setVisible(true);
    }
    
}
