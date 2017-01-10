
package servlet;

import bbdd.Entrada;
import bbdd.EntradaNormal;
import bbdd.EntradaReducida;
import bbdd.Reserva;
import bbdd.Sesion;
import bbdd.Usuario;
import bbdd.modeloDatos;
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
        modeloDatos bd = new modeloDatos();
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
        bd.abrirConexion();
        Usuario usuario = bd.getUsuario(idUsuario);
        Sesion sesion2 = bd.getSesion(idSesion);
        String idEntrada = idSesion + "-" + String.valueOf((fila + 1) * columna);
        Entrada entrada;
        //Creamos la entrada
        if (tipo.equals("reducida")) {
            entrada = new EntradaReducida();
            entrada.setPrecio(4);
        } else {
            entrada = new EntradaNormal();
            entrada.setPrecio(5.9);
        }
        entrada.setId(idEntrada);
        entrada.setSesion(sesion2);
        entrada.setFila(fila);
        entrada.setColumna(columna);
        entrada.setVendida(true);
        
        //Ahora guardamos la reserva si la habia realizado el usuario
        if (!usuario.getIdUsuario().equals("admin")) {
            entrada.setPrecio(7);
            bd.guardarEntrada(entrada);
            Reserva reserva = new Reserva();
            reserva.setEntrada(entrada);
            reserva.setIdUsuario(usuario.getIdUsuario());
            reserva.setIdReserva("r-" + entrada.getId());
            bd.guardarReserva(reserva);
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
