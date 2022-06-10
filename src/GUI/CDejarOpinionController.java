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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */

public class CDejarOpinionController implements Initializable {

    @FXML
    private ToggleGroup tgSeleccion;
    @FXML
    private Button btnAgregarImagen;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnAceptar;
    @FXML
    private TextArea txaOpinion;
    @FXML
    private TextArea txaDescripcionProducto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicAgregarImagen(ActionEvent event) {
        cambiarVentana("/GUI/FXMLInicioUsuarioProductos.fxml");
    }

    @FXML
    private void clicRegresar(ActionEvent event) {
        cambiarVentana("/GUI/FXMLInicioUsuarioProductos.fxml");
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
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
}
