/********************************************************************/
/* Archivo: Validacion.java                                         */
/* Programador: David Alexander                                     */
/* Fecha de creación: 30/May/2022                                   */
/* Fecha modificación:  02/Jun/2022                                 */
/* Descripción: Archivo donde se inicializa los metodos             */
/*              para las validaciones                               */
/********************************************************************/

package GUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
    public boolean existeCampoInvalido(String campo){
        boolean valor = false;
        Pattern pattern = Pattern.compile("[!$%'*+=?^_`{|}~]");
        Matcher mather = pattern.matcher(campo);
        if(mather.find()){  
            valor=true;
        }   
        return valor;  
    }
    
    public boolean existeCampoVacio(String campo){
        boolean valor = false;
        if(campo.isEmpty()){
            valor=true;
        }else if(campo.trim().length()==0){
            valor=true;
        }
        return valor;
    }
    
    public boolean existeHoraInvalida(String horaInicio, String horaFin){
        boolean valor = false;
        int inicio = Integer.parseInt(horaInicio);
        int fin = Integer.parseInt(horaFin);
        if(inicio > fin){
            valor = true;
        }
        return valor;
    }
    
    public boolean existenHorasIguales(String horaInicio, String horaFin){
        boolean valor = false;
        int inicio = Integer.parseInt(horaInicio);
        int fin = Integer.parseInt(horaFin);
        if(inicio == fin){
            valor = true;
        }
        return valor;
    }
    
    public boolean existeCorreoInvalido(String correo){
        boolean existe = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(correo);
        if(!matcher.find()){
           existe = true; 
        }
        return existe;
    }
    
    public boolean existeContraseniaInvalida(String contrasenia){
        boolean existe = false;
        Pattern pattern = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$");
        Matcher matcher = pattern.matcher(contrasenia);
        if(!matcher.find()){
            existe = true;
        }
        return existe;
    }
    
    public boolean existeCurpInvalida(String CURP){
        boolean existe = false;
        String textoValidar = "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" + "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
                   "[HM]{1}" + "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
                   "[B-DF-HJ-NP-TV-Z]{3}" + "[0-9A-Z]{1}[0-9]{1}$";
        Pattern pattern = Pattern.compile(textoValidar);
        Matcher matcher = pattern.matcher(CURP);
        if(!matcher.find()){
            existe = true;
        }    
        return existe;
    }
}
