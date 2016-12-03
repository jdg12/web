/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jesus
 */
class ModeloDatos {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void abrirConexion() {
        String sURL = "jdbc:odbc:mvc";
        try {
//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
//    con = DriverManager.getConnection(sURL,"","");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/kers", "usuario", "pass");
            System.out.println("Se ha conectado");
        } catch (Exception e) {
            System.out.println("No se ha conectado");
        }
    }

    public String getDatoPrueba() {
        try {
            set = con.createStatement();
            System.out.println("Estoy aqui");
            rs = set.executeQuery("SELECT * FROM Prueba");
            String cad = null;
            while (rs.next()) {
                cad = rs.getString("dato");
            }
            System.out.println(cad);
            rs.close();
            return cad;
        } catch (Exception e) {
            System.out.println("Error en la consulta");
        }
        return null;
    }

    /**
     * Devuelve una lista con los coches actuales
     *
     * @return
     */
    public ArrayList<Coche> getCoches() {
        ArrayList<Coche> coches = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM coche");
            while (rs.next()) {
                Coche coche = new Coche();
                coche.setNombre(rs.getString("NOMBRE_COCHE"));
                coche.setGanancia(Double.parseDouble(rs.getString("ganancia")));
                
                coches.add(coche);
            }
            rs.close();
            return coches;
        } catch (Exception e) {
            System.out.println("Error en la consulta de los circuitos: " + e.getMessage());
        }
        return coches;
    }

    /**
     * Devuelve una lista con los circuitos actuales
     *
     * @return
     */
    public ArrayList<Circuito> getCircuitos() {
        ArrayList<Circuito> circuitos = new ArrayList<>();
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM circuito");
            while (rs.next()) {
                Circuito circuito = new Circuito();
                circuito.setNombre(rs.getString("NOMBRE_CIRCUITO"));
                circuito.setCiudad(rs.getString("ciudad"));
                circuito.setPais(rs.getString("pais"));
                circuito.setCurvas(Integer.parseInt(rs.getString("curvas")));
                circuito.setLongitud(Double.parseDouble(rs.getString("longitud")));
                circuito.setVueltas(Integer.parseInt(rs.getString("vueltas")));
                circuitos.add(circuito);
            }
            rs.close();
            return circuitos;
        } catch (Exception e) {
            System.out.println("Error en la consulta de los circuitos: " + e.getMessage());
        }
        return circuitos;
    }
    
    public Circuito getCircuito(String nombre){
        Circuito circuito = new Circuito();
        try{
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM circuito WHERE NOMBRE_CIRCUITO ='"+nombre+"'");
                rs.next();
                circuito.setNombre(nombre);
                circuito.setCiudad(rs.getString("ciudad"));
                circuito.setPais(rs.getString("pais"));
                circuito.setCurvas(Integer.parseInt(rs.getString("curvas")));
                circuito.setLongitud(Double.parseDouble(rs.getString("longitud")));
                circuito.setVueltas(Integer.parseInt(rs.getString("vueltas")));
        }catch (Exception e) {
            System.out.println("Error en la consulta del circuito: " +nombre+" ,"+ e.getMessage());
        }
        return circuito;
    }
    
    public Coche getCoche(String nombre){
        Coche coche = new Coche();
        try{
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM coche WHERE NOMBRE_COCHE ='"+nombre+"'");
            rs.next();
                coche.setNombre(nombre);
                coche.setGanancia(Double.parseDouble(rs.getString("ganancia")));

            rs.close();
        }catch (Exception e) {
            System.out.println("Error en la consulta del coche: " +nombre+" ,"+ e.getMessage());
        }
        return coche;
    }
    
    public void insertarCircuito(String nombre, String ciudad, String pais, int vueltas, Double longitud, int curvas)
    {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO CIRCUITO "
                    + " (NOMBRE_CIRCUITO,ciudad, pais, vueltas, longitud, curvas) VALUES ('" + nombre + "','"+ciudad+"','"+pais+"',"+vueltas+","+longitud+","+curvas+")");
            rs.close();
            set.close();
        } catch (Exception e) {
            System.out.println("No se ha insertado el circuito"+e.getMessage());
        }
    }

    public boolean estaCircuito(String nombre) {
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT NOMBRE_CIRCUITO FROM circuito");
            while (rs.next()) {
                if (rs.getString("NOMBRE_CIRCUITO").equals(nombre)) {
                    return true;
                }
            }
            rs.close();
            return false;
        } catch (Exception e) {
            System.out.println("Error en la consulta de los circuitos: " + e.getMessage());
        }
        return false;
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
        }
    }

}
