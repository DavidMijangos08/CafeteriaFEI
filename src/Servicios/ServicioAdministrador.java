/********************************************************************/
/* Archivo: ServicioAdministrador.java                              */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se crean los metodos para             */
/*              llamarlos los request del servidor                  */
/********************************************************************/

package Servicios;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioAdministrador {
    
    public int enviarCredencialesAdministrador(String correoElectronico, String contrasenia){
        int respuesta = 0;
        try {
            URL url = new URL("http://127.0.0.1:9090/administradores/" + correoElectronico);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            String datos = "{" + "\"contrasenia\":"+ " " + "\"" + contrasenia + "\"" + "}";
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);
            OutputStream output = conexion.getOutputStream();
            output.write(datos.getBytes());
            output.flush();
            output.close();
            respuesta = conexion.getResponseCode();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    } 
}
