package Paquete;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ACB extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        String nombreP = (String) req.getParameter("txtNombre");
        String nombre = (String) req.getParameter("R1");
        if (nombre.equals("Otros")) {
            nombre = (String) req.getParameter("txtOtros");
        }
        if (bd.existeJugador(nombre)) {
            System.out.println("Voy a actualizarlo");
            bd.actualizarJugador(nombre);
        } else {
            System.out.println("Voy a insertar un jugador");
            //bd.insertarJugador(nombre);
        }
        s.setAttribute("nombreCliente", nombreP);
        s.setAttribute("votos", bd.getVotos());
        s.setAttribute("metadata", bd.getMetaData());
        // Llamada a la página jsp que nos da las gracias
        res.sendRedirect(res.encodeRedirectURL("/PracticaMVC-EjemploACB/TablaVotos.jsp"));
    }

    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
