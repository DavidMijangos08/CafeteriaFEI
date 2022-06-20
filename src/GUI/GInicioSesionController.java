/********************************************************************/
/* Archivo: GInicioSesionController.java                            */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Inicio sesion" con sus metodos                     */
/********************************************************************/

package GUI;

import Dominio.Administrador;
import Dominio.Consumidor;
import Dominio.PersonalCafeteria;
import Servicios.ServicioAdministrador;
import Servicios.ServicioConsumidor;
import Servicios.ServicioPersonalCafeteria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML
    private Button btnSolicitarCodigo;
    
    private String codigoEnviado = "";
    private int tipoUsuario;
    private PersonalCafeteria personalCafeteria = new PersonalCafeteria();
    private Administrador administrador = new Administrador();
    private Consumidor consumidor = new Consumidor();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fijarTamanoMaximo();
    }

    @FXML
    private void clicIniciarSesion(ActionEvent event){
        try {
            ServicioAdministrador servicioAdministrador = new ServicioAdministrador();
            ServicioPersonalCafeteria servicioPersonal = new ServicioPersonalCafeteria();
            ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
            String correoElectronico = txfCorreoInicio.getText();
            String contrasenia = pfContraseniaInicio.getText();
            if(!existenCamposVaciosInicioSesion()){
                if(servicioAdministrador.enviarCredencialesAdministrador(correoElectronico, contrasenia) == 200){
                    cambiarVentana("/GUI/ADInicio.fxml");
                    tipoUsuario = 1;
                }else if(servicioPersonal.obtenerPersonalPorCredencial(correoElectronico, contrasenia) != null){
                    personalCafeteria = servicioPersonal.obtenerPersonalPorCredencial(correoElectronico, contrasenia);
                    if(personalCafeteria.getCargo().equals("Responsable")){
                        tipoUsuario = 2;
                        cambiarVentana("GInicioProductos.fxml");
                    }else{
                        tipoUsuario = 3;
                        cambiarVentana("GInicioProductos.fxml");
                    }
                }else if (servicioConsumidor.obtenerConsumidorPorCredencial(correoElectronico,contrasenia) != null){
                    consumidor = servicioConsumidor.obtenerConsumidorPorCredencial(correoElectronico,contrasenia);
                    tipoUsuario = 4;
                    cambiarVentana("CEleccionCafeteria.fxml");
                }else{
                    MensajeAlerta mensajeAlerta = new MensajeAlerta();
                    mensajeAlerta.mostrarAlertaInformacionInvalida("Credenciales incorrectas");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentana();
        }
    }
    @FXML
    private void clicEnviarCodigo(ActionEvent event) {
        if(!existenCamposInvalidosEnviarCodigo()){
            try {
                ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
                this.codigoEnviado = servicioConsumidor.enviarCodigoAlCorreo(txfCorreoCrear.getText());
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                mensajeAlerta.mostrarAlertaEnvioCorreo();
            } catch (IOException ex) {
                Logger.getLogger(GInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                cerrarVentana();
            }
            
        }
    }

    @FXML
    private void clicCrearCuenta(ActionEvent event){
        if(!existenCamposInvalidosEnviarCodigo() && !existenCamposInvalidosRegistrar() || longitudCamposRequerida()){
            try {
                ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
                String nombre = txfNombreCrear.getText();
                String correo = txfCorreoCrear.getText();
                String contrasenia = pfContraseniaCrear.getText();
                Consumidor nuevoconsumidor = new Consumidor(nombre, correo, contrasenia);
                int respuesta = servicioConsumidor.agregarNuevoConsumidor(nuevoconsumidor);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                if(respuesta == 201){
                    mensajeAlerta.mostrarAlertaGuardado("Tu cuenta ha sido registrada con éxito, por favor inicia sesión");
                    limpiarCamposRegistro();
                }else if(respuesta == 400){
                    mensajeAlerta.mostrarAlertaInformacionInvalida("Datos ya registrados, es probable que el nombre o correo electrónico ya se encuentren en el sistema");
                }
            } catch (IOException ex) {
                Logger.getLogger(GInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                cerrarVentana();
            }
        }
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
    
    private boolean existenCamposInvalidosEnviarCodigo(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if(txfNombreCrear.getText().isEmpty()|| txfCorreoCrear.getText().isEmpty() || pfContraseniaCrear.getText().isEmpty() || pfConfContraseniaCrear.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        
        Validacion validacion = new Validacion();
        
        if(!existe && validacion.existeCampoInvalido(txfNombreCrear.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el nombre");
        }

        if(!existe && validacion.existeCorreoInvalido(txfCorreoCrear.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }

        if(!existe && validacion.existeContraseniaInvalida(pfContraseniaCrear.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Contraseña inválida \n"
                    + "La contraseña debe tener por lo menos 8 caracteres \n"
                           + "La contraseña debe tener por lo menos un digito \n"
                                 + "La contraseña debe tener por lo menos una letra mayúscula \n"
                                 + "La contraseña debe tener por lo menos una letra minúscula");
        }
        
        if(!existe && !pfContraseniaCrear.getText().equals(pfConfContraseniaCrear.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Las contraseñas no coinciden, revisa por favor");
        }
        return existe;
    }
    
    private boolean existenCamposInvalidosRegistrar(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if(pfCodigoCrear.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Por favor ingresa el código de verificación que llegó a tu correo, revisa en correo no deseado");
        }
        
        if(!existe && !rbtTerminos.isSelected()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Por favor acepta los términos y condiciones");
        }
        
        if(!existe && !this.codigoEnviado.equals(pfCodigoCrear.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Código de verificación inválido");
        }
        
        return existe;
    }
    
    private void cerrarVentana(){
        Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
        stage.close();
    }
    
    private void limpiarCamposRegistro(){
        txfNombreCrear.clear();
        txfCorreoCrear.clear();
        pfContraseniaCrear.clear();
        pfConfContraseniaCrear.clear();
        pfCodigoCrear.clear();
        rbtTerminos.setSelected(false);
    }

    private void cambiarVentana(String ruta) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(ruta));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        if (tipoUsuario == 2 || tipoUsuario == 3){
            GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
            controlador.recibirParametros(tipoUsuario, consumidor, personalCafeteria, personalCafeteria.getIdCafeteria());
        }else if(tipoUsuario == 4){
            CEleccionCafeteriaController controlador = (CEleccionCafeteriaController) fxmlLoader.getController();
            controlador.recibirParametros(tipoUsuario, consumidor);
        }
        cerrarVentana();
        stage.setTitle("Cafeterías UV");
        stage.setResizable(false);
        stage.show();
    }

    public void fijarTamanoMaximo() {
        txfCorreoInicio.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (txfCorreoInicio.getText().length() >= 70) {
                        txfCorreoInicio.setText(txfCorreoInicio.getText().substring(0, 70));
                    }
                }
            }
        });

        pfContraseniaInicio.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (pfContraseniaInicio.getText().length() >= 40) {
                        pfContraseniaInicio.setText(pfContraseniaInicio.getText().substring(0, 40));
                    }
                }
            }
        });

        txfCorreoCrear.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (txfCorreoCrear.getText().length() >= 70) {
                        txfCorreoCrear.setText(txfCorreoCrear.getText().substring(0, 70));
                    }
                }
            }
        });

        txfNombreCrear.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (txfNombreCrear.getText().length() >= 70) {
                        txfNombreCrear.setText(txfNombreCrear.getText().substring(0, 70));
                    }
                }
            }
        });

        pfContraseniaCrear.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (pfContraseniaCrear.getText().length() >= 40) {
                        pfContraseniaCrear.setText(pfContraseniaCrear.getText().substring(0, 40));
                    }
                }
            }
        });

        pfConfContraseniaCrear.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (pfConfContraseniaCrear.getText().length() >= 40) {
                        pfConfContraseniaCrear.setText(pfConfContraseniaCrear.getText().substring(0, 40));
                    }
                }
            }
        });

        pfCodigoCrear.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (pfCodigoCrear.getText().length() >= 6) {
                        pfCodigoCrear.setText(pfCodigoCrear.getText().substring(0, 6));
                    }
                }
            }
        });
    }

    private boolean longitudCamposRequerida(){
        if(txfNombreCrear.getText().length() < 15 || txfCorreoCrear.getText().length() < 10 || pfContraseniaCrear.getText().length() < 8 || pfConfContraseniaCrear.getText().length() < 8 || pfCodigoCrear.getText().length() < 6){
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Longitud mínima requerida no válida, revisa la información.");
            return false;
        }
        return true;
    }
}
