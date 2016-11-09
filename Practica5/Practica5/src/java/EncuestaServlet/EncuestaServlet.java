package EncuestaServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author jesus
 */
@WebServlet(name = "EncuestaServlet", urlPatterns = {"/EncuestaServlet/EncuestaServlet"})
public class EncuestaServlet extends HttpServlet {

    Statement mandato = null;
    Connection conexion = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String URL = "jdbc:odbc:AccessODBC";
        String usuario = "";
        String contraseña = "";
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (Exception e) {
            System.out.println("Error al cargar el driver JDBC/ODBC.");
            return;
        }
        try {
            conexion = DriverManager.getConnection(URL, usuario, contraseña);
            mandato = conexion.createStatement();
        } catch (Exception e) {
            System.out.println("Problemas al conectar con " + URL);
            return;
        }
    }

    @Override
    public void service(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException {
        /* creación del flujo de salida hacia el cliente */
        ServletOutputStream out = respuesta.getOutputStream();
        respuesta.setContentType("text/html");
        /* recuperamos los valores que nos manda el cliente */
        String strNombre = peticion.getParameter("NOMBRE");
        String strEmail = peticion.getParameter("EMAIL");
        String strRespuesta = peticion.getParameter("RESPUESTA");
        /* insertamos los datos en la base de datos */
        try {
            mandato.executeUpdate("INSERT INTO ENCUESTA VALUES( '" + strNombre + "', '" + strEmail + "', '" + strRespuesta + "');");
        } catch (SQLException e) {
            System.out.println(e);
            return;
        }
        /* leemos todos los registros para crear la estadística */
        try {
            int intSI = 0;
            int intNO = 0;
            ResultSet resultado = mandato.executeQuery("SELECT RESPUESTA FROM ENCUESTA");
            while (resultado.next()) {
                String resp = resultado.getString("RESPUESTA");
                if (resp.compareTo("SI") == 0) {
                    intSI++;
                } else {
                    intNO++;
                }
            }
            out.println("<h2><center>Encuesta Servlet</center></h2>");
            out.println("<BR>Gracias por participar en esta encuesta.");
            out.println("<BR>Los resultados hasta este momento son :");
            out.println("<BR> SI : " + intSI);
            out.println("<BR> NO : " + intNO);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }

    @Override
    public void destroy() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e);
            return;
        }
    }
}
