/********************************************************************/
/* Archivo: ServicioReseñaProductoTest.java                         */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se crean los test para las            */
/*              llamadas realizadas al servidor                     */
/********************************************************************/

import Dominio.ReseñaProducto;
import Servicios.ServicioReseñasProducto;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServicioReseñasProductoTest {
    
    @Test
    public void obtenerReseñasDeProductoTest() throws IOException{
        ServicioReseñasProducto servicioReseñasProducto = new ServicioReseñasProducto();
        ArrayList<ReseñaProducto> reseñas = servicioReseñasProducto.obtenerReseñasDeProducto(1);
        int numeroReseñasEsperada = 1;
        int numeroReseñasObtenidas = reseñas.size();
        assertEquals(numeroReseñasEsperada, numeroReseñasObtenidas);
    }
    
    @Test
    public void agregarNuevaReseñaProductoTest() throws IOException{
        ServicioReseñasProducto servicioReseñasProducto = new ServicioReseñasProducto();
        ReseñaProducto reseñaProducto = new ReseñaProducto("Chilaquiles ricos", "Los chilaquiles tanto rojos como verdes "
                                                           + "están muy ricos", 5, "", 2);
        int respuestaEsperada = 201;
        int respuestaObtenida = servicioReseñasProducto.agregarNuevaReseñaProducto(reseñaProducto, 2);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }
    
}
