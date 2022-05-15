/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author david
 */
public class AltaCafeteriaController implements Initializable {

    @FXML
    private TextField tfNombreCafeteria;
    @FXML
    private TextField tfDireccion;
    @FXML
    private ComboBox<String> cbFacultad;
    @FXML
    private ComboBox<String> cbHoraInicio;
    @FXML
    private ComboBox<String> cbMinutoInicio;
    @FXML
    private ComboBox<String> cbHoraFin;
    @FXML
    private ComboBox<String> cbMinutoFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCombosHora();
    }   
    
    private void llenarCombosHora(){
        ObservableList<String> listaHoras = FXCollections.observableArrayList("07","08","09","10", "11","12","13","14","15","16","17",
                                                                             "18","19","20", "21");  
        ObservableList<String> listaMinutos = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55","59");
        cbHoraInicio.setItems(listaHoras);
        cbHoraFin.setItems(listaHoras);
        cbMinutoInicio.setItems(listaMinutos);
        cbMinutoFin.setItems(listaMinutos);
    }
    
}
