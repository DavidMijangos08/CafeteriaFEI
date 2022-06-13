
package Servicios;

import Dominio.Consumidor;
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


public class ServicioConsumidor {
    
    public int agregarNuevoConsumidor(Consumidor consumidor){
        int respuesta = 0;
        try{
            URL url = new URL("http://127.0.0.1:9090/consumidores");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            String datos = "{" + 
                            "\"nombre\":"+ " " + "\"" + consumidor.getNombre() + "\"" + ", " +
                            "\"correoElectronico\":"+ " " + "\"" + consumidor.getCorreoElectronico() + "\"" + ", " +
                            "\"contrasenia\":"+ " " + "\"" + consumidor.getContrasenia()+ "\"" + 
                            "}";
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);
            OutputStream output = conexion.getOutputStream();
            output.write(datos.getBytes("UTF-8"));
            output.flush();
            output.close();
            respuesta = conexion.getResponseCode(); 
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public int cerrarSesionConsumidor(String correoElectronico){
        int respuesta = 0;
         try{
            URL url = new URL("http://127.0.0.1:9090/consumidor/login/" + correoElectronico);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("PUT");
            respuesta = conexion.getResponseCode(); 
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    public Consumidor obtenerConsumidorPorCredencial(String correoElectronico, String contrasenia){
        Consumidor consumidor = null;
        try{
            URL url = new URL("http://127.0.0.1:9090/consumidor/login/" + correoElectronico);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
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
                JSONObject consumidorObtenido = new JSONObject(respuesta);
                int idConsumidor = consumidorObtenido.getInt("idConsumidor");
                String nombre = consumidorObtenido.getString("nombre");
                String token = consumidorObtenido.getString("token");
                consumidor = new Consumidor(idConsumidor, nombre, correoElectronico, contrasenia);
                consumidor.setToken(token);
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consumidor;
    }
    
    public Consumidor obtenerConsumidorPorId(int idConsumidor){
        Consumidor consumidor = null;
        try{
            URL url = new URL("http://127.0.0.1:9090/consumidor/idConsumidor/" + idConsumidor);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            if(conexion.getResponseCode() == 200){
                BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(conexion.getInputStream()));
                String linea;
                StringBuilder respuesta = new StringBuilder();
                while((linea = entrada.readLine()) != null){
                    respuesta.append(linea);
                }
                entrada.close();
                JSONObject consumidorObtenido = new JSONObject(respuesta.toString());
                String nombre = consumidorObtenido.getString("nombre");
                String correoElectronico = consumidorObtenido.getString("correoElectronico");
                String contrasenia = consumidorObtenido.getString("contrasenia");
                String token = consumidorObtenido.getString("token");
                consumidor = new Consumidor(idConsumidor, nombre, correoElectronico, contrasenia);
                consumidor.setToken(token);
            }
            
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return consumidor;
    }
    
    public int modificarConsumidor(Consumidor consumidor, int idConsumidor){
        int respuesta = 0;
        try{
            URL url = new URL("http://127.0.0.1:9090/consumidor/idConsumidor/" + idConsumidor);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("PUT");
            String datos = "{" + 
                            "\"nombre\":"+ " " + "\"" + consumidor.getNombre() + "\"" + ", " +
                            "\"correoElectronico\":"+ " " + "\"" + consumidor.getCorreoElectronico() + "\"" + ", " +
                            "\"contrasenia\":"+ " " + "\"" + consumidor.getContrasenia() + "\"" + ", " +
                            "\"token\":"+ " " + "\"" + consumidor.getToken()+ "\"" + 
                            "}";
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);
            OutputStream output = conexion.getOutputStream();
            output.write(datos.getBytes("UTF-8"));
            output.flush();
            output.close();
            respuesta = conexion.getResponseCode(); 
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
