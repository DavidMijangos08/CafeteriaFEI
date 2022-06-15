package GUI;

import Dominio.Administrador;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ADInicioController {

    @FXML
    private Button btnAltaCafeteria;
    @FXML
    private Button btnAltaResponsable;
    @FXML
    private Button btnCerrarSesion;
    
    private Administrador administrador;
    
    @FXML
    private void clicAltaCafeteria(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ADAltaCafeteria.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            ADAltaCafeteriaController controlador = (ADAltaCafeteriaController) fxmlLoader.getController();
            controlador.recibirParametros(administrador);
            cerrarVentana();
            stage.setTitle("Alta cafetería");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void clicAltaResponsable(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ADAltaResponsable.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            ADAltaResponsableController controlador = (ADAltaResponsableController) fxmlLoader.getController();
            controlador.recibirParametros(administrador);
            cerrarVentana();
            stage.setTitle("Alta responsable");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    
    public void recibirParametros(Administrador a){
        this.administrador = a;
    }
    
    private void cerrarVentana(){
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();
    }
}
