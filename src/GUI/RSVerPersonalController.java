/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Dominio.PersonalCafeteria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Dominio.Producto;
import Servicios.ServicioPersonalCafeteria;
import Servicios.ServicioProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 *
 * @author marie
 */
public class RSVerPersonalController implements Initializable{
    @FXML
    private Button btnModificarCuenta;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnVerPersonal;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnModificarPersonal;
    @FXML
    private Button btnEliminarPersonal;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private ImageView imgPregunta;
    @FXML
    private Button btnVerCafeteria;
    @FXML
    private Button btnAñadirPersonal;
    @FXML
    private ComboBox<String> cmbCargoAñadir;
    @FXML
    private Button btnAceptar;

    private MyListenerPersonal myListener;
    private List<PersonalCafeteria> lPersonal = new ArrayList<>();
    @FXML
    private Label lblNombrePersonal;
    @FXML
    private Label lblCurpPersonal;
    @FXML
    private Label lblCorreoPersonal;
    @FXML
    private Label lblCargoPersonal;
    @FXML
    private GridPane gdPersonal;
    @FXML
    private TextField txfNombreAñadir;
    @FXML
    private TextField txfCurpAñadir;
    @FXML
    private TextField txfCorreoAñadir;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lPersonal.addAll(obtenerPersonal(2));
        if(lPersonal.size()>0){
            setPersonalElegido(lPersonal.get(0));
            myListener = new MyListenerPersonal(){
                @Override
                public void onClickListener(PersonalCafeteria p){
                    setPersonalElegido(p);
                }
            };
        }


        int fila = 0;
        try {
            for(int i = 0; i < lPersonal.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ItemPersonal.fxml"));

                AnchorPane acp = fxmlLoader.load();

                ItemPersonalController pc = fxmlLoader.getController();
                pc.setPersonal(lPersonal.get(i), myListener);

                gdPersonal.add(acp,0,fila++);

                //Ajustar el ancho del grid
                gdPersonal.setMinWidth(Region.USE_COMPUTED_SIZE);
                gdPersonal.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gdPersonal.setMaxWidth(Region.USE_COMPUTED_SIZE);

                //Ajustar el alto del grid
                gdPersonal.setMinHeight(Region.USE_COMPUTED_SIZE);
                gdPersonal.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gdPersonal.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(acp, new Insets(  10));
            }
        } catch (IOException ex) {
            Logger.getLogger(GInicioProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private List<PersonalCafeteria> obtenerPersonal(int idCafeteria){
        ServicioPersonalCafeteria servicioProducto = new ServicioPersonalCafeteria();
        List<PersonalCafeteria> lpersonal = servicioProducto.obtenerListaPersonal(idCafeteria);
        return lpersonal;
    }
    /*private List<PersonalCafeteria> obtenerPersonal(){
        List<PersonalCafeteria> lp = new ArrayList<>();

        for (int i = 0; i<25;i++){
            //En cada iteración se crea un nnuevo objeto de tipo persona
            PersonalCafeteria persona = new PersonalCafeteria();
            persona.setNombre("mario antonio martinez ocasio");
            persona.setCURP("FECG040587HTGSRLK04");
            persona.setCorreoElectronico("juanmartinez@gmail.com");
            persona.setCargo(String.valueOf(i));
            //Se guarda el objeto en la lista
            lp.add(persona);
        }
        return lp;

    }*/

    @FXML
    private void clicModificarCuenta(ActionEvent event) {
    }

    @FXML
    private void clicProductos(ActionEvent event) {
        try {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/GInicioProductos.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void clicCerrarSesion(ActionEvent event) {
        try {
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            Scene scenePrincipal = new Scene(FXMLLoader.load(getClass().getResource("/GUI/GInicioSesion.fxml")));
            stage.setScene(scenePrincipal);
            stage.setTitle("Inicio");
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @FXML
    private void clicModificarPersonal(ActionEvent event) {
    }

    @FXML
    private void clicPreguntas(MouseEvent event) {
    }

    @FXML
    private void clicVerCafeteria(ActionEvent event) {
    }

    @FXML
    private void clicAceptar(ActionEvent event) {
    }

    private void setPersonalElegido(PersonalCafeteria p){
        lblNombrePersonal.setText(p.getNombre());
        lblCurpPersonal.setText(p.getCURP());
        lblCorreoPersonal.setText(p.getCorreoElectronico());
        lblCargoPersonal.setText(p.getCargo());
    }

    private boolean existenCamposInvalidos(){
        boolean existen = false;
        MensajeAlerta mensajeAlerta = new MensajeAlerta();
        Validacion validacion = new Validacion();

        if(txfNombreAñadir.getText().isEmpty() || txfCurpAñadir.getText().isEmpty() || txfCorreoAñadir.getText().isEmpty() || cmbCargoAñadir.getSelectionModel().getSelectedItem().isEmpty()){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen campos vacíos");
        }

        if(validacion.existeCampoInvalido(txfNombreAñadir.getText()) || validacion.existeCampoInvalido(txfCurpAñadir.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos");
        }

        if(validacion.existeCorreoInvalido(txfCorreoAñadir.getText())){
            existen = true;
            mensajeAlerta.mostrarAlertaInformacionInvalida("Existen caracteres inválidos en el correo electrónico");
        }

        return existen;
    }
}