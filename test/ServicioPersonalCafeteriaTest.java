/********************************************************************/
/* Archivo: ServicioPersonalCafeteriaTest.java                      */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se crean los test para las            */
/*              llamadas realizadas al servidor                     */
/********************************************************************/

import Dominio.PersonalCafeteria;
import Servicios.ServicioPersonalCafeteria;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServicioPersonalCafeteriaTest {
    
    @Test
    public void agregarNuevoPersonalCafeteriaTest() throws IOException{
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        PersonalCafeteria personalCafeteria = new PersonalCafeteria("Ramon Vargas Velarde", "VAVR950821MOCJRVA3", "ramon@gmail.com", 
                                                                    "Responsable", 3);
        personalCafeteria.setContrasenia("VAVR950821MOCJRVA3");
        int respuesta = servicioPersonalCafeteria.agregarNuevoPersonalCafeteria(3, personalCafeteria);
        int respuestaEsperada = 201;
        assertEquals(respuesta, respuestaEsperada);
    }
    
    @Test
    public void obtenerPersonalPorCredencialTest() throws IOException{
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        PersonalCafeteria personalCafeteria = servicioPersonalCafeteria.obtenerPersonalPorCredencial("LauraHernandez@gmail.com", "MAHL990411MOCJRVA9");
        assertEquals(personalCafeteria.getNombre(), "Laura Martinez Hernandez");
    }
    
    @Test
    public void obtenerPersonalPorIdTest() throws IOException{
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        PersonalCafeteria personalCafeteria = servicioPersonalCafeteria.obtenerPersonalPorId(2);
        assertEquals(personalCafeteria.getNombre(), "Laura Martinez Hernandez");
    }
    
    @Test
    public void obtenerPersonalDeCafeteriaTest() throws IOException{
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        ArrayList<PersonalCafeteria> personalCafeteria = servicioPersonalCafeteria.obtenerPersonalDeCafeteria(2);
        int tamanioEsperado = 2;
        assertEquals(personalCafeteria.size(), tamanioEsperado);
    }
    
    @Test
    public void modificarPersonalCafeteriaTest() throws IOException{
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        PersonalCafeteria personalCafeteria = new PersonalCafeteria("Ramón Vargas Velarde", "VAVR950821MOCJRVA3", "ramon@gmail.com", 
                                                                    "Responsable", 3);
        personalCafeteria.setContrasenia("VAVR950821MOCJRVA3");
        int respuestaObtenida = servicioPersonalCafeteria.modificarPersonalCafeteria(personalCafeteria, 3);
        int respuestaEsperada = 200;
        assertEquals(respuestaObtenida, respuestaEsperada);
    }
    
    @Test
    public void eliminarPersonalCafeteriaTest() throws IOException{
        ServicioPersonalCafeteria servicioPersonalCafeteria = new ServicioPersonalCafeteria();
        int respuestaEseperada = 200;
        int respuestaObtenida = servicioPersonalCafeteria.eliminarPersonalCafeteria(3);
        assertEquals(respuestaEseperada, respuestaObtenida);
    }
}
