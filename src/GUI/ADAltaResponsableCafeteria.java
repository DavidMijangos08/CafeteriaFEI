/********************************************************************/
/* Archivo: ADAltaResponsableController.java                        */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Alta responsable" con sus metodos                  */
/********************************************************************/

package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ADAltaResponsableCafeteria implements Initializable {

    @FXML
    private ComboBox<String> cbCafeteria;
    @FXML
    private TextField txfNombre;
    @FXML
    private TextField txfCorreo;
    @FXML
    private TextField txfCurp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private boolean existenCamposInvalidos(){
        boolean existen = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        
        if(cbCafeteria.getSelectionModel().getSelectedItem().isEmpty() || txfNombre.getText().isEmpty() ||
           txfCorreo.getText().isEmpty() || txfCurp.getText().isEmpty()){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        
        if(validacion.existeCampoInvalido(txfNombre.getText()) || validacion.existeCampoInvalido(txfCurp.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }
        
        if(validacion.existeCorreoInvalido(txfCorreo.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }
        
        return existen;
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
    }
}
