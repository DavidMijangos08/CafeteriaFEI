/********************************************************************/
/* Archivo: CDejarOpinionController.java                            */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  10/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Dejar opinion" con sus metodos                     */
/********************************************************************/

package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CDejarOpinionController implements Initializable {

    @FXML
    private ToggleGroup tgSeleccion;
    @FXML
    private Button btnAgregarImagen;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnAceptar;
    @FXML
    private TextArea txaOpinion;
    @FXML
    private TextArea txaDescripcionProducto;
    @FXML
    private TextArea txaRutaImagen;
    @FXML
    private TextField txfTituloOpinion;
    @FXML
    private RadioButton rbCalif1;

    @FXML
    private RadioButton rbCalif2;

    @FXML
    private RadioButton rbCalif3;

    @FXML
    private RadioButton rbCalif4;

    @FXML
    private RadioButton rbCalif5;
    FileChooser fc = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicAgregarImagen(ActionEvent event) {
        fc.setTitle("Selecciona una imagen");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File file = fc.showOpenDialog(null);
        if (file != null){
            txaRutaImagen.setText(file.getAbsolutePath());
        }

    }

    @FXML
    private void clicRegresar(ActionEvent event) {
        cambiarVentana("/GUI/GInicioProductos.fxml");
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
        int calificacion;
        if (validarCampos()){
            System.out.println("todo olrigt ventana dejar op");
            calificacion = obtenerCalificacion();

        }
    }
    private void cambiarVentana(String ruta){
        try {
            Stage stage = (Stage) btnRegresar.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource(ruta)));

            stage.setScene(scenePrincipal);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private boolean validarCampos(){
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        if (obtenerCalificacion() == -1) {
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
            return false;
        }else if(txfTituloOpinion.getText() == ""){
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
            return false;
        }else if(txaOpinion.getText() == ""){
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
            return false;
        }else{
            return true;
        }
    }

    private int obtenerCalificacion(){
        if(rbCalif1.isSelected()){
            return 1;
        }else if(rbCalif2.isSelected()){
            return 2;
        }else if(rbCalif3.isSelected()){
            return 3;
        }else if(rbCalif4.isSelected()){
            return 4;
        }else if(rbCalif5.isSelected()){
            return 5;
        }else{
            return -1;
        }
    }
}
