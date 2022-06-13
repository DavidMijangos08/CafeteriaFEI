/********************************************************************/
/* Archivo: ServicioReseñaProducto.java                             */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  10/Jun/2022                                 */
/* Descripción: Archivo donde se crean los metodos para             */
/*              llamarlos los request del servidor                  */
/********************************************************************/

package Servicios;

import Dominio.ReseñaProducto;
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

public class ServicioReseñasProducto {
    
    public ArrayList<ReseñaProducto> obtenerReseñasDeProducto(int idProducto){
        ArrayList<ReseñaProducto> reseñas = new ArrayList<ReseñaProducto>();
        try{
            URL url = new URL("http://127.0.0.1:9090/reseniasProducto/" + idProducto);
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
                    JSONObject reseñaIndividual = arregloDatos.getJSONObject(i);
                    int idReseña = reseñaIndividual.getInt("idReseña");
                    String titulo = reseñaIndividual.getString("titulo");
                    String opinion = reseñaIndividual.getString("opinion");
                    int calificacion = reseñaIndividual.getInt("calificacion");
                    String rutaImagen;
                    if(reseñaIndividual.get("rutaImagen").equals(null)){
                        rutaImagen = "";
                    }else{
                        rutaImagen = reseñaIndividual.getString("rutaImagen");
                    }
                    ReseñaProducto reseñaProducto = new ReseñaProducto(idReseña, titulo, opinion, calificacion, rutaImagen, idProducto);
                    reseñas.add(reseñaProducto);
                }
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return reseñas;
    }
    
    public int agregarNuevaReseñaProducto(ReseñaProducto reseña, int idProducto){
        int respuesta = 0;
        try{
            URL url = new URL("http://127.0.0.1:9090/reseniasProducto/" + idProducto);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            String datos = "{" + 
                            "\"titulo\":"+ " " + "\"" + reseña.getTitulo() + "\"" + ", " +
                            "\"opinion\":"+ " " + "\"" + reseña.getOpinion() + "\"" + ", " +
                            "\"calificacion\":"+ " " + "\"" + reseña.getCalificacion() + "\"" + ", " +
                            "\"idProducto\":"+ " " + "\"" + idProducto + "\"" + ", " +
                            "\"rutaImagen\":"+ " " + "\"" + reseña.getRutaImagen() + "\"" + 
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
