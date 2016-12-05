/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manu
 */
public class cocheServlet extends HttpServlet {

    private ModeloDatos bd;
    
    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }
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
        String coche;
        Double ganancia;
        coche = request.getParameter("nombre");
        ganancia = Double.parseDouble(request.getParameter("ganancia"));

        //A continuación comprobamos de nuevo que los datos sean correctos
        if (!isValidGanancia(ganancia)) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Fallo</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Los datos introducidos son erroneos</h1>");
                out.println("<p>Pulse en el siguiente enlace para volver a introducirlos</p>");
                out.println("<a href=\"/Practica6/nuevoCoche.html\">Añadir coche</a>");
                out.println("</body>");
                out.println("</html>");
                response.sendRedirect(response.encodeRedirectURL("/Practica6/errorDatos.html"));
            }
        }else
        {
            //En el caso de que los datos sean correctos y no esté en la bbdd lo añadimos
            if (bd.estaCoche(coche))
            {
                response.sendRedirect(response.encodeRedirectURL("/Practica6/yaEstaCircuito.html"));
            }else
            {
                bd.insertarCoche(coche, ganancia);
                response.sendRedirect(response.encodeRedirectURL("/Practica6/insertadoCorrectamente.html"));
            }
        }
    }

    public boolean isValidGanancia(Double ganancia) {
        if (ganancia >= 4 && ganancia <= 10) {
            return true;
        } else {
            return false;
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
