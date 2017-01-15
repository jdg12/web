/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.util.ArrayList;

/**
 *
 * @author JD
 */
public class Proxy implements BBDD{
    
    private static Proxy instancia;
    private modeloDatos md = new modeloDatos();
    private Sorteo sorteo = new Sorteo();
    
    private Proxy(){
        
    }
    /**
     * Devuelve la instancia de la clase.
     * Acceso controlado a la única instancia. 
     * Otras clases que quieran una referencia a la única instancia de la clase Singleton conseguirán esa instancia 
     * llamando al método estático getInstancia de la clase. 
     * @return Instancia de la clase.
     */
    public static Proxy getInstancia() {
        if (instancia == null) {// Si la instancia es null, se crea.
            System.out.println("Nueva instancia");
            instancia = new Proxy();
            instancia.abrirConexion();
        }
        return instancia;
    }
    
    public modeloDatos getMd() {
        return md;
    }

    public void setMd(modeloDatos md) {
        this.md = md;
    }
    
    @Override
    public void abrirConexion(){
        md.abrirConexion();
    }
    
    @Override
    public boolean estaUsuario(String idUsuario){
        return md.estaUsuario(idUsuario);
    }
    /**
     * Funcion para saber si esa contrasena pertenece a un usuario
     * @param idUsuario usuario de la contrasena
     * @param contrasena contraseña
     * @return true si es valida, false en caso contrario
     */
    @Override
    public boolean contrasenaCorrecta(String idUsuario, String contrasena){
       return md.contrasenaCorrecta(idUsuario, contrasena);
    }
    /**
     * Funcion que devuelve un usuario a traves de su id
     * @param idUsuario id del usuario
     * @return objeto Usuario con ese id
     */
    public Usuario getUsuario(String idUsuario){
        return md.getUsuario(idUsuario);
    }
    /**
     * Funcion para obtener la lista con todos los usuarios
     * @return arraylist con todos los usuarios
     */
    @Override

    public ArrayList<Usuario> getUsuarios(){
        return md.getUsuarios();
    }
    /**
     * Funcion para guardar un usuario
     * @param usuario objeto de tipo usuario que queremos guardar
     */
    @Override
    public void guardarUsuario(Usuario usuario){
        md.guardarUsuario(usuario);
    }
    /**
     * funcion para obtener las reservas de un usuario
     * @param idUsuario id del usuario cuyas reservas necesitamos
     * @return arraylist con todas las reservas
     */
    @Override
    public ArrayList<Reserva> getReservas(String idUsuario){
        return md.getReservas(idUsuario);
    }
    /**
     * funcion para obtener una sesion
     * @param idSesion id de la sesion que queremos
     * @return Objeto sesion
     */
    @Override
    public Sesion getSesion(String idSesion){
        return md.getSesion(idSesion);
    }
    /**
     * Funcion para obtener el tipo de entrada
     * @param idEntrada String que indica el id de la entrada
     * @return "reducida" en caso de ser reducida, "normal" en otro caso
     */
    @Override
    public String getTipoEntrada(String idEntrada){
        return md.getTipoEntrada(idEntrada);
    }
    /**
     * Obtiene una entrada a través de su id
     * @param idEntrada
     * @return objeto de tipo entrada
     */
    @Override
    public Entrada getEntrada(String idEntrada){
        return md.getEntrada(idEntrada);
    }
    /**
     * Funcion para modificar un usuario
     * @param usuario 
     */
    public void modificarUsuario(Usuario usuario){
        md.modificarUsuario(usuario);
    }
    /**
     * Funcion para obtener todas las entradas de la base de datos
     * @return un arrayList con todas las entradas
     */
    @Override

    public ArrayList<Entrada> getEntradas(){
        return md.getEntradas();
    }
    /**
     * Funcion para eliminar la entrada con ese ID
     * @param idEntrada 
     */
    @Override
    public void eliminarEntrada(String idEntrada){
        md.eliminarEntrada(idEntrada);
    }
    /**
     * Funcion para eliminar la reserva con ese ID
     * @param idReserva 
     */
    @Override
    public void eliminarReserva(String idReserva){
        md.eliminarReserva(idReserva);
    }
    /**
     * Funcion apra obtener todas las películas
     * @return 
     */
    @Override
    public ArrayList<Pelicula> getPeliculas(){
        return md.getPeliculas();
    }
    /**
     * Funcion para obtener una pelicula a través de su ID
     * @param idPelicula
     * @return objeto Pelicula
     */
    @Override
    public Pelicula getPelicula(String idPelicula){
        return md.getPelicula(idPelicula);
    }
    /**
     * FUncion para obtener los actores de una pelicula
     * @param idPelicula
     * @return lista con todos los actores
     */
    @Override
    public ArrayList<Actor> getActores(String idPelicula){
        return md.getActores(idPelicula);
    }
    /**
     * Funcion para obtener los comentarios de una pelicula
     * @param idPelicula
     * @return lista con todos los comentarios
     */
    @Override
    public ArrayList<Comentario> getComentarios(String idPelicula){
        return md.getComentarios(idPelicula);
    }
    /**
     * Funcion para guardar un comentario
     * @param comentario 
     */
    @Override
    public void guardarComentario(Comentario comentario){
        md.guardarComentario(comentario);
    }
    /**
     * Funcion que elimina la pelicula con ese id
     * @param idPelicula 
     */
    @Override
    public void eliminarPelicula(String idPelicula){
        md.eliminarPelicula(idPelicula);
    }
    /**
     * Funcion que modifica una pelicula
     * @param pelicula 
     */
    @Override
    public void modificarPelicula(Pelicula pelicula){
        md.modificarPelicula(pelicula);
    }
    /**
     * Funcion para eliminar todas las entradas relacionadas con una pelicula
     * @param idPelicula 
     */
    @Override
    public void eliminarEntradasPelicula(String idPelicula){
        md.eliminarEntradasPelicula(idPelicula);
    }
    /**
     * Elimina las reservas relacionadas con la pelicula pasada como parametro
     * @param idPelicula 
     */
    @Override
    public void eliminarReservasPelicula(String idPelicula){
        md.eliminarReservasPelicula(idPelicula);
    }
    /**
     * Elimina los comentarios relacionados con la pelicula pasada como parametro
     * @param idPelicula 
     */
    @Override
    public void eliminarComentariosPelicula(String idPelicula){
        md.eliminarComentariosPelicula(idPelicula);
    }
    /**
     * Elimina las sesiones de la pelicula pasada como parametro
     * @param idPelicula 
     */
    @Override
    public void eliminarSesionPelicula(String idPelicula){
        md.eliminarSesionPelicula(idPelicula);
    }
    /**
     * Borra los actores de la pelicula pasada como parametro
     * @param idPelicula 
     */
    @Override
    public void borrarActoresPelicula(String idPelicula){
        md.borrarActoresPelicula(idPelicula);
    }
    /**
     * Guarda la pelicula pasada como parametro
     * @param peli 
     */
    @Override
    public void guardarPelicula(Pelicula peli){
        md.guardarPelicula(peli);
    }
    /**
     * Guarda el actor pasado como parametro
     * @param actor
     * @param pelicula 
     */
    @Override
    public void guardarActor(Actor actor, String pelicula){
        md.guardarActor(actor, pelicula);
    }
    /**
     * Devuelve true si esta la pelicula en la bbdd, false en caso contrario
     * @param pelicula
     * @return 
     */
    @Override
    public boolean estaPelicula(String pelicula){
        return md.estaPelicula(pelicula);
    }
    /**
     * Devuelve true si el actor está en la bbdd, false en caso contrario
     * @param nombre
     * @param apellidos
     * @return 
     */
    @Override
    public boolean estaActor(String nombre, String apellidos){
        return md.estaActor(nombre, apellidos);
    }
    /**
     * Obtiene todas las sesiones de la pelicula pasada como parametro
     * @param idPelicula id de la peliucla
     * @return 
     */
    @Override
    public ArrayList<Sesion> getSesionesPelicula(String idPelicula){
        return md.getSesionesPelicula(idPelicula);
    }
    /**
     * Devuelve la sala con ese id
     * @param idSala
     * @return 
     */
    @Override
    public Sala getSala(String idSala){
        return md.getSala(idSala);
    }
    /**
     * Devuelve true si el asiento está ocupado
     * @param idSesion sesion en la que nos encontramos
     * @param fila
     * @param columna
     * @return boolean
     */
    @Override
    public boolean estaOcupadoAsiento(String idSesion,int fila, int columna){
        return md.estaOcupadoAsiento(idSesion, fila, columna);
    }
    /**
     * Guarda en la bbdd la entrada pasada como parametro
     * @param entrada 
     */
    @Override
    public void guardarEntrada(Entrada entrada){
        md.guardarEntrada(entrada);
    }
    /**
     * Guarda la reserva pasada como parametro
     * @param reserva 
     * @param usuario
     */
    @Override
    public void guardarReserva(Usuario usuario,Reserva reserva){
        md.guardarReserva(usuario,reserva);
        sorteo.nuevoParticipante(usuario, reserva);
    }
    /**
     * Obtiene una lista con todas las salas
     * @return 
     */
    @Override
    public ArrayList<Sala> getSalas(){
        return md.getSalas();
    }
    /**
     * Elimina la sala con ese id
     * @param idSala 
     */
    @Override
    public void eliminarSala(String idSala){
        md.eliminarSala(idSala);
    }
    /**
     * Modifica la sala pasada como parametor
     * @param sala 
     */
    @Override
    public void modificarSala(Sala sala){
        md.modificarSala(sala);
    }
    /**
     * Añade la sala pasada como parametor
     * @param sala 
     */
    @Override
    public void anadirSala(Sala sala){
        md.anadirSala(sala);
    }
    /**
     * Devuelve true si ya existe esa sala
     * @param sala
     * @return 
     */
    @Override
    public boolean estaSala(Sala sala){
        return md.estaSala(sala);
    }
    /**
     * Devuelve una lista con todas las sesiones
     * @return 
     */
    @Override
    public ArrayList<Sesion> getSesiones(){
        return md.getSesiones();
    }
    /**
     * Elimina la sesion con ese id
     * @param idSesion 
     */
    @Override
    public void eliminarSesion(String idSesion){
        md.eliminarSesion(idSesion);
    }
    /**
     * Mofifica la sesion pasada como parametro
     * @param sesion 
     */
    @Override
    public void modificarSesion(Sesion sesion){
        md.modificarSesion(sesion);
    }
    /**
     * Añade la sesion en la bbdd
     * @param sesion 
     */
    @Override
    public void anadirSesion(Sesion sesion){
        md.anadirSesion(sesion);
    }
    /**
     * ELimina todas las entradas en una sesion
     * @param idSesion 
     */
    @Override
    public void eliminarEntradasSesion(String idSesion){
        md.eliminarEntradasSesion(idSesion);
    }
    /**
     * Elimina las reservas relacionadas con esa sesion
     * @param idSesion 
     */
    @Override
    public void eliminarReservasSesion(String idSesion){
        md.eliminarReservasSesion(idSesion);
    }
    /**
     * Obtiene todas las entradas relacionadas con una pelicula
     * @param idPelicula
     * @return 
     */
    @Override
    public ArrayList<Entrada> getEntradasPelicula(String idPelicula){
        return md.getEntradasPelicula(idPelicula);
    }
    /**
     * Guarda la relación entre una pelicula y un actor
     * @param pelicula
     * @param actor 
     */
    @Override
    public void relacionActorPelicula(Pelicula pelicula, Actor actor){
        md.relacionActorPelicula(pelicula, actor);
    }
    /**
     * Sirve para saber si es existente una relacion entre una pelicula y un actor
     * @param pelicula
     * @param actor
     * @return 
     */
    @Override
    public boolean estaRelacionActor(Pelicula pelicula, Actor actor){
        return md.estaRelacionActor(pelicula, actor);
    }
    public FichaSorteo getGanadorSorteo(){
        return sorteo.getGanador();
    }
    
    public int sortear(){
        return sorteo.sortear();
        
    }
    public ArrayList<FichaSorteo> getParticipantes(){
        return sorteo.getParticipantes();
    }
    
    public void resetearSorteo(){
        sorteo = new Sorteo();
    }
}
