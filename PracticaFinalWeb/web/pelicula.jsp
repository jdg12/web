<%-- 
    Document   : pelicula
    Created on : 05-ene-2017, 15:21:44
    Author     : jesus
--%>

<%@page import="bbdd.Comentario"%>
<%@page import="bbdd.Actor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Pelicula"%>
<%@page import="bbdd.Proxy"%>
<%@page import="bbdd.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style2.css">
        <title>Película</title>
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#formulario").submit(function () {
                    if ($("#titulo").val().length > 20) {
                        alert("El titulo es demasiado largo");
                        return false;
                    }
                    if ($("#idPelicula").val().length > 100) {
                        alert("El id es demasiado largo");
                        return false;
                    }
                    if ($("#sinopsis").val().length > 500) {
                        alert("La sinopsis es demasiado larga");
                        return false;
                    }
                    if ($("#pagina").val().length > 50) {
                        alert("La pagina web es demasiado larga");
                        return false;
                    }
                    if ($("#genero").val().length > 20) {
                        alert("El genero es demasiado largo");
                        return false;
                    }
                    if ($("#nacionalidad").val().length > 20) {
                        alert("La nacionalidad es demasiado larga");
                        return false;
                    }
                     if ($("#distribuidora").val().length > 20) {
                        alert("La distirbuidora es demasiado larga");
                        return false;
                    }
                     if ($("#director").val().length > 20) {
                        alert("El director es demasiado largo");
                        return false;
                    }
                    if ($("#otros").val().length > 20) {
                        alert("Los otros datos son demasiado largos");
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
            
            Usuario usuario = bd.getUsuario(idUsuario);
            
            //Obtenemos datos como el usuario y la pelicula a mostrar
            if (usuario.getIdUsuario() == null) {
                usuario = new Usuario();
                usuario.setNombre("visitante");
                usuario.setIdUsuario("visitante");
            }
            String idPelicula = (String) session.getAttribute("peliculaId");
            Pelicula pelicula = bd.getPelicula(idPelicula);
        %>
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

        <!--MOSTRAREMOS DE DOS MANERAS LOS DATOS->
        <!--En el caso de ser el admin además podremos modificar y eliminas la pelicula-->
        <%if (!usuario.getIdUsuario().equals("admin")) {%>
        <h1><%=pelicula.getNombre()%></h1>
        <!--mostramos el boton de reservar la pelicula-->
        <form action="tipo.jsp" class="pelicula" id="formulario" method="POST">
            <input type="hidden" id="thisField" name="inputName" value="<%= pelicula.getNombre()%>">
            <input class="boton" type="submit" value="Reservar entrada">
        </form>
         <!--Mostramos los datos de la pelicula-->
        <div class="datosPelicula">
            <h2>Sinopsis</h2>
            <p><%=pelicula.getSinopsis()%></p>
            <h2>Genero</h2>
            <p><%=pelicula.getGenero()%></p>
            <h2>Clasificacion</h2>
            <p><%=pelicula.getClasificacion()%></p>
            <h2>Actores</h2>
            <% ArrayList<Actor> actores = bd.getActores(pelicula.getNombre());
                for (int j = 0; j < actores.size(); j++) {%>
            <div class="actor">
                <p>Nombre: <%=actores.get(j).getNombre()%></p>
                <p>Apellidos: <%=actores.get(j).getApellidos()%></p>
            </div>
            <%}%>
            <h2>Otros datos</h2>
            <p>Nacionalidad: <%=pelicula.getNacionalidad()%></p>
            <p>Duracion: <%=pelicula.getDuracion()%></p>
            <p>Año: <%=pelicula.getAno()%></p>
            <p>Otros: <%=pelicula.getOtros()%></p>
        </div>
        <!--Mostramos los comentarios-->
        <h2>Comentarios</h2>
        <% ArrayList<Comentario> comentarios = bd.getComentarios(pelicula.getNombre());
            for (int k = 0; k < comentarios.size(); k++) {%>
        <div class="comentario">
            <div class="autor"><%=comentarios.get(k).getIdUsuario()%></div>
            <div class="comentario"><%=comentarios.get(k).getTexto()%></div>
            <div class="puntuacion"><%=comentarios.get(k).getPuntuacion()%></div>
        </div>
        <%}%>
        <% if (usuario.getIdUsuario().equals("visitante")) {%>
        <h2>Registrse para dejar un comentario</h2>
        <%} else {%>
        <!--Cuadro de texto para dejar un comentario-->
        <form action="/PracticaFinalWeb/dejarComentario" class="dejarComentario" id="formulario" method="POST">
            <p>
                <label>Comentario: </label><br><input id="comentario" type="text" name="comentario" autofocus required></input>
                <br /><label>Puntuacion: </label><br><input id="puntuacion" type="number" name="puntuacion" min="1" max="5" autofocus required></input>
                <br /><br>
                <input type="hidden" id="thisField" name="inputName" value="<%= usuario.getIdUsuario()%>">
                <input type="hidden" id="thisField2" name="inputName2" value="<%= pelicula.getNombre()%>">
                <input class="boton" type="submit" value="Dejar comentario">
            </p>
        </form>
        <%}%>
        <%} else {%>
        <!--Si somo el administrador los datos se mostrarán con textfields-->
        <h1><%=pelicula.getNombre()%></h1>
        <h2>Datos pelicula</h2>
        <form action="/PracticaFinalWeb/modificarPelicula" class="pelicula" id="formulario" method="POST">
            <p>
                <label>Nombre: </label><br><input id="idPelicula" type="text" name="idPelicula" autofocus required value="<%=pelicula.getNombre()%>" readonly></input>
                <br/><label>Titulo: </label><br><input id="titulo" type="text" name="titulo" autofocus required value="<%=pelicula.getTitulo()%>"></input>
                <br/><label>Sinopsis: </label><br><input id="sinopsis" type="text" name="sinopsis" autofocus required value="<%=pelicula.getSinopsis()%>"></input>
                <br/><label>Pagina: </label><br><input id="pagina" type="text" name="pagina" autofocus required value="<%=pelicula.getPagina()%>"></input>
                <br/><label>Genero: </label><br><input id="genero" type="text" name="genero" autofocus required value="<%=pelicula.getGenero()%>"></input>
                <br/><label>Nacionalidad: </label><br><input id="nacionalidad" type="text" name="nacionalidad" autofocus required value="<%=pelicula.getNacionalidad()%>"></input>
                <br/><label>Duracion: </label><br><input id="duracion" type="number" name="duracion" autofocus required value="<%=pelicula.getDuracion()%>"></input>
                <br/><label>Año: </label><br><input id="ano" type="number" min="1950" max="2020" name="ano" autofocus required value="<%=pelicula.getAno()%>"></input>
                <br/><label>Distribuidora: </label><br><input id="distribuidora" type="text" name="distribuidora" autofocus required value="<%=pelicula.getDistribuidora()%>"></input>
                <br/><label>Director: </label><br><input id="director" type="text" name="director" autofocus required value="<%=pelicula.getDirector()%>"></input>
                <br/><label>Clasificacion: </label><br><input id="clasificacion" min="0" max="18"type="number" name="clasificacion" autofocus required value="<%=pelicula.getClasificacion()%>"></input>
                <br/><label>Otros: </label><br><input id="otros" type="text" name="otros" autofocus required value="<%=pelicula.getOtros()%>"></input>
                <br /><br>
                <input class="boton" type="submit" value="Modificar">
            </p>
        </form>
        <form action="tipo.jsp" class="pelicula" id="formulario" method="POST">
            <input type="hidden" id="thisField" name="inputName" value="<%= pelicula.getNombre()%>">
            <input class="boton" type="submit" value="Reservar entrada">
        </form>
        <form action="/PracticaFinalWeb/borrarPelicula" class="pelicula" id="formulario" method="POST">
            <input type="hidden" id="thisField" name="inputName" value="<%= pelicula.getNombre()%>">
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
