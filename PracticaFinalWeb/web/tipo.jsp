<%-- 
    Document   : tipo
    Created on : 08-ene-2017, 12:49:13
    Author     : jesus
--%>

<%@page import="bbdd.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo de entrada</title>
        <link rel="stylesheet" href="style/style.css">
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
            String id = request.getParameter("inputName");
            session.setAttribute("idPelicula", id);
        %>
<!--
        Esta es la parte predefinida del header que se repite
        -->
        <ul class="menu">
			<li><img src="style/chinchon.png" alt="" class="unstyled"></li>
            <li><a href="index.html">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
            <li><a href="perfil.jsp">Mi perfil</a></li>
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

        <h1>Tipo de entrada</h1>
        <form action="seleccionTipoServlet" class="pelicula" id="formulario" method="POST">
            <select id="tipo" name="tipo" >
                <option value="normal">Normal</option>
                <option value="reducida">Reducida</option>
            </select>
            <input class="boton" type="submit" value="Seleccionar tipo">
        </form>
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
