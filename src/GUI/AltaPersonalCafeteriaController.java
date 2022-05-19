/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author david
 */
public class AltaPersonalCafeteriaController implements Initializable {

    @FXML
    private TableColumn<?, ?> columnaNombre;
    @FXML
    private TableColumn<?, ?> columnaCURP;
    @FXML
    private TableColumn<?, ?> columnaCorreo;
    @FXML
    private TableColumn<?, ?> columnaCargo;
    @FXML
    private ComboBox<String> cbCargo;
    @FXML
    private TableView<?> tablaPersonal;
    @FXML
    private TextField txfNombre;
    @FXML
    private TextField txfCURP;
    @FXML
    private TextField txfCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listaCargos = FXCollections.observableArrayList("Recepcionista");
        cbCargo.setItems(listaCargos);
    }    

    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
    }

    @FXML
    private void clicAñadir(ActionEvent event) {
    }

    @FXML
    private void clicX(ActionEvent event) {
    }
    
    private boolean existenCamposInvalidos(){
        boolean existen = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        
        if(txfNombre.getText().isEmpty() || txfCURP.getText().isEmpty() || txfCorreo.getText().isEmpty() || cbCargo.getSelectionModel().getSelectedItem().isEmpty()){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        
        if(validacion.existeCampoInvalido(txfNombre.getText()) || validacion.existeCampoInvalido(txfCURP.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }
        
        if(validacion.existeCorreoInvalido(txfCorreo.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }
        
        return existen;
    }
    
}
