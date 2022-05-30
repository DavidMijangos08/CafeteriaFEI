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
public class FXMLVerCafeteriaController implements Initializable {

    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private ImageView imgPregunta;
    @FXML
    private VBox vbCafeteria;
    @FXML
    private Label lblNombreCafeteria;
    @FXML
    private Label lblHoraInicio;
    @FXML
    private Label lblHoraFinal;
    @FXML
    private TextArea txaFacultad;
    @FXML
    private TextArea txaDireccion;
    @FXML
    private Button btnRegresar;
    @FXML
    private ScrollPane scpReseñasCafeteria;
    private List<ReseñaCafeteria> reseñas = new ArrayList<>();
    @FXML
    private GridPane gdReseñas;

    /**
     * Initializes the controller class.
     */
    private List<ReseñaCafeteria> obtenerReseñas(){
        List<ReseñaCafeteria> lReseña = new ArrayList<>();
        ReseñaCafeteria p;
        for(int i=0; i<20; i++){
                p = new ReseñaCafeteria();
                p.setTitulo("Muy buen producto " +i);
                p.setOpinion("siempre pido lo mismo y nunca me decepciona, sigan así :) "+i);
                p.setRutaImagen("/img/usuario.png");
                
                lReseña.add(p);
        }
        
        return lReseña;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reseñas.addAll(obtenerReseñas());
        
        int fila = 1;
        try {
            for(int i = 0; i < reseñas.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("FXMLItemReseñaCafeteria.fxml"));
                
                AnchorPane acp = fxmlLoader.load();
            
                FXMLItemReseñaCafeteriaController reseñaController = fxmlLoader.getController();
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
            Logger.getLogger(FXMLVerReseñasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    @FXML
    private void clicModificarCuenta(ActionEvent event) {
    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
    }

    @FXML
    private void clicRegresar(ActionEvent event) {
    }
    
}
