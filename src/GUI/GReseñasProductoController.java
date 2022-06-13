/********************************************************************/
/* Archivo: GReseñasProductosController.java                        */
/* Programador: Eder Ivan                                           */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Reseñas productos" con sus metodos                 */
/********************************************************************/

package GUI;

import Dominio.ReseñaProducto;
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
    private GridPane gdReseñas;
    @FXML

    private Button btnDejarOpinion;
    private List<ReseñaProducto> reseñaProductos = new ArrayList<>();
    
    private List<ReseñaProducto> obtenerReseñas(){
        List<ReseñaProducto> lReseña = new ArrayList<>();
        ReseñaProducto p;
        for(int i=0; i<20; i++){
            p = new ReseñaProducto();
            p.setTitulo("Muy buen producto " +i);
            p.setOpinion("siempre pido lo mismo y nunca me decepciona, sigan así :) "+i);
            p.setRutaImagen("/img/usuario.png");

            lReseña.add(p);
        }

        return lReseña;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reseñaProductos.addAll(obtenerReseñas());

        int fila = 1;
        try {
            for(int i = 0; i < reseñaProductos.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemReseñaProducto.fxml"));

                AnchorPane acp = fxmlLoader.load();

                ItemReseñaProductoController reseñaController = fxmlLoader.getController();
                reseñaController.setReseña(reseñaProductos.get(i));

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
            Logger.getLogger(GReseñasProductoController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    void clicDejarOpinion(ActionEvent event) {

    }
    
    private void cerrarVentana(){
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
}