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
 * @author jesus
 */
public class circuitoServlet extends HttpServlet {

    private ModeloDatos bd;
    private ArrayList<Circuito> circuitos;

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
        String circuito, ciudad, pais;
        int vueltas, curvas;
        Double longitud;
        circuito = request.getParameter("nombre");
        ciudad = request.getParameter("ciudad");
        pais = request.getParameter("pais");
        vueltas = Integer.parseInt(request.getParameter("vueltas"));
        longitud = Double.parseDouble(request.getParameter("longitud"));
        curvas = Integer.parseInt(request.getParameter("curvas"));

        //A continuación comprobamos de nuevo que los datos sean correctos
        if (!isValidVueltas(vueltas) || !isValidLongitud(longitud) || !isValidCurvas(curvas)) {
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
                out.println("<a href=\"/Practica6/nuevoCircuito.html\">Añadir circuito</a>");
                out.println("</body>");
                out.println("</html>");
                response.sendRedirect(response.encodeRedirectURL("/Practica6/errorDatos.html"));
            }
        }else
        {
            //En el caso de que los datos sean correctos y no esté en la bbdd lo añadimos
            if (bd.estaCircuito(circuito))
            {
                response.sendRedirect(response.encodeRedirectURL("/Practica6/yaEstaCircuito.html"));
            }else
            {
                bd.insertarCircuito(circuito, ciudad, pais, vueltas, longitud, curvas);
                response.sendRedirect(response.encodeRedirectURL("/Practica6/insertadoCorrectamente.html"));
            }
        }
    }

    public boolean isValidVueltas(int vueltas) {
        if (vueltas > 40 && vueltas < 80) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidLongitud(Double longitud) {
        if (longitud >= 3000 && longitud <= 9000) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidCurvas(int curvas) {
        if (curvas >= 6 && curvas <= 20) {
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
