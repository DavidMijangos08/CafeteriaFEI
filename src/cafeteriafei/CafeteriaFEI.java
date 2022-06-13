/********************************************************************/
/* Archivo: CafeteriaFEI.java                                       */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 18/May/2022                                   */
/* Fecha modificación:  20/May/2022                                 */
/* Descripción: Archivo principal donde se decide direcciona        */
/*              a la ventana "Inicio de Sesion"                     */
/********************************************************************/

package cafeteriafei;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CafeteriaFEI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/GUI/GInicioSesion.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Inicio de Sesión");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}