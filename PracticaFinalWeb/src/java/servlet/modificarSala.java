
package servlet;

import bbdd.Sala;
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
public class modificarSala extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        int filas = Integer.valueOf(request.getParameter("filas"));
        int columnas = Integer.valueOf(request.getParameter("columnas"));
        String select = request.getParameter("inputName");
        
        //Creamos la sala
        Sala sala = new Sala();
        sala.setNombre(nombre);
        sala.setFilas(filas);
        sala.setColumnas(columnas);

        //La modificamos o añadimos en la base de datos según lo seleccionado
        Proxy bd = Proxy.getInstancia();
        
        if (select.equals("anadir")) {
            if (!bd.estaSala(sala)) {
                bd.anadirSala(sala);
            }
        } else {
            bd.modificarSala(sala);
        }

        //Redirigimos de nuevo
        response.sendRedirect(response.encodeRedirectURL("/PracticaFinalWeb/salas.jsp"));
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
