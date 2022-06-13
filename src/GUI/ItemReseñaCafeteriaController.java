/********************************************************************/
/* Archivo: ItemReseñaCafeteriaController.java                      */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la visualizacion        */
/*              de los datos de las reseñas                         */
/********************************************************************/

package GUI;

import Dominio.ReseñaCafeteria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemReseñaCafeteriaController implements Initializable {

    private ReseñaCafeteria res;
    @FXML
    private ImageView imgReseña;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextArea txaDescripcion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setReseña(ReseñaCafeteria p){
        this.res = p;
        lblTitulo.setText(res.getTitulo());
        txaDescripcion.setText(res.getOpinion());
        Image img = new Image(getClass().getResourceAsStream(res.getRutaImagen()));
        imgReseña.setImage(img);
    }
}
