
package Dominio;

public class Consumidor {
    private String nombre;
    private String correoElectronico;
    private String contrasenia;
    private int idConsumidor;

    public Consumidor(String nombre, String correoElectronico, String contrasenia) {
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    } 

    public int getIdConsumidor() {
        return idConsumidor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public void setIdConsumidor(int idConsumidor) {
        this.idConsumidor = idConsumidor;
    }
   
}
