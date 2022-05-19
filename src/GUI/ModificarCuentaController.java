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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ModificarCuentaController implements Initializable {

    @FXML
    private PasswordField pfContrasenia;
    @FXML
    private TextField txfNombre;
    @FXML
    private TextField txfCorreoElectronico;
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
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
    }

    @FXML
    private void clicSolicitarCodigo(ActionEvent event) {
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
    
}
