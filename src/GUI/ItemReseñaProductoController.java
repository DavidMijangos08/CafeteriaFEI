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
    }
}
