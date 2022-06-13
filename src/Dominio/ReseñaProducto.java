/********************************************************************/
/* Archivo: ReseñaProducto.java                                     */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 18/May/2022                                   */
/* Fecha modificación:  20/May/2022                                 */
/* Descripción: Archivo donde se tiene la clase ReseñaProducto      */
/*              con sus metodos y atributos                         */
/********************************************************************/

package Dominio;

public class ReseñaProducto {
    private String titulo;
    private String opinion;
    private String rutaImagen;
    private int calificacion;
    private int idReseña;
    private int idProducto;

    public ReseñaProducto() {
    }

    public ReseñaProducto(String titulo, String opinion, int calificacion, String rutaImagen, int idProducto) {
        this.titulo = titulo;
        this.opinion = opinion;
        this.calificacion = calificacion;
        this.rutaImagen = rutaImagen;
        this.idProducto = idProducto;
    }
    
    public ReseñaProducto(int idReseña, String titulo, String opinion, int calificacion, String rutaImagen, int idProducto) {
        this.idReseña = idReseña;
        this.titulo = titulo;
        this.opinion = opinion;
        this.calificacion = calificacion;
        this.rutaImagen = rutaImagen;
        this.idProducto = idProducto;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getOpinion() {
        return opinion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public int getIdReseña() {
        return idReseña;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public void setIdReseña(int idReseña) {
        this.idReseña = idReseña;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
}
