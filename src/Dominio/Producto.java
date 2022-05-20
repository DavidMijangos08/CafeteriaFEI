
package Dominio;

public class Producto {
    private String nombreProducto;
    private float precioProducto;
    private int tiempoRealizacion;
    private String descripcion;
    private int idProducto;

    public Producto(String nombreProducto, float precioProducto, int tiempoRealizacion, String descripcion) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.tiempoRealizacion = tiempoRealizacion;
        this.descripcion = descripcion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public int getTiempoRealizacion() {
        return tiempoRealizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public void setTiempoRealizacion(int tiempoRealizacion) {
        this.tiempoRealizacion = tiempoRealizacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
}
