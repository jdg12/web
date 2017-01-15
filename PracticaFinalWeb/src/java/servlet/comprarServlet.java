
package servlet;

import bbdd.Entrada;
import bbdd.FactoriaEntradas;
import bbdd.Reserva;
import bbdd.Sesion;
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
public class comprarServlet extends HttpServlet {

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
        //Obtenemos la fila, la columna, la sesion y el usuario
        int fila = Integer.valueOf(request.getParameter("inputName"));
        int columna = Integer.valueOf(request.getParameter("inputName2"));
        HttpSession sesion = request.getSession();
        String tipo = (String) sesion.getAttribute("tipo");
        String idSesion = (String) sesion.getAttribute("idSesion");
        Cookie[] cookies = request.getCookies();
        Proxy bd = Proxy.getInstancia();
        String idUsuario = "";
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String nombre = cookie.getName();
                if (nombre.equals("idUsuario")) {
                    idUsuario = cookie.getValue();
                }
            }
        }
        
        Usuario usuario = bd.getUsuario(idUsuario);
        Sesion sesion2 = bd.getSesion(idSesion);
        String idEntrada = idSesion + "-" + String.valueOf((fila + 1)) + "-" + String.valueOf((columna + 1));
        Entrada entrada;
        FactoriaEntradas factor = new FactoriaEntradas();
        //Creamos la entrada
        if (tipo.equals("reducida")) {
            entrada = factor.nuevaEntrada(1, idEntrada, sesion2, fila, columna, true, 6);
        } else {

        //Entrada normal
        entrada = factor.nuevaEntrada(0, idEntrada, sesion2, fila, columna, true, 6);
        }
        
        
        //Ahora guardamos la reserva si la habia realizado el usuario
        if (!usuario.getIdUsuario().equals("admin")) {
            entrada.setPrecio(7);
            bd.guardarEntrada(entrada);
            Reserva reserva = new Reserva();
            reserva.setEntrada(entrada);
            reserva.setIdUsuario(usuario.getIdUsuario());
            reserva.setIdReserva("r-" + entrada.getId());
            bd.guardarReserva(usuario,reserva);
        }else
        {
            bd.guardarEntrada(entrada);
        }
        response.sendRedirect(response.encodeRedirectURL("/PracticaFinalWeb/perfil.jsp"));
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
