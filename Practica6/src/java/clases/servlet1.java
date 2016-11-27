package clases;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class servlet1 extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
       //Comprobamos que la conexion es correcta con un dato de prueba
       String datoPrueba = bd.getDatoPrueba();
       s.setAttribute("datoPrueba", datoPrueba);
        res.sendRedirect(res.encodeRedirectURL("/Practica6/jsp1.jsp"));
    }

    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
