/********************************************************************/
/* Archivo: ServicioConsumidorTest.java                             */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se crean los test para las            */
/*              llamadas realizadas al servidor                     */
/********************************************************************/

import Dominio.Consumidor;
import Servicios.ServicioConsumidor;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ServicioConsumidorTest {
 
    @Test
    public void agregarNuevoConsumidorTest(){
        ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
        Consumidor consumidor = new Consumidor("Mario Alberto Castillo Gonzalez", "MarioAlbert@gmail.com", "Mario123");
        int respuestaEsperada = 201;
        int respuestaObtenida = servicioConsumidor.agregarNuevoConsumidor(consumidor);
        assertEquals(respuestaObtenida, respuestaEsperada);
    }
    
    @Test
    public void obtenerConsumidorPorCredencialTest(){
        ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
        Consumidor consumidor = servicioConsumidor.obtenerConsumidorPorCredencial("Rodrigo@gmail.com", "Rodrigo123");
        String nombreCosumidorEsperado = "Rodrigo Sánchez Hernández";
        String nombreConsumidorObtenido = consumidor.getNombre();
        assertEquals(nombreCosumidorEsperado, nombreConsumidorObtenido);
    }
    
    @Test
    public void obtenerConsumidorPorIdTest(){
        ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
        Consumidor consumidor = servicioConsumidor.obtenerConsumidorPorId(2);
        String nombreCosumidorEsperado = "Rodrigo Sánchez Hernández";
        String nombreConsumidorObtenido = consumidor.getNombre();
        assertEquals(nombreCosumidorEsperado, nombreConsumidorObtenido);
    }
    
    @Test
    public void modificarConsumidorTest(){
        ServicioConsumidor servicioConsumidor = new ServicioConsumidor();
        Consumidor consumidor = new Consumidor("Rodrigo Sánchez Hernández", "Rodrigo@gmail.com", "Rodrigo123");
        consumidor.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZENvbnN1bWlkb3IiOjIsIm5vbWJyZSI6IlJvZHJpZ28gU1x1MDBlMW5jaGV6IEhlcm5cdTAwZTFuZGV6IiwiY29ycmVvRWxlY3Ryb25pY28iOiJSb2RyaWdvQGdtYWlsLmNvbSJ9.kIuhGYVjTSRHJaVRgCq80mYe1oVTIupuCSlT7rXySus");
        int respuestaEsperada = 200;
        int respuestaObtenida = servicioConsumidor.modificarConsumidor(consumidor, 2);
        assertEquals(respuestaObtenida, respuestaEsperada);
    }
}
