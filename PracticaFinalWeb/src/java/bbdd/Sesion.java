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
public class Sesion {
    private String pelicula;
    private String sala;
    private Fecha fecha;
    private String hora;

    public Sesion(String pelicula, String sala, Fecha fecha, String hora) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
    }


    
    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }



    @Override
    public String toString() {
        return "Sesion{" + "pelicula=" + pelicula + ", sala=" + sala + ", fecha=" + fecha + ", hora=" + hora + '}';
    }
    
    
    
}
