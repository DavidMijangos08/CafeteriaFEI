
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ServicioPersonalCafeteria {
    
    public PersonalCafeteria obtenerPersonalPorCredencial(String correoElectronico, String contrasenia){
        PersonalCafeteria personalCafeteria = null;
        try {
            URL url = new URL("http://127.0.0.1:9090/personalCafeteria/login/" + correoElectronico);
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
                JSONObject miRespuesta = new JSONObject(respuesta);
                int idPersonal = miRespuesta.getInt("idPersonal");
                String nombre = miRespuesta.getString("nombre");
                String CURP = miRespuesta.getString("CURP");
                String cargo = miRespuesta.getString("cargo");
                int idCafeteria = miRespuesta.getInt("idCafeteria"); 
                String token = miRespuesta.getString("token");
                personalCafeteria = new PersonalCafeteria(idPersonal, nombre, CURP, correoElectronico, cargo, idCafeteria);
                personalCafeteria.setToken(token);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personalCafeteria;
    }
    
    public PersonalCafeteria obtenerPersonalPorId(int idPersonal){
        PersonalCafeteria personalCafeteria = null;
        try {
            URL url = new URL("http://127.0.0.1:9090/personalCafeteria/idPersonal/" + idPersonal);
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
                JSONObject miRespuesta = new JSONObject(respuesta.toString());
                String nombre = miRespuesta.getString("nombre");
                String CURP = miRespuesta.getString("CURP");
                String correoElectronico = miRespuesta.getString("correoElectronico");
                String cargo = miRespuesta.getString("cargo");
                int idCafeteria = miRespuesta.getInt("idCafeteria"); 
                String token = miRespuesta.getString("token");
                personalCafeteria = new PersonalCafeteria(idPersonal, nombre, CURP, correoElectronico, cargo, idCafeteria);
                personalCafeteria.setToken(token);
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return personalCafeteria;
    }   
        
    public ArrayList<PersonalCafeteria> obtenerPersonalDeCafeteria(int idCafeteria){
        ArrayList<PersonalCafeteria> personalCafeteria = new ArrayList<PersonalCafeteria>();
        try{
            URL url = new URL("http://127.0.0.1:9090/personalCafeteria/" + idCafeteria);
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
                JSONArray arregloDatos = new JSONArray(respuesta.toString());
                for(int i = 0; i < arregloDatos.length(); i++){
                    JSONObject personal = arregloDatos.getJSONObject(i);
                    int idPersonal = personal.getInt("idPersonal");
                    String nombre = personal.getString("nombre");
                    String CURP = personal.getString("CURP");
                    String correoElectronico = personal.getString("correoElectronico");
                    String cargo = personal.getString("cargo");
                    PersonalCafeteria personalCafe = new PersonalCafeteria(idPersonal, nombre, CURP, correoElectronico, cargo, idCafeteria);
                    personalCafeteria.add(personalCafe);
                }
             }
            
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return personalCafeteria;
    }

    public ArrayList<PersonalCafeteria> obtenerListaPersonal(int idCafeteria){
        ArrayList<PersonalCafeteria> personalCafeteria = new ArrayList<PersonalCafeteria>();
        try{
            URL url = new URL("http://127.0.0.1:9090/personalCafeteria/" + idCafeteria);
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
                JSONArray arregloDatos = new JSONArray(respuesta.toString());
                for(int i = 0; i < arregloDatos.length(); i++){
                    JSONObject personal = arregloDatos.getJSONObject(i);
                    String nombre = personal.getString("nombre");
                    String CURP = personal.getString("CURP");
                    String correoElectronico = personal.getString("correoElectronico");
                    String cargo = personal.getString("cargo");
                    PersonalCafeteria personalCafe = new PersonalCafeteria(nombre, CURP, correoElectronico, cargo);
                    personalCafeteria.add(personalCafe);
                }
            }

        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personalCafeteria;
    }
    
    public int agregarNuevoPersonalCafeteria(int idCafeteria, PersonalCafeteria personalCafeteria){
        int respuesta = 0;
        try{
            URL url = new URL("http://127.0.0.1:9090/personalCafeteria/" + idCafeteria);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            String datos = "{" + 
                            "\"nombre\":"+ " " + "\"" + personalCafeteria.getNombre() + "\"" + ", " +
                            "\"CURP\":"+ " " + "\"" + personalCafeteria.getCURP() + "\"" + ", " +
                            "\"correoElectronico\":"+ " " + "\"" + personalCafeteria.getCorreoElectronico() + "\"" + ", " +
                            "\"cargo\":"+ " " + "\"" + personalCafeteria.getCargo() + "\"" + ", " +
                            "\"idCafeteria\":" + idCafeteria + ", " +
                            "\"contrasenia\":"+ " " + "\"" + personalCafeteria.getContrasenia()+ "\"" + 
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
    
    public int modificarPersonalCafeteria(PersonalCafeteria personalCafeteria, int idPersonal){
        int respuesta = 0;
         try{
            URL url = new URL("http://127.0.0.1:9090/personalCafeteria/idPersonal/" + idPersonal);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("PUT");
            String datos = "{" + 
                            "\"nombre\":"+ " " + "\"" + personalCafeteria.getNombre() + "\"" + ", " +
                            "\"CURP\":"+ " " + "\"" + personalCafeteria.getCURP() + "\"" + ", " +
                            "\"correoElectronico\":"+ " " + "\"" + personalCafeteria.getCorreoElectronico() + "\"" + ", " +
                            "\"cargo\":"+ " " + "\"" + personalCafeteria.getCargo() + "\"" + ", " +
                            "\"idCafeteria\":" + personalCafeteria.getIdCafeteria() + ", " +
                            "\"contrasenia\":" + personalCafeteria.getContrasenia() + ", " +
                            "\"token\":"+ " " + "\"" + personalCafeteria.getToken() + "\"" + 
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
    
    public int eliminarPersonalCafeteria(int idPersonal){
        int respuesta = 0;
        try{
            URL url = new URL("http://127.0.0.1:9090/personalCafeteria/idPersonal/" + idPersonal);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("DELETE");
            respuesta = conexion.getResponseCode();
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
    
    
    
}
