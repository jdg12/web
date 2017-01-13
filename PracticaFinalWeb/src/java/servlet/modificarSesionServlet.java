
package servlet;

import bbdd.Sesion;
import bbdd.Proxy;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesus
 */
public class modificarSesionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Obtenemos los datos
        String idSesion = (String) request.getParameter("nombre");
        String idPelicula = (String) request.getParameter("pelicula");
        String idSala = (String) request.getParameter("sala");
        String hora = (String) request.getParameter("hora");
        String diaSemana = (String) request.getParameter("diasemana");
        String diaMes = (String) request.getParameter("diames");
        String mes = (String) request.getParameter("mes");
        String select = request.getParameter("inputName");

        //Creamos la sesion
        Sesion sesion = new Sesion();
        sesion.setIdSesion(idSesion);
        sesion.setPelicula(idPelicula);
        sesion.setSala(idSala);
        sesion.setHora(hora);
        sesion.setDiaSemana(diaSemana);
        sesion.setDiaMes(diaMes);
        sesion.setMes(mes);

        Proxy bd = new Proxy();
        bd.abrirConexion();
        //Ahora si es modificar. 
        if (select.equals("anadir")) {
            bd.anadirSesion(sesion);
        } else {
            bd.modificarSesion(sesion);
        }

        response.sendRedirect(response.encodeRedirectURL("/PracticaFinalWeb/sesiones.jsp"));
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
