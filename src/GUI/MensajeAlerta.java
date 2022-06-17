/********************************************************************/
/* Archivo: MensajeAlerta.java                                      */
/* Programador: Eder Ivan                                           */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa los mensajes de alerta  */
/********************************************************************/

package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MensajeAlerta {
    
    @FXML
    public  void mostrarAlertaInformacionInvalida(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información invalida");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    @FXML
    public  void mostrarAlertaGuardado(String mensaje){    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información guardada");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
     
    @FXML
    public  void mostrarAlertaError(String mensaje){    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    @FXML
    public  void mostrarAlertaEnvioCorreo(){    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Código enviado");
        alert.setContentText("El código ha sido enviado a tu correo, revisa por favor");
        alert.showAndWait();
    }
}
