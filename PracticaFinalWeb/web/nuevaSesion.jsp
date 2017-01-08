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
    </head>
    <body>
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
        <form action="/PracticaFinalWeb/modificarSesionServlet">
            <br /><label>Id sesion: </label><br><input id="nombre" type="text" name="nombre" autofocus required/>
            <br /><label>Id pelicula: </label> 
            <select id="pelicula" name="pelicula"> 
                <% ponerPeliculas(out);%> 
            </select> 
            <br /><label>Id sala </label> 
            <select id="sala" name="sala">
                <% ponerSalas(out);%>
            </select>
            <br /><label>Hora: </label> <input id="hora" type="text" name="hora" autofocus required/>
            <br /><label>Dia semana: </label> <input id="diasemana" type="text"  name="diasemana" autofocus required/>
            <br /><label>Dia mes: </label> <input id="diames" min="1" max="31" type="number" name="diames"  autofocus required/>
            <br /><label>Mes: </label> <input id="mes" type="number" min="1" max="12" name="mes" autofocus required/>
            <input type="hidden" id="thisField" name="inputName" value="anadir">
            <br/><input class="boton" type="submit" value="Guardar">
        </form>
    </body>
</html>
