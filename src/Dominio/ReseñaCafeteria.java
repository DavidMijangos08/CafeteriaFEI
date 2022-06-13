/********************************************************************/
/* Archivo: ReseñaCafeteria.java                                    */
/* Programador: Maria Elena                                         */
/* Fecha de creación: 18/May/2022                                   */
/* Fecha modificación:  20/May/2022                                 */
/* Descripción: Archivo donde se tiene la clase ReseñaCafeteria     */
/*              con sus metodos y atributos                         */
/********************************************************************/

package Dominio;

public class ReseñaCafeteria {
    private String titulo;
    private String opinion;
    private String rutaImagen;
    private int calificacion;
    private int idReseña;
    private int idCafeteria;

    public ReseñaCafeteria() {
    }

    public ReseñaCafeteria(String titulo, String opinion, int calificacion, String rutaI, int idCafeteria) {
        this.titulo = titulo;
        this.opinion = opinion;
        this.calificacion = calificacion;
        this.rutaImagen = rutaI;
        this.idCafeteria = idCafeteria;
    }
    
    public ReseñaCafeteria(int idReseña, String titulo, String opinion, int calificacion, String rutaI, int idCafeteria) {
        this.idReseña = idReseña;
        this.titulo = titulo;
        this.opinion = opinion;
        this.calificacion = calificacion;
        this.rutaImagen = rutaI;
        this.idCafeteria = idCafeteria;
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

    public int getIdCafeteria() {
        return idCafeteria;
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

    public void setIdCafeteria(int idCafeteria) {
        this.idCafeteria = idCafeteria;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
}
