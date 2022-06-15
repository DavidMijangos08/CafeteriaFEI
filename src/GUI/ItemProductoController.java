/********************************************************************/
/* Archivo: ItemProductoController.java                             */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la visualizacion        */
/*              de los datos del producto                           */
/********************************************************************/

package GUI;

import Dominio.Producto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemProductoController implements Initializable {

    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPrecio;
    @FXML
    private ImageView imgProducto;
    @FXML
    private Label lblTiempoAprox;
    
    private Producto product;
    private MyListenerProducto myListener;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setProducto(Producto p, MyListenerProducto ml){
        this.product=p;
        this.myListener = ml;
        lblNombre.setText(product.getNombre());
        lblPrecio.setText("$" + Float.toString(product.getPrecio()));
        Image img = new Image(getClass().getResourceAsStream(product.getRutaImagen()));
        imgProducto.setImage(img);
        lblTiempoAprox.setText(Integer.toString(product.getTiempoAproximado()) + " min");

    }
    
    @FXML
    private void click(MouseEvent actionEvent){
        myListener.onClickListener(product);
    }    
}
