/********************************************************************/
/* Archivo: ADAltaResponsableController.java                        */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Alta responsable" con sus metodos                  */
/********************************************************************/

package GUI;

import Dominio.Cafeteria;
import Dominio.PersonalCafeteria;
import Servicios.ServicioCafeteria;
import Servicios.ServicioPersonalCafeteria;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ADAltaResponsableCafeteria implements Initializable {

    @FXML
    private ComboBox<Cafeteria> cbCafeteria;
    @FXML
    private TextField txfNombre;
    @FXML
    private TextField txfCorreo;
    @FXML
    private TextField txfCurp;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxCafeterias();
    }    

    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicRegistrar(ActionEvent event) {
        if(!existenCamposInvalidos()){
            int idCafeteria = cbCafeteria.getValue().getIdCafeteria();
            String nombre = txfNombre.getText();
            String correo = txfCorreo.getText();
            String CURP = txfCurp.getText();
            String cargo = "Responsable";
            PersonalCafeteria personal = new PersonalCafeteria(nombre, CURP, correo, cargo, idCafeteria);
            personal.setContrasenia(CURP);
            ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
            try{
                int respuesta = servicioPersonalCafeteria.agregarNuevoPersonalCafeteria(idCafeteria, personal);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                if(respuesta == 201){
                    mensajeAlerta.mostrarAlertaGuardado("El responsable de la cafetería " + cbCafeteria.getValue().getNombreCafeteria() + " se registró con éxito");
                    //REGRESA A LA VENTANA ANTERIOR
                }else if(respuesta == 400){
                    mensajeAlerta.mostrarAlertaInformacionInvalida("Datos existentes, verifica que el nombre, CURP o correo electrónico no estén registrados");
                }
            }catch(IOException ex){
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                cerrarVentana();
            }
        }
    }
    
    private void llenarComboBoxCafeterias(){
        try {
            ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
            ArrayList<Cafeteria> cafeterias = servicioCafeteria.obtenerTodasLasCafeterias();
            ObservableList<Cafeteria> listaCafeterias = FXCollections.observableArrayList(cafeterias);
            cbCafeteria.setItems(listaCafeterias);
        } catch (IOException ex) {
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentana();
        }
    }
    
    private boolean existenCamposInvalidos(){
        boolean existen = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        
        if(cbCafeteria.getValue() == null || txfNombre.getText().isEmpty() ||
           txfCorreo.getText().isEmpty() || txfCurp.getText().isEmpty()){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        
        if(!existen && validacion.existeCampoInvalido(txfNombre.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el nombre");
        }
        
        if(!existen && validacion.existeCurpInvalida(txfCurp.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en la CURP");
        }
        
        if(!existen && validacion.existeCorreoInvalido(txfCorreo.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }
        
        return existen;
    }
    
    private void cerrarVentana(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}
