
import Dominio.PersonalCafeteria;
import Servicios.ServicioPersonalCafeteria;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServicioPersonalCafeteriaTest {
    
    @Test
    public void agregarNuevoPersonalCafeteriaTest(){
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        PersonalCafeteria personalCafeteria = new PersonalCafeteria("Ramon Vargas Velarde", "VAVR950821MOCJRVA3", "ramon@gmail.com", 
                                                                    "Responsable", 2);
        personalCafeteria.setContrasenia("VAVR950821MOCJRVA3");
        int respuesta = servicioPersonalCafeteria.agregarNuevoPersonalCafeteria(3, personalCafeteria);
        int respuestaEsperada = 201;
        assertEquals(respuesta, respuestaEsperada);
    }
    
    @Test
    public void obtenerPersonalPorCredencial(){
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        PersonalCafeteria personalCafeteria = servicioPersonalCafeteria.obtenerPersonalPorCredencial("LauraHernandez@gmail.com", "MAHL990411MOCJRVA9");
        assertEquals(personalCafeteria.getNombre(), "Laura Martinez Hernandez");
    }
    
    @Test
    public void obtenerPersonalPorId(){
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        PersonalCafeteria personalCafeteria = servicioPersonalCafeteria.obtenerPersonalPorId(2);
        assertEquals(personalCafeteria.getNombre(), "Laura Martinez Hernandez");
    }
    
    @Test
    public void obtenerPersonalDeCafeteria(){
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        ArrayList<PersonalCafeteria> personalCafeteria = servicioPersonalCafeteria.obtenerPersonalDeCafeteria(2);
        int tamanioEsperado = 2;
        assertEquals(personalCafeteria.size(), tamanioEsperado);
    }
}
