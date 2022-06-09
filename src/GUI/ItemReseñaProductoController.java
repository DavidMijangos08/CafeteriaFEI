/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Dominio.ReseñaProducto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class ItemReseñaProductoController implements Initializable {

    private ReseñaProducto res;
    @FXML
    private ImageView imgReseña;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextArea txaDescripcion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setReseña(ReseñaProducto p){
        this.res = p;
        lblTitulo.setText(res.getTitulo());
        txaDescripcion.setText(res.getOpinion());
        Image img = new Image(getClass().getResourceAsStream(res.getRutaImagen()));
        imgReseña.setImage(img);
    }
}
