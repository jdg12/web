package PrimerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesus
 */
@WebServlet(urlPatterns = {"/PrimerServlet/PrimerServlet"})
public class PrimerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = new PrintWriter(res.getOutputStream());
        out.println("<html>");
        out.println("<head><title>HolaMundoServlet</title></head>");
        out.println("<body>");
        out.println("<h1><center>Hola Mundo desde el servidor WEB</center > </h1>");
        out.println("</body></html>");
        out.close();
    }

    @Override
    public String getServletInfo() {
        return "Crea una página HTML que dice HolaMundo";
    }
}
