import Dominio.Cafeteria;
import Servicios.ServicioCafeteria;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServicioCafeteriaTest {
    
    @Test
    public void agregarNuevaCafeteriaTest(){
        ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
        Cafeteria cafeteria = new Cafeteria("El doc sazón", "Medicina", "Calle Medicos y odontologos s/n Col. Unidad del Bosque",
                                            "8:00", "20:00", true);
        int respuesta = servicioCafeteria.agregarNuevaCafeteria(cafeteria);
        int respuestaEsperada = 201;
        assertEquals(respuesta, respuestaEsperada);
    }
    
    @Test
    public void obtenerTodasLasCafeterias(){
        ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
        ArrayList<Cafeteria> cafeterias = servicioCafeteria.obtenerTodasLasCafeterias();
        int numeroCafeteriasEsperadas = 6;
        int numeroCafeteriasObtenidas = cafeterias.size();
        assertEquals(numeroCafeteriasObtenidas, numeroCafeteriasEsperadas);
    }
    
    @Test
    public void obtenerCafeteriasDeFacultad(){
        ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
        ArrayList<Cafeteria> cafeterias = servicioCafeteria.obtenerCafeteriasDeFacultad("Facultad de Estadística e Informática");
        int numeroCafeteriasEsperadas = 1;
        int numeroCafeteriasObtenidas = cafeterias.size();
        assertEquals(numeroCafeteriasObtenidas, numeroCafeteriasEsperadas);
    }
    
    @Test
    public void obtenerCafeteriaPorId(){
        ServicioCafeteria servicioCafeteria = new ServicioCafeteria();
        Cafeteria cafeteria = servicioCafeteria.obtenerCafeteriaPorId(2);
        String nombreCafeteriaEsperada = "La FEICAF";
        String nombreCafeteriaObtenida = cafeteria.getNombreCafeteria();
        assertEquals(nombreCafeteriaObtenida, nombreCafeteriaEsperada);
    }
}
