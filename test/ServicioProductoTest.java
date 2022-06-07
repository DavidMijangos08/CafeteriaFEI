
import Dominio.Producto;
import Servicios.ServicioProducto;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ServicioProductoTest {
    
    @Test
    public void obtenerProductosDeCafeteriaTest(){
        ServicioProducto servicioProducto = new ServicioProducto();
        ArrayList<Producto> productos = servicioProducto.obtenerProductosDeCafeteria(2);
        int numeroProductosEsperado = 2;
        int numeroProductosObtenidos = productos.size();
        assertEquals(numeroProductosEsperado, numeroProductosObtenidos);
    }
    
    @Test
    public void agregarNuevoProductoTest(){
        ServicioProducto servicioProducto = new ServicioProducto();
        Producto producto = new Producto("Torta de milanesa", "Rica torta de milanesa de pollo acompañada de vegetales", 
                                         "c://users//david//imagenes//tortaMilanesa.png", 20, 6);
        int respuestaEsperada = 201;
        int respuestaObtenida = servicioProducto.agregarNuevoProducto(producto, 1);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }
    
    @Test
    public void obtenerProductoPorIdTest(){
        ServicioProducto servicioProducto = new ServicioProducto();
        Producto producto = servicioProducto.obtenerProductoPorId(2);
        String nombreProductoEsperado = "Chilaquiles rojos";
        String nombreProductoObtenido = producto.getNombre();
        assertEquals(nombreProductoEsperado, nombreProductoObtenido);
    }
    
    @Test
    public void modificarProductoTest(){
        ServicioProducto servicioProducto = new ServicioProducto();
        Producto producto = new Producto(4, "Torta de milanesa de pollo", "Rica torta de milanesa de pollo acompañada de vegetales", 
                                         "c://users//david//imagenes//tortaMilanesaPollo.png", 20, 6, 1);
        int respuestaEseperada = 200;
        int respuestaObtenida = servicioProducto.modificarProducto(producto, 4);
        assertEquals(respuestaEseperada, respuestaObtenida);
    }
    
    @Test
    public void eliminarProductoTest(){
        ServicioProducto servicioProducto = new ServicioProducto();
        int respuestaEseperada = 200;
        int respuestaObtenida = servicioProducto.eliminarProducto(4);
        assertEquals(respuestaEseperada, respuestaObtenida);
    }
}
