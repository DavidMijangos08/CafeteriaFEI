
package Dominio;

public class PersonalCafeteria {
    private String nombre;
    private String CURP;
    private String correoElectronico;
    private String cargo;
    private int idCafeteria;
    private String contrasenia;
    private int idPersonal;

    public PersonalCafeteria() {
    }

    public PersonalCafeteria(String nombre, String CURP, String correoElectronico, String cargo, int idCafeteria) {
        this.nombre = nombre;
        this.CURP = CURP;
        this.correoElectronico = correoElectronico;
        this.cargo = cargo;
        this.idCafeteria = idCafeteria;
    }
    
    public PersonalCafeteria(int idPersonal, String nombre, String CURP, String correoElectronico, String cargo, int idCafeteria) {
        this.idPersonal = idPersonal;
        this.nombre = nombre;
        this.CURP = CURP;
        this.correoElectronico = correoElectronico;
        this.cargo = cargo;
        this.idCafeteria = idCafeteria;
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

    public int getIdCafeteria() {
        return idCafeteria;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public String getContrasenia() {
        return contrasenia;
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

    public void setIdCafeteria(int idCafeteria) {
        this.idCafeteria = idCafeteria;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
      
}
