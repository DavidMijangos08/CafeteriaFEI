
package Dominio;

public class PersonalCafeteria {
    private String nombre;
    private String CURP;
    private String correoElectronico;
    private String cargo;
    private String cafeteriaAsociada;
    private int idPersonal;

    public PersonalCafeteria() {
    }

    public PersonalCafeteria(String nombre, String CURP, String correoElectronico, String cargo, String cafeteriaAsociada) {
        this.nombre = nombre;
        this.CURP = CURP;
        this.correoElectronico = correoElectronico;
        this.cargo = cargo;
        this.cafeteriaAsociada = cafeteriaAsociada;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCURP() {
        return CURP;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getCargo() {
        return cargo;
    }

    public String getCafeteriaAsociada() {
        return cafeteriaAsociada;
    }

    public int getIdPersonal() {
        return idPersonal;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setCafeteriaAsociada(String cafeteriaAsociada) {
        this.cafeteriaAsociada = cafeteriaAsociada;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }
      
}
