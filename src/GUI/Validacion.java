/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author david
 */
public class Validacion {
    public boolean existeCampoInvalido(String campo){
        boolean valor = false;
        Pattern pattern = Pattern.compile("[!$%'*+/=?^_`{|}~]");
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
}
