
package Servicios;

import Dominio.Cafeteria;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServicioCafeteria {
    
    public ArrayList<Cafeteria> obtenerTodasLasCafeterias(){
        ArrayList<Cafeteria> cafeterias = new ArrayList<Cafeteria>();
        try{
            URL url = new URL("http://127.0.0.1:9090/cafeterias");
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
                    JSONObject cafeteriaIndividual = arregloDatos.getJSONObject(i);
                    int idCafeteria = cafeteriaIndividual.getInt("idCafeteria");
                    String nombreCafeteria = cafeteriaIndividual.getString("nombreCafeteria");
                    String facultadPerteneciente = cafeteriaIndividual.getString("facultadPerteneciente");
                    String direccion = cafeteriaIndividual.getString("direccion");
                    String horaInicio = cafeteriaIndividual.getString("horaInicio");
                    String horaFin = cafeteriaIndividual.getString("horaFin");
                    Cafeteria cafeteria = new Cafeteria(idCafeteria, nombreCafeteria, facultadPerteneciente, direccion, horaInicio, horaFin, true);
                    cafeterias.add(cafeteria);
                }
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return cafeterias;
    }
    
    public int agregarNuevaCafeteria(Cafeteria cafeteria){
        int respuesta = 0;
        try{
            URL url = new URL("http://127.0.0.1:9090/cafeterias");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            String datos = "{" + 
                            "\"nombreCafeteria\":"+ " " + "\"" + cafeteria.getNombreCafeteria() + "\"" + ", " +
                            "\"facultadPerteneciente\":"+ " " + "\"" + cafeteria.getFacultadPerteneciente() + "\"" + ", " +
                            "\"direccion\":"+ " " + "\"" + cafeteria.getDireccion() + "\"" + ", " +
                            "\"horaInicio\":"+ " " + "\"" + cafeteria.getHoraInicio() + "\"" + ", " +
                            "\"horaFin\":"+ " " + "\"" + cafeteria.getHoraFin() + "\"" + 
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
    
    public ArrayList<Cafeteria> obtenerCafeteriasDeFacultad(String facultadPerteneciente){
        ArrayList<Cafeteria> cafeterias = new ArrayList<Cafeteria>();
        String facultadCorregida = convertirCadenaConEspacios(facultadPerteneciente);
        try{
            URL url = new URL("http://127.0.0.1:9090/cafeterias/" + facultadCorregida);
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
                    JSONObject cafeteriaIndividual = arregloDatos.getJSONObject(i);
                    int idCafeteria = cafeteriaIndividual.getInt("idCafeteria");
                    String nombreCafeteria = cafeteriaIndividual.getString("nombreCafeteria");
                    String direccion = cafeteriaIndividual.getString("direccion");
                    String horaInicio = cafeteriaIndividual.getString("horaInicio");
                    String horaFin = cafeteriaIndividual.getString("horaFin");
                    Cafeteria cafeteria = new Cafeteria(idCafeteria, nombreCafeteria, facultadPerteneciente, direccion, horaInicio, horaFin, true);
                    cafeterias.add(cafeteria);
                }
            }   
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cafeterias;
    }
    
    public Cafeteria obtenerCafeteriaPorId(int idCafeteria){
        Cafeteria cafeteria = null;
        try{
            URL url = new URL("http://127.0.0.1:9090/cafeterias/" + idCafeteria);
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
                String nombreCafeteria = miRespuesta.getString("nombreCafeteria");
                String facultadPerteneciente = miRespuesta.getString("facultadPerteneciente");
                String direccion = miRespuesta.getString("direccion");
                String horaInicio = miRespuesta.getString("horaInicio");
                String horaFin = miRespuesta.getString("horaFin");
                boolean activo = miRespuesta.getBoolean("activo");
                cafeteria = new Cafeteria(idCafeteria, nombreCafeteria, facultadPerteneciente, direccion, horaInicio, horaFin, activo);
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cafeteria;
    }
    
    private String convertirCadenaConEspacios(String cadena){
        String facultadCorregida = cadena.replace(" ", "%20");
        System.out.println(facultadCorregida);
        return facultadCorregida;
    }
}
