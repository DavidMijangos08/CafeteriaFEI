/********************************************************************/
/* Archivo: GInicioProductosController.java                         */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Cafeteria" con sus metodos                         */
/********************************************************************/

package GUI;

import Dominio.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Servicios.ServicioCafeteria;
import Servicios.ServicioProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javax.imageio.ImageIO;


public class GInicioProductosController implements Initializable {

    @FXML
    private VBox vbproductoEscogidoCarta;
    @FXML
    private ScrollPane scpProductos;
    @FXML
    private GridPane gdProductos;
    @FXML
    private Button btnDejarOpinion;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblNombreCafeteria;
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private Button btnVerOpiniones;
    @FXML
    private ImageView imgPregunta;
    @FXML
    private Button btnVerCafeteria;
    @FXML
    private Label lblNombreEscogido;
    @FXML
    private ImageView imgProductoEscogido;
    @FXML
    private TextArea txaDescripcionEscogido;
    @FXML
    private Label lblPrecioEscogido;
    @FXML
    private Label lblTiempoAprox;
    @FXML
    private Label lblTituloCafeteria;
    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Button btnModificarProducto;
    @FXML
    private Button btnAñadirProducto;
    //private int tipoUsuario;
    //private int idCafeteria;
    private Consumidor consumidor = new Consumidor();
    private PersonalCafeteria personalCafeteria = new PersonalCafeteria();
    private MyListenerProducto myListener;
    ServicioCafeteria servicioCafeteria = new ServicioCafeteria();

    public void recibirParametrosConsumidor(int tipoUsuario, Consumidor c, int idCafeteria1) throws IOException {
        this.consumidor = c;
        lblTituloCafeteria.setText(servicioCafeteria.obtenerCafeteriaPorId(idCafeteria1).getNombreCafeteria());
        obtenerProductos(idCafeteria1);
        iniciarVentana(tipoUsuario);
    }

    public void recibirParametrosPersonal(int tipoUsuario, PersonalCafeteria p) throws IOException {
        personalCafeteria = p;
        lblTituloCafeteria.setText(servicioCafeteria.obtenerCafeteriaPorId(p.getIdCafeteria()).getNombreCafeteria());
        obtenerProductos(p.getIdCafeteria());
        iniciarVentana(tipoUsuario);
    }

    private void obtenerProductos(int idCafeteria){
        try {
            ServicioProducto servicioProducto = new ServicioProducto();
            List<Producto> productos = servicioProducto.obtenerProductosDeCafeteria(idCafeteria);

            if(productos.size()>0){
                setProductoElegido(productos.get(0));
                myListener = new MyListenerProducto(){
                    @Override
                    public void onClickListener(Producto p){
                        setProductoElegido(p);
                    }
                };
            }
            int columna=0;
            int fila=1;

            for(int i = 0; i < productos.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemProducto.fxml"));
                AnchorPane acp = fxmlLoader.load();

                ItemProductoController productoController = fxmlLoader.getController();
                productoController.setProducto(productos.get(i), myListener);

                if(columna == 4){
                    columna = 0;
                    fila++;
                }

                gdProductos.add(acp,columna++,fila);
                //Ajustar el ancho del grid
                gdProductos.setMinWidth(Region.USE_COMPUTED_SIZE);
                gdProductos.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gdProductos.setMaxWidth(Region.USE_COMPUTED_SIZE);

                //Ajustar el alto del grid
                gdProductos.setMinHeight(Region.USE_COMPUTED_SIZE);
                gdProductos.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gdProductos.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(acp, new Insets(5));
            }
        } catch (IOException ex) {
            Logger.getLogger(GInicioProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setProductoElegido(Producto p){
        lblNombreEscogido.setText(p.getNombre());
        lblPrecioEscogido.setText("$" + Float.toString(p.getPrecio()));
        Image img = new Image(getClass().getResourceAsStream(p.getRutaImagen()));
        imgProductoEscogido.setImage(img);
        txaDescripcionEscogido.setText(p.getDescripcion());
        lblTiempoAprox.setText(Integer.toString(p.getTiempoAproximado()) + " min");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //productos.addAll(obtenerProductos());

    }
    
    @FXML
    private void clicDejarOpinion(ActionEvent event) {
        cambiarVentana("/GUI/CDejarOpinion.fxml", "Escribe una opinión");
    }

    @FXML
    private void clicCerrarSesion(ActionEvent event) {
        cambiarVentana("/GUI/GInicioSesion.fxml", "Inicio de sesión");
    }

    @FXML
    private void clicModificarCuenta(ActionEvent event) {
        cambiarVentana("/GUI/GModificarCuenta.fxml", "Modificar Cuenta");
    }

    @FXML
    private void clicVerOpiniones(ActionEvent event) {
        cambiarVentana("/GUI/GReseñasProducto.fxml", "Reseñas");
    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
        //cambiarVentana("/GUI/FXMLPreguntasFrecuentes.fxml", "Preguntas Frecuentes");
    }
    
    private void cambiarVentana(String ruta, String titulo){
        try {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource(ruta)));
            stage.setScene(scenePrincipal);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void clicVerCafeteria(ActionEvent event) {
        cambiarVentana("/GUI/GVerCafeteria.fxml", "Cafetería");
    }
    @FXML
    void clicEliminarProducto(ActionEvent event) {

    }

    @FXML
    void clicModificarProducto(ActionEvent event) {
        cambiarVentana("/GUI/RCModificarProducto.fxml", "Modificar Producto");
    }
    
    @FXML
    void clicAñadirProducto(ActionEvent event) {
        cambiarVentana("/GUI/RCAgregarProducto.fxml", "Agregar Producto");
    }

    private void iniciarVentana(int tipoUsuario){
        if(tipoUsuario == 2){
            btnAñadirProducto.setVisible(false);
            btnModificarProducto.setVisible(false);
            btnEliminarProducto.setVisible(false);
            btnDejarOpinion.setVisible(false);
        }else if(tipoUsuario == 3){
            btnVerCafeteria.setVisible(false);
            btnVerOpiniones.setVisible(false);
            btnDejarOpinion.setVisible(false);
        }else if(tipoUsuario == 4){
            btnAñadirProducto.setVisible(false);
            btnModificarProducto.setVisible(false);
            btnEliminarProducto.setVisible(false);
            btnVerCafeteria.setVisible(false);
            btnVerOpiniones.setVisible(false);
        }
    }
}
