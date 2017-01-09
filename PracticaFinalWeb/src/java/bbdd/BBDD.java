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
    /**
     * Funcion para saber si existe el usuario en la bbdd
     * @param idUsuario usuario que buscams
     * @return true si esta, false si no
     */
    public boolean estaUsuario(String idUsuario);
    /**
     * Funcion para saber si esa contrasena pertenece a un usuario
     * @param idUsuario usuario de la contrasena
     * @param contrasena contraseña
     * @return true si es valida, false en caso contrario
     */
    public boolean contrasenaCorrecta(String idUsuario, String contrasena);
    /**
     * Funcion que devuelve un usuario a traves de su id
     * @param idUsuario id del usuario
     * @return objeto Usuario con ese id
     */
    public Usuario getUsuario(String idUsuario);
    /**
     * Funcion para obtener la lista con todos los usuarios
     * @return arraylist con todos los usuarios
     */
    public ArrayList<Usuario> getUsuarios();
    /**
     * Funcion para guardar un usuario
     * @param usuario objeto de tipo usuario que queremos guardar
     */
    public void guardarUsuario(Usuario usuario);
    /**
     * funcion para obtener las reservas de un usuario
     * @param idUsuario id del usuario cuyas reservas necesitamos
     * @return arraylist con todas las reservas
     */
    public ArrayList<Reserva> getReservas(String idUsuario);
    /**
     * funcion para obtener una sesion
     * @param idSesion id de la sesion que queremos
     * @return Objeto sesion
     */
    public Sesion getSesion(String idSesion);
    /**
     * Funcion para obtener el tipo de entrada
     * @param idEntrada String que indica el id de la entrada
     * @return "reducida" en caso de ser reducida, "normal" en otro caso
     */
    public String getTipoEntrada(String idEntrada);
    /**
     * Obtiene una entrada a través de su id
     * @param idEntrada
     * @return objeto de tipo entrada
     */
    public Entrada getEntrada(String idEntrada);
    /**
     * Funcion para modificar un usuario
     * @param usuario 
     */
    public void modificarUsuario(Usuario usuario);
    /**
     * Funcion para obtener todas las entradas de la base de datos
     * @return un arrayList con todas las entradas
     */
    public ArrayList<Entrada> getEntradas();
    /**
     * Funcion para eliminar la entrada con ese ID
     * @param idEntrada 
     */
    public void eliminarEntrada(String idEntrada);
    /**
     * Funcion para eliminar la reserva con ese ID
     * @param idReserva 
     */
    public void eliminarReserva(String idReserva);
    /**
     * Funcion apra obtener todas las películas
     * @return 
     */
    public ArrayList<Pelicula> getPeliculas();
    /**
     * Funcion para obtener una pelicula a través de su ID
     * @param idPelicula
     * @return objeto Pelicula
     */
    public Pelicula getPelicula(String idPelicula);
    /**
     * FUncion para obtener los actores de una pelicula
     * @param idPelicula
     * @return lista con todos los actores
     */
    public ArrayList<Actor> getActores(String idPelicula);
    /**
     * Funcion para obtener los comentarios de una pelicula
     * @param idPelicula
     * @return lista con todos los comentarios
     */
    public ArrayList<Comentario> getComentarios(String idPelicula);
    /**
     * Funcion para guardar un comentario
     * @param comentario 
     */
    public void guardarComentario(Comentario comentario);
    /**
     * Funcion que elimina la pelicula con ese id
     * @param idPelicula 
     */
    public void eliminarPelicula(String idPelicula);
    /**
     * Funcion que modifica una pelicula
     * @param pelicula 
     */
    public void modificarPelicula(Pelicula pelicula);
    /**
     * Funcion para eliminar todas las entradas relacionadas con una pelicula
     * @param idPelicula 
     */
    public void eliminarEntradasPelicula(String idPelicula);
    /**
     * Elimina las reservas relacionadas con la pelicula pasada como parametro
     * @param idPelicula 
     */
    public void eliminarReservasPelicula(String idPelicula);
    /**
     * Elimina los comentarios relacionados con la pelicula pasada como parametro
     * @param idPelicula 
     */
    public void eliminarComentariosPelicula(String idPelicula);
    /**
     * Elimina las sesiones de la pelicula pasada como parametro
     * @param idPelicula 
     */
    public void eliminarSesionPelicula(String idPelicula);
    /**
     * Borra los actores de la pelicula pasada como parametro
     * @param idPelicula 
     */
    public void borrarActoresPelicula(String idPelicula);
    /**
     * Guarda la pelicula pasada como parametro
     * @param peli 
     */
    public void guardarPelicula(Pelicula peli);
    /**
     * Guarda el actor pasado como parametro
     * @param actor
     * @param pelicula 
     */
    public void guardarActor(Actor actor, String pelicula);
    /**
     * Devuelve true si esta la pelicula en la bbdd, false en caso contrario
     * @param pelicula
     * @return 
     */
    public boolean estaPelicula(String pelicula);
    /**
     * Devuelve true si el actor está en la bbdd, false en caso contrario
     * @param nombre
     * @param apellidos
     * @return 
     */
    public boolean estaActor(String nombre, String apellidos);
    /**
     * Obtiene todas las sesiones de la pelicula pasada como parametro
     * @param idPelicula id de la peliucla
     * @return 
     */
    public ArrayList<Sesion> getSesionesPelicula(String idPelicula);
    /**
     * Devuelve la sala con ese id
     * @param idSala
     * @return 
     */
    public Sala getSala(String idSala);
    /**
     * Devuelve true si el asiento está ocupado
     * @param idSesion sesion en la que nos encontramos
     * @param fila
     * @param columna
     * @return boolean
     */
    public boolean estaOcupadoAsiento(String idSesion,int fila, int columna);
    /**
     * Guarda en la bbdd la entrada pasada como parametro
     * @param entrada 
     */
    public void guardarEntrada(Entrada entrada);
    /**
     * Guarda la reserva pasada como parametor
     * @param reserva 
     */
    public void guardarReserva(Reserva reserva);
    /**
     * Obtiene una lista con todas las salas
     * @return 
     */
    public ArrayList<Sala> getSalas();
    /**
     * Elimina la sala con ese id
     * @param idSala 
     */
    public void eliminarSala(String idSala);
    /**
     * Modifica la sala pasada como parametor
     * @param sala 
     */
    public void modificarSala(Sala sala);
    /**
     * Añade la sala pasada como parametor
     * @param sala 
     */
    public void anadirSala(Sala sala);
    /**
     * Devuelve true si ya existe esa sala
     * @param sala
     * @return 
     */
    public boolean estaSala(Sala sala);
    /**
     * Devuelve una lista con todas las sesiones
     * @return 
     */
    public ArrayList<Sesion> getSesiones();
    /**
     * Elimina la sesion con ese id
     * @param idSesion 
     */
    public void eliminarSesion(String idSesion);
    /**
     * Mofifica la sesion pasada como parametro
     * @param sesion 
     */
    public void modificarSesion(Sesion sesion);
    /**
     * Añade la sesion en la bbdd
     * @param sesion 
     */
    public void anadirSesion(Sesion sesion);
    /**
     * ELimina todas las entradas en una sesion
     * @param idSesion 
     */
    public void eliminarEntradasSesion(String idSesion);
    /**
     * Elimina las reservas relacionadas con esa sesion
     * @param idSesion 
     */
    public void eliminarReservasSesion(String idSesion);
    /**
     * Obtiene todas las entradas relacionadas con una pelicula
     * @param idPelicula
     * @return 
     */
    public ArrayList<Entrada> getEntradasPelicula(String idPelicula);
    /**
     * Guarda la relación entre una pelicula y un actor
     * @param pelicula
     * @param actor 
     */
    public void relacionActorPelicula(Pelicula pelicula, Actor actor);
    /**
     * Sirve para saber si es existente una relacion entre una pelicula y un actor
     * @param pelicula
     * @param actor
     * @return 
     */
    public boolean estaRelacionActor(Pelicula pelicula, Actor actor);
}
