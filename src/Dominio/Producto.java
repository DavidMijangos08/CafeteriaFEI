
package Dominio;

public class Producto {
    private String descripcion;
    private String rutaImagen;
    private String nombreProducto;
    private float precioProducto;
    private int tiempoRealizacion;
    private int idProducto;

    public Producto() {
    }
    
    public Producto(String nombreProducto, String descripcionProducto, String rutaI, float precioProducto, int tiempoRealizacion){
        this.precioProducto = precioProducto;
        this.nombreProducto = nombreProducto;
        this.rutaImagen = rutaI;
        this.tiempoRealizacion = tiempoRealizacion;
        this.descripcion = descripcionProducto;
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

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
}
