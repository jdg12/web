<%-- 
    Document   : sesiones
    Created on : 08-ene-2017, 14:40:08
    Author     : jesus
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Sesion"%>
<%@page import="bbdd.modeloDatos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sesiones</title>
        <link rel="stylesheet" href="style/style.css">
    </head>
    <body>
         <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
            <li><a href="perfil.jsp">Mi perfil</a></li>
        </ul>
        <h1>Sesiones</h1>
        <input type="button" value="Añadir una sesion" onClick=" window.location.href='nuevaSesion.jsp' ">
        <% modeloDatos bd = new modeloDatos();
        bd.abrirConexion();
        ArrayList<Sesion> sesiones = bd.getSesiones();
        for (int i = 0; i < sesiones.size(); i++)
        {
        %>
        <form action="/PracticaFinalWeb/modificarSesionServlet">
            <br /><label>Id sesion: </label><br><input id="nombre" type="text" value="<%=sesiones.get(i).getIdSesion()%>"name="nombre" autofocus required readonly/>
            <br /><label>Id pelicula: </label> <input id="pelicula" type="text" name="pelicula" value="<%=sesiones.get(i).getPelicula()%>"autofocus required readonly/>
            <br /><label>Id sala </label> <input id="sala" type="text" name="sala" value="<%=sesiones.get(i).getSala()%>" autofocus required readonly/>
            <br /><label>Hora: </label> <input id="hora" type="text" name="hora" value="<%=sesiones.get(i).getHora()%>" autofocus required/>
            <br /><label>Dia semana: </label> <input id="diasemana" type="text" value="<%=sesiones.get(i).getDiaSemana()%>" name="diasemana" autofocus required/>
            <br /><label>Dia mes: </label> <input id="diames" type="number" name="diames" value="<%=sesiones.get(i).getDiaMes()%>" autofocus required/>
            <br /><label>Mes: </label> <input id="mes" type="number" name="mes" value="<%=sesiones.get(i).getMes()%>"autofocus required/>
            <input type="hidden" id="thisField" name="inputName" value="modificar">
            <br/><input class="boton" type="submit" value="Modificar">
        </form>
        <form action="/PracticaFinalWeb/eliminarSesion">
            <input type="hidden" id="thisField" name="inputName" value="<%=sesiones.get(i).getIdSesion()%>">
            <input class="boton" type="submit" value="Eliminar">
        </form>
        <%}%>
    </body>
</html>
