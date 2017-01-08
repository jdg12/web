<%-- 
    Document   : pelicula
    Created on : 05-ene-2017, 15:21:44
    Author     : jesus
--%>

<%@page import="bbdd.Comentario"%>
<%@page import="bbdd.Actor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Pelicula"%>
<%@page import="bbdd.modeloDatos"%>
<%@page import="bbdd.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css">
        <title>Película</title>
    </head>
    <body>
        <%
            modeloDatos bd = new modeloDatos();
            bd.abrirConexion();
            //Obtenemos datos como el usuario y la pelicula a mostrar
            Usuario usuario = (Usuario) session.getAttribute("usuarioActual");
            if (usuario == null) {
                System.out.println("Estoy aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
                usuario = new Usuario();
                usuario.setNombre("visitante");
                usuario.setIdUsuario("visitante");
            }
            String idPelicula = (String) session.getAttribute("peliculaId");
            Pelicula pelicula = bd.getPelicula(idPelicula);
        %>
        <ul class="menu">
            <li><a href="inicio.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
                <% if (usuario == null || usuario.getIdUsuario().equals("") || usuario.getIdUsuario().equals("visitante")) {%> 
            <li><a href="acceso.html">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
                <%} else {%>
            <li><a href="perfil.jsp">Hola: <%=usuario.getIdUsuario()%></a></li>
            <li><a href="/PracticaFinalWeb/SalirServlet">Salir</a></li>
                <%}%>
        </ul>
        <%if (!usuario.getIdUsuario().equals("admin")) {%>
        <h1><%=pelicula.getNombre()%></h1>
       <form action="tipo.jsp" class="pelicula" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= pelicula.getNombre()%>">
                <input class="boton" type="submit" value="Reservar entrada">
            </form>
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
            <p>Nombre: <%=actores.get(j).getNombre()%></p>
            <p>Apellidos: <%=actores.get(j).getApellidos()%></p>
            <%}%>
            <h2>Otros datos</h2>
            <p>Nacionalidad: <%=pelicula.getNacionalidad()%></p>
            <p>Duracion: <%=pelicula.getDuracion()%></p>
            <p>Año: <%=pelicula.getAno()%></p>
            <p>Otros: <%=pelicula.getOtros()%></p>
        </div>
        <h2>Comentarios</h2>
        <% if (usuario.getIdUsuario().equals("visitante")) {%>
        <h2>Registrse para dejar un comentario</h2>
        <%} else {%>
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
        <!--Ahora mostramos los comentarios y dejamos un cuadro de texto para dejar uno nuevo-->
        <% ArrayList<Comentario> comentarios = bd.getComentarios(pelicula.getNombre());
            for (int k = 0; k < comentarios.size(); k++) {%>
        <div class="comentario">
            <div class="autor"><%=comentarios.get(k).getIdUsuario()%></div>
            <div class="comentario"><%=comentarios.get(k).getTexto()%></div>
            <div class="puntuacion"><%=comentarios.get(k).getPuntuacion()%></div>
        </div>
        <%}%>
        <%} else {%>
        <form action="tipo.jsp" class="pelicula" id="formulario" method="POST">
                <input type="hidden" id="thisField" name="inputName" value="<%= pelicula.getNombre()%>">
                <input class="boton" type="submit" value="Reservar entrada">
            </form>
        <form action="/PracticaFinalWeb/modificarPelicula" class="pelicula" id="formulario" method="POST">
            <p>
                <label>Nombre: </label><br><input id="idPelicula" type="text" name="idPelicula" autofocus required value="<%=pelicula.getNombre()%>" readonly></input>
                <br/><label>Titulo: </label><br><input id="titulo" type="text" name="titulo" autofocus required value="<%=pelicula.getTitulo()%>"></input>
                <br/><label>Sinopsis: </label><br><input id="sinopsis" type="text" name="sinopsis" autofocus required value="<%=pelicula.getSinopsis()%>"></input>
                <br/><label>Pagina: </label><br><input id="pagina" type="text" name="pagina" autofocus required value="<%=pelicula.getPagina()%>"></input>
                <br/><label>Genero: </label><br><input id="genero" type="text" name="genero" autofocus required value="<%=pelicula.getGenero()%>"></input>
                <br/><label>Nacionalidad: </label><br><input id="nacionalidad" type="text" name="nacionalidad" autofocus required value="<%=pelicula.getNacionalidad()%>"></input>
                <br/><label>Duracion: </label><br><input id="duracion" type="number" name="duracion" autofocus required value="<%=pelicula.getDuracion()%>"></input>
                <br/><label>Titulo: </label><br><input id="titulo" type="text" name="titulo" autofocus required value="<%=pelicula.getTitulo()%>"></input>
                <br/><label>Año: </label><br><input id="ano" type="number" name="ano" autofocus required value="<%=pelicula.getAno()%>"></input>
                <br/><label>Distribuidora: </label><br><input id="distribuidora" type="text" name="distribuidora" autofocus required value="<%=pelicula.getDistribuidora()%>"></input>
                <br/><label>Director: </label><br><input id="director" type="text" name="director" autofocus required value="<%=pelicula.getDirector()%>"></input>
                <br/><label>Clasificacion: </label><br><input id="clasificacion" type="number" name="clasificacion" autofocus required value="<%=pelicula.getClasificacion()%>"></input>
                <br/><label>Otros: </label><br><input id="otros" type="text" name="otros" autofocus required value="<%=pelicula.getOtros()%>"></input>
                <br /><br>
                <input class="boton" type="submit" value="Modificar">
            </p>
        </form>
        <form action="/PracticaFinalWeb/borrarPelicula" class="pelicula" id="formulario" method="POST">
            <input type="hidden" id="thisField" name="inputName" value="<%= pelicula.getNombre()%>">
            <input class="boton" type="submit" value="Eliminar">
        </form>
        <%}%>
    </body>
</html>
