
import Dominio.ReseñaCafeteria;
import Servicios.ServicioReseñasCafeteria;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ServicioReseñasCafeteriaTest {
    
    @Test
    public void obtenerReseñasDeCafeteria(){
        ServicioReseñasCafeteria servicioReseñasCafeteria = new ServicioReseñasCafeteria();
        ArrayList<ReseñaCafeteria> reseñas = servicioReseñasCafeteria.obtenerReseñasDeCafeteria(2);
        int numeroReseñasEsperada = 2;
        int numeroReseñasObtenidas = reseñas.size();
        assertEquals(numeroReseñasEsperada, numeroReseñasObtenidas);
    }
    
    @Test
    public void agregarNuevaReseñaCafeteria(){
        ServicioReseñasCafeteria servicioReseñasCafeteria = new ServicioReseñasCafeteria();
        ReseñaCafeteria reseña = new ReseñaCafeteria("Reseña dani", "La cafetería es muy buena, de las mejores en la fac", 
                                                    5, "", 2);
        int respuesta = servicioReseñasCafeteria.agregarNuevaReseñaCafeteria(reseña, 2);
        int respuestaEsperada = 201;
        assertEquals(respuesta, respuestaEsperada);
    }
}
