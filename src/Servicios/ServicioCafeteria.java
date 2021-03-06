/********************************************************************/
/* Archivo: ServicioCaferia.java                                    */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se crean los metodos para             */
/*              llamarlos los request del servidor                  */
/********************************************************************/

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
    
    public ArrayList<Cafeteria> obtenerTodasLasCafeterias() throws IOException{
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
            throw new IOException();
        } 
        return cafeterias;
    }
    
    public int agregarNuevaCafeteria(Cafeteria cafeteria) throws IOException{
        int respuesta = 0;
        cafeteria.setFacultadPerteneciente(buscarAbreviacion(cafeteria.getFacultadPerteneciente()));
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
            throw new IOException();
        }
        return respuesta;
    }
    
    public ArrayList<Cafeteria> obtenerCafeteriasDeFacultad(String facultadPerteneciente) throws IOException{
        ArrayList<Cafeteria> cafeterias = new ArrayList<Cafeteria>();
        String abreviacionFacultad = buscarAbreviacion(facultadPerteneciente);
        try{
            URL url = new URL("http://127.0.0.1:9090/cafeterias/" + abreviacionFacultad);
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
            throw new IOException();
        }
        return cafeterias;
    }
    
    public Cafeteria obtenerCafeteriaPorId(int idCafeteria) throws IOException{
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
            throw new IOException();
        }
        return cafeteria;
    }
    
    private String buscarAbreviacion(String facultad){
        String abreviacion = "";
        switch (facultad) {
            case "Facultad de Estadística e Informática":
                abreviacion = "FEI";
                break;
            case "Facultad de Medicina":
                abreviacion = "FMEDICINA";
                break;
            case "Facultad de Humanidades":
                abreviacion = "FHUMANIDADES";
                break;
            case "Facultad de Derecho":
                abreviacion = "FDERECHO";
                break;
            case "Facultad de Ciencias Administrativas y Sociales":
                abreviacion = "FCAS";
                break;
            default:
                break;
        }
        return abreviacion;
    }
}
