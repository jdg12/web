
package servlet;

import bbdd.Pelicula;
import bbdd.Proxy;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jesus
 */
public class modificarPelicula extends HttpServlet {

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
        //Obtenemos los datos de la pelicula
        String nombre = request.getParameter("idPelicula");
        String sinopsis = request.getParameter("sinopsis");
        String pagina = request.getParameter("pagina");
        String titulo = request.getParameter("titulo");
        String genero = request.getParameter("genero");
        String nacionalidad = request.getParameter("nacionalidad");
        int duracion = Integer.valueOf(request.getParameter("duracion"));
        int ano = Integer.valueOf(request.getParameter("ano"));
        String distribuidora = request.getParameter("distribuidora");
        String director = request.getParameter("director");
        int clasificaicon = Integer.valueOf(request.getParameter("clasificacion"));
        String otros = request.getParameter("otros");
        
        //Creamos la pelicula
        Pelicula pelicula = new Pelicula();
        pelicula.setNombre(nombre);
        pelicula.setSinopsis(sinopsis);
        pelicula.setPagina(pagina);
        pelicula.setTitulo(titulo);
        pelicula.setGenero(genero);
        pelicula.setNacionalidad(nacionalidad);
        pelicula.setDuracion(duracion);
        pelicula.setAno(ano);
        pelicula.setDistribuidora(distribuidora);
        pelicula.setDirector(director);
        pelicula.setClasificacion(clasificaicon);
        pelicula.setOtros(otros);
        
        //Ahora lo modificamos
        Proxy bd = Proxy.getInstancia();
        
        bd.modificarPelicula(pelicula);
        
        //Volvemos a la pelicula
        sesion.setAttribute("peliculaId", nombre);
        response.sendRedirect(response.encodeRedirectURL("/PracticaFinalWeb/pelicula.jsp"));
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
