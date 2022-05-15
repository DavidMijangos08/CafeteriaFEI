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
        Pattern pattern = Pattern.compile("[!#$%'*+/=?^_`{|}~]");
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
        if(fin > inicio){
            valor = true;
        }
        return valor;
    }
}
