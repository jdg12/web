<%-- 
    Document   : cartelera
    Created on : 05-ene-2017, 15:22:30
    Author     : jesus
--%>

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
        <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
            <li><a href="perfil.jsp">Mi perfil</a></li>
        </ul>
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
        </div>
        <%}%>
    </body>
</html>
