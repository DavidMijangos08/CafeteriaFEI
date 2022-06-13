/********************************************************************/
/* Archivo: MensajeAlerta.java                                      */
/* Programador: Eder Ivan                                           */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  30/May/2022                                 */
/* Descripción: Archivo donde se inicializa los mensajes de alerta  */
/********************************************************************/

package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

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
        alert.setContentText(mensaje + " ha sido guardado con exito");
        alert.showAndWait();
    }
     
    @FXML
    public  void mostrarAlertaError(String mensaje){    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
