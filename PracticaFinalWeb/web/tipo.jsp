<%-- 
    Document   : tipo
    Created on : 08-ene-2017, 12:49:13
    Author     : jesus
--%>

<%@page import="bbdd.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo de entrada</title>
        <link rel="stylesheet" href="style/style.css">
    </head>
    <body>
        <%
            Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
            if (usuarioActual == null) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Registrese primero');");
                out.println("location='acceso.html';");
                out.println("</script>");
            }
            String id = request.getParameter("inputName");
            session.setAttribute("idPelicula", id);
        %>
        <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
                <% if (usuarioActual == null || usuarioActual.getIdUsuario().equals("") || usuarioActual.getIdUsuario().equals("visitante")) {%> 
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
                <%} else {%>
            <li><a href="perfil.jsp">Hola: <%=usuarioActual.getIdUsuario()%></a></li>
            <li><a href="/PracticaFinalWeb/SalirServlet">Salir</a></li>
                <%}%>
        </ul>
        <h1>Tipo de entrada</h1>
        <form action="seleccionTipoServlet" class="pelicula" id="formulario" method="POST">
            <select id="tipo" name="tipo" >
                <option value="normal">Normal</option>
                <option value="reducida">Reducida</option>
            </select>
            <input class="boton" type="submit" value="Seleccionar tipo">
        </form>
    </body>
</html>
