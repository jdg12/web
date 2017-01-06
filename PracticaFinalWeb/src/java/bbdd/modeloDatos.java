/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jesus
 */
package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class modeloDatos implements BBDD {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void abrirConexion() {
        String sURL = "jdbc:odbc:mvc";
        String url = "jdbc:derby://localhost:1527/finalweb";
        String usuario = "finalweb";
        String contrasena = "finalweb";
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
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM RESERVA");
            while (rs.next()) {
                reserva = new Reserva();
                String idPelicula = rs.getString(1);
                String idSala = rs.getString(2);
                String hora = rs.getString(3);
                int fila = Integer.valueOf(rs.getString(5));
                int columna = Integer.valueOf(rs.getString(6));
                String tipo = getTipoEntrada(idPelicula, idSala, hora, fila, columna);
                Entrada entrada;
                if (tipo.equals("normal")) {
                    entrada = (EntradaNormal) getEntrada(idPelicula, idSala, hora, fila, columna);
                } else {
                    entrada = (EntradaReducida) getEntrada(idPelicula, idSala, hora, fila, columna);
                }
                reserva.setEntrada(entrada);
                reserva.setIdReserva(rs.getString(7));
                reservas.add(reserva);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al obtener las reservas: " + e.getMessage());
        }
        return reservas;
    }

    @Override
    public Sesion getSesion(String idPelicula, String idSala, String hora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTipoEntrada(String idPelicula, String idSala, String hora, int fila, int columna) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entrada getEntrada(String idPelicula, String idSala, String hora, int fila, int columna) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
