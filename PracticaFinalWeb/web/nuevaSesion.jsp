<%@page import="bbdd.Usuario"%>
<%@page import="bbdd.Sala"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Pelicula"%>
<%@page import="bbdd.modeloDatos"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Sesion</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <%!
            modeloDatos bd = new modeloDatos();
            ArrayList<Pelicula> peliculas = bd.getPeliculas();
            ArrayList<Sala> salas = bd.getSalas();

            private void ponerPeliculas(javax.servlet.jsp.JspWriter out) {
                try {
                    for (int i = 0; i < peliculas.size(); i++) {
                        out.println("<option value=\"" + peliculas.get(i).getNombre() + "\">" + peliculas.get(i).getTitulo() + "</option>");
                    }
                } catch (java.io.IOException e1) {
                    System.out.println(e1);
                }
            }

            private void ponerSalas(javax.servlet.jsp.JspWriter out) {
                try {
                    for (int i = 0; i < salas.size(); i++) {
                        System.out.println("Estoy aqui2");
                        out.println("<option value=\"" + salas.get(i).getNombre() + "\">" + salas.get(i).getNombre() + "</option>");
                    }
                } catch (java.io.IOException e1) {
                    System.out.println(e1);
                }
            }
        %> 
        <%Usuario usuario = (Usuario) session.getAttribute("usuarioActual");%>
        <form action="/PracticaFinalWeb/modificarSesionServlet" name="formulario" id="formulario">
            <br /><label>Id sesion: </label><br><input id="nombre" type="text" name="nombre" autofocus required/>
            <br /><label>Id pelicula: </label><br> 
            <select id="pelicula" name="pelicula"> 
                <% ponerPeliculas(out);%> 
            </select> 
            <br /><label>Id sala </label> <br>
            <select id="sala" name="sala">
                <% ponerSalas(out);%>
            </select>
            <br /><label>Hora: </label> <br><input id="hora" type="text" name="hora" autofocus required/>
            <br /><label>Dia semana: </label> <br><input id="diasemana" type="text"  name="diasemana" autofocus required/>
            <br /><label>Dia mes: </label><br> <input id="diames" min="1" max="31" type="number" name="diames"  autofocus required/>
            <br /><label>Mes: </label><br> <input id="mes" type="number" min="1" max="12" name="mes" autofocus required/>
            <input type="hidden" id="thisField" name="inputName" value="anadir">
            <br/><br><input class="boton" type="submit" value="Guardar">
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
