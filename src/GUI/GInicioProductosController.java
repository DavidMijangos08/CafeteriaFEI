/********************************************************************/
/* Archivo: GInicioProductosController.java                         */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  15/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa la ventana              */
/*              "Cafeteria" con sus metodos                         */
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
import Servicios.ServicioPersonalCafeteria;
import Servicios.ServicioProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GInicioProductosController implements Initializable {

    @FXML
    private VBox vbproductoEscogidoCarta;
    @FXML
    private ScrollPane scpProductos;
    @FXML
    private GridPane gdProductos;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private TextArea txaTituloCafeteria;
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private Button btnVerOpiniones;
    @FXML
    private ImageView imgReloj;
    @FXML
    private Button btnVerCafeteria;
    @FXML
    private Label lblNombreEscogido;
    @FXML
    private ImageView imgProductoEscogido;
    @FXML
    private TextArea txaDescripcionEscogido;
    @FXML
    private Label lblPrecioEscogido;
    @FXML
    private Label lblTiempoAprox;
    @FXML
    private Button btnEliminarProducto;
    @FXML
    private Button btnModificarProducto;
    @FXML
    private Button btnAñadirProducto;
    @FXML
    private Button btnVerPersonal;
    
    private int tipoUsuario;
    private int idCafeteria;
    private int idProductoElegido;
    private Consumidor consumidor = new Consumidor();
    private PersonalCafeteria personalCafeteria = new PersonalCafeteria();
    private MyListenerProducto myListener;
    ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
    ServicioProducto servicioProducto = new ServicioProducto();
    
    public void recibirParametros(int tipoUsuario1, Consumidor c, PersonalCafeteria p, int idCafeteria1){
        try {
            if(c != null){
                this.consumidor = c;
                lblNombreUsuario.setText(c.getNombre());
            }
            if(p != null){
                this.personalCafeteria = p;
                lblNombreUsuario.setText(p.getNombre());
            }
            txaTituloCafeteria.setText(servicioCafeteria.obtenerCafeteriaPorId(idCafeteria1).getNombreCafeteria());
            obtenerProductos(idCafeteria1);
            iniciarVentana(tipoUsuario1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void obtenerProductos(int idCafeteria1){
        this.idCafeteria = idCafeteria1;
        gdProductos.getChildren().clear();
        try {
            ServicioProducto servicioProducto = new ServicioProducto();
            List<Producto> productos = servicioProducto.obtenerProductosDeCafeteria(idCafeteria1);
            if(productos.size()>0){
                setProductoElegido(productos.get(0));
                myListener = new MyListenerProducto(){
                    @Override
                    public void onClickListener(Producto p){
                        setProductoElegido(p);
                    }
                };
            }
            int columna=0;
            int fila=1;
            for(int i = 0; i < productos.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemProducto.fxml"));
                AnchorPane acp = fxmlLoader.load();

                ItemProductoController productoController = fxmlLoader.getController();
                productoController.setProducto(productos.get(i), myListener);

                if(columna == 4){
                    columna = 0;
                    fila++;
                }

                gdProductos.add(acp,columna++,fila);
                //Ajustar el ancho del grid
                gdProductos.setMinWidth(Region.USE_COMPUTED_SIZE);
                gdProductos.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gdProductos.setMaxWidth(Region.USE_COMPUTED_SIZE);
                //Ajustar el alto del grid
                gdProductos.setMinHeight(Region.USE_COMPUTED_SIZE);
                gdProductos.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gdProductos.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(acp, new Insets(5));
            }
        } catch (IOException ex) {
            Logger.getLogger(GInicioProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setProductoElegido(Producto p){
        this.idProductoElegido = p.getIdProducto();
        this.lblNombreEscogido.setText(p.getNombre());
        this.lblPrecioEscogido.setText("$" + Float.toString(p.getPrecio()));
        Image img = new Image(getClass().getResourceAsStream(p.getRutaImagen()));
        this.imgProductoEscogido.setImage(img);
        Image imgReloj = new Image(getClass().getResourceAsStream("/img/reloj.png"));
        this.imgReloj.setImage(imgReloj);
        this.txaDescripcionEscogido.setText(p.getDescripcion());
        lblTiempoAprox.setText(Integer.toString(p.getTiempoAproximado()) + " min");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void clicCerrarSesion(ActionEvent event) {
        try {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("GInicioSesion.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio sesion");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
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
                controlador.recibirParametros(tipoUsuario, null, personalCafeteria,idCafeteria, 5);
            }else if(tipoUsuario == 4){
                GModificarCuentaController controlador = (GModificarCuentaController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario, consumidor, null,  idCafeteria, 5);
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
    private void clicVerOpiniones(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GReseñasProducto.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            if (tipoUsuario == 2) {
                GReseñasProductoController controlador = (GReseñasProductoController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario, consumidor, personalCafeteria, personalCafeteria.getIdCafeteria(), idProductoElegido);
            } else if (tipoUsuario == 4) {
                GReseñasProductoController controlador = (GReseñasProductoController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario, consumidor, personalCafeteria, idCafeteria, idProductoElegido);
            }
            cerrarVentana();
            stage.setTitle("Reseñas producto");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
        //cambiarVentana("/GUI/FXMLPreguntasFrecuentes.fxml", "Preguntas Frecuentes");
    }
    
    private void cambiarVentanaEleccion(){
        try {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("CEleccionCafeteria.fxml")));
            stage.setScene(scenePrincipal);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void clicVerCafeteria(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("GVerCafeteria.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);

            if (tipoUsuario == 2 || tipoUsuario == 3) {
                GVerCafeteriaController controlador = (GVerCafeteriaController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario,null, personalCafeteria, idCafeteria, 5);
            } else if (tipoUsuario == 4) {
                GVerCafeteriaController controlador = (GVerCafeteriaController) fxmlLoader.getController();
                controlador.recibirParametros(tipoUsuario, consumidor, null, idCafeteria, 5);
            }
            cerrarVentana();
            stage.setTitle("Ver cafetería");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void clicEliminarProducto(ActionEvent event) {
        ButtonType cancelar = new ButtonType("Cancelar");
        ButtonType aceptar = new ButtonType("Aceptar");
        Alert a = new Alert(Alert.AlertType.NONE, "¿Deseas eliminar este producto?", cancelar, aceptar);
        a.setTitle("Confirmacion");
        a.setHeaderText("Confirmación");
        a.setResizable(true);
        a.setContentText("¿Deseas eliminar este producto?");
        a.showAndWait().ifPresent(response -> {
            if (response == aceptar) {
                try {
                    ServicioProducto servicioProducto1 = new ServicioProducto();
                    int respuesta = 0;
                    respuesta = servicioProducto1.eliminarProducto(idProductoElegido);
                    MensajeAlerta mensajeAlerta = new MensajeAlerta();
                    if(respuesta == 200){
                        obtenerProductos(idCafeteria);
                        mensajeAlerta.mostrarAlertaGuardado("El producto ha sido eliminado con éxito");
                    }
                } catch (IOException e) {
                    Logger.getLogger(RSVerPersonalController.class.getName()).log(Level.SEVERE, null, e);
                    MensajeAlerta mensajeAlerta = new MensajeAlerta();
                    mensajeAlerta.mostrarAlertaError("Ocurrió un error en el servidor, intenta más tarde");
                    cerrarVentana();
                }
            }
        });
    }

    @FXML
    void clicModificarProducto(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("RCAgregarProducto.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            if (tipoUsuario == 3) {
                RCAgregarProductoController controlador = (RCAgregarProductoController) fxmlLoader.getController();
                controlador.recibirParametros(personalCafeteria, idCafeteria, idProductoElegido);
            }
            cerrarVentana();
            stage.setTitle("Modificar producto");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void clicAñadirProducto(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("RCAgregarProducto.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            if (tipoUsuario == 3) {
                RCAgregarProductoController controlador = (RCAgregarProductoController) fxmlLoader.getController();
                controlador.recibirParametros(personalCafeteria, idCafeteria, -1);
            }
            cerrarVentana();
            stage.setTitle("Agregar producto");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void iniciarVentana(int tipoUsuario1){
        tipoUsuario = tipoUsuario1;
        switch (tipoUsuario1) {
            case 2:
                btnAñadirProducto.setVisible(false);
                btnModificarProducto.setVisible(false);
                btnEliminarProducto.setVisible(false);
                if (idProductoElegido < 1){
                    btnVerOpiniones.setVisible(false);
                }
                break;
            case 3:
                btnVerCafeteria.setVisible(false);
                btnVerOpiniones.setVisible(false);
                btnVerPersonal.setVisible(false);
                break;
            case 4:
                if (idProductoElegido < 1){
                    MensajeAlerta mensajeAlerta = new MensajeAlerta();
                    mensajeAlerta.mostrarAlertaError("No hay productos en esta cafeteria, selecciona otra por favor");
                    cambiarVentanaEleccion();
                    btnVerOpiniones.setVisible(false);
                }else{
                    btnAñadirProducto.setVisible(false);
                    btnModificarProducto.setVisible(false);
                    btnEliminarProducto.setVisible(false);
                    btnVerPersonal.setVisible(false);
                }
                break;
            }

    }

    private void cerrarVentana(){
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clicVerPersonal(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("RSVerPersonal.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            RSVerPersonalController controlador = (RSVerPersonalController) fxmlLoader.getController();
            controlador.recibirParametros(personalCafeteria, idCafeteria);
            cerrarVentana();
            stage.setTitle("Ver personal");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
