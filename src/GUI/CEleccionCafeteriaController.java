/********************************************************************/
/* Archivo: CEleccionCafeteriaController.java                       */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Eleccion cafeteria" con sus metodos                */
/********************************************************************/

package GUI;

import Dominio.Cafeteria;
import Dominio.Consumidor;
import Servicios.ServicioCafeteria;
import Servicios.ServicioConsumidor;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    
    private GInicioSesionController inicioSesionController;
    private int tipoUsuario;
    private Consumidor consumidor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxFacultades();
    }

    @FXML
    private void clicCerrarSesion(ActionEvent event){
        try {
            ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
            servicioConsumidor.cerrarSesionConsumidor(consumidor.getCorreoElectronico());
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/GInicioSesion.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio sesion");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ADAltaCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentanaPorExcepcion();
        }
    }

    @FXML
    private void clicContinuar(ActionEvent event) {
        if (!existenCamposVacios()){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/GInicioProductos.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stageHijo = new Stage();
                stageHijo.setScene(scene);
                GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario, consumidor, null, cbCafeteria.getValue().getIdCafeteria());
                Stage stagePadre = (Stage) btnContinuar.getScene().getWindow();
                stagePadre.close();
                stageHijo.setTitle("Productos");
                stageHijo.setResizable(false);
                stageHijo.show();
            } catch (IOException ex) {
                Logger.getLogger(CEleccionCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
                cerrarVentanaPorExcepcion();
            }
        }
    }
    
    @FXML
    private void seleccionFacultad(ActionEvent event) {
        String facultadPerteneciente = cbFacultad.getValue();
        llenarCafeteriasDeFacultad(facultadPerteneciente);
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
        try {
            ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
            ArrayList<Cafeteria> cafeterias = servicioCafeteria.obtenerCafeteriasDeFacultad(facultadPerteneciente);
            ObservableList<Cafeteria> listaCafeterias = FXCollections.observableArrayList(cafeterias);          
            cbCafeteria.setItems(listaCafeterias);
        } catch (IOException ex) {
            Logger.getLogger(CEleccionCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentanaPorExcepcion();
        }
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
    
    private void cerrarVentana(){
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();
    }

    public void recibirParametros(int tipoUsuario1, Consumidor consumidor1){
        tipoUsuario = tipoUsuario1;
        consumidor = consumidor1;
    }
    
    private void cerrarVentanaPorExcepcion(){
        Platform.exit();
    }
}
