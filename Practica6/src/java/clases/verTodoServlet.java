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
 * @author JD
 */
public class verTodoServlet extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"style/style.css\">");
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
            //Circuitos
            out.println("<h2> CIRCUITOS </h2>");
            out.println("<TABLE border=\"1\" WIDTH=\"400\">\n"
                    + "\n"
                    + "                    <TD aling=\"rigth\">Nombre circuito\n"
                    + "                    </TD>\n"
                    + "                    <TD aling=\"rigth\">Ciudad\n"
                    + "                    </TD>\n"
                    + "                    <TD aling=\"rigth\">Pais\n"
                    + "                    </TD>\n"
                    + "                    <TD aling=\"rigth\">Vueltas \n"
                    + "                    </TD>\n"
                    + "                    <TD aling=\"rigth\">Longitud (km)\n"
                    + "                    <TD aling=\"rigth\">Curvas\n"
                    + "                    </TD>");
            if (!circuitos.isEmpty()) {
                Iterator it = circuitos.iterator();
                Circuito circuito = new Circuito();

                while (it.hasNext()) {
                    circuito = (Circuito) it.next();
                    out.println("<TR>");
                    out.println("<TD>" + circuito.getNombre() + "</TD>");
                    out.println("<TD>" + circuito.getCiudad() + "</TD>");
                    out.println("<TD>" + circuito.getPais() + "</TD>");
                    out.println("<TD>" + circuito.getVueltas() + "</TD>");
                    out.println("<TD>" + circuito.getLongitud() + "</TD>");
                    out.println("<TD>" + circuito.getCurvas() + "</TD>");
                    out.println("</TR>");
                }
            }
            out.println("</TABLE> <BR>");
            //Coches
            out.println("<h2> COCHES </h2>");
            out.println("<TABLE border=\"1\" WIDTH=\"400\">\n"
                    + "\n"
                    + "                    <TD aling=\"rigth\">Nombre coche\n"
                    + "                    </TD>\n"
                    + "                    <TD aling=\"rigth\">Ganancia de Potencia (Kw)\n"
                    + "                    </TD>\n"
                    );
            

            if (!coches.isEmpty()) {
                Iterator it = coches.iterator();
                Coche coche = new Coche();

                while (it.hasNext()) {
                    coche = (Coche) it.next();
                    out.println("<TR>");
                    out.println("<TD>" + coche.getNombre() + "</TD>");
                    out.println("<TD>" + coche.getGanancia() + "</TD>");
                    
                    out.println("</TR>");
                }
            out.println("</TABLE>");    
            out.println("</body>");
            out.println("</html>");
                
            }
        }catch (Exception e) {
                System.out.println("excepcion: " + e.getMessage());
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
