/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 *
 * @author david
 */
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
    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información guardada");
        alert.setContentText(mensaje + " ha sido guardado con exito");
        alert.showAndWait();
    }
}
