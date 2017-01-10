
package servlet;

import bbdd.Comentario;
import bbdd.modeloDatos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jesus
 */
public class dejarComentario extends HttpServlet {

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
        HttpSession sesion = request.getSession();
        //Obtenemos los datos
        String idUsuario = request.getParameter("inputName");
        String idPelicula = request.getParameter("inputName2");
        String comentario = request.getParameter("comentario");
        int puntuacion = Integer.valueOf(request.getParameter("puntuacion"));
        
        //Creamos el comentario
        Comentario c = new Comentario();
        c.setTexto(comentario);
        c.setPuntuacion(puntuacion);
        c.setIdPelicula(idPelicula);
        c.setIdUsuario(idUsuario);
        c.setIdComentario(idPelicula+"-"+idUsuario);
        
        //Creamos una sesión en la bbdd y lo guardamos
        modeloDatos bd = new modeloDatos();
        bd.abrirConexion();
        bd.guardarComentario(c);
        
        //Redirigimos de nuevo a la pelicula
         sesion.setAttribute("peliculaId", idPelicula);
        response.sendRedirect(response.encodeRedirectURL("/PracticaFinalWeb/pelicula.jsp"));
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
