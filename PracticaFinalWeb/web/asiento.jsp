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
            modeloDatos bd = new modeloDatos();
            Cookie[] cookies = request.getCookies();
            String idUsuario = "";
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    String nombre = cookie.getName();
                    if (nombre.equals("idUsuario")) {
                        idUsuario = cookie.getValue();
                        System.out.println("Id: " + idUsuario);
                    }
                }
            }
            bd.abrirConexion();
            Usuario usuarioActual = bd.getUsuario(idUsuario);
            bd.abrirConexion();
            if (usuarioActual == null) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Registrese primero');");
                out.println("location='acceso.html';");
                out.println("</script>");
            }
            String idSesion = (String) session.getAttribute("idSesion");
            Sala sala = (Sala) session.getAttribute("sala");
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
        
        <h1>Seleccione el tipo de entrada y el asiento</h1>
        <div class="seleccionAsiento">
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
        </div>
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
