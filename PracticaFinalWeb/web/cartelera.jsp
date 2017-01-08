<%-- 
    Document   : cartelera
    Created on : 05-ene-2017, 15:22:30
    Author     : jesus
--%>

<%@page import="bbdd.Usuario"%>
<%@page import="bbdd.modeloDatos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Pelicula"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css">
        <title>Cartelera</title>
    </head>
    <body>
        <%Usuario usuario = (Usuario) session.getAttribute("usuarioActual");
            if (usuario == null) {
                System.out.println("Estoy aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                usuario = new Usuario();
                usuario.setNombre("visitante");
                usuario.setIdUsuario("visitante");
            }%>
        <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
                <% if (usuario == null || usuario.getIdUsuario().equals("") || usuario.getIdUsuario().equals("visitante") ) {%> 
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
                <%} else {%>
            <li><a href="perfil.jsp">Hola: <%=usuario.getIdUsuario()%></a></li>
            <li><a href="/PracticaFinalWeb/SalirServlet">Salir</a></li>
                <%}%>
        </ul>
        <% if (usuario.getNombre().equals("admin")) {%>
        <input type="button" value="Nueva película" onClick="window.location.href = 'nuevaPelicula.html'">
        <%}%>
        <h1>Cartelera</h1>
        <h2>Películas</h2>
        <%  modeloDatos bd = new modeloDatos();
            bd.abrirConexion();
            ArrayList<Pelicula> peliculas = bd.getPeliculas();
            for (int i = 0; i < peliculas.size(); i++) {%>
        <div class="pelicula">
            <p>Titulo: <%=peliculas.get(i).getNombre()%></p>
            <p>Sinopsis: <%=peliculas.get(i).getSinopsis()%></p>
            <p>Genero: <%=peliculas.get(i).getGenero()%></p>
            <form action="/PracticaFinalWeb/verMasPeliculaServlet" class="verMas" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= peliculas.get(i).getNombre()%>">
                <input class="boton" type="submit" value="Ver mas">
            </form>
            <% if (usuario.getNombre().equals("admin")) {%>
            <form action="/PracticaFinalWeb/borrarPelicula" class="pelicula" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= peliculas.get(i).getNombre()%>">
                <input class="boton" type="submit" value="Eliminar">
            </form>
            <form action="tipo.jsp" class="pelicula" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= peliculas.get(i).getNombre()%>">
                <input class="boton" type="submit" value="Comprar entrada">
            </form>
            <%} else {%>
            <form action="tipo.jsp" class="pelicula" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= peliculas.get(i).getNombre()%>">
                <input class="boton" type="submit" value="Reservar entrada">
            </form>
            <%}%>
        </div>
        <%}%>
    </body>
</html>
