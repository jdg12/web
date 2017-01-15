<%-- 
    Document   : perfil
    Created on : 05-ene-2017, 15:22:43
    Author     : jesus
--%>

<%@page import="bbdd.Entrada"%>
<%@page import="bbdd.Reserva"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Proxy"%>
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
        <%
            Proxy bd = Proxy.getInstancia();
            Cookie[] cookies = request.getCookies();
            String idUsuario = "";
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    String nombre = cookie.getName();
                    if (nombre.equals("idUsuario")) {
                        idUsuario = cookie.getValue();
                        System.out.println("Id: "+idUsuario);
                    }
                }
            }
            
            Usuario usuarioActual = bd.getUsuario(idUsuario);
            if (usuarioActual.getIdUsuario() == null)
            {
                usuarioActual.setIdUsuario("Visitante");
            }
            if (usuarioActual.getIdUsuario().equals("Visitante"))
            {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Tiene que estar registrado');");
                out.println("location='acceso.jsp';");
                out.println("</script>");
            }
            %>
<!--
        Esta es la parte predefinida del header que se repite
        -->
       <ul class="menu">
            <li><img src="style/chinchon.png" alt="" class="unstyled"></li>
            <li><a href="index.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
                <%if (usuarioActual == null || usuarioActual.getIdUsuario().equals("visitante")) {%>
            <li><a href="acceso.jsp">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
                <%} else {%>
            <li><a href="perfil.jsp">Mi perfil</a></li>
            <li><a href="/PracticaFinalWeb/SalirServlet">Salir</a></li>
                <%}%>
        </ul>
		<br><br/>
		<div>
		
	<div style="height: 200px; width: 100%;">
	
		
	<a class="user" id="date"></a> 
	<script>
		var date = new Date();
		var day = date.getDate();
		var month = date.getMonth()+1;
		var year = date.getFullYear();
		var fecha = day + "/" + month + "/" + year;
		document.getElementById("date").innerHTML = fecha;
	</script>
	<br><br/>
	<a class="user">Bienvenido, <%=usuarioActual.getIdUsuario()%></a>  <!-- AQUÍ VA EL MÉTODO DE COGER EL USUARIO-->
	</div>
	</div>
                	
	<div class="linea">
	</div>
        <!--
        Aquí acaba la parte predefinida del header que se repite
        -->
        <h1>Tu perfil</h1>
        <% if (!usuarioActual.getIdUsuario().equals("admin")) {%>
        <!--Mostramos las reservas realizadas por nosotros-->
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
            <!--Nuestros datos para modificarlos-->
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
        <!--EN EL CASO DE SER EL ADMINISTRADOR-->
        <!--Mostramos los botones para la gestion de salas y sesiones-->
        <h2>Informes</h2>
        <input class="boton" type="button" value="Ir a informes" onClick=" window.location.href = 'informes.jsp'">
        <h2>Salas</h2>
        <input class="boton" type="button" value="Gestionar salas" onClick=" window.location.href = 'salas.jsp'">
        <h2>Sesiones</h2>
        <input class="boton" type="button" value="Gestionar sesiones" onClick=" window.location.href = 'sesiones.jsp'">
        <h2>Sorteo</h2>
        <input class="boton" type="button" value="Ir a gestión de sorteo" onClick=" window.location.href = 'sorteo.jsp'">
        <!--Mostramos todas las entradas-->
        <h2>Entradas</h2>
        <%
            ArrayList<Entrada> entradas = bd.getEntradas();
            for (int j = 0; j < entradas.size(); j++) {
        %>
        <form action="/PracticaFinalWeb/borrarEntradaServlet" class="entrada" id="formulario" method="POST">
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
        <!--Todas las reservas-->
        <h2>Reservas</h2>
        <%
            ArrayList<Reserva> reservas = bd.getReservas(usuarioActual.getIdUsuario());%>
            <%
                for (int k = 0; k < reservas.size(); k++) {%>
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
                                <!--
        Esta es la parte predefinida del footer que se repite
        -->
	<br><br/>
	<div class="vacio">
	</div>
		<div class="containerFooter">
			<h3>Aviso Legal</h3>
			<br><br/>
			<div class="footer">
				<p>Estás en el sitio web de Chinchón Multicines Madrid. Aquí puedes acceder al aviso legal. © 1997 AWESOME MULTICINES</p><br></br>
			</div>
		</div>
		
		<div class="containerRestFooter">
			<h3>Aviso Sobre Cookies</h3>
			<br><br/>
			<div class="footer">
				<p>Cuando visite nuestra página, podemos enviar a su computadora una o más cookies, un pequeño archivo de texto que contiene una cadena de caracteres alfanuméricos, que identifica de forma exclusiva su navegador y le permite conectarse más rápido y mejorar su navegación a través del sitio. Una cookie no recopila información personal sobre usted. Este sitio utiliza cookies de sesión y cookies persistentes. Una cookie persistente permanece en su disco duro después de cerrar su navegador.</p><br></br>
			</div>
		</div>
		
		<div class="containerRightFooter">
			<div class="footer">
				<img src="style/escudo.png" alt="" class="unstyled">
			</div>
		</div>
    </body>
</html>
