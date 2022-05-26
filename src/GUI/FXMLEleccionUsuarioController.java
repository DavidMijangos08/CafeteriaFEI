/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class FXMLEleccionUsuarioController implements Initializable {

    @FXML
    private ComboBox<String> cbFacultad;
    @FXML
    private ComboBox<String> cbCafeteria;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnContinuar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicCerrarSesion(ActionEvent event) throws IOException {
        cambiarVentana("/GUI/FXMLInicioSesion.fxml", "Inicio de Sesión");
    }

    @FXML
    private void clicContinuar(ActionEvent event) {
        cambiarVentana("/GUI/FXMLInicioUsuario.fxml", "Productos");
    }
    
    private void cambiarVentana(String ruta, String titulo){
        try {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource(ruta)));
            stage.setScene(scenePrincipal);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
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
