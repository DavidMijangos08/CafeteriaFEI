/********************************************************************/
/* Archivo: ADAltaResponsableController.java                        */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Alta responsable" con sus metodos                  */
/********************************************************************/

package GUI;

import Dominio.Administrador;
import Dominio.Cafeteria;
import Dominio.PersonalCafeteria;
import Servicios.ServicioCafeteria;
import Servicios.ServicioPersonalCafeteria;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ADAltaResponsableController implements Initializable {

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
    @FXML
    private Button btnRegistrar;
    
    private Administrador administrador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxCafeterias();
    }    

    @FXML
    private void clicCancelar(ActionEvent event) {
        regresarVentana();
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
                    regresarVentana();
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
