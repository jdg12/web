
package servlet;

import bbdd.Usuario;
import bbdd.Proxy;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jesus
 */
public class registroServlet extends HttpServlet {

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
        
        //Creamos el usuario
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(request.getParameter("idUsuario"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellidos(request.getParameter("apellidos"));
        usuario.setCorreo(request.getParameter("correo"));
        usuario.setContrasena(request.getParameter("contrasena"));
        usuario.setDireccion(request.getParameter("direccion"));
        usuario.setCuenta(request.getParameter("cuenta"));

        //Nos conectamos a la bbdd
        Proxy bd = Proxy.getInstancia();
        
        //Si el usuario no esta en la bbdd, creamos la cookie y redirigimos al perfil
        if (!bd.estaUsuario(usuario.getIdUsuario())) {
            bd.guardarUsuario(usuario);
            Cookie cookie = new Cookie("idUsuario", usuario.getIdUsuario());
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
            //A continuación redirigimos a mi perfil
            response.sendRedirect(response.encodeRedirectURL("/PracticaFinalWeb/perfil.jsp"));
        } else {
            response.sendRedirect(response.encodeRedirectURL("acceso.html"));
        }
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
