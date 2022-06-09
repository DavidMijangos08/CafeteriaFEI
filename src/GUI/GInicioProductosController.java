/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Dominio.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author marie
 */
public class FXMLInicioUsuarioController implements Initializable {

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
    private Button btnEliminarProducto;

    @FXML
    private Button btnModificarProducto;

    private MyListenerProducto myListener;
    private List<Producto> productos = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    
    private List<Producto> obtenerProductos(){
        List<Producto> lproductos = new ArrayList<>();
        Producto p;
        for(int i=0; i<25; i++){
                p=new Producto();
                p.setNombre("Enchiladas rojas");
                p.setPrecio(i);
                p.setRutaImagen("/img/Productos/enchiladasRojas.png");
                p.setDescripcion("Deliciosas tortillas dobladas en forma de flautas rellenas de pollo bañanas en salsa roja");
                
                lproductos.add(p);
        }
        
        return lproductos;
    }
    
    private void setProductoElegido(Producto p){
        //precioP = p.getPrecio();
        lblNombreEscogido.setText(p.getNombre());
        lblPrecioEscogido.setText(Float.toString(p.getPrecio()));
        Image img = new Image(getClass().getResourceAsStream(p.getRutaImagen()));
        imgProductoEscogido.setImage(img);
        txaDescripcionEscogido.setText(p.getDescripcion());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productos.addAll(obtenerProductos());
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
        try {
            for(int i = 0; i < productos.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLItemProducto.fxml"));
                
                AnchorPane acp = fxmlLoader.load();
            
                FXMLItemProductoController productoController = fxmlLoader.getController();
                productoController.setProducto(productos.get(i), myListener);
                
                if(columna == 3){
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
                
                GridPane.setMargin(acp, new Insets(10));
             }
        } catch (IOException ex) {
            Logger.getLogger(FXMLInicioUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    @FXML
    private void clicDejarOpinion(ActionEvent event) {
        cambiarVentana("/GUI/FXMLEscribirOpinion.fxml", "Escribe una opinión");
    }

    @FXML
    private void clicCerrarSesion(ActionEvent event) {
        cambiarVentana("/GUI/GInicioSesion.fxml", "Inicio de sesión");
    }

    @FXML
    private void clicModificarCuenta(ActionEvent event) {
        
        //cambiarVentana("/GUI/FXMLModificarCuenta.fxml", "Modificar Cuenta");
    }

    @FXML
    private void clicVerOpiniones(ActionEvent event) {
        
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
    }
    @FXML
    void clicEliminarProducto(ActionEvent event) {

    }

    @FXML
    void clicModificarProducto(ActionEvent event) {

    }
    
}
