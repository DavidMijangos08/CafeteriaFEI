/********************************************************************/
/* Archivo: CDejarOpinionController.java                            */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Dejar opinion" con sus metodos                     */
/********************************************************************/

package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Dominio.Consumidor;
import Dominio.Producto;
import Dominio.ReseñaCafeteria;
import Dominio.ReseñaProducto;
import Servicios.ServicioCafeteria;
import Servicios.ServicioProducto;
import Servicios.ServicioReseñasCafeteria;
import Servicios.ServicioReseñasProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private TextArea txaDescripcion;
    @FXML
    private TextArea txaRutaImagen;
    @FXML
    private TextField txfTituloOpinion;
    @FXML
    private Label lblNombre;
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
    @FXML
    private ImageView imgImagen;
    
    FileChooser fc = new FileChooser();
    int idProducto;
    int idCafeteria;
    int tipoOpinion;
    private String rutaImagen;
    ServicioProducto servicioProducto = new ServicioProducto();
    ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
    Consumidor consumidor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicAgregarImagen(ActionEvent event) {
        try {
            fc.setTitle("Selecciona una imagen");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
            File file = fc.showOpenDialog(null);
            if (file != null){
                txaRutaImagen.setText(file.getAbsolutePath());
                String rutaAnterior = null;
                rutaAnterior = copiarImagen(txaRutaImagen.getText());
                rutaImagen = modificarRutaImagen(rutaAnterior);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void clicRegresar(ActionEvent event) {
        cambiarVentana();
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
        if(validarCampos()){

            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            String tituloOpinion = txfTituloOpinion.getText();
            String opinion = txaOpinion.getText();
            int calificacion = obtenerCalificacion();
            if(rutaImagen == null){
                rutaImagen = "";
            }
            if(idProducto > 0){
                //Reseña Producto
                try {
                    ServicioReseñasProducto servicioReseñasProducto = new ServicioReseñasProducto();
                    ReseñaProducto rp = new ReseñaProducto(tituloOpinion, opinion, calificacion, rutaImagen, idProducto);
                    int respuesta = servicioReseñasProducto.agregarNuevaReseñaProducto(rp, idProducto);
                    if(respuesta == 201){
                        mensajeAlerta.mostrarAlertaGuardado("La reseña se registró con éxito");
                        cambiarVentana();
                    }else if(respuesta == 400){
                        mensajeAlerta.mostrarAlertaInformacionInvalida("Datos existentes, verifica la información de la reseña");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CDejarOpinionController.class.getName()).log(Level.SEVERE, null, ex);
                    mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                    cerrarVentana();
                }
            }else if(idCafeteria > 0){
                //Reseña Cafeteria
                try {
                    ServicioReseñasCafeteria servicioReseñasCafeteria = new ServicioReseñasCafeteria();
                    ReseñaCafeteria rc = new ReseñaCafeteria(tituloOpinion, opinion, calificacion, rutaImagen, idCafeteria);
                    int respuesta = servicioReseñasCafeteria.agregarNuevaReseñaCafeteria(rc, idCafeteria);
                    if(respuesta == 201){
                        mensajeAlerta.mostrarAlertaGuardado("La reseña se registró con éxito");
                        //cambiarVentana();
                    }else if(respuesta == 400){
                        mensajeAlerta.mostrarAlertaInformacionInvalida("Datos existentes, verifica la información de la reseña");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ADAltaCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
                    mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                    cerrarVentana();
                }
            }
        }
    }

    private void cargarDatos(Consumidor c, int idProducto1, int idCafeteria1, int tipoOpinion1){
        try {

            this.lblNombre.setText(c.getNombre());
            this.consumidor =c;
            this.idProducto = idProducto1;
            this.idCafeteria = idCafeteria1;
            this.tipoOpinion = tipoOpinion1;

            if(idProducto > 0){
                lblNombre.setText(servicioProducto.obtenerProductoPorId(idProducto).getNombre());
                txaDescripcion.setText(servicioProducto.obtenerProductoPorId(idProducto).getDescripcion());
                Image img = new Image(getClass().getResourceAsStream(servicioProducto.obtenerProductoPorId(idProducto).getRutaImagen()));
                this.imgImagen.setImage(img);
            }else if(idCafeteria > 0){
                lblNombre.setText(servicioCafeteria.obtenerCafeteriaPorId(idCafeteria).getNombreCafeteria());
                txaDescripcion.setText(servicioCafeteria.obtenerCafeteriaPorId(idCafeteria).getDireccion());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recibirParametros(Consumidor c, int idProducto1, int idCafeteria1, int tipoOpinion1){
        cargarDatos(c, idProducto1, idCafeteria1, tipoOpinion1);
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

    private String copiarImagen(String rutaOrigen) throws IOException {
        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get("src\\img\\Productos");

        Path copiar = Files.copy(origen, destino.resolve(origen.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        return destino +"\\"+copiar.getFileName();
    }

    private String modificarRutaImagen(String rutaActual){
        String rutaNueva = rutaActual.replaceAll("\\\\", "/" );
        String partes[] = rutaNueva.split("src");
        return partes[1];
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


    private void cambiarVentana(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Scene scene = null;
            Stage stage = new Stage();
            String titulo = "Cafeterías UV";
            if(idProducto > 0){
                fxmlLoader.setLocation(getClass().getResource("/GUI/GInicioProductos.fxml"));
                scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                titulo = "Productos";
                GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
                controlador.recibirParametros(4, consumidor, null, idCafeteria);
            }else if(idCafeteria > 0){
                fxmlLoader.setLocation(getClass().getResource("/GUI/GVerCafeteria.fxml"));
                scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                titulo = "Ver cafeteria";
                GVerCafeteriaController controlador = (GVerCafeteriaController) fxmlLoader.getController();
                controlador.recibirParametros(4, consumidor, null, idCafeteria, 5);
            }
            idCafeteria = 0;
            idProducto = 0;
            cerrarVentana();
            stage.setTitle(titulo);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cerrarVentana(){
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
}
