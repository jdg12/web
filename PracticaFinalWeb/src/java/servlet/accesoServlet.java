package servlet;

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
public class accesoServlet extends HttpServlet {

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
        //Obtenemos el id de usuario y contraseña
        String idUsuario, contrasena;
        idUsuario = request.getParameter("idUsuario");
        contrasena = request.getParameter("contrasena");
        Proxy bd = Proxy.getInstancia();
        
        
        //Comprobamos si los datos son correctos
        if (bd.estaUsuario(idUsuario) && bd.contrasenaCorrecta(idUsuario, contrasena)) {
            //Si son correctos obtenemos el usuario actual y lo guardamos en la cookie
            sesion.setAttribute("usuarioActual", bd.getUsuario(idUsuario));
            Cookie cookie = new Cookie("idUsuario",idUsuario);
            cookie.setMaxAge(-1); 
            response.addCookie(cookie);
            //Redirigimos al perfil
            response.sendRedirect(response.encodeRedirectURL("/PracticaFinalWeb/perfil.jsp"));
        } else {
            //En caso de ser incorrectos volvemos a cargar la página de acceso
            response.sendRedirect(response.encodeRedirectURL("acceso.jsp"));
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
