<%-- 
    Document   : salas
    Created on : 08-ene-2017, 13:41:53
    Author     : jesus
--%>

<%@page import="bbdd.Sala"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.modeloDatos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css">
        <title>Salas</title>
    </head>
    <body>
        <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
            <li><a href="perfil.jsp">Mi perfil</a></li>
        </ul>
        <h1>Salas</h1>
        <input type="button" value="Añadir una sala" onClick=" window.location.href='nuevaSala.html' ">
        <% modeloDatos bd = new modeloDatos();
            bd.abrirConexion();
            ArrayList<Sala> salas = bd.getSalas();
            for (int i = 0; i < salas.size(); i++) {
        %>
        <form action="/PracticaFinalWeb/modificarSala">
            <br /><label>Nombre: </label><br><input id="nombre" type="text" name="nombre" value="<%=salas.get(i).getNombre()%>"autofocus required readonly/>
            <br /><label>Filas: </label><br><input id="filas" type="number" name="filas" value="<%=salas.get(i).getFilas()%>" autofocus required/>
            <br /><label>Columnas: </label><br><input id="columnas" type="number" name="columnas" value="<%=salas.get(i).getColumnas()%>" autofocus required/>
            <input type="hidden" id="thisField" name="inputName" value="modificar">
            <br/><input class="boton" type="submit" value="Modificar">
        </form>
        <form action="/PracticaFinalWeb/eliminarSala">
            <input type="hidden" id="thisField" name="inputName" value="<%= salas.get(i).getNombre()%>">
            <input class="boton" type="submit" value="Eliminar">
        </form>
        <br>
        <%}%>
    </body>
</html>
