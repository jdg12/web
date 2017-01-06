<%-- 
    Document   : perfil
    Created on : 05-ene-2017, 15:22:43
    Author     : jesus
--%>

<%@page import="bbdd.Reserva"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.modeloDatos"%>
<%@page import="bbdd.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css">
        <title>Mi perfil</title>
    </head>
    <body>
         <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
            <li><a href="perfil.jsp">Mi perfil</a></li>
        </ul>
        <h1>Tu perfil</h1>
        <h2>Mis reservas</h2>
        <%Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
        modeloDatos bd = new modeloDatos();
        bd.abrirConexion();
        ArrayList<Reserva> reservas = bd.getReservas(usuarioActual.getIdUsuario());
        for (int i = 0; i < reservas.size(); i++){%>
    </body>
</html>
