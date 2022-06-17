/********************************************************************/
/* Archivo: ServicioProductoTest.java                               */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se crean los test para las            */
/*              llamadas realizadas al servidor                     */
/********************************************************************/

import Dominio.Producto;
import Servicios.ServicioProducto;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ServicioProductoTest {
    
    @Test
    public void obtenerProductosDeCafeteriaTest() throws IOException{
        ServicioProducto servicioProducto = new ServicioProducto();
        ArrayList<Producto> productos = servicioProducto.obtenerProductosDeCafeteria(2);
        int numeroProductosEsperado = 2;
        int numeroProductosObtenidos = productos.size();
        assertEquals(numeroProductosEsperado, numeroProductosObtenidos);
    }
    
    @Test
    public void agregarNuevoProductoTest() throws IOException{
        ServicioProducto servicioProducto = new ServicioProducto();
        Producto producto = new Producto("Chocomilk", "Refrescante chocomilk servido en vaso mediano", 
                                         "C:/Users/david/Documents/Sexto semestre/Desarrollo de sistemas en red/CafeteriaFEI/src/img/Productos/chocomilk.jpg", 30, 10);
        int respuestaEsperada = 201;
        int respuestaObtenida = servicioProducto.agregarNuevoProducto(producto, 3);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }
    
    @Test
    public void obtenerProductoPorIdTest() throws IOException{
        ServicioProducto servicioProducto = new ServicioProducto();
        Producto producto = servicioProducto.obtenerProductoPorId(2);
        String nombreProductoEsperado = "Chilaquiles rojos";
        String nombreProductoObtenido = producto.getNombre();
        assertEquals(nombreProductoEsperado, nombreProductoObtenido);
    }
    
    @Test
    public void modificarProductoTest() throws IOException{
        ServicioProducto servicioProducto = new ServicioProducto();
        Producto producto = new Producto(8, "Cheeseburger", "Hamburguesa acompañados de los quesos más exquisitos",
                                         "/img/Productos/cheeseburger.png", 18, 20, 1);
        int respuestaEseperada = 200;
        int respuestaObtenida = servicioProducto.modificarProducto(producto, 8);
        assertEquals(respuestaEseperada, respuestaObtenida);
    }
    
    @Test
    public void eliminarProductoTest() throws IOException{
        ServicioProducto servicioProducto = new ServicioProducto();
        int respuestaEseperada = 200;
        int respuestaObtenida = servicioProducto.eliminarProducto(27);
        assertEquals(respuestaEseperada, respuestaObtenida);
    }
}
