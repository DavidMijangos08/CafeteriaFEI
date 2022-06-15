/********************************************************************/
/* Archivo: RCAgregarProductoController.java                        */
/* Programador: Eder Ivan                                           */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Agregar producto" con sus metodos                  */
/********************************************************************/

package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Dominio.Consumidor;
import Dominio.PersonalCafeteria;
import Servicios.ServicioProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RCAgregarProductoController implements Initializable {

    @FXML
    private TextField txfPrecio;
    @FXML
    private TextArea txfDescripcion;
    @FXML
    private TextField txfTiempoAproximado;
    @FXML
    private Button btnAñadirImagen;
    @FXML
    private ImageView imgProducto;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAceptar;
    @FXML
    private Label lblNombreVentana;
    @FXML
    private Label lblNombreVentana1;
    @FXML
    private TextField txfNombre;
    private int idCafeteria;
    private int idProducto;
    PersonalCafeteria personalCafeteria;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicAñadirImagen(ActionEvent event) {
    }


    @FXML
    private void clicCancelar(ActionEvent event) {
        cambiarVentana();
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
        cambiarVentana();
    }  
    
    private boolean existenCamposInvalidos(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        if(txfNombre.getText().isEmpty() || txfPrecio.getText().isEmpty() || txfDescripcion.getText().isEmpty()){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }
        if(validacion.existeCampoInvalido(txfNombre.getText()) || validacion.existeCampoInvalido(txfPrecio.getText()) 
                || validacion.existeCampoInvalido(txfDescripcion.getText())){
            existe = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }
        return existe;
    }

    public void recibirParametros(PersonalCafeteria p, int idCafeteria1, int idProducto1){
        this.personalCafeteria = p;
        this.idCafeteria = idCafeteria1;
        try {
            if (idProducto1 > 0){
                lblNombreVentana.setText("ModificarProducto");
                lblNombreVentana1.setText("Modificar");
                btnAñadirImagen.setText("Cambiar Imagen");
                this.idProducto = idProducto1;
                ServicioProducto servicioProducto = new ServicioProducto();
                this.personalCafeteria = p;
                this.idCafeteria = idCafeteria1;
                this.txfNombre.setText(servicioProducto.obtenerProductoPorId(idProducto).getNombre());
                this.txfPrecio.setText(Integer.toString(servicioProducto.obtenerProductoPorId(idProducto).getPrecio()));
                this.txfTiempoAproximado.setText(Integer.toString(servicioProducto.obtenerProductoPorId(idProducto).getTiempoAproximado()));
                this.txfDescripcion.setText(servicioProducto.obtenerProductoPorId(idProducto).getDescripcion());
                Image img = new Image(getClass().getResourceAsStream(servicioProducto.obtenerProductoPorId(idProducto1).getRutaImagen()));
                this.imgProducto.setImage(img);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void cerrarVentana(){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private void cambiarVentana(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GInicioProductos.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
            controlador.recibirParametros(3, null, personalCafeteria, idCafeteria);
            cerrarVentana();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}