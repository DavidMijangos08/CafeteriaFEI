/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Dominio.ReseñaCafeteria;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class FXMLVerReseñasController implements Initializable {

    @FXML
    private Button btnVerCafeteria;
    @FXML
    private VBox vbproducto;
    @FXML
    private Label lblNombreProducto;
    @FXML
    private Label lblPrecioProducto;
    @FXML
    private ImageView imgProducto;
    @FXML
    private TextArea txaDescripcionProducto;
    @FXML
    private Button btnRegresar;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private ImageView imgPregunta;
    @FXML
    private ScrollPane scpReseñas;
    @FXML
    private GridPane gdProductos;

    private List<ReseñaCafeteria> reseñas = new ArrayList<>();
    
    /**
     * Initializes the controller class.
     */
    
    private List<ReseñaCafeteria> obtenerProductos(){
        List<ReseñaCafeteria> lReseña = new ArrayList<>();
        ReseñaCafeteria p;
        for(int i=0; i<20; i++){
                p=new ReseñaCafeteria();
                p.setTitulo("Muy buen producto " +i);
                p.setOpinion("siempre pido lo mismo y nunca me decepciona, sigan así :) "+i);
                p.setRutaImagen("/img/usuario.png");
                
                lReseña.add(p);
        }
        
        return lReseña;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reseñas.addAll(obtenerProductos());
        
        int fila = 1;
        try {
            for(int i = 0; i < reseñas.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLItemReseña.fxml"));
                
                AnchorPane acp = fxmlLoader.load();
            
                FXMLItemReseñaController reseñaController = fxmlLoader.getController();
                reseñaController.setProducto(reseñas.get(i));
                                
                gdProductos.add(acp,0,fila++);
                
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
            Logger.getLogger(FXMLVerReseñasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void clicVerCafeteria(ActionEvent event) {
    }

    @FXML
    private void clicRegresar(ActionEvent event) {
    }

    @FXML
    private void clicModificarCuenta(ActionEvent event) {
    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
    }
    
}
