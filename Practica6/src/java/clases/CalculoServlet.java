/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JD
 */
public class CalculoServlet extends HttpServlet {

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
        String nomCoche = (String)request.getParameter("coches");
        String nomCircuito = (String)request.getParameter("circuitos");
   
        Circuito circuito = bd.getCircuito(nomCircuito);
        Coche coche =bd.getCoche(nomCoche);
        
        double resultado = coche.getGanancia()*circuito.getCurvas()*circuito.getVueltas();
        
        HttpSession s = request.getSession(true);
        s.setAttribute("resultado", resultado);
        s.setAttribute("coche", nomCoche);
        s.setAttribute("circuito", nomCircuito);
        
        response.sendRedirect(response.encodeRedirectURL("/Practica6/calculoGanancia.jsp"));
        
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
