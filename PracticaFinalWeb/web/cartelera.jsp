<%-- 
    Document   : cartelera
    Created on : 05-ene-2017, 15:22:30
    Author     : jesus
--%>

<%@page import="bbdd.Usuario"%>
<%@page import="bbdd.modeloDatos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Pelicula"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style2.css">
        <title>Cartelera</title>
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
                    }
                }
            }
            bd.abrirConexion();
            Usuario usuario = bd.getUsuario(idUsuario);
            if (usuario.getIdUsuario() == null) {
                usuario = new Usuario();
                usuario.setNombre("visitante");
                usuario.setIdUsuario("visitante");
            }%>
        <!--
        Esta es la parte predefinida del header que se repite
        -->
        <ul class="menu">
            <li><img src="style/chinchon.png" alt="" class="unstyled"></li>
            <li><a href="index.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
                <%if (usuario == null || usuario.getIdUsuario().equals("visitante")) {%>
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
                    var month = date.getMonth() + 1;
                    var year = date.getFullYear();
                    var fecha = day + "/" + month + "/" + year;
                    document.getElementById("date").innerHTML = fecha;
                </script>
                <br><br/>
                <a class="user">Bienvenido, <%=usuario.getIdUsuario()%></a>  <!-- AQUÍ VA EL MÉTODO DE COGER EL USUARIO-->
            </div>
        </div>

        <div class="linea">
        </div>
        <!--
        Aquí acaba la parte predefinida del header que se repite
        -->
        <% if (usuario.getNombre().equals("admin")) {%>
        <input type="button" value="Nueva película" onClick="window.location.href = 'nuevaPelicula.jsp'">
        <%}%>
        <h1>Cartelera</h1>
        <h1>Películas</h1>
        <%  ArrayList<Pelicula> peliculas = bd.getPeliculas();
            for (int i = 0; i < peliculas.size(); i++) {%>
        <div class="pelicula">
            <p>Titulo: <%=peliculas.get(i).getNombre()%></p>
            <p>Sinopsis: <%=peliculas.get(i).getSinopsis()%></p>
            <p>Genero: <%=peliculas.get(i).getGenero()%></p>
            <form action="/PracticaFinalWeb/verMasPeliculaServlet" class="verMas" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= peliculas.get(i).getNombre()%>">
                <input class="boton" type="submit" value="Ver mas">
            </form>
            <% if (usuario.getNombre().equals("admin")) {%>
            <form action="/PracticaFinalWeb/borrarPelicula" class="pelicula" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= peliculas.get(i).getNombre()%>">
                <input class="boton" type="submit" value="Eliminar">
            </form>
            <form action="tipo.jsp" class="pelicula" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= peliculas.get(i).getNombre()%>">
                <input class="boton" type="submit" value="Comprar entrada">
            </form>
            <%} else {%>
            <form action="tipo.jsp" class="pelicula" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= peliculas.get(i).getNombre()%>">
                <input class="boton" type="submit" value="Reservar entrada">
            </form>
            <%}%>
        </div>
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
