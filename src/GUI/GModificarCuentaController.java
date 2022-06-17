/********************************************************************/
/* Archivo: GModificarCuentaController.java                         */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Modificar cuenta" con sus metodos                  */
/********************************************************************/

package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Dominio.Consumidor;
import Dominio.PersonalCafeteria;
import Dominio.Producto;
import Servicios.ServicioCafeteria;
import Servicios.ServicioConsumidor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GModificarCuentaController implements Initializable {

    @FXML
    private Button btnSolicitarCodigo;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnAceptar;
    @FXML
    private TextField txfNombre;
    @FXML
    private TextField txfCorreoElectronico;
    @FXML
    private PasswordField pfContrasenia;
    @FXML
    private PasswordField pfConfContrasenia;
    @FXML
    private TextField txfCodigoVerificacion;
    
    private int tipoUsuario;
    private int idCafeteria;
    private int idVentana;
    private int idProducto;
    private String codigoEnviado = "";
    Consumidor consumidor = new Consumidor();
    PersonalCafeteria personalCafeteria = new PersonalCafeteria();
    ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }     

    @FXML
    private void clicRegresar(ActionEvent event) {
        cambiarVentana();
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
        if(!existenCamposInvalidosEnviarCodigo() && !existenCamposInvalidosModificar()){
            try {
                ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
                String nombre = txfNombre.getText();
                String correo = txfCorreoElectronico.getText();
                String contrasenia = pfContrasenia.getText();
                Consumidor nuevoconsumidor = new Consumidor(nombre, correo, contrasenia);
                nuevoconsumidor.setIdConsumidor(consumidor.getIdConsumidor());
                nuevoconsumidor.setToken(consumidor.getToken());
                consumidor = nuevoconsumidor;
                int respuesta = servicioConsumidor.modificarConsumidor(nuevoconsumidor, consumidor.getIdConsumidor());
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                if(respuesta == 200){
                    mensajeAlerta.mostrarAlertaGuardado("Tu cuenta ha sido modificada con éxito.");
                    cambiarVentana();
                }
            } catch (IOException ex) {
                Logger.getLogger(GInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                cerrarVentana();
            }
        }
    }
    
    private void cambiarVentana(){
        String ruta ="";
        String titulo = "Cafeterías UV";
        if(idVentana == 5){
            ruta = "GInicioProductos.fxml";
            titulo = "Productos";
        }else if(idVentana == 8){
            ruta = "GReseñasProducto.fxml";
            titulo = "Reseñas producto";
        }else if(idVentana == 9) {
            ruta = "GVerCafeteria.fxml";
            titulo = "Ver cafetería";
        }else if(idVentana == 15){
            ruta = "RSVerPersonal.fxml";
            titulo = "Ver personal";
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(ruta));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            if (tipoUsuario == 2 || tipoUsuario == 3){
                if(idVentana == 9) {
                    GVerCafeteriaController controlador = (GVerCafeteriaController) fxmlLoader.getController();
                    controlador.recibirParametros(tipoUsuario, null, personalCafeteria, idCafeteria, 7);
                }else if(idVentana == 5){
                    GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
                    controlador.recibirParametros(tipoUsuario, null, personalCafeteria, idCafeteria);
                }else if(idVentana == 8){
                    GReseñasProductoController controlador = (GReseñasProductoController) fxmlLoader.getController();
                    controlador.recibirParametros(tipoUsuario, null, personalCafeteria, personalCafeteria.getIdCafeteria(), idProducto);
                }else if(idVentana == 15){
                    RSVerPersonalController controlador = (RSVerPersonalController) fxmlLoader.getController();
                    controlador.recibirParametros(personalCafeteria, personalCafeteria.getIdCafeteria());
                }
            }else if(tipoUsuario == 4){
                if(idVentana == 9) {
                    GVerCafeteriaController controlador = (GVerCafeteriaController) fxmlLoader.getController();
                    controlador.recibirParametros(tipoUsuario,consumidor, null, idCafeteria, 7);
                }else if(idVentana == 5){
                    GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
                    controlador.recibirParametros(tipoUsuario, consumidor, null, idCafeteria);
                }else if(idVentana == 8){
                    GReseñasProductoController controlador = (GReseñasProductoController) fxmlLoader.getController();
                    controlador.recibirParametros(tipoUsuario, consumidor, null, idCafeteria, idProducto);
                }
            }
            cerrarVentana();
            stage.setTitle(titulo);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     
   private boolean existenCamposPrimariosInvalidos(){
        boolean existen = false;  
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        
        if(txfNombre.getText().isEmpty() || txfCorreoElectronico.getText().isEmpty() || pfContrasenia.getText().isEmpty() ){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        
        if(validacion.existeCampoInvalido(txfNombre.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }
        
        if(validacion.existeCorreoInvalido(txfCorreoElectronico.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }

        if(validacion.existeContraseniaInvalida(pfContrasenia.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Contraseña inválida \n"
                           + "La contraseña debe tener por lo menos un digito \n"
                                 + "La contraseña debe tener por lo menos una letra mayúscula \n"
                                 + "La contraseña debe tener por lo menos una letra minúscula");
        }
        return existen;
    }

    @FXML
    private void clicSolicitarCodigo(ActionEvent event) {
        if(!existenCamposInvalidosEnviarCodigo()){
            try {
                ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
                this.codigoEnviado = servicioConsumidor.enviarCodigoAlCorreo(txfCorreoElectronico.getText());
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
    private void cerrarVentana(){
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
    
    private void iniciarVentana(int tipoUsuario1, Consumidor c, PersonalCafeteria p, int idCafeteria1, int idVentana1, int idProducto1){
        this.tipoUsuario = tipoUsuario1;
        this.consumidor = c;
        this.personalCafeteria = p;
        this.idCafeteria = idCafeteria1;
        this.idVentana = idVentana1;
        this.idProducto = idProducto1;
    }
    
    public void recibirParametros(int tipoUsuario1, Consumidor c, PersonalCafeteria p,  int idCafeteria1, int idVentana1){
        if(p != null){
            txfNombre.setText(p.getNombre());
            txfCorreoElectronico.setText(p.getCorreoElectronico());
        }else if(c != null){
            txfNombre.setText(c.getNombre());
            txfCorreoElectronico.setText(c.getCorreoElectronico());
        }
        iniciarVentana(tipoUsuario1, c, p, idCafeteria1, idVentana1, -1);
    }
    
    public void recibirParametrosProducto(int tipoUsuario1, Consumidor c, PersonalCafeteria p,  int idCafeteria1, int idVentana1, int idProducto1){
        if(p != null){
            txfNombre.setText(p.getNombre());
            txfCorreoElectronico.setText(p.getCorreoElectronico());
        }else if(c != null){
            txfNombre.setText(c.getNombre());
            txfCorreoElectronico.setText(c.getCorreoElectronico());
        }
        iniciarVentana(tipoUsuario1, c, p, idCafeteria1, idVentana1, idProducto1);
    }

    private boolean existenCamposInvalidosEnviarCodigo(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if(txfNombre.getText().isEmpty()|| txfCorreoElectronico.getText().isEmpty() || pfContrasenia.getText().isEmpty() || pfConfContrasenia.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }

        Validacion validacion = new Validacion();

        if(!existe && validacion.existeCampoInvalido(txfNombre.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el nombre");
        }

        if(!existe && validacion.existeCorreoInvalido(txfCorreoElectronico.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }

        if(!existe && validacion.existeContraseniaInvalida(pfContrasenia.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Contraseña inválida \n"
                    + "La contraseña debe tener por lo menos 8 caracteres \n"
                    + "La contraseña debe tener por lo menos un digito \n"
                    + "La contraseña debe tener por lo menos una letra mayúscula \n"
                    + "La contraseña debe tener por lo menos una letra minúscula");
        }

        if(!existe && !pfContrasenia.getText().equals(pfConfContrasenia.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Las contraseñas no coinciden, revisa por favor");
        }
        return existe;
    }

    private boolean existenCamposInvalidosModificar(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if(txfCodigoVerificacion.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Por favor ingresa el código de verificación que llegó a tu correo, revisa en correo no deseado");
        }

        if(!existe && !this.codigoEnviado.equals(txfCodigoVerificacion.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Código de verificación inválido");
        }

        return existe;
    }
}
