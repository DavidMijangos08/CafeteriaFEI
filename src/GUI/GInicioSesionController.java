/********************************************************************/
/* Archivo: GInicioSesionController.java                            */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  10/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Inicio sesion" con sus metodos                     */
/********************************************************************/

package GUI;

import Dominio.Consumidor;
import Dominio.PersonalCafeteria;
import Servicios.ServicioAdministrador;
import Servicios.ServicioConsumidor;
import Servicios.ServicioPersonalCafeteria;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GInicioSesionController implements Initializable {

    @FXML
    private TextField txfCorreoInicio;
    @FXML
    private PasswordField pfContraseniaInicio;
    @FXML
    private TextField txfNombreCrear;
    @FXML
    private TextField txfCorreoCrear;
    @FXML
    private PasswordField pfContraseniaCrear;
    @FXML
    private PasswordField pfConfContraseniaCrear;
    @FXML
    private TextField pfCodigoCrear;
    @FXML
    private RadioButton rbtTerminos;
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private Button btnIniciarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) throws IOException {
        ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
        ServicioPersonalCafeteria servicioPersonal = new ServicioPersonalCafeteria();
        ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
        String correoElectronico = txfCorreoInicio.getText();
        String contrasenia = pfContraseniaInicio.getText();
        if(servicioAdministrador.enviarCredencialesAdministrador(correoElectronico, contrasenia) == 200){
            enviarAVentanaAdministrador();
        }else if(servicioPersonal.obtenerPersonalPorCredencial(correoElectronico, contrasenia) != null){
            PersonalCafeteria personalCafeteria = servicioPersonal.obtenerPersonalPorCredencial(correoElectronico, contrasenia);
            if(personalCafeteria.getCargo().equals("Responsable")){
                enviarAVentanaResponsable(personalCafeteria);
            }else{
                enviarAVentanaRecepcionista(personalCafeteria);
            }
        }else if (servicioConsumidor.obtenerConsumidorPorCredencial(correoElectronico,contrasenia) != null){
            Consumidor consumidor = servicioConsumidor.obtenerConsumidorPorCredencial(correoElectronico,contrasenia);
            enviarAVentanaConsumidor(consumidor);
        }else{
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaInformacionInvalida("Credenciales incorrectas");
        }
    }
    
    private void enviarAVentanaAdministrador(){
        try {
            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLEleccionCafeteria.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void enviarAVentanaConsumidor(Consumidor consumidor){
        try {
            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/CEleccionCafeteria.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void enviarAVentanaResponsable(PersonalCafeteria personalCafeteria){
        try {
            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/GInicioProductos.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio");
            stage.show();
            System.out.println("El personal es " + personalCafeteria.getNombre() + " y es " + personalCafeteria.getCargo());
        } catch (IOException ex) {
            Logger.getLogger(GInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void enviarAVentanaRecepcionista(PersonalCafeteria personalCafeteria){
        try {
            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/CEleccionCafeteria.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio");
            stage.show();
            System.out.println("El personal es " + personalCafeteria.getNombre() + " y es " + personalCafeteria.getCargo());
        } catch (IOException ex) {
            Logger.getLogger(GInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clicEnviarCodigo(ActionEvent event) {
    }

    @FXML
    private void clicCrearCuenta(ActionEvent event) throws MalformedURLException, IOException {
        
    }
    
    private boolean existenCamposVaciosInicioSesion(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if(txfCorreoInicio.getText().isEmpty()|| pfContraseniaInicio.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        return existe;
    }
    
    private boolean existenCrearCuenta(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if(txfNombreCrear.getText().isEmpty()|| txfCorreoCrear.getText().isEmpty() || pfContraseniaCrear.getText().isEmpty() || pfConfContraseniaCrear.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        return existe;
    }
    
    //Validar campos invalidos
}
