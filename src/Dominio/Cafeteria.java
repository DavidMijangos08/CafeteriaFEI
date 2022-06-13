/********************************************************************/
/* Archivo: Cafeteria.java                                          */
/* Programador: David Alexander                                     */
/* Fecha de creación: 18/May/2022                                   */
/* Fecha modificación:  20/May/2022                                 */
/* Descripción: Archivo donde se tiene la clase Cafeteria           */
/*              con sus metodos y atributos                         */
/********************************************************************/

package Dominio;

public class Cafeteria {
    private String nombreCafeteria;
    private String facultadPerteneciente;
    private String direccion;
    private String horaInicio;
    private String horaFin;
    private int idCafeteria;
    private boolean activo;
    
    public Cafeteria(String nombreCafeteria, String facultadPerteneciente, String direccion, String horaInicio, String horaFin, boolean activo) {
        this.nombreCafeteria = nombreCafeteria;
        this.facultadPerteneciente = facultadPerteneciente;
        this.direccion = direccion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.activo = activo;
    }
    
    public Cafeteria(int idCafeteria, String nombreCafeteria, String facultadPerteneciente, String direccion, String horaInicio, String horaFin, boolean activo) {
        this.idCafeteria = idCafeteria;
        this.nombreCafeteria = nombreCafeteria;
        this.facultadPerteneciente = facultadPerteneciente;
        this.direccion = direccion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.activo = activo;
    }

    public String getNombreCafeteria() {
        return nombreCafeteria;
    }

    public String getFacultadPerteneciente() {
        return facultadPerteneciente;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public int getIdCafeteria() {
        return idCafeteria;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public void setNombreCafeteria(String nombreCafeteria) {
        this.nombreCafeteria = nombreCafeteria;
    }

    public void setFacultadPerteneciente(String facultadPerteneciente) {
        this.facultadPerteneciente = facultadPerteneciente;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public void setIdCafeteria(int idCafeteria) {
        this.idCafeteria = idCafeteria;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public void desactivarCafeteria(){
        this.activo = false;
    }
    
    @Override
    public String toString(){
        return nombreCafeteria;
    }
    
}
