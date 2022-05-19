/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author david
 */
public class InicioUsuarioController implements Initializable {

    @FXML
    private ComboBox<String> cbFacultad; //CAMBIAR ACA AL DOMINIO CUANDO SE CREE
    @FXML
    private ComboBox<String> cbCafeteria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIr(ActionEvent event) {
    }

    @FXML
    private void clicCerrarSesion(ActionEvent event) {
    }
    
    private boolean existenCamposVacios(){
        boolean existen = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        
        //VERIFICARÁ QUE SEA NULL CUANDO SE PONGAN LOS DOMINIOS
        if(cbFacultad.getSelectionModel().getSelectedItem().isEmpty() || cbCafeteria.getSelectionModel().getSelectedItem().isEmpty()){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        
        return existen;
    }
    
}
