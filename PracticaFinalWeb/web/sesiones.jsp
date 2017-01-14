<%-- 
    Document   : sesiones
    Created on : 08-ene-2017, 14:40:08
    Author     : jesus
--%>

<%@page import="bbdd.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Sesion"%>
<%@page import="bbdd.Proxy"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sesiones</title>
        <link rel="stylesheet" href="style/style.css">
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#formulario").submit(function () {
                    if ($("#diasemana").val().length > 20) {
                        alert("El dia de la semana es demasiado largo");
                        return false;
                    }
                    if ($("#hora").val().length > 20) {
                        alert("La hora es demasiado larga");
                        return false;
                    }
                    if ($("#nombre").val().length > 100) {
                        alert("El id es demasiado largo");
                        return false;
                    }
                    return true;
                });
            });

        </script>
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
                    }
                }
            }
            
            Usuario usuarioActual = bd.getUsuario(idUsuario);

            if (usuarioActual == null) {
                usuarioActual = new Usuario();
                usuarioActual.setIdUsuario("visitante");
            }

            if (!usuarioActual.getIdUsuario().equals("admin")) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Tiene que ser el administrador');");
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
                    var month = date.getMonth() + 1;
                    var year = date.getFullYear();
                    var fecha = day + "/" + month + "/" + year;
                    document.getElementById("date").innerHTML = fecha;
                </script>
                <br><br/>
                <a class="user">Bienvenido, admin</a>  <!-- AQUÍ VA EL MÉTODO DE COGER EL USUARIO-->
            </div>
        </div>

        <div class="linea">
        </div>
        <!--
        Aquí acaba la parte predefinida del header que se repite
        -->
        <h1>Sesiones</h1>
        <h2>Gestion sesiones</h2>
        <input class="boton" type="button" value="Añadir una sesion" onClick=" window.location.href = 'nuevaSesion.jsp'">
        <h2>Consulta sesiones</h2>
        <%
            
            ArrayList<Sesion> sesiones = bd.getSesiones();
            for (int i = 0; i < sesiones.size(); i++) {
        %>
        <div class="sesion">
        <form action="/PracticaFinalWeb/modificarSesionServlet" id="formulario" name="formulario">
            <br /><label>Id sesion: </label><input id="nombre" type="text" value="<%=sesiones.get(i).getIdSesion()%>"name="nombre" autofocus required readonly/>
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
