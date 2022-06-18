/********************************************************************/
/* Archivo: ADAltaCafeteriaController.java                          */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Alta de cafeteria"                                 */
/********************************************************************/

package GUI;

import Dominio.Administrador;
import Dominio.Cafeteria;
import Servicios.ServicioCafeteria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ADAltaCafeteriaController implements Initializable {

    @FXML
    private TextField txfNombreCafeteria;
    @FXML
    private ComboBox<String> cbFacultad;
    @FXML
    private TextField txfDireccion;
    @FXML
    private ComboBox<String> cbHoraInicio;
    @FXML
    private ComboBox<String> cbMinutoInicio;
    @FXML
    private ComboBox<String> cbHoraFin;
    @FXML
    private ComboBox<String> cbMinutoFin;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRegistrar;
    
    private Administrador administrador = new Administrador();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxHoras();
        llenarComboBoxFacultades();
    }    
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        regresarVentana();
    }

    @FXML
    private void clicRegistrar(ActionEvent event) { 
        if(!existenCamposInvalidos()){
            try {
                String nombreCafeteria = txfNombreCafeteria.getText();
                String facultadPerteneciente = cbFacultad.getValue();
                String direccion = txfDireccion.getText();
                String horaInicio = cbHoraInicio.getValue() + ":" + cbMinutoInicio.getValue();
                String horaFin = cbHoraFin.getValue() + ":" + cbMinutoFin.getValue();
                boolean activa = true;
                Cafeteria cafeteria = new Cafeteria(nombreCafeteria, facultadPerteneciente, direccion, horaInicio, horaFin, activa);
                ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
                int respuesta = servicioCafeteria.agregarNuevaCafeteria(cafeteria);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                if(respuesta == 201){
                    mensajeAlerta.mostrarAlertaGuardado("La cafetería se registró con éxito");
                }else if(respuesta == 400){
                    mensajeAlerta.mostrarAlertaInformacionInvalida("Datos existentes, verifica el nombre de la cafetería");
                }
            } catch (IOException ex) {
                Logger.getLogger(ADAltaCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                cerrarVentana();
            }
        }
    }
    
    private void llenarComboBoxHoras(){
        ObservableList<String> listaHoras = FXCollections.observableArrayList("06","07","08","09","10", "11","12","13","14","15","16","17",
                                                                             "18","19","20", "21");  
        ObservableList<String> listaMinutos = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55","59");
        cbHoraInicio.setItems(listaHoras);
        cbHoraFin.setItems(listaHoras);
        cbMinutoInicio.setItems(listaMinutos);
        cbMinutoFin.setItems(listaMinutos);
    }
    
    private void llenarComboBoxFacultades(){
        ObservableList<String> facultades = FXCollections.observableArrayList("Facultad de Estadística e Informática", 
                                                                              "Facultad de Medicina", 
                                                                              "Facultad de Derecho",
                                                                              "Facultad de Humanidades",
                                                                              "Facultad de Ciencias Administrativas y Sociales");
        cbFacultad.setItems(facultades);
    }
    
    private boolean existenCamposInvalidos(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();

        if(txfNombreCafeteria.getText().isEmpty() || txfDireccion.getText().isEmpty() || cbFacultad.getValue() == null || 
           cbHoraInicio.getValue() == null || cbMinutoInicio.getValue() == null ||
           cbHoraFin.getValue() == null || cbMinutoFin.getValue() == null){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        } 

        if(!existe && (validacion.existeCampoInvalido(txfNombreCafeteria.getText()) || validacion.existeCampoInvalido(txfDireccion.getText()))){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }

        if(! existe && (validacion.existeHoraInvalida(cbHoraInicio.getSelectionModel().getSelectedItem(), cbHoraFin.getSelectionModel().getSelectedItem()))){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("La hora de inicio no puede ser mayor a la hora de fin");
        }else if(! existe && (validacion.existenHorasIguales(cbHoraInicio.getSelectionModel().getSelectedItem(), cbHoraFin.getSelectionModel().getSelectedItem()))){
            if(validacion.existeHoraInvalida(cbMinutoInicio.getSelectionModel().getSelectedItem(), cbMinutoFin.getSelectionModel().getSelectedItem())){
                existe = true;
                mensajeAlerta.mostrarAlertaInformacionInvalida("La hora de inicio no puede ser mayor a la hora de fin");
            }
        }else if(! existe && (validacion.existenHorasIguales(cbHoraInicio.getSelectionModel().getSelectedItem(), cbHoraFin.getSelectionModel().getSelectedItem())) &&
                 validacion.existenHorasIguales(cbMinutoInicio.getSelectionModel().getSelectedItem(), cbMinutoFin.getSelectionModel().getSelectedItem())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("La hora de inicio no puede ser igual a la hora de fin");
        }

        return existe;
    }
    
    public void recibirParametros(Administrador a){
        this.administrador = a;
    }
    private void regresarVentana(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ADInicio.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            ADInicioController controlador = (ADInicioController) fxmlLoader.getController();
            controlador.recibirParametros(administrador);
            cerrarVentana();
            stage.setTitle("Cafeterías UV");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void cerrarVentana(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
