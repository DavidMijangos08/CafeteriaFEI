/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.text.html.ImageView;

/**
 * FXML Controller class
 *
 * @author eder_
 */
public class AgregarProductoController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField txfNombreProducto;
    @FXML
    private TextField txfPrecio;
    @FXML
    private TextField txfTiempoAproximado;
    @FXML
    private TextField txfDescripcion;
    @FXML
    private ImageView imgProducto;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicAgregar(ActionEvent event) {
    }

    @FXML
    private void clicElegirImagen(ActionEvent event) {
    }
    
    private boolean existenCamposInvalidos(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        
        if(txfNombreProducto.getText().isEmpty() || txfPrecio.getText().isEmpty() || txfTiempoAproximado.getText().isEmpty() || txfDescripcion.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        } 
        
        if(validacion.existeCampoInvalido(txfNombreProducto.getText()) || validacion.existeCampoInvalido(txfPrecio.getText()) 
                || validacion.existeCampoInvalido(txfTiempoAproximado.getText()) || validacion.existeCampoInvalido(txfDescripcion.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }
        
        return existe;
    }
    
}
