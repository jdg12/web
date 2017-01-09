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
	<a class="user">Bienvenido, Yisus</a>  <!-- AQUÍ VA EL MÉTODO DE COGER EL USUARIO-->
	</div>
	</div>
                	
	<div class="linea">
	</div>
        <!--
        Aquí acaba la parte predefinida del header que se repite
        -->
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
