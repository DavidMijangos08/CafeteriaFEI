
import Dominio.ReseñaProducto;
import Servicios.ServicioReseñasProducto;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ServicioReseñasProductoTest {
    
    @Test
    public void obtenerReseñasDeProductoTest(){
        ServicioReseñasProducto servicioReseñasProducto = new ServicioReseñasProducto();
        ArrayList<ReseñaProducto> reseñas = servicioReseñasProducto.obtenerReseñasDeProducto(1);
        int numeroReseñasEsperada = 1;
        int numeroReseñasObtenidas = reseñas.size();
        assertEquals(numeroReseñasEsperada, numeroReseñasObtenidas);
    }
    
    @Test
    public void agregarNuevaReseñaProductoTest(){
        ServicioReseñasProducto servicioReseñasProducto = new ServicioReseñasProducto();
        ReseñaProducto reseñaProducto = new ReseñaProducto("Chilaquiles ricos", "Los chilaquiles tanto rojos como verdes "
                                                           + "están muy ricos", 5, "", 2);
        int respuestaEsperada = 201;
        int respuestaObtenida = servicioReseñasProducto.agregarNuevaReseñaProducto(reseñaProducto, 2);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }
    
}
