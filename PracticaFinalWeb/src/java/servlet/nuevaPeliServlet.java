/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bbdd.Actor;
import bbdd.ConstructorPelicula;
import bbdd.Pelicula;
import bbdd.Pelicula0Builder;
import bbdd.Pelicula13Builder;
import bbdd.Pelicula18Builder;
import bbdd.Pelicula7Builder;
import bbdd.Proxy;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JD
 */
@WebServlet(name = "nuevaPeliServlet", urlPatterns = {"/nuevaPeliServlet"})
public class nuevaPeliServlet extends HttpServlet {

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
        
        //Utilizaremos el patron builder para construir la pelicula
        try {

            ConstructorPelicula cons = new ConstructorPelicula();
            Pelicula0Builder b0 = new Pelicula0Builder();
            Pelicula18Builder b1 = new Pelicula18Builder();
            Pelicula13Builder b2 = new Pelicula13Builder();
            Pelicula7Builder b3 = new Pelicula7Builder();
            Pelicula peli;
            //elegimos el Builder adecuado
            int clasi = Integer.parseInt(request.getParameter("clasificacion"));
            switch (clasi) {
                case 0:
                    cons.setPeliculaBuilder(b0);
                    break;
                case 7:
                    cons.setPeliculaBuilder(b3);
                    break;
                case 13:
                    cons.setPeliculaBuilder(b2);
                    break;
                case 18:
                    cons.setPeliculaBuilder(b1);
                    break;
            }
            String nombre = request.getParameter("nombre");
            String sinopsis = request.getParameter("sinopsis");
            String pagina = request.getParameter("pagina");
            String titulo = request.getParameter("titulo");
            String genero = request.getParameter("genero");
            String nacionalidad = request.getParameter("nacionalidad");
            int duracion = Integer.parseInt(request.getParameter("duracion"));
            int ano = Integer.parseInt(request.getParameter("ano"));
            String distribibuidora = request.getParameter("distribuidora");
            String director = request.getParameter("director");
            String otros = request.getParameter("otros");
            String listaActores = request.getParameter("listaActores");

            //sacamos los actores del String
            Actor miActor;
            ArrayList<Actor> arrayLActores = new ArrayList<>();
            String nombreA = "", apellidoA = "";
            String[] actores, actor;
            if (!listaActores.equals("")) {
                actores = listaActores.split(";");
                for (String a : actores) {
                    actor = a.split(",");
                    nombreA = actor[0];
                    apellidoA = actor[1];
                    miActor = new Actor(nombreA, apellidoA);
                    //System.out.println(miActor.toString());
                    arrayLActores.add(miActor);
                }
            }
            System.out.println("ArrayActores: " + arrayLActores);
            //Creamos la pelicula
            cons.crearPelicula(nombre, sinopsis, pagina, titulo, genero, nacionalidad, duracion, ano, distribibuidora, director, arrayLActores, otros);
            peli = cons.getPelicula();
            //Guardamos la Pelicula en bbdd
            Proxy bd = new Proxy();
            bd.abrirConexion();
            //Si ya existe esa pelicula, se le indica al usuario
            if (bd.estaPelicula(nombre)) {
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Ya existe la película</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1> Ya hay una pelicula en el sistema con el nombre: " + nombre + "</h1>");
                    out.println("Intenta insertar otra o modificar la existente");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            bd.guardarPelicula(peli);
            //Se añaden los actores
            Iterator it = arrayLActores.iterator();
            Actor a;
            while (it.hasNext()) {
                a = (Actor) it.next();
                if (!bd.estaActor(a.getNombre(), a.getApellidos())) {
                    bd.guardarActor(a, nombre);
                }
                if (!bd.estaRelacionActor(peli, a)) {
                    bd.relacionActorPelicula(peli, a);
                }
            }

            sesion.setAttribute("peliculaId", nombre);
            response.sendRedirect(response.encodeRedirectURL("/PracticaFinalWeb/pelicula.jsp"));

        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
            e.printStackTrace();

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
