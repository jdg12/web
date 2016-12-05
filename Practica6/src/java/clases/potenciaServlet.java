/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesus
 */
public class potenciaServlet extends HttpServlet {

    private ModeloDatos bd;
    private ArrayList<Circuito> circuitos;
    private ArrayList<Coche> coches;

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

        circuitos = bd.getCircuitos();
        coches = bd.getCoches();

        if (circuitos.isEmpty() || coches.isEmpty()) {
            response.sendRedirect(response.encodeRedirectURL("/Practica6/noHayDatos.html"));
        } else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet potenciaServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<ul class=\"menu\">\n"
                        + "            <li><a href=\"/Practica6/index.html\">Inicio</a></li>\n"
                        + "            <li><a href=\"/Practica6/nuevoCircuito.html\">Nuevo circuito</a></li>\n"
                        + "            <li><a href=\"/Practica6/nuevoCoche.html\">Nuevo coche</a></li>\n"
                        + "            <li><a href=\"/Practica6/verTodos.html\">Ver todos</a></li>\n"
                        + "            <li><a  href=\"/Practica6/potenciaServlet\">Calculo Kers</a></li>\n"
                        + "        </ul>");

                out.println("<h1>Elige un circuito y un coche</h1>");
                out.println("<form name=\"formulario\" action=\"/Practica6/CalculoServlet\">");

                //circuitos
                out.println("<select id=\"circuitos\" name=\"circuitos\" size=7 > ");
                Iterator it = circuitos.iterator();
                Circuito circuito = new Circuito();

                while (it.hasNext()) {
                    circuito = (Circuito) it.next();
                    out.println("<option value=\"" + circuito.getNombre() + "\">" + circuito.getNombre() + "</option>");

                }
                out.println("</select>");

                //coches
                out.println("<select id=\"coches\" name=\"coches\" size=7 > ");

                it = coches.iterator();
                Coche coche = new Coche();

                while (it.hasNext()) {
                    coche = (Coche) it.next();
                    out.println("<option value=\"" + coche.getNombre() + "\">" + coche.getNombre() + "</option>");

                }
                out.println("</select>");
                out.println("<br>");
                out.println("<input class=\"boton\" type=\"submit\" value=\"Calcular\">");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");

            } catch (Exception e) {
                System.out.println("excepcion: " + e.getMessage());
            }
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
