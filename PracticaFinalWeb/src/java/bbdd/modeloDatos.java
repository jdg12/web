/**
 *
 * @author jesus
 */
package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class modeloDatos implements BBDD {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void abrirConexion() {
        String sURL = "jdbc:odbc:mvc";
        String url = "jdbc:derby://localhost:1527/finalweb3";
        String usuario = "final";
        String contrasena = "final";
        try {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //    con = DriverManager.getConnection(sURL,"","");
            con = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Se ha conectado");
        } catch (Exception e) {
            System.out.println("No se ha conectado");
        }
    }

    @Override
    public boolean estaUsuario(String idUsuario) {
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT IDUSUARIO FROM USUARIO");
            while (rs.next()) {
                if (rs.getString("IDUSUARIO").equals(idUsuario)) {
                    return true;
                }
            }
            rs.close();
            return false;
        } catch (Exception e) {
            System.out.println("Error en la consulta del usuario: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean contrasenaCorrecta(String idUsuario, String contrasena) {
        Usuario us = this.getUsuario(idUsuario);
        if (us != null) {
            if (contrasena.equals(us.getContrasena())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Usuario getUsuario(String idUsuario) {
        Usuario usuario = new Usuario();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM USUARIO WHERE IDUSUARIO ='" + idUsuario + "'");
            rs.next();
            usuario.setIdUsuario(rs.getString(1));
            usuario.setNombre(rs.getString(2));
            usuario.setApellidos(rs.getString(3));
            usuario.setDireccion(rs.getString(4));
            usuario.setCorreo(rs.getString(5));
            usuario.setContrasena(rs.getString(6));
            usuario.setCuenta(rs.getString(7));
            rs.close();
        } catch (Exception e) {
            System.out.println("Error en la consulta del usuario: " + idUsuario + " ," + e.getMessage());
        }
        return usuario;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO USUARIO (IDUSUARIO, NOMBRE, APELLIDOS, CORREO, DIRECCION, CONTRASENA, CUENTA) "
                    + "VALUES ('" + usuario.getIdUsuario()
                    + "', '" + usuario.getNombre()
                    + "', '" + usuario.getApellidos()
                    + "', '" + usuario.getCorreo()
                    + "', '" + usuario.getDireccion()
                    + "', '" + usuario.getContrasena()
                    + "', '" + usuario.getCuenta()
                    + "')");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No se ha guardado el usuario" + e.getMessage());
        }
    }

    @Override
    public ArrayList<Reserva> getReservas(String idUsuario) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        Reserva reserva = null;
        ResultSet rs;
        try {
            set = con.createStatement();
            if (idUsuario.equals("admin")) {
                rs = set.executeQuery("SELECT * FROM RESERVA");
            } else {
                rs = set.executeQuery("SELECT * FROM RESERVA WHERE IDUSUARIO='" + idUsuario + "'");
            }
            while (rs.next()) {
                reserva = new Reserva();
                Entrada e;
                String usuario = rs.getString(2);
                String idReserva = rs.getString(3);
                String idEntrada = rs.getString(1);
                String tipo = this.getTipoEntrada(idEntrada);
                if (tipo.equals("reducida")) {
                    e = (EntradaReducida) this.getEntrada(idEntrada);
                } else {
                    e = (EntradaNormal) this.getEntrada(idEntrada);
                }
                reserva.setEntrada(e);
                reserva.setIdUsuario(usuario);
                reserva.setIdReserva(idReserva);
                reservas.add(reserva);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
        }
        return reservas;
    }

    @Override
    public String getTipoEntrada(String idEntrada) {
        String tipo = "";
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT TIPO FROM ENTRADA WHERE IDENTRADA='" + idEntrada + "'");
            rs.next();
            tipo = rs.getString(1);
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al recuperar el tipo de la entrada: " + e.getMessage());
        }
        return tipo;
    }

    @Override
    public Entrada getEntrada(String idEntrada) {
        Entrada entrada = null;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM ENTRADA WHERE IDENTRADA='" + idEntrada + "'");
            rs.next();
            String tipo = rs.getString(5);
            if (tipo.equals("reducida")) {
                entrada = new EntradaReducida();
            } else {
                entrada = new EntradaNormal();
            }
            entrada.setId(rs.getString(1));
            int fila = Integer.valueOf(rs.getString(3));
            int columna = Integer.valueOf(rs.getString(4));
            double precio = Double.parseDouble(rs.getString(6));
            boolean vendida = Boolean.valueOf(rs.getString(7));

            entrada.setSesion(this.getSesion(rs.getString(2)));
            entrada.setFila(fila);
            entrada.setColumna(columna);
            entrada.setPrecio(precio);
            entrada.setVendida(vendida);
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al recuperar la entrada: " + e.getMessage());
        }
        return entrada;
    }

    @Override
    public Sesion getSesion(String idSesion) {
        Sesion sesion = new Sesion();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM SESION WHERE IDSESION='" + idSesion + "'");
            rs.next();
            sesion.setIdSesion(rs.getString(1));
            sesion.setPelicula(rs.getString(2));
            sesion.setSala(rs.getString(3));
            sesion.setHora(rs.getString(4));
            sesion.setDiaSemana(rs.getString(5));
            sesion.setDiaMes(rs.getString(6));
            sesion.setMes(rs.getString(7));
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al recuperar la sesion");
        }
        return sesion;
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE USUARIO "
                    + "SET nombre='" + usuario.getNombre()
                    + "',apellidos='" + usuario.getApellidos()
                    + "',correo='" + usuario.getCorreo()
                    + "', cuenta='" + usuario.getCuenta()
                    + "', direccion='" + usuario.getDireccion()
                    + "' "
                    + "WHERE idusuario='" + usuario.getIdUsuario()
                    + "'"
                    + "");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("Error al modificar el usuario " + usuario.getIdUsuario() + " ," + e.getMessage());
        }
    }

    @Override
    public ArrayList<Entrada> getEntradas() {
        ArrayList<Entrada> entradas = new ArrayList<>();
        ResultSet rs;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM ENTRADA");
            while (rs.next()) {
                String idEntrada = rs.getString(1);
                String idSesion = rs.getString(2);
                int fila = Integer.valueOf(rs.getString(3));
                int columna = Integer.valueOf(rs.getString(4));
                double precio = Double.parseDouble(rs.getString(6));
                boolean vendida = Boolean.valueOf(rs.getString(7));
                String tipo = this.getTipoEntrada(idEntrada);
                Entrada entrada;
                if (tipo.equals("reducida")) {
                    entrada = new EntradaReducida();
                } else {
                    entrada = new EntradaNormal();
                }
                entrada.setId(idEntrada);
                entrada.setSesion(this.getSesion(idSesion));
                entrada.setFila(fila);
                entrada.setColumna(columna);
                entrada.setPrecio(precio);
                entrada.setVendida(vendida);
                entradas.add(entrada);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener las entradas: " + e.getMessage());
        }
        return entradas;
    }

    @Override
    public void eliminarEntrada(String idEntrada) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM ENTRADA"
                    + " WHERE IDENTRADA= '" + idEntrada
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar la entrada" + e.getMessage());
        }
    }

    @Override
    public void eliminarReserva(String idReserva) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM RESERVA"
                    + " WHERE IDENTRADA= '" + idReserva
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar la entrada" + e.getMessage());
        }
    }

    @Override
    public ArrayList<Pelicula> getPeliculas() {
        this.abrirConexion();
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM PELICULA");
            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setNombre(rs.getString(1));
                pelicula.setSinopsis(rs.getString(2));
                pelicula.setPagina(rs.getString(3));
                pelicula.setTitulo(rs.getString(4));
                pelicula.setGenero(rs.getString(5));
                pelicula.setNacionalidad(rs.getString(6));
                pelicula.setDuracion(Integer.valueOf(rs.getString(7)));
                pelicula.setAno(Integer.valueOf(rs.getString(8)));
                pelicula.setDistribuidora(rs.getString(9));
                pelicula.setDirector(rs.getString(10));
                pelicula.setClasificacion(Integer.valueOf(rs.getString(11)));
                pelicula.setOtros(rs.getString(12));
                peliculas.add(pelicula);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener las peliculas: " + e.getMessage());
        }
        return peliculas;
    }

    @Override
    public Pelicula getPelicula(String idPelicula) {
        Pelicula pelicula = new Pelicula();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM PELICULA WHERE NOMBRE='" + idPelicula + "'");
            rs.next();
            pelicula.setNombre(rs.getString(1));
            pelicula.setSinopsis(rs.getString(2));
            pelicula.setPagina(rs.getString(3));
            pelicula.setTitulo(rs.getString(4));
            pelicula.setGenero(rs.getString(5));
            pelicula.setNacionalidad(rs.getString(6));
            pelicula.setDuracion(Integer.valueOf(rs.getString(7)));
            pelicula.setAno(Integer.valueOf(rs.getString(8)));
            pelicula.setDistribuidora(rs.getString(9));
            pelicula.setDirector(rs.getString(10));
            pelicula.setClasificacion(Integer.valueOf(rs.getString(11)));
            pelicula.setOtros(rs.getString(12));

            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la pelicula: " + e.getMessage());
        }
        return pelicula;
    }

    @Override
    public ArrayList<Actor> getActores(String idPelicula) {
        ArrayList<Actor> actores = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM RELACTORPEL WHERE NOMBREPELICULA='" + idPelicula + "'");
            while (rs.next()) {
                Actor actor = new Actor();
                actor.setNombre(rs.getString(2));
                actor.setApellidos(rs.getString(3));
                actores.add(actor);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener los actores: " + e.getMessage());
        }
        return actores;
    }

    @Override
    public ArrayList<Comentario> getComentarios(String idPelicula) {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM COMENTARIO WHERE IDPELICULA='" + idPelicula + "'");
            while (rs.next()) {
                Comentario c = new Comentario();
                c.setIdPelicula(rs.getString(1));
                c.setIdUsuario(rs.getString(2));
                c.setIdComentario(rs.getString(3));
                c.setTexto(rs.getString(4));
                c.setPuntuacion(Integer.valueOf(rs.getString(5)));
                comentarios.add(c);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener los comentarios: " + e.getMessage());
        }
        return comentarios;
    }

    @Override
    public void guardarComentario(Comentario comentario) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO COMENTARIO (IDPELICULA, IDUSUARIO, IDCOMENTARIO, TEXTO, PUNTUACION) VALUES ('" + comentario.getIdPelicula()
                    + "', '" + comentario.getIdUsuario()
                    + "', '" + comentario.getIdComentario()
                    + "', '" + comentario.getTexto()
                    + "', " + comentario.getPuntuacion()
                    + ")");
            System.out.println("Estoy aqui: ");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No se ha guardado el comentario " + e.getMessage());
        }
    }

    @Override
    public void eliminarPelicula(String idPelicula) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM PELICULA"
                    + " WHERE NOMBRE= '" + idPelicula
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void modificarPelicula(Pelicula pelicula) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE PELICULA "
                    + "SET nombre='" + pelicula.getNombre()
                    + "',sinopsis='" + pelicula.getSinopsis()
                    + "',pagina='" + pelicula.getPagina()
                    + "',titulor='" + pelicula.getTitulo()
                    + "',genero='" + pelicula.getGenero()
                    + "',nacionalidad='" + pelicula.getNacionalidad()
                    + "',duracion=" + pelicula.getDuracion()
                    + ",ano=" + pelicula.getAno()
                    + ",distribuidora='" + pelicula.getDistribuidora()
                    + "',director='" + pelicula.getDirector()
                    + "',clasificacion=" + pelicula.getClasificacion()
                    + ",otros='" + pelicula.getOtros()
                    + "' "
                    + "WHERE nombre='" + pelicula.getNombre()
                    + "'"
                    + "");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("Error al modificar la pelicula " + pelicula.getNombre() + " ," + e.getMessage());
        }
    }

    @Override
    public void eliminarEntradasPelicula(String idPelicula) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM ENTRADA"
                    + " WHERE IDSESION IN (SELECT IDSESION FROM SESION WHERE IDPELICULA = '" + idPelicula
                    + "')");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar las entradas de la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void eliminarReservasPelicula(String idPelicula) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM RESERVA"
                    + " WHERE IDENTRADA IN (SELECT IDENTRADA FROM ENTRADA WHERE IDSESION IN (SELECT IDSESION FROM SESION WHERE IDPELICULA = '" + idPelicula
                    + "'))");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar las reservas de la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void eliminarComentariosPelicula(String idPelicula) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM COMENTARIO"
                    + " WHERE IDPELICULA = '" + idPelicula
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar los comentarios de la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void eliminarSesionPelicula(String idPelicula) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM SESION"
                    + " WHERE IDPELICULA = '" + idPelicula
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar las sesiones de la pelicula: " + e.getMessage());
        }
    }

    @Override
    public void borrarActoresPelicula(String idPelicula) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM RELACTORPEL"
                    + " WHERE NOMBREPELICULA = '" + idPelicula
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar los actores " + e.getMessage());
        }
    }

    @Override
    public void guardarPelicula(Pelicula peli) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO PELICULA (NOMBRE, SINOPSIS, PAGINA, TITULOR, GENERO, NACIONALIDAD, DURACION, ANO, DISTRIBUIDORA, DIRECTOR, CLASIFICACION,OTROS) "
                    + "VALUES ('" + peli.getNombre()
                    + "', '" + peli.getSinopsis()
                    + "', '" + peli.getPagina()
                    + "', '" + peli.getTitulo()
                    + "', '" + peli.getGenero()
                    + "', '" + peli.getNacionalidad()
                    + "', " + peli.getDuracion()
                    + ", " + peli.getAno()
                    + ", '" + peli.getDistribuidora()
                    + "', '" + peli.getDirector()
                    + "', " + peli.getClasificacion()
                    + ", '" + peli.getOtros()
                    + "')");

            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No se ha guardado la pelicula" + e.getMessage());
        }
    }

    @Override
    public void guardarActor(Actor actor, String pelicula) {

        try {
            set = con.createStatement();
            String consulta = "INSERT INTO ACTOR(NOMBRE, APELLIDOS) VALUES('";
            consulta += actor.getNombre()
                    + "', '" + actor.getApellidos()
                    + "')";

            set.executeUpdate(consulta);

            set = con.createStatement();
            consulta = "INSERT INTO RELACTORPEL(NOMBREPELICULA,NOMBREACTOR,APELLIDOS) VALUES('";
            consulta += pelicula
                    + "', '" + actor.getNombre()
                    + "', '" + actor.getApellidos()
                    + "')";

            set.executeUpdate(consulta);
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(modeloDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean estaPelicula(String pelicula) {
        try {
            set = con.createStatement();
            rs = set.executeQuery("select nombre from pelicula");
            while (rs.next()) {
                if (rs.getString("nombre").equals(pelicula)) {
                    rs.close();
                    set.close();
                    return true;
                }
            }
            rs.close();
            set.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(modeloDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean estaActor(String nombre, String apellidos) {
        try {
            set = con.createStatement();
            rs = set.executeQuery("select nombre,apellidos from actor");
            while (rs.next()) {
                if (rs.getString("nombre").equals(nombre) && rs.getString("apellidos").equals(apellidos)) {
                    rs.close();
                    set.close();
                    return true;
                }
            }
            rs.close();
            set.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(modeloDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Sesion> getSesionesPelicula(String idPelicula) {
        ArrayList<Sesion> sesiones = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM SESION WHERE IDPELICULA = '" + idPelicula + "'");
            while (rs.next()) {
                Sesion sesion = new Sesion();
                sesion.setIdSesion(rs.getString(1));
                sesion.setPelicula(rs.getString(2));
                sesion.setSala(rs.getString(3));
                sesion.setDiaMes(rs.getString(5));
                sesion.setDiaSemana(rs.getString(4));
                sesion.setMes(rs.getString(6));
                sesiones.add(sesion);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener las sesiones de la película: " + e.getMessage());
        }
        return sesiones;
    }

    @Override
    public Sala getSala(String idSala) {
        Sala sala = new Sala();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM SALA WHERE IDSALA='" + idSala + "'");
            rs.next();
            sala.setNombre(idSala);
            sala.setFilas(Integer.valueOf(rs.getString(2)));
            sala.setColumnas(Integer.valueOf(rs.getString(3)));
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener la sala: " + e.getMessage());
        }
        return sala;
    }

    @Override
    public boolean estaOcupadoAsiento(String idSesion, int fila, int columna) {
        if (existeEntrada(idSesion, fila, columna)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean existeEntrada(String idSesion, int fila, int columna) {
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM ENTRADA WHERE IDSESION = '" + idSesion
                    + "'AND FILA =" + fila
                    + "AND COLUMNA = " + columna
                    + "");
            while (rs.next()) {
                if (rs.getString(2).equals(idSesion)) {
                    rs.close();
                    set.close();
                    return true;
                }
            }
            rs.close();
            set.close();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(modeloDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void guardarEntrada(Entrada entrada) {
        String tipo = "";
        if (entrada instanceof EntradaReducida) {
            tipo = "reducida";
        } else {
            tipo = "normal";
        }
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO ENTRADA (IDENTRADA, IDSESION, FILA, COLUMNA, TIPO, PRECIO, VENDIDA) VALUES ('"
                    + entrada.getId() + "', '" + entrada.getSesion().getIdSesion()
                    + "', " + entrada.getFila() + ", " + entrada.getColumna()
                    + ", '" + tipo
                    + "', " + entrada.getPrecio()
                    + ", " + entrada.isVendida()
                    + ")");

            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No se ha guardado la entrada " + e.getMessage());
        }
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO RESERVA (IDENTRADA, IDUSUARIO, IDRESERVA) VALUES ('" + reserva.getEntrada().getId()
                    + "', '" + reserva.getIdUsuario()
                    + "', '" + reserva.getIdReserva()
                    + "')");

            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No se ha guardado la reserva " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Sala> getSalas() {
        this.abrirConexion();
        ArrayList<Sala> salas = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM SALA");
            while (rs.next()) {
                Sala sala = new Sala();
                sala.setNombre(rs.getString(1));
                sala.setFilas(Integer.valueOf(rs.getString(2)));
                sala.setColumnas(Integer.valueOf(rs.getString(3)));
                salas.add(sala);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener los actores: " + e.getMessage());
        }
        return salas;
    }

    @Override
    public void eliminarSala(String idSala) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM SALA"
                    + " WHERE IDSALA= '" + idSala
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar la sala: " + e.getMessage());
        }
    }

    @Override
    public void modificarSala(Sala sala) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE SALA "
                    + "SET IDSALA='" + sala.getNombre()
                    + "',FILAS=" + sala.getFilas()
                    + ",COLUMNAS=" + sala.getColumnas()
                    + " WHERE IDSALA='" + sala.getNombre()
                    + "'"
                    + "");
            rs.close();
            set.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void anadirSala(Sala sala) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO SALA (IDSALA, FILAS, COLUMNAS) VALUES ('" + sala.getNombre()
                    + "', " + sala.getFilas()
                    + ", " + sala.getColumnas()
                    + ")");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No se ha guardado la sala" + e.getMessage());
        }
    }

    @Override
    public boolean estaSala(Sala sala) {
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM SALA");
            while (rs.next()) {
                if (rs.getString("IDSALA").equals(sala.getNombre())) {
                    return true;
                }
            }
            rs.close();
            return false;
        } catch (Exception e) {
            System.out.println("Error en la consulta de la sala: " + e.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Sesion> getSesiones() {
        ArrayList<Sesion> sesiones = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM SESION");
            while (rs.next()) {
                Sesion sesion = new Sesion();
                sesion.setIdSesion(rs.getString(1));
                sesion.setPelicula(rs.getString(2));
                sesion.setSala(rs.getString(3));
                sesion.setHora(rs.getString(4));
                sesion.setDiaSemana(rs.getString(5));
                sesion.setDiaMes(rs.getString(6));
                sesion.setMes(rs.getString(7));
                sesiones.add(sesion);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener las sesiones: " + e.getMessage());
        }
        return sesiones;
    }

    @Override
    public void eliminarSesion(String idSesion) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM SESION"
                    + " WHERE IDSESION= '" + idSesion
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar la sesion: " + e.getMessage());
        }
    }

    @Override
    public void modificarSesion(Sesion sesion) {
        try {
            set = con.createStatement();
            System.out.println("Guardo: "+sesion.toString());
            set.executeUpdate("UPDATE SESION "
                    + "SET IDSESION='" + sesion.getIdSesion()
                    + "',IDPELICULA='" + sesion.getPelicula()
                    + "',IDSALA='" + sesion.getSala()
                    + "',HORA='" + sesion.getHora()
                    + "',DIASEMANA='" + sesion.getDiaSemana()
                    + "',DIAMES= " + sesion.getDiaMes()
                    + ",MES= " + sesion.getMes()
                    + " WHERE IDSESION='" + sesion.getIdSesion()
                    + "'"
                    + "");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible modificar la sesion: "+e.getMessage());
        }
    }

    @Override
    public void anadirSesion(Sesion sesion) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO SESION (IDSESION, IDPELICULA, IDSALA, "
                    + "HORA, DIASEMANA, DIAMES, MES) "
                    + "VALUES ('" + sesion.getIdSesion()
                    + "', '" + sesion.getPelicula()
                    + "', '" + sesion.getSala()
                    + "', '" + sesion.getHora()
                    + "', '" + sesion.getDiaSemana()
                    + "', " + sesion.getDiaMes()
                    + ", " + sesion.getMes()
                    + ")");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No se ha guardado la sesion: " + e.getMessage());
        }
    }

    @Override
    public void eliminarEntradasSesion(String idSesion) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM ENTRADA"
                    + " WHERE IDSESION= '" + idSesion
                    + "'");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar las entradas de esa sesion" + e.getMessage());
        }
    }

    @Override
    public void eliminarReservasSesion(String idSesion) {
        try {
            set = con.createStatement();
            set.executeUpdate("DELETE FROM RESERVA"
                    + " WHERE IDENTRADA IN (SELECT IDENTRADA FROM ENTRADA WHERE IDSESION = '"+idSesion
                    + "')");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No ha sido posible eliminar las reservas de esa sesion" + e.getMessage());
        }
    }

}
