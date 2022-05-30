
package Servicios;

import Dominio.PersonalCafeteria;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class ServicioPersonalCafeteria {
    
    public PersonalCafeteria obtenerPersonalPorCredencial(String correoElectronico, String contrasenia){
        PersonalCafeteria personalCafeteria = null;
        try {
            URL url = new URL("http://127.0.0.1:9090/personalCafeteria/login/" + correoElectronico);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            String datos = "{" + 
                            "\"contrasenia\":"+ " " + "\"" + contrasenia + "\"" + 
                            "}";
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);
            OutputStream output = conexion.getOutputStream();
            output.write(datos.getBytes());
            output.flush();
            output.close();

            if(conexion.getResponseCode() == 200){
                Reader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                for(int c; (c = entrada.read()) >= 0;){
                    stringBuilder.append((char)c);
                }
                String respuesta = stringBuilder.toString();
                JSONObject miRespuesta = new JSONObject(respuesta);
                int idPersonal = miRespuesta.getInt("idPersonal");
                String nombre = miRespuesta.getString("nombre");
                String CURP = miRespuesta.getString("CURP");
                String cargo = miRespuesta.getString("cargo");
                int idCafeteria = miRespuesta.getInt("idCafeteria"); 
                personalCafeteria = new PersonalCafeteria(idPersonal, nombre, CURP, correoElectronico, cargo, idCafeteria);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personalCafeteria;
    }
}
