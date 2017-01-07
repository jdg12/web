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
        String url = "jdbc:derby://localhost:1527/finalweb2";
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
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM RESERVA WHERE IDUSUARIO='" + idUsuario + "'");
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

}
