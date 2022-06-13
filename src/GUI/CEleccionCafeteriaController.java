/********************************************************************/
/* Archivo: CEleccionCafeteriaController.java                       */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Eleccion cafeteria" con sus metodos                */
/********************************************************************/

package GUI;

import Dominio.Cafeteria;
import Servicios.ServicioCafeteria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class CEleccionCafeteriaController implements Initializable {

    @FXML
    private ComboBox<String> cbFacultad;
    @FXML
    private ComboBox<Cafeteria> cbCafeteria;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnContinuar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxFacultades();
    }    

    @FXML
    private void clicCerrarSesion(ActionEvent event) throws IOException {
        cambiarVentana("/GUI/GInicioSesion.fxml", "Inicio de Sesión");
    }

    @FXML
    private void clicContinuar(ActionEvent event) {
        cambiarVentana("/GUI/GInicioProductos.fxml", "Productos");
    }
    
    @FXML
    private void seleccionFacultad(ActionEvent event) {
        String facultadPerteneciente = cbFacultad.getValue();
        llenarCafeteriasDeFacultad(facultadPerteneciente);
    }
    
    private void cambiarVentana(String ruta, String titulo){
        try {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource(ruta)));
            stage.setScene(scenePrincipal);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private void llenarComboBoxFacultades(){
        ObservableList<String> facultades = FXCollections.observableArrayList("Facultad de Estadística e Informática", 
                                                                              "Facultad de Medicina", 
                                                                              "Facultad de Derecho",
                                                                              "Facultad de Humanidades",
                                                                              "Facultad de Ciencias Administrativas y Sociales");
        cbFacultad.setItems(facultades);
    }
    
    private void llenarCafeteriasDeFacultad(String facultadPerteneciente){
        ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
        ArrayList<Cafeteria> cafeterias = servicioCafeteria.obtenerCafeteriasDeFacultad(facultadPerteneciente);
        ObservableList<Cafeteria> listaCafeterias = FXCollections.observableArrayList(cafeterias);
        
        cbCafeteria.setItems(listaCafeterias);
    }
    
    private boolean existenCamposVacios(){
        boolean existen = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        
        if(cbFacultad.getValue() == null || cbCafeteria.getSelectionModel().getSelectedItem() == null){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        
        return existen;
    }
}
