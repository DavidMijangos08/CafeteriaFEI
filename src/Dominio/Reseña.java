
package Dominio;

public class Reseña {
    private String titulo;
    private String opinion;
    private int calificacion;
    private int idReseña;

    public Reseña(String titulo, String opinion, int calificacion) {
        this.titulo = titulo;
        this.opinion = opinion;
        this.calificacion = calificacion;
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
    
}
