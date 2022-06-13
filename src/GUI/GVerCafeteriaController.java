/********************************************************************/
/* Archivo: VerCafeteriasController.java                            */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Ver cafeteria" con sus metodos                     */
/********************************************************************/

package GUI;

import Dominio.Cafeteria;
import Dominio.Producto;
import Dominio.ReseñaCafeteria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Servicios.ServicioCafeteria;
import Servicios.ServicioReseñasCafeteria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class GVerCafeteriaController implements Initializable {

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
    @FXML
    private Button btnDejarOpinion;

    private int idCafeteria = 1;

    private List<ReseñaCafeteria> obtenerReseñas(){
        ServicioReseñasCafeteria servicioReseñas = new ServicioReseñasCafeteria();
        List<ReseñaCafeteria> lr = servicioReseñas.obtenerReseñasDeCafeteria(idCafeteria);
        return lr;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerInfoCafeteria(idCafeteria);
        reseñas.addAll(obtenerReseñas());
        int fila = 1;
        try {
            for(int i = 0; i < reseñas.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemReseñaCafeteria.fxml"));
                
                AnchorPane acp = fxmlLoader.load();
                acp.setMaxHeight(50);
                acp.setPrefWidth(600);
                ItemReseñaCafeteriaController reseñaController = fxmlLoader.getController();
                reseñaController.setReseña(reseñas.get(i));
                                
                gdReseñas.add(acp,0,fila++);
                
                //Ajustar el ancho del grid
                gdReseñas.setMinWidth(Region.USE_COMPUTED_SIZE);
                gdReseñas.setMinWidth(Region.USE_COMPUTED_SIZE);
                gdReseñas.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gdReseñas.setMaxWidth(Region.USE_COMPUTED_SIZE);
                
                //Ajustar el alto del grid
                gdReseñas.setMinHeight(Region.USE_COMPUTED_SIZE);
                gdReseñas.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gdReseñas.setMaxHeight(Region.USE_COMPUTED_SIZE);

                gdReseñas.setAlignment(Pos.CENTER);
                
                GridPane.setMargin(acp, new Insets(10));
             }
        } catch (IOException ex) {
            Logger.getLogger(GVerCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    void clicDejarOpinion(ActionEvent event) {

    }

    public void obtenerInfoCafeteria(int idCafeteria){
        ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
        Cafeteria c = servicioCafeteria.obtenerCafeteriaPorId(idCafeteria);
        lblNombreCafeteria.setText(c.getNombreCafeteria());
        lblHoraInicio.setText(c.getHoraInicio());
        lblHoraFinal.setText(c.getHoraFin());
        txaFacultad.setText(c.getFacultadPerteneciente());
        txaDireccion.setText(c.getDireccion());
    }
}
