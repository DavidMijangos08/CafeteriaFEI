/********************************************************************/
/* Archivo: RCModificarProductoController.java                      */
/* Programador: Eder Ivan                                           */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Modificar producto" con sus metodos                */
/********************************************************************/

package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RCModificarProductoController implements Initializable {

    @FXML
    private TextField txfPrecio;
    @FXML
    private TextArea txfDescripcion;
    @FXML
    private Button btnAñadirImagen;
    @FXML
    private ImageView imgProducto;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;
    @FXML
    private TextField txfNombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicAñadirImagen(ActionEvent event) {
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
    }
    
    private boolean existenCamposInvalidos(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        
        if(txfNombre.getText().isEmpty() || txfPrecio.getText().isEmpty() || txfDescripcion.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        } 
        
        if(validacion.existeCampoInvalido(txfNombre.getText()) || validacion.existeCampoInvalido(txfPrecio.getText()) 
                || validacion.existeCampoInvalido(txfDescripcion.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }
        
        return existe;
    }
}
