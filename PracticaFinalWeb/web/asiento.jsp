<%-- 
    Document   : asiento
    Created on : 05-ene-2017, 15:23:40
    Author     : jesus
--%>

<%@page import="bbdd.modeloDatos"%>
<%@page import="bbdd.Sala"%>
<%@page import="bbdd.Sesion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css">
        <title>Asientos</title>
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
            String idSesion = (String) session.getAttribute("idSesion");
            Sala sala = (Sala) session.getAttribute("sala");
            modeloDatos bd = new modeloDatos();
            bd.abrirConexion();
        %>
        <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
            <li><a href="perfil.jsp">Mi perfil</a></li>
        </ul>
        <h1>Seleccione el tipo de entrada y el asiento</h1>
        <% for (int j = -1; j < sala.getColumnas(); j++) {%>
        <form style="float: left;">
            <button type="submit" style="width: 50px;
                    height: 50px;"><%=j%></button>
        </form>
        <%}%>
        <br>
        <br>
        <br>
        <% for (int i = 0; i < sala.getFilas(); i++) {%>
        <!--En la primera fila mostramos los numeros-->
        <form style="float: left;">
            <button  style="width: 50px;
                     height: 50px;"><%=i%></button>
        </form>
        <%for (int j = 0; j < sala.getColumnas(); j++) {%>
        <% if (bd.estaOcupadoAsiento(idSesion, i, j)) {%>
        <form style="float: left;">
            <button style="width: 50px;
                    height: 50px;
                    color: black;
                    background: red;"></button>
        </form>
        <%} else {%>
        <form action="/PracticaFinalWeb/comprarServlet" style="float: left;">
            <input type="hidden" id="thisField" name="inputName" value="<%=i%>">
            <input type="hidden" id="thisField2" name="inputName2" value="<%=j%>">
            <button type="submit" style="width: 50px;
                    height: 50px;
                    color: black;
                    background: blue;"></button>
        </form>
        <%}%>
        <%}%>
        <br>
        <br>
        <br>
        <%}%>
    </body>
</html>
