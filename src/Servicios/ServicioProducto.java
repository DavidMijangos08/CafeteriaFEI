/********************************************************************/
/* Archivo: ServicioProducto.java                                   */
/* Programador: Eder Ivan                                           */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  10/Jun/2022                                 */
/* Descripción: Archivo donde se crean los metodos para             */
/*              llamarlos los request del servidor                  */
/********************************************************************/

package Servicios;

import Dominio.Producto;
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

public class ServicioProducto {
    
    public ArrayList<Producto> obtenerProductosDeCafeteria(int idCafeteria) throws IOException{
        ArrayList<Producto> productos = new ArrayList<Producto>();
        try{
            URL url = new URL("http://127.0.0.1:9090/productos/" + idCafeteria);
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
                    JSONObject producto = arregloDatos.getJSONObject(i);
                    int idProducto = producto.getInt("idProducto");
                    String nombre = producto.getString("nombre");
                    String descripcion = producto.getString("descripcion");
                    int precio = producto.getInt("precio");
                    int tiempoAproximado = producto.getInt("tiempoAproximado");
                    String rutaImagen = producto.getString("rutaImagen");
                    Producto productoCafeteria = new Producto(idProducto, nombre, descripcion, rutaImagen, precio, tiempoAproximado, idCafeteria);
                    productos.add(productoCafeteria);
                }
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            throw new IOException();
        } 
        return productos;
    }

    public int agregarNuevoProducto(Producto producto, int idCafeteria) throws IOException{
        int respuesta = 0;
        //producto.setRutaImagen(modificarRutaImagen(producto.getRutaImagen()));
        try{
            URL url = new URL("http://127.0.0.1:9090/productos/" + idCafeteria);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            String datos = "{" + 
                            "\"nombre\":"+ " " + "\"" + producto.getNombre() + "\"" + ", " +
                            "\"descripcion\":"+ " " + "\"" + producto.getDescripcion() + "\"" + ", " +
                            "\"precio\":"+ " " + producto.getPrecio() + ", " +
                            "\"tiempoAproximado\":"+ " " + producto.getTiempoAproximado() + ", " +
                            "\"rutaImagen\":" + " " + "\"" + producto.getRutaImagen() + "\"" + ", " +
                            "\"idCafeteria\":"+ " " + idCafeteria + 
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
    
    public Producto obtenerProductoPorId(int idProducto) throws IOException{
        Producto producto = null;
        try{
            URL url = new URL("http://127.0.0.1:9090/productos/idProducto/" + idProducto);
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
                JSONObject productoCafeteria = new JSONObject(respuesta.toString());
                String nombre = productoCafeteria.getString("nombre");
                String descripcion = productoCafeteria.getString("descripcion");
                int tiempoAproximado = productoCafeteria.getInt("tiempoAproximado");
                int precio = productoCafeteria.getInt("precio");
                String rutaImagen = productoCafeteria.getString("rutaImagen");
                int idCafeteria = productoCafeteria.getInt("idCafeteria");
                producto = new Producto(idProducto, nombre, descripcion, rutaImagen, precio, tiempoAproximado, idCafeteria);
            }
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioPersonalCafeteria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | JSONException ex) {
            throw new IOException();
        }
        return producto;
    }
    
    public int modificarProducto(Producto producto, int idProducto) throws IOException{
        int respuesta = 0;
        try{
            URL url = new URL("http://127.0.0.1:9090/productos/idProducto/" + idProducto);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("PUT");
            String datos = "{" + 
                            "\"nombre\":"+ " " + "\"" + producto.getNombre() + "\"" + ", " +
                            "\"descripcion\":"+ " " + "\"" + producto.getDescripcion() + "\"" + ", " +
                            "\"precio\":"+ " " + producto.getPrecio() + ", " +
                            "\"tiempoAproximado\":"+ " " + producto.getTiempoAproximado() + ", " +
                            "\"rutaImagen\":" + " " + "\"" + producto.getRutaImagen() + "\"" + ", " +
                            "\"idCafeteria\":"+ " " + producto.getIdCafeteria() + 
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
    
    public int eliminarProducto(int idProducto) throws IOException{
        int respuesta = 0;
        try{
            URL url = new URL("http://127.0.0.1:9090/productos/idProducto/" + idProducto);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("DELETE");
            respuesta = conexion.getResponseCode();
        }catch (MalformedURLException ex) {
            Logger.getLogger(ServicioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            throw new IOException();
        }
        return respuesta;
    }
    
    /*private String modificarRutaImagen(String rutaActual){
        String rutaNueva = rutaActual.replaceAll("\\\\", "/" );
        String partes[] = rutaNueva.split("src");
        return partes[1];
    }*/
    
}
