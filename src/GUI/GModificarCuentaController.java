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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class GModificarCuentaController implements Initializable {

    @FXML
    private Button btnSolicitarCodigo;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private TextField txfNombre;
    @FXML
    private TextField txfCorreoElectronico;
    @FXML
    private PasswordField pfContrasenia;
    @FXML
    private PasswordField pfConfContraseniaConf;
    @FXML
    private TextField txfCodigoVerificacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     


    @FXML
    private void clicRegresar(ActionEvent event) {
        cambiarVentana("/GUI/FXMLInicioUsuarioProductos.fxml");
    }

    @FXML
    private void clicCrearCuenta(ActionEvent event) {
        cambiarVentana("/GUI/FXMLInicioUsuarioProductos.fxml");
    }
    
     private void cambiarVentana(String ruta){
        try {
            Stage stage = (Stage) btnRegresar.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource(ruta)));
            stage.setScene(scenePrincipal);
            stage.setTitle("Productos");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
   private boolean existenCamposPrimariosInvalidos(){
        boolean existen = false;  
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        
        if(txfNombre.getText().isEmpty() || txfCorreoElectronico.getText().isEmpty() || pfContrasenia.getText().isEmpty() ){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        
        if(validacion.existeCampoInvalido(txfNombre.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }
        
        if(validacion.existeCorreoInvalido(txfCorreoElectronico.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }
        
        if(validacion.existeContraseniaInvalida(pfContrasenia.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Contraseña inválida \n"
                           + "La contraseña debe tener por lo menos un digito \n"
                                 + "La contraseña debe tener por lo menos una letra mayúscula \n"
                                 + "La contraseña debe tener por lo menos una letra minúscula");
        }
        return existen;
    }

    @FXML
    private void clicSolicitarCodigo(ActionEvent event) {
    }
}
