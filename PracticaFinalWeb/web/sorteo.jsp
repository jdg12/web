<%-- 
    Document   : sorteo
    Created on : 15-ene-2017, 0:43:40
    Author     : JD
--%>

<%@page import="java.util.Iterator"%>
<%@page import="bbdd.FichaSorteo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Usuario"%>
<%@page import="bbdd.Proxy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css">
        <title>Gestion de Sorteo</title>
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
        <h1>Datos Sorteo</h1>
        
        <%
            
            ArrayList<FichaSorteo> participantes = bd.getParticipantes();
            FichaSorteo ganador=bd.getGanadorSorteo();
            
        %>
        <h2>Ganador del sorteo</h2>
        <%
            if(ganador==null){
                out.println("<h3 style=\"color:white;\">Aun no se ha realizado el sorteo </h3><br>");
                
            }
        
            else{
        %>
        <div class="reserva">
            <p>Usuario <%=ganador.getUsuario().getIdUsuario()%></p>
            <p>Reserva <%=ganador.getReserva().getIdReserva()%></p>
            <p>Fecha y hora <%=ganador.getFecha()%></p>
        </div>
        <%}

        %>
                <BR>
            <form action="/PracticaFinalWeb/SorteoServlet"  id="formulario" method="POST">
                <label>Realizar Sorteo</label><br>
                <input type="hidden" id="thisField" name="sorteo" value="1">
                <input class="boton" type="submit" value="Sortear">
            </form>
            
            <BR>
        <h2>Participantes:</h2>
        <%
            if(participantes ==null|| participantes.isEmpty()){
                out.println("<h3 style=\"color:white;\">Aun no hay ningun participante para este sorteo </h3><br>");
            }
            else{
                Iterator it =participantes.iterator();
                FichaSorteo ficha = new FichaSorteo();
                 %>
        <TABLE border= "1" WIDTH= "400">
            <TH aling= "rigth">Id Usuario</TH>
            <TH aling= "rigth">Id Reserva</TH>
            <TH aling= "rigth">Fecha y Hora</TH>
                <%
                while (it.hasNext()) {
                    ficha =(FichaSorteo)it.next();
                
        %>
         <TR>
                <TD><%= ficha.getUsuario().getIdUsuario()%> </TD>
                <TD><%= ficha.getReserva().getIdReserva()%> </TD>
                <TD><%=ficha.getFecha()%></TD>

            </TR>
        <%      }
        %>
        </TABLE> 
        <% }%>
        <BR>
        <form action="/PracticaFinalWeb/SorteoServlet"  id="formulario2" method="POST">
                <label>Resetear Sorteo</label><br>
                <input type="hidden" id="thisField2" name="sorteo" value="2">
                <input class="boton" type="submit" value="Resetear">
            </form>  
        <BR>
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
