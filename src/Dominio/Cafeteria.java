
package Dominio;

public class Cafeteria {
    private String nombreCafeteria;
    private String facultadPerteneciente;
    private String direccion;
    private String horaInicio;
    private String horaFin;
    private int idCafeteria;
    private boolean estaActiva;
    
    public Cafeteria(String nombreCafeteria, String facultadPerteneciente, String direccion, String horaInicio, String horaFin, boolean estaActiva) {
        this.nombreCafeteria = nombreCafeteria;
        this.facultadPerteneciente = facultadPerteneciente;
        this.direccion = direccion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estaActiva = estaActiva;
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

    public boolean isEstaActiva() {
        return estaActiva;
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

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }
    
    
}
