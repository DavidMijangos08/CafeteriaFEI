/********************************************************************/
/* Archivo: GReseñasProductosController.java                        */
/* Programador: Eder Ivan                                           */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Reseñas productos" con sus metodos                 */
/********************************************************************/

package GUI;

import Dominio.Consumidor;
import Dominio.PersonalCafeteria;
import Dominio.ReseñaProducto;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Servicios.ServicioCafeteria;
import Servicios.ServicioProducto;
import Servicios.ServicioReseñasProducto;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GReseñasProductoController implements Initializable {

    @FXML
    private Button btnVerCafeteria;
    @FXML
    private VBox vbproducto;
    @FXML
    private Label lblNombreProducto;
    @FXML
    private Label lblPrecioProducto;
    @FXML
    private Label lblTiempoAprox;
    @FXML
    private ImageView imgProducto;
    @FXML
    private TextArea txaDescripcionProducto;
    @FXML
    private TextArea txaNombreCafeteria;
    @FXML
    private Button btnRegresar;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private ImageView imgReloj;
    @FXML
    private ScrollPane scpReseñas;
    @FXML
    private GridPane gdReseñas;
    @FXML
    private Button btnDejarOpinion;

    ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
    private int tipoUsuario = 0;
    private int idCafeteria;
    private int idProducto;
    PersonalCafeteria personalCafeteria;
    Consumidor consumidor;
    
    private void obtenerReseñas(int idProducto1){
        try {
            gdReseñas.getChildren().clear();
            ServicioReseñasProducto servicioReseñas = new ServicioReseñasProducto();
            List<ReseñaProducto> reseñas = servicioReseñas.obtenerReseñasDeProducto(idProducto1);
            int fila = 1;
            try {
                for(int i = 0; i < reseñas.size(); i++){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ItemReseñaProducto.fxml"));
                    AnchorPane acp = fxmlLoader.load();
                    acp.setPrefHeight(10);
                    acp.setMaxHeight(10);
                    acp.setPrefWidth(580);
                    acp.setMaxWidth(600);

                    ItemReseñaProductoController reseñaController = fxmlLoader.getController();
                    reseñaController.setReseña(reseñas.get(i));
                    gdReseñas.add(acp,0,fila++);
                    //Ajustar el ancho del grid
                    gdReseñas.setMinWidth(Region.USE_COMPUTED_SIZE);
                    gdReseñas.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    gdReseñas.setMaxWidth(Region.USE_COMPUTED_SIZE);
                    //Ajustar el alto del grid
                    gdReseñas.setMinHeight(Region.USE_COMPUTED_SIZE);
                    gdReseñas.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    gdReseñas.setMaxHeight(Region.USE_COMPUTED_SIZE);
                    GridPane.setMargin(acp, new Insets(10));
                }
            } catch (IOException ex) {
                Logger.getLogger(ADAltaCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                cerrarVentanaPorExcepcion();
            }
        } catch (IOException ex) {
            Logger.getLogger(ADAltaCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentanaPorExcepcion();
        }
    }

    private void obtenerInfoProducto(int idProducto){
        try {
            ServicioProducto p = new ServicioProducto();
            this.lblNombreProducto.setText(p.obtenerProductoPorId(idProducto).getNombre());
            this.lblPrecioProducto.setText("$" + p.obtenerProductoPorId(idProducto).getPrecio());
            this.lblTiempoAprox.setText(Integer.toString(p.obtenerProductoPorId(idProducto).getTiempoAproximado()) + " min");
            this.txaDescripcionProducto.setText(p.obtenerProductoPorId(idProducto).getDescripcion());
            Image img = new Image(getClass().getResourceAsStream(p.obtenerProductoPorId(idProducto).getRutaImagen()));
            imgProducto.setImage(img);
            Image imgR = new Image(getClass().getResourceAsStream("/img/reloj.png"));
            imgReloj.setImage(imgR);
        } catch (IOException ex) {
            Logger.getLogger(ADAltaCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentanaPorExcepcion();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            controlador.recibirParametrosProducto(tipoUsuario, consumidor, personalCafeteria, idCafeteria,idProducto, 8);
            cerrarVentana();
            stage.setTitle("Ver cafetería");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            cerrarVentana();
        }
    }

    @FXML
    private void clicRegresar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GInicioProductos.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
            controlador.recibirParametros(tipoUsuario, consumidor, personalCafeteria, idCafeteria);
            cerrarVentana();
            stage.setTitle("Inicio sesion");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            cerrarVentana();
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
            controlador.recibirParametrosProducto(tipoUsuario, consumidor, personalCafeteria, idCafeteria, 8, idProducto);
            cerrarVentana();
            stage.setTitle("Modificar cuenta");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            cerrarVentana();
        }
    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
    }

    
    public void recibirParametros(int tipoUsuario1, Consumidor c, PersonalCafeteria p, int idCafeteria1, int idProducto1){
        try {
            if(p != null){
                btnDejarOpinion.setVisible(false);
                this.lblNombreUsuario.setText(p.getNombre());
            }
            if (c!= null){
                btnDejarOpinion.setVisible(true);
                lblNombreUsuario.setText(c.getNombre());
            }
            txaNombreCafeteria.setText(servicioCafeteria.obtenerCafeteriaPorId(idCafeteria1).getNombreCafeteria());
            this.tipoUsuario = tipoUsuario1;
            this.idCafeteria = idCafeteria1;
            this.consumidor = c;
            this.personalCafeteria = p;
            this.idProducto =  idProducto1;
            obtenerReseñas(idProducto1);
            obtenerInfoProducto(idProducto1);
        } catch (IOException ex) {
            Logger.getLogger(GInicioProductosController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentanaPorExcepcion();
        }
    }

    private void cerrarVentana(){
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }

    public void clicDejarOpinion(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/GUI/CDejarOpinion.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            if (tipoUsuario == 4) {
                CDejarOpinionController controlador = (CDejarOpinionController) fxmlLoader.getController();
                controlador.recibirParametros(consumidor, idProducto, idCafeteria, 1);
            }
            cerrarVentana();
            stage.setTitle("Dejar opinión");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            cerrarVentana();
        }
    }
    
    private void cerrarVentanaPorExcepcion(){
        Platform.exit();
    }
}