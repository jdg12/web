/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

/**
 *
 * @author JD
 */
public class Comentario {
    private String idPelicula;
    private String idUsuario;
    private String idComentario;
    private String texto;
    private int puntuacion;

    public Comentario(String idPelicula, String idUsuario, String idComentario, String texto, int puntuacion) {
        this.idPelicula = idPelicula;
        this.idUsuario = idUsuario;
        this.idComentario = idComentario;
        this.texto = texto;
        this.puntuacion = puntuacion;
    }

    public Comentario() {
    }

    public String getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(String idComentario) {
        this.idComentario = idComentario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Comentario{" + "idPelicula=" + idPelicula + ", idUsuario=" + idUsuario + ", idComentario=" + idComentario + ", texto=" + texto + ", puntuacion=" + puntuacion + '}';
    }
    
    
}
