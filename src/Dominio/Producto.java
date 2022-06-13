
package Dominio;

public class Producto {
    private String descripcion;
    private String rutaImagen;
    private String nombre;
    private int precio;
    private int tiempoAproximado;
    private int idProducto;
    private int idCafeteria;

    public Producto() {
    }
    public Producto(String nombreProducto, String descripcionProducto, String rutaI, int precioProducto){
        this.precio = precioProducto;
        this.nombre = nombreProducto;
        this.rutaImagen = rutaI;
        this.descripcion = descripcionProducto;
    }
    public Producto(String nombreProducto, String descripcionProducto, String rutaI, int precioProducto, int tiempoRealizacion){
        this.precio = precioProducto;
        this.nombre = nombreProducto;
        this.rutaImagen = rutaI;
        this.tiempoAproximado = tiempoRealizacion;
        this.descripcion = descripcionProducto;
    }

    public Producto(int idProducto, String nombreProducto, String descripcionProducto, String rutaI, int precioProducto, int tiempoRealizacion, int idCafeteria){
        this.idProducto = idProducto;
        this.precio = precioProducto;
        this.nombre = nombreProducto;
        this.rutaImagen = rutaI;
        this.tiempoAproximado = tiempoRealizacion;
        this.descripcion = descripcionProducto;
        this.idCafeteria = idCafeteria;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getTiempoAproximado() {
        return tiempoAproximado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getIdCafeteria() {
        return idCafeteria;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setTiempoAproximado(int tiempoAproximado) {
        this.tiempoAproximado = tiempoAproximado;
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

    public void setIdCafeteria(int idCafeteria) {
        this.idCafeteria = idCafeteria;
    }
    
}
