/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SegundoServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesus
 */
@WebServlet(urlPatterns = {"/SegundoServlet/SegundoServlet"})
public class SegundoServlet extends HttpServlet {

    String nombre;

    @Override
    public void service(HttpServletRequest peticion, HttpServletResponse respuesta)
            throws ServletException, IOException {
        nombre = peticion.getParameter("NOMBRE");
        ServletOutputStream out = respuesta.getOutputStream();
        out.println("<html>");
        out.println("<head><title>HolaTalServlet</title></head>");
        out.println("<body>");
        out.println("<p><h1><center>Su nombre es:<B>" + nombre + " </B></center></h1></p>");
        out.println("</body></html>");
        out.close();
    }
}
