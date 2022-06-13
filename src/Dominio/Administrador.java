/********************************************************************/
/* Archivo: Administrador.java                                      */
/* Programador: David Alexander                                     */
/* Fecha de creación: 18/May/2022                                   */
/* Fecha modificación:  20/May/2022                                 */
/* Descripción: Archivo donde se tiene la clase Administrador       */
/*              con sus metodos y atributos                         */
/********************************************************************/

package Dominio;

public class Administrador {

    private String correoElectronico;
    private String contrasenia;

    public Administrador(String correoElectronico, String contrasenia) {
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
