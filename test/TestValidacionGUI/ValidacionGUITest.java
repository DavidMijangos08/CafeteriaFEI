/********************************************************************/
/* Archivo: ValidacionGUITest.java                                  */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  13/Jun/2022                                 */
/* Descripción: Archivo donde se crean los test para los            */
/*              metodos de validacion                               */
/********************************************************************/

package TestValidacionGUI;

import GUI.Validacion;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ValidacionGUITest {
    @Test
    public void existeCampoInvalidoTest(){
        Validacion validacion = new Validacion();
        String campoInvalido = "DELETE * FROM Producto";
        assertTrue(validacion.existeCampoInvalido(campoInvalido));
    }
    
    @Test
    public void existeCampoVacioTest(){
        Validacion validacion = new Validacion();
        String campoVacio = "";
        assertTrue(validacion.existeCampoVacio(campoVacio));
    }
    
    @Test
    public void existeHoraInvalidaTest(){
        Validacion validacion = new Validacion();
        String horaInicio = "11";
        String horaFin = "10";
        assertTrue(validacion.existeHoraInvalida(horaInicio, horaFin));
    }
    
    @Test
    public void existenHorasIgualesTest(){
        Validacion validacion = new Validacion();
        String horaInicio = "10";
        String horaFin = "10";
        assertTrue(validacion.existenHorasIguales(horaInicio, horaFin));
    }
    
    @Test
    public void existeCorreoInvalidoTest(){
        Validacion validacion = new Validacion();
        String correoInvalido = "este.es.un.correo.invalido@";
        assertTrue(validacion.existeCorreoInvalido(correoInvalido));
    }
    
    @Test
    public void existeContraseniaInvalida(){
        Validacion validacion = new Validacion();
        String contraseniaInvalida = "1234";
        assertTrue(validacion.existeContraseniaInvalida(contraseniaInvalida));
    }  
    
    @Test
    public void existeCURPInvalidaTest(){
        Validacion validacion = new Validacion();
        String CURPInvalida = "MIP05JJDJDJDJMDD";
        assertTrue(validacion.existeCurpInvalida(CURPInvalida));
    }  
}
