/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Dominio.PersonalCafeteria;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author marie
 */
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
    
    /**
     * Initializes the controller class.
     */
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
