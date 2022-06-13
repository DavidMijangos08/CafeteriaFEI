/********************************************************************/
/* Archivo: VerProductosController.java                             */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Ver productos" con sus metodos                     */
/********************************************************************/

package GUI;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;

public class VerProductosController implements Initializable {

    @FXML
    private TableView<?> tablaProductos;
    @FXML
    private TableColumn<?, ?> columnaNombreProducto;
    @FXML
    private TableColumn<?, ?> columnaPrecio;
    @FXML
    private TableColumn<?, ?> columnaTiempoAproximado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicRegresar(ActionEvent event) {
    }

    @FXML
    private void clicVer(ActionEvent event) {
    }
    
}
