/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Dominio.Producto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class FXMLItemProductoController implements Initializable {

    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPrecio;
    @FXML
    private ImageView imgProducto;
    
    private Producto product;
    private MyListenerProducto myListener;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setProducto(Producto p, MyListenerProducto ml){
        this.product=p;
        this.myListener = ml;
        lblNombre.setText(product.getNombreProducto());
        lblPrecio.setText(Float.toString(product.getPrecioProducto()));
        Image img = new Image(getClass().getResourceAsStream(product.getRutaImagen()));
        imgProducto.setImage(img);
    }
    
    @FXML
    private void click(MouseEvent actionEvent){
        myListener.onClickListener(product);
    }
    
    
}