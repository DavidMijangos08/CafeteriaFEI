/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField txfCorreoInicio;
    @FXML
    private PasswordField pfContraseniaInicio;
    @FXML
    private TextField txfNombreCrear;
    @FXML
    private TextField txfCorreoCrear;
    @FXML
    private PasswordField pfContraseniaCrear;
    @FXML
    private PasswordField pfConfContraseniaCrear;
    @FXML
    private TextField pfCodigoCrear;
    @FXML
    private RadioButton rbtTerminos;
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private Button btnIniciarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLEleccionUsuario.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void clicEnviarCodigo(ActionEvent event) {
    }

    @FXML
    private void clicCrearCuenta(ActionEvent event) throws MalformedURLException, IOException {
        
    }
    
    private boolean existenCamposVaciosInicioSesion(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if(txfCorreoInicio.getText().isEmpty()|| pfContraseniaInicio.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        return existe;
    }
    
    private boolean existenCrearCuenta(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if(txfNombreCrear.getText().isEmpty()|| txfCorreoCrear.getText().isEmpty() || pfContraseniaCrear.getText().isEmpty() || pfConfContraseniaCrear.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        return existe;
    }
    
    //Validar campos invalidos
}
