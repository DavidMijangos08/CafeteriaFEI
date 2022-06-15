/********************************************************************/
/* Archivo: VerCafeteriasController.java                            */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Ver cafeteria" con sus metodos                     */
/********************************************************************/

package GUI;

import Dominio.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Servicios.ServicioCafeteria;
import Servicios.ServicioReseñasCafeteria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GVerCafeteriaController implements Initializable {
    
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private ImageView imgPregunta;
    @FXML
    private VBox vbCafeteria;
    @FXML
    private TextArea txaTituloCafeteria;
    @FXML
    private TextArea txaTituloCafeteriaEncab;
    @FXML
    private Label lblHoraInicio;
    @FXML
    private Label lblHoraFinal;
    @FXML
    private TextArea txaFacultad;
    @FXML
    private TextArea txaDireccion;
    @FXML
    private Button btnRegresar;
    @FXML
    private ScrollPane scpReseñasCafeteria;
    @FXML
    private GridPane gdReseñas;
    @FXML
    private Button btnDejarOpinion;
    
    private int tipoUsuario;
    //private List<ReseñaCafeteria> reseñas = new ArrayList<>();
    private int idCafeteria;
    private int idProducto;
    private int idVentanaOrigen;
    Consumidor consumidor = new Consumidor();
    PersonalCafeteria personalCafeteria = new PersonalCafeteria();
    ServicioCafeteria servicioCafeteria = new ServicioCafeteria();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void clicModificarCuenta(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GModificarCuenta.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            if (tipoUsuario == 2 || tipoUsuario == 3){
                GModificarCuentaController controlador = (GModificarCuentaController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario,null, personalCafeteria,personalCafeteria.getIdCafeteria(), 9);
            }else if(tipoUsuario == 4){
                GModificarCuentaController controlador = (GModificarCuentaController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario, consumidor, null, idCafeteria, 9);
            }
            cerrarVentana();
            stage.setTitle("Modificar cuenta");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
    }

    @FXML
    private void clicRegresar(ActionEvent event) {
        String ruta ="";
        String titulo = "Cafeterías UV";
        if(idVentanaOrigen == 5){
            ruta = "GInicioProductos.fxml";
            titulo = "Productos";
        }else if(idVentanaOrigen == 8){
            ruta = "GReseñasProducto.fxml";
            titulo = "Reseñas producto";
        }else if(idVentanaOrigen == 15){
            ruta = "RSVerPersonal.fxml";
            titulo = "Ver personal";
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(ruta));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            if(idVentanaOrigen == 5){
                GInicioProductosController controlador = (GInicioProductosController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario, consumidor, personalCafeteria, idCafeteria);
            }else if(idVentanaOrigen == 8){
                GReseñasProductoController controlador = (GReseñasProductoController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario, consumidor, personalCafeteria, idCafeteria, idProducto);
            }else if(idVentanaOrigen == 15){
                RSVerPersonalController controlador = (RSVerPersonalController) fxmlLoader.getController();
                controlador.recibirParametros(personalCafeteria, personalCafeteria.getIdCafeteria());
            }
            cerrarVentana();
            stage.setTitle(titulo);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void clicDejarOpinion(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/GUI/CDejarOpinion.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            if (tipoUsuario == 4) {
                CDejarOpinionController controlador = (CDejarOpinionController) fxmlLoader.getController();
                controlador.recibirParametros(consumidor, -1, idCafeteria, 2);
            }
            cerrarVentana();
            stage.setTitle("Dejar opinión");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void obtenerInfoCafeteria(int idCafeteria) throws IOException {
        ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
        Cafeteria c = servicioCafeteria.obtenerCafeteriaPorId(idCafeteria);
        txaTituloCafeteria.setText(c.getNombreCafeteria());
        lblHoraInicio.setText(c.getHoraInicio());
        lblHoraFinal.setText(c.getHoraFin());
        txaFacultad.setText(c.getFacultadPerteneciente());
        txaDireccion.setText(c.getDireccion());
    }

    private void obtenerReseñasCafe(int idCafeteria){
        try {
            ServicioReseñasCafeteria servicioReseñas = new ServicioReseñasCafeteria();
            List<ReseñaCafeteria> reseñas = servicioReseñas.obtenerReseñasDeCafeteria(idCafeteria);

            int fila = 1;
            for(int i = 0; i < reseñas.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemReseñaCafeteria.fxml"));

                AnchorPane acp = fxmlLoader.load();
                acp.setMaxHeight(50);
                acp.setPrefWidth(600);
                ItemReseñaCafeteriaController reseñaController = fxmlLoader.getController();
                reseñaController.setReseña(reseñas.get(i));

                gdReseñas.add(acp,0,fila++);

                //Ajustar el ancho del grid
                gdReseñas.setMinWidth(Region.USE_COMPUTED_SIZE);
                gdReseñas.setMinWidth(Region.USE_COMPUTED_SIZE);
                gdReseñas.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gdReseñas.setMaxWidth(Region.USE_COMPUTED_SIZE);

                //Ajustar el alto del grid
                gdReseñas.setMinHeight(Region.USE_COMPUTED_SIZE);
                gdReseñas.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gdReseñas.setMaxHeight(Region.USE_COMPUTED_SIZE);

                gdReseñas.setAlignment(Pos.CENTER);

                GridPane.setMargin(acp, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(GVerCafeteriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iniciarVentana(int idCafeteria1, Consumidor c, PersonalCafeteria p, int tipoUsuario1, int idVentanaOrigen1){
        this.tipoUsuario = tipoUsuario1;
        this.consumidor =c;
        this.personalCafeteria = p;
        this.idVentanaOrigen = idVentanaOrigen1;
        this.idCafeteria = idCafeteria1;
        if(tipoUsuario1 < 4){
            btnDejarOpinion.setVisible(false);
        }
    }


    public void recibirParametros(int tipoUsuario1, Consumidor c, PersonalCafeteria p, int idCafeteria1, int idVentanaOrigen1){
        try {
            if(p != null){
                lblNombreUsuario.setText(p.getNombre());
            }else if(c != null){
                lblNombreUsuario.setText(c.getNombre());
            }
            this.idCafeteria = idCafeteria1;
            txaTituloCafeteriaEncab.setText(servicioCafeteria.obtenerCafeteriaPorId(idCafeteria1).getNombreCafeteria());
            obtenerInfoCafeteria(idCafeteria1);
            obtenerReseñasCafe(idCafeteria1);
            iniciarVentana(idCafeteria1, c, p, tipoUsuario1, idVentanaOrigen1);
        } catch (IOException ex) {
            Logger.getLogger(GInicioProductosController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentana();
        }
    }

    public void recibirParametrosProducto(int tipoUsuario1, Consumidor c, PersonalCafeteria p, int idCafeteria1, int idProducto1, int idVentanaOrigen1){
        try {
            if(p != null){
                lblNombreUsuario.setText(p.getNombre());
            }else if(c != null){
                lblNombreUsuario.setText(c.getNombre());
            }
            this.idProducto = idProducto1;
            this.idVentanaOrigen = idVentanaOrigen1;
            txaTituloCafeteriaEncab.setText(servicioCafeteria.obtenerCafeteriaPorId(idCafeteria1).getNombreCafeteria());
            obtenerInfoCafeteria(idCafeteria1);
            obtenerReseñasCafe(idCafeteria1);
            iniciarVentana(idCafeteria1, c, p, tipoUsuario1, idVentanaOrigen1);
        } catch (IOException ex) {
            Logger.getLogger(GInicioProductosController.class.getName()).log(Level.SEVERE, null, ex);
            MensajeAlerta mensajeAlerta = new MensajeAlerta();
            mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
            cerrarVentana();
        }
    }

    private void cerrarVentana(){
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
}
