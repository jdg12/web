/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
interface BBDD {
    public boolean estaUsuario(String idUsuario);
    public boolean contrasenaCorrecta(String idUsuario, String contrasena);
    public Usuario getUsuario(String idUsuario);
    public ArrayList<Usuario> getUsuarios();
    public void guardarUsuario(Usuario usuario);
    public ArrayList<Reserva> getReservas(String idUsuario);
    public Sesion getSesion(String idSesion);
    public String getTipoEntrada(String idEntrada);
    public Entrada getEntrada(String idEntrada);
    public void modificarUsuario(Usuario usuario);
    public ArrayList<Entrada> getEntradas();
    public void eliminarEntrada(String idEntrada);
    public void eliminarReserva(String idReserva);
    public ArrayList<Pelicula> getPeliculas();
    public Pelicula getPelicula(String idPelicula);
    public ArrayList<Actor> getActores(String idPelicula);
    public ArrayList<Comentario> getComentarios(String idPelicula);
    public void guardarComentario(Comentario comentario);
    public void eliminarPelicula(String idPelicula);
    public void modificarPelicula(Pelicula pelicula);
    public void eliminarEntradasPelicula(String idPelicula);
    public void eliminarReservasPelicula(String idPelicula);
    public void eliminarComentariosPelicula(String idPelicula);
    public void eliminarSesionPelicula(String idPelicula);
    public void borrarActoresPelicula(String idPelicula);
    public void guardarPelicula(Pelicula peli);
    public void guardarActor(Actor actor, String pelicula);
    public boolean estaPelicula(String pelicula);
    public boolean estaActor(String nombre, String apellidos);
    public ArrayList<Sesion> getSesionesPelicula(String idPelicula);
    public Sala getSala(String idSala);
    public boolean estaOcupadoAsiento(String idSesion,int fila, int columna);
    public void guardarEntrada(Entrada entrada);
    public void guardarReserva(Reserva reserva);
    public ArrayList<Sala> getSalas();
    public void eliminarSala(String idSala);
    public void modificarSala(Sala sala);
    public void anadirSala(Sala sala);
    public boolean estaSala(Sala sala);
    public ArrayList<Sesion> getSesiones();
    public void eliminarSesion(String idSesion);
    public void modificarSesion(Sesion sesion);
    public void anadirSesion(Sesion sesion);
    public void eliminarEntradasSesion(String idSesion);
    public void eliminarReservasSesion(String idSesion);
    public ArrayList<Entrada> getEntradasPelicula(String idPelicula);
}
