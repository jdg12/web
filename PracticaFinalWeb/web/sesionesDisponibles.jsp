<%-- 
    Document   : sesionesDisponibles
    Created on : 08-ene-2017, 10:45:57
    Author     : jesus
--%>

<%@page import="bbdd.Sesion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.modeloDatos"%>
<%@page import="bbdd.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css">
        <title>Sesiones</title>
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
            String id = (String) session.getAttribute("idPelicula");
            modeloDatos md = new modeloDatos();
            md.abrirConexion();
            ArrayList<Sesion> sesiones = md.getSesionesPelicula(id);
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
        <h1>Sesiones</h1>
        <h2>Escoja la sesion de la película </h2>
        <% for (int i = 0; i < sesiones.size(); i++) {%>
        <form action="/PracticaFinalWeb/sesionPelicula" class="pelicula" id="formulario" method="POST">
            <p>
                <label>IDsesion: </label><br><input id="idSesion" type="text" name="idSesion" autofocus required value="<%= sesiones.get(i).getIdSesion()%>" readonly></input>
                <br/><label>Sala: </label><br><input id="sala" type="text" name="sala" autofocus required value="<%= sesiones.get(i).getSala()%>" readonly></input>
                <br/><label>Hora: </label><br><input id="hora" type="text" name="hora" autofocus required value="<%=sesiones.get(i).getHora()%>" readonly></input>
                <br/><label>Fecha: </label><br><input id="fecha" type="text" name="fecha" autofocus required value="<%=sesiones.get(i).getFechaS()%>" readonly></input>
                <br /><br>
                <input type="hidden" id="thisField" name="inputName" value="<%=sesiones.get(i).getIdSesion()%>">
                <input class="boton" type="submit" value="Seleccionar">
            </p>
        </form>
                <br>
        <%}%>
    </body>
</html>
