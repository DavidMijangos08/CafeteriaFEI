/********************************************************************/
/* Archivo: RCAgregarProductoController.java                        */
/* Programador: Eder Ivan                                           */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Agregar producto" con sus metodos                  */
/********************************************************************/

package GUI;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Dominio.PersonalCafeteria;
import Dominio.Producto;;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.nio.*;

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
    private Label lblruta;
    @FXML
    private Label lblNombreVentana1;
    @FXML
    private TextField txfNombre;
    private int idCafeteria;
    private int idProducto;
    private String rutaOrigen;
    private String rutaImagen ="";
    PersonalCafeteria personalCafeteria;
    FileChooser fc = new FileChooser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicAñadirImagen(ActionEvent event) throws IOException {
        fc.setTitle("Selecciona una imagen");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File file = fc.showOpenDialog(null);
        if (file != null){
            lblruta.setText(file.getAbsolutePath());
            rutaOrigen = file.getAbsolutePath();
            copiarImagen();

            System.out.println("rutaimagen copiar "+rutaImagen);
            //Image img = new Image(getClass().getResourceAsStream(rutaImagen));
            //this.imgProducto.setImage(img);
        }
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        cambiarVentana();
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
        try {
            if(!existenCamposInvalidos()){
                String nombreProducto = txfNombre.getText();
                int precioProducto = Integer.parseInt(txfPrecio.getText());
                int tiempoAproximado = Integer.parseInt(txfTiempoAproximado.getText());
                String descripcion = txfDescripcion.getText();
                Producto p = new Producto(nombreProducto, descripcion, rutaImagen, precioProducto, tiempoAproximado);
                ServicioProducto servicioProducto = new ServicioProducto();
                int respuesta = servicioProducto.agregarNuevoProducto(p, idCafeteria);
                MensajeAlerta mensajeAlerta = new MensajeAlerta();
                if(respuesta == 201){
                    mensajeAlerta.mostrarAlertaGuardado("El producto se registró con éxito");
                    cambiarVentana();
                    //REGRESA A LA VENTANA ANTERIOR
                    //ANTES DEL stage.show poner esto stage.setResizable(false);
                }else if(respuesta == 400){
                    System.out.println(p.getNombre());
                    System.out.println(p.getRutaImagen());
                    System.out.println(p.getDescripcion());
                    System.out.println(p.getTiempoAproximado());
                    mensajeAlerta.mostrarAlertaInformacionInvalida("Datos existentes, verifica el nombre del producto");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ADAltaCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentana();
        }
    }  
    
    private boolean existenCamposInvalidos(){
        boolean existe = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();
        if(txfNombre.getText().isEmpty() || txfPrecio.getText().isEmpty() || txfDescripcion.getText().isEmpty() || rutaImagen.isEmpty()){
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
            stage.setTitle("Productos");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void copiarImagen() throws IOException {
        Path origen = Path.of(rutaOrigen);
        Path destino = Path.of("C:\\Users\\marie\\IdeaProjects\\CafeteriaFEI\\src\\img\\Productos");

        Path copiar = Files.copy(origen, destino.resolve(origen.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Se copio a "+destino);
        System.out.println("Se copio a "+copiar.getFileName());
        rutaImagen = destino +"\\"+copiar.getFileName();
    }
}