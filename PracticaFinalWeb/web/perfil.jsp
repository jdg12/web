<%-- 
    Document   : perfil
    Created on : 05-ene-2017, 15:22:43
    Author     : jesus
--%>

<%@page import="bbdd.Entrada"%>
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
        <%Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
            modeloDatos bd = new modeloDatos();
            bd.abrirConexion();%>
        <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
            <li><a href="perfil.jsp">Mi perfil</a></li>
        </ul>
        <h1>Tu perfil</h1>
        <% if (!usuarioActual.getIdUsuario().equals("admin")) {%>
        <h2>Mis reservas</h2>
        <%
            ArrayList<Reserva> reservas = bd.getReservas(usuarioActual.getIdUsuario());
            if (reservas != null) {
                for (int i = 0; i < reservas.size(); i++) {%>
        <div class="reserva">
            <h3>id Reserva: <%= reservas.get(i).getIdReserva()%></h3>
            <h3>Datos de la sesion:</h3>
            <p>Pelicula: <%=reservas.get(i).getEntrada().getSesion().getPelicula()%></p>
            <p>Sala: <%=reservas.get(i).getEntrada().getSesion().getSala()%></p>
            <p>Horario: <%=reservas.get(i).getEntrada().getSesion().getHora()%></p>
            <p>Fecha: <%=reservas.get(i).getEntrada().getSesion().getFechaS()%></p>
            <h3>Datos de la entrada: </h3>
            <p>Fila y columna: <%= reservas.get(i).getEntrada().getFila()%> <%=reservas.get(i).getEntrada().getColumna()%></P>
            <p>Precio: <%= reservas.get(i).getEntrada().getPrecio()%></p>
        </div>
        <%}
            }%>
        <h2>Mis datos</h2>
        <form action="/PracticaFinalWeb/modificarPerfil" class="perfil" id="formulario" method="POST">
            <p>
                <label>Nombre de usuario: </label><br><input id="idUsuario" type="text" name="idUsuario" value="<%= usuarioActual.getIdUsuario()%>"autofocus required readonly></input>
                <br /><label>Correo: </label><br><input id="correo" type="email" name="correo" value="<%= usuarioActual.getCorreo()%>" autofocus required></input>
                <br /><label>Nombre: </label><br><input id="nombre" type="text" name="nombre" value="<%= usuarioActual.getNombre()%>" autofocus></input>
                <br /><label>Apellidos: </label><br><input id="apellidos" type="text" name="apellidos" value="<%= usuarioActual.getApellidos()%>"></input>
                <br /><label>Direccion: </label><br><input id="direccion" type="text" name="direccion" value="<%= usuarioActual.getDireccion()%>"required></input>
                <br /><label>Cuenta: </label><br><input id="cuenta" type="text" name="cuenta" value="<%= usuarioActual.getCuenta()%>"></input>
                <br /><br>
                <input type="hidden" id="thisField" name="inputName" value="<%= usuarioActual.getIdUsuario()%>">
                <input class="boton" type="submit" value="Modificar">
            </p>
        </form>
        <%} else { %>
        <h2>Informes</h2>
        <input type="button" value="Ir a informes" onClick=" window.location.href='informes.jsp' ">
        <h2>Salas</h2>
         <input type="button" value="Gestionar salas" onClick=" window.location.href='salas.jsp' ">
         <h2>Sesiones</h2>
          <input type="button" value="Añadir sesion" onClick=" window.location.href='sesion.jsp' ">
         <h2>Entradas</h2>
        <%
            ArrayList<Entrada> entradas = bd.getEntradas();
            for (int j = 0; j < entradas.size(); j++) {
        %>
        <form action="/PracticaFinalWeb/borrarEntradaServlet" class="perfil" id="formulario" method="POST">
            <div class="reserva">
                <h3>Id entrada: <%=entradas.get(j).getId()%></h3>
                <p>Fila y columna: <%=entradas.get(j).getFila()%> <%=entradas.get(j).getColumna()%></p>
                <p>Precio: <%=entradas.get(j).getPrecio()%></p>
                <h3>Datos de la sesion</h3>
                <p>Pelicula: <%= entradas.get(j).getSesion().getPelicula()%></p>
                <p>Sala: <%= entradas.get(j).getSesion().getSala()%></p>
                <p>Horario: <%= entradas.get(j).getSesion().getHora()%></p>
                <p>Fecha: <%= entradas.get(j).getSesion().getFechaS()%></p>
            </div>
            <input type="hidden" id="thisField" name="inputName" value="<%= entradas.get(j).getId()%>">
            <input class="boton" type="submit" value="Eliminar entrada">
        </form>
        <%}%>
        <h2>Reservas</h2>
        <%
            ArrayList<Reserva> reservas = bd.getReservas(usuarioActual.getIdUsuario());%>
            <p>Total: <%=reservas.size()%>
                <%
            for (int k = 0; k < reservas.size(); k++){%>
                <div class="reserva">
            <h3>id Reserva: <%= reservas.get(k).getIdReserva()%></h3>
            <h3>Datos de la sesion:</h3>
            <p>Pelicula: <%=reservas.get(k).getEntrada().getSesion().getPelicula()%></p>
            <p>Sala: <%=reservas.get(k).getEntrada().getSesion().getSala()%></p>
            <p>Horario: <%=reservas.get(k).getEntrada().getSesion().getHora()%></p>
            <p>Fecha: <%=reservas.get(k).getEntrada().getSesion().getFechaS()%></p>
            <h3>Datos de la entrada: </h3>
            <p>Fila y columna: <%= reservas.get(k).getEntrada().getFila()%> <%=reservas.get(k).getEntrada().getColumna()%></P>
            <p>Precio: <%= reservas.get(k).getEntrada().getPrecio()%></p>
        </div>
            <%}%>
        <%}%>
    </body>
</html>
