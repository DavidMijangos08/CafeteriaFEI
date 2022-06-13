/********************************************************************/
/* Archivo: ItemPersonalController.java                             */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la visualizacion        */
/*              de los datos del personal                           */
/********************************************************************/

package GUI;

import Dominio.PersonalCafeteria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ItemPersonalController implements Initializable {

    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCurp;
    @FXML
    private Label lblCorreo;
    @FXML
    private Label lblCargo;

    private PersonalCafeteria personal;
    private MyListenerPersonal myListener;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setPersonal(PersonalCafeteria p, MyListenerPersonal lp){
        this.personal=p;
        this.myListener = lp;
        lblNombre.setText(personal.getNombre());
        lblCurp.setText(personal.getCURP());
        lblCorreo.setText(personal.getCorreoElectronico());
        lblCargo.setText(personal.getCargo());
        
    }
    
    @FXML
    private void click(MouseEvent actionEvent){
        myListener.onClickListener(personal);
    }
}
