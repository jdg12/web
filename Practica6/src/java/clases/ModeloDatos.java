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
            Class.forName( "org.apache.derby.jdbc.ClientDriver" );
//    con = DriverManager.getConnection(sURL,"","");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/prueba", "prueba", "prueba");
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
     * TODO
     * @return 
     */
    public ResultSet getCoches()
    {
        //TODO
        return null;
    }
    
    /**
     * TODO
     * @return 
     */
    public ResultSet getCircuitos()
    {
        //TODO
        return null;
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
        }
    }
    
}
