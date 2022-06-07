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
public class FXMLInicioRecepcionistaController implements Initializable {

    @FXML
    private Button btnVerCafeteria;
    @FXML
    private VBox vbproductoEscogidoCarta;
    @FXML
    private Label lblNombreEscogido;
    @FXML
    private Label lblPrecioEscogido;
    @FXML
    private ImageView imgProductoEscogido;
    @FXML
    private TextArea txaDescripcionEscogido;
    @FXML
    private Button btnVerOpiniones;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private ImageView imgPregunta;
    @FXML
    private ScrollPane scpProductos;
    @FXML
    private GridPane gdProductos;
    
    private MyListenerProducto myListener;
    private List<Producto> productos = new ArrayList<>();
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAñadir;

    /**
     * Initializes the controller class.
     */
    private List<Producto> obtenerProductos(){
        List<Producto> lproductos = new ArrayList<>();
        Producto p;
        for(int i=0; i<25; i++){
                p=new Producto();
                p.setNombre("Sandwich de Pollo");
                p.setPrecio(i);
                p.setRutaImagen("/img/Productos/SandwichJamon.png");
                p.setDescripcion("Delicioso sandwich relleno de pechuga de pollo deshebrada (la foto es de jamon hshs)");
                
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
            Logger.getLogger(FXMLInicioRecepcionistaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   

    @FXML
    private void clicVerCafeteria(ActionEvent event) {
    }

    @FXML
    private void clicVerOpiniones(ActionEvent event) {
    }


    @FXML
    private void clicCerrarSesion(ActionEvent event) {
    }

    @FXML
    private void clicModificarCuenta(ActionEvent event) {
    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
    }
    
    @FXML
    private void clicModificar(ActionEvent event) {
        cambiarVentana("/GUI/FXMLModificarProducto.fxml", "Inicio de Sesion");
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
    }

    @FXML
    private void clicAñadir(ActionEvent event) {
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
}
