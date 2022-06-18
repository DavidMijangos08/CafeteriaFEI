/********************************************************************/
/* Archivo: ItemReseñaProductoController.java                       */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la visualizacion        */
/*              de los datos de las reseñas                         */
/********************************************************************/

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

public class ItemReseñaProductoController implements Initializable {

    private ReseñaProducto res;
    @FXML
    private ImageView imgReseña;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextArea txaDescripcion;
    @FXML
    private ImageView imgEstrella1;
    @FXML
    private ImageView imgEstrella2;
    @FXML
    private ImageView imgEstrella3;
    @FXML
    private ImageView imgEstrella4;
    @FXML
    private ImageView imgEstrella5;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    public void setReseña(ReseñaProducto p){
        this.res = p;
        lblTitulo.setText(res.getTitulo());
        txaDescripcion.setText(res.getOpinion());
        if(res.getRutaImagen() != "" || res.getRutaImagen() != null){
            Image img = new Image(getClass().getResourceAsStream(res.getRutaImagen()));
            imgReseña.setImage(img);
        }
        switch (res.getCalificacion()){
            case 1:
                imgEstrella2.setVisible(false);
                imgEstrella3.setVisible(false);
                imgEstrella4.setVisible(false);
                imgEstrella5.setVisible(false);
                break;
            case 2:
                imgEstrella3.setVisible(false);
                imgEstrella4.setVisible(false);
                imgEstrella5.setVisible(false);
                break;
            case 3:
                imgEstrella4.setVisible(false);
                imgEstrella5.setVisible(false);
                break;
            case 4:
                imgEstrella5.setVisible(false);
                break;
        }
    }
}
