/********************************************************************/
/* Archivo: RSVerPersonalController.java                            */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Ver personal" con sus metodos                      */
/********************************************************************/

package GUI;

import Dominio.PersonalCafeteria;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Servicios.ServicioCafeteria;
import Servicios.ServicioPersonalCafeteria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class RSVerPersonalController implements Initializable{
    
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnEliminarPersonal;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private ImageView imgPregunta;
    @FXML
    private Button btnVerCafeteria;
    @FXML
    private Button btnAñadirPersonal;
    @FXML
    private ComboBox<String> cmbCargoAñadir;
    @FXML
    private Button btnAceptar;
    @FXML
    private Label lblNombrePersonal;
    @FXML
    private Label lblCurpPersonal;
    @FXML
    private Label lblCorreoPersonal;
    @FXML
    private Label lblCargoPersonal;
    @FXML
    private GridPane gdPersonal;
    @FXML
    private TextField txfNombreAñadir;
    @FXML
    private TextField txfCurpAñadir;
    @FXML
    private TextField txfCorreoAñadir;
    @FXML
    private TextArea txaNombrePersonal;
    @FXML
    private TextArea txaCorreoPersonal;
    @FXML
    private Button btnVerProductos;
    @FXML
    private TextArea txaTituloCafeteria;

    PersonalCafeteria personalCafeteria = new PersonalCafeteria();
    private MyListenerPersonal myListener;
    //private List<PersonalCafeteria> lPersonal = new ArrayList<>();
    private int idCafeteria;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void obtenerPersonal(int idCafeteria) {
        try {
            ServicioPersonalCafeteria servicioProducto = new ServicioPersonalCafeteria();
            List<PersonalCafeteria> lPersonal = servicioProducto.obtenerListaPersonal(idCafeteria);
            //lPersonal.addAll(obtenerPersonal(idCafeteria));
            if(lPersonal.size()>0){
                setPersonalElegido(lPersonal.get(0));
                myListener = new MyListenerPersonal(){
                    @Override
                    public void onClickListener(PersonalCafeteria p){
                        setPersonalElegido(p);
                    }
                };
            }
            int fila = 0;
            for(int i = 0; i < lPersonal.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemPersonal.fxml"));

                AnchorPane acp = fxmlLoader.load();

                ItemPersonalController pc = fxmlLoader.getController();
                pc.setPersonal(lPersonal.get(i), myListener);

                gdPersonal.add(acp,0,fila++);

                //Ajustar el ancho del grid
                gdPersonal.setMinWidth(Region.USE_COMPUTED_SIZE);
                gdPersonal.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gdPersonal.setMaxWidth(Region.USE_COMPUTED_SIZE);

                //Ajustar el alto del grid
                gdPersonal.setMinHeight(Region.USE_COMPUTED_SIZE);
                gdPersonal.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gdPersonal.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(acp, new Insets(  10));
            }
        } catch (IOException ex) {
            Logger.getLogger(GInicioProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void clicModificarCuenta(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GModificarCuenta.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            GModificarCuentaController controlador = (GModificarCuentaController) fxmlLoader.getController();
            controlador.recibirParametros(2, null, personalCafeteria,idCafeteria, 15);
            cerrarVentana();
            stage.setTitle("Modificar cuenta");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void clicCerrarSesion(ActionEvent event) {
        try {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/GInicioSesion.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Iniciar sesion");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void clicModificarPersonal(ActionEvent event) {

    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
    }

    @FXML
    private void clicVerCafeteria(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GVerCafeteria.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            GVerCafeteriaController controlador = (GVerCafeteriaController) fxmlLoader.getController();
            controlador.recibirParametros(2,null, personalCafeteria, idCafeteria, 15);
            cerrarVentana();
            stage.setTitle("Ver cafetería");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
    }

    private void setPersonalElegido(PersonalCafeteria p){
        this.txaNombrePersonal.setText(p.getNombre());
        this.lblCurpPersonal.setText(p.getCURP());
        this.txaCorreoPersonal.setText(p.getCorreoElectronico());
        this.lblCargoPersonal.setText(p.getCargo());
    }

    private boolean existenCamposInvalidos(){
        boolean existen = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();

        if(txfNombreAñadir.getText().isEmpty() || txfCurpAñadir.getText().isEmpty() || txfCorreoAñadir.getText().isEmpty() || cmbCargoAñadir.getSelectionModel().getSelectedItem().isEmpty()){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }

        if(validacion.existeCampoInvalido(txfNombreAñadir.getText()) || validacion.existeCampoInvalido(txfCurpAñadir.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }

        if(validacion.existeCorreoInvalido(txfCorreoAñadir.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }

        return existen;
    }
    
    public void recibirParametros(PersonalCafeteria p, int idCafeteria1){
        try {
            ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
            this.personalCafeteria = p;
            this.idCafeteria = idCafeteria1;
            this.lblNombreUsuario.setText(p.getNombre());
            txaTituloCafeteria.setText(servicioCafeteria.obtenerCafeteriaPorId(idCafeteria1).getNombreCafeteria());
            obtenerPersonal(idCafeteria1);
            //iniciarVentana(tipoUsuario1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void cerrarVentana(){
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clicVerProductos(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/GUI/GInicioProductos.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
            controlador.recibirParametros(2, null, personalCafeteria, idCafeteria);
            cerrarVentana();
            stage.setTitle("Productos");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void clicAñadirPersonal(ActionEvent event) {
    }
}