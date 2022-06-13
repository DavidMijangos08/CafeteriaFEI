/********************************************************************/
/* Archivo: ADAltaCafeteriaController.java                          */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Alta de cafeteria"                                 */
/********************************************************************/

package GUI;

import Dominio.Cafeteria;
import Servicios.ServicioCafeteria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxHoras();
        llenarComboBoxFacultades();
    }    
    
    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicRegistrar(ActionEvent event) { 
        if(!existenCamposInvalidos()){
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
                //REGRESA A LA VENTANA ANTERIOR
            }else if(respuesta == 400){
                mensajeAlerta.mostrarAlertaInformacionInvalida("Datos existentes, verifica el nombre de la cafetería");
            }else if(respuesta >= 500){
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
    
    private void cerrarVentana(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
