<%-- 
    Document   : informes
    Created on : 05-ene-2017, 15:22:09
    Author     : jesus
--%>

<%@page import="bbdd.Reserva"%>
<%@page import="bbdd.Sesion"%>
<%@page import="bbdd.EntradaNormal"%>
<%@page import="bbdd.EstrategiaClasificacion"%>
<%@page import="bbdd.EstrategiaAno"%>
<%@page import="bbdd.EstrategiaGenero"%>
<%@page import="java.util.Iterator"%>
<%@page import="bbdd.Entrada"%>
<%@page import="bbdd.Contexto"%>
<%@page import="bbdd.EstrategiaNombre"%>
<%@page import="bbdd.Usuario"%>
<%@page import="bbdd.modeloDatos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bbdd.Pelicula"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informes</title>
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">

        </script>
    </head>

    <body>
        <h1>Informes de Sistema</h1>
        <ul class="menu" >
            <li><a href="index.jsp">Inicio</a></li>
            <li><a href="cartelera.jsp">Cartelera</a></li>
            <li><a href="acceso.jsp">Acceder</a></li>
            <li><a href="registro.html">Registro</a></li>
            <li><a href="perfil.jsp">Mi perfil</a></li>
        </ul>
        <BR>
        <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario" method="POST">
            <label>Peliculas por Nombre</label>
            <input type="hidden" id="thisField" name="informe" value="1">
            <input class="boton" type="submit" value="Ver Informe">
        </form>
        <BR>
        <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario2" method="POST">
            <label>Peliculas por Genero</label>
            <input type="hidden" id="thisField2" name="informe" value="2">
            <input class="boton" type="submit" value="Ver Informe">
        </form>
        <BR>
        <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario3" method="POST">
            <label>Peliculas por Año</label>
            <input type="hidden" id="thisField3" name="informe" value="3">
            <input class="boton" type="submit" value="Ver Informe">
        </form>
        <BR>
        <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario4" method="POST">
            <label>Peliculas por Clasificación</label>
            <input type="hidden" id="thisField4" name="informe" value="4">
            <input class="boton" type="submit" value="Ver Informe">
        </form>
        <BR>
        <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario5" method="POST">
            <label>Usuarios por Id</label>
            <input type="hidden" id="thisField5" name="informe" value="5">
            <input class="boton" type="submit" value="Ver Informe">
        </form>
        <BR>
        <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario6" method="POST">
            <label>Entradas por Pelicula y Recaudacion</label>
            <input type="hidden" id="thisField6" name="informe" value="6">
            <input class="boton" type="submit" value="Ver Informe">
        </form>
        <BR>
        <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario7" method="POST">
            <label>Sesiones por pelicula</label>
            <input type="hidden" id="thisField7" name="informe" value="7">
            <input class="boton" type="submit" value="Ver Informe">
        </form>
        <BR>
        <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario8" method="POST">
            <label>Reservas Por Usuario</label>
            <input type="hidden" id="thisField8" name="informe" value="8">
            <input class="boton" type="submit" value="Ver Informe">
        </form>
        <BR>

        <%
            String opcion = (String) session.getAttribute("opcion");
            System.out.println(opcion);
            if (opcion == null) {
                opcion = "1";
            }
            modeloDatos bd = new modeloDatos();
            bd.abrirConexion();

            if (opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equals("4")) {
                ArrayList<Pelicula> peliculas = bd.getPeliculas();
        %>

        <TABLE border= "1" WIDTH= "400">
            <TD aling= "rigth">Nombre Pelicula</TD>
            <TD aling= "rigth">Pagina Web</TD>
            <TD aling= "rigth">Titulo Original</TD>
            <TD aling= "rigth">genero</TD>
            <TD aling= "rigth">nacionalidad</TD>
            <TD aling= "rigth">Duracion</TD>
            <TD aling= "rigth">Año</TD>
            <TD aling= "rigth">Director</TD>
            <TD aling= "rigth">Clasificación</TD>
            <TD aling= "rigth">Más info</TD>



            <%  EstrategiaNombre en = new EstrategiaNombre();
                EstrategiaGenero eg = new EstrategiaGenero();
                EstrategiaAno ea = new EstrategiaAno();
                EstrategiaClasificacion ec = new EstrategiaClasificacion();
                Contexto con = new Contexto(en, peliculas);
                if (opcion.equals("2")) {
                    con.setEstrategia(eg);
                }
                if (opcion.equals("3")) {
                    con.setEstrategia(ea);
                }
                if (opcion.equals("4")) {
                    con.setEstrategia(ec);
                }
                con.ejecutaEstrategia();
                Iterator it = peliculas.iterator();
                Pelicula peli = new Pelicula();

                while (it.hasNext()) {
                    peli = (Pelicula) it.next();
            %>
            <TR>
                <TD><%= peli.getNombre()%> </TD>
                <TD><%= peli.getPagina()%> </TD>
                <TD><%=peli.getTitulo()%></TD>
                <TD><%=peli.getGenero()%></TD>
                <TD><%= peli.getNacionalidad()%></TD>
                <TD><%= peli.getDuracion()%></TD>
                <TD><%= peli.getAno()%></TD>
                <TD><%= peli.getDirector()%></TD>
                <TD><%= peli.getClasificacion()%></TD>
                <TD><form action="/PracticaFinalWeb/verMasPeliculaServlet" class="verMas" id="formulario" method="POST">
                        <input type="hidden" id="thisField" name="inputName" value="<%= peli.getNombre()%>">
                        <input class="boton" type="submit" value="Ver mas">
                    </form></TD>
            </TR>

            <%}

            %>
        </TABLE> 

        <%            }
            if (opcion.equals("5")) {
                ArrayList<Usuario> usuarios = bd.getUsuarios();
                Iterator it = usuarios.iterator();

        %>
        <TABLE border= "1" WIDTH= "400">
            <TD aling= "rigth">Id Usuario</TD>
            <TD aling= "rigth">Nombre</TD>
            <TD aling= "rigth">Apellidos</TD>
            <TD aling= "rigth">Direccion</TD>
            <TD aling= "rigth">Correo</TD>
            <TD aling= "rigth">Cuenta</TD>




            <%                Usuario user = new Usuario();

                while (it.hasNext()) {
                    user = (Usuario) it.next();
            %>
            <TR>
                <TD><%= user.getIdUsuario()%> </TD>
                <TD><%= user.getNombre()%> </TD>
                <TD><%=user.getApellidos()%></TD>
                <TD><%=user.getDireccion()%></TD>
                <TD><%= user.getCorreo()%></TD>
                <TD><%= user.getCuenta()%></TD>

            </TR>

            <%}

            %>
        </TABLE> 


        <%}%>
        <%if (opcion.equals("6")) {
                ArrayList<Pelicula> peliculas = bd.getPeliculas();
        %>

        <TABLE border= "1" WIDTH= "400">
            <TD aling= "rigth">Nombre Pelicula</TD>
            <TD aling= "rigth">Id Entrada</TD>
            <TD aling= "rigth">IdSesion</TD>
            <TD aling= "rigth">fila</TD>
            <TD aling= "rigth">columna</TD>
            <TD aling= "rigth">Precio</TD>
            <TD aling= "rigth">Total Recaudado</TD>




            <%  EstrategiaNombre en = new EstrategiaNombre();

                Contexto con = new Contexto(en, peliculas);

                con.ejecutaEstrategia();
                Iterator it = peliculas.iterator();
                Pelicula peli = new Pelicula();
                ArrayList<Entrada> entradas = new ArrayList<Entrada>();
                Iterator it2;
                Entrada entrada = new EntradaNormal();
                double total = 0;
                while (it.hasNext()) {
                    peli = (Pelicula) it.next();
                    entradas = bd.getEntradasPelicula(peli.getNombre());
                    if (entradas == null || entradas.isEmpty()) {
            %>
            <TD><%= peli.getNombre()%> </TD>
            <TD>- </TD>
            <TD>-</TD>
            <TD>-</TD>
            <TD>-</TD>
            <TD>-</TD>
            <TD>-</TD>


            <%
            } else {
                it2 = entradas.iterator();
                total = 0;
                while (it2.hasNext()) {
                    entrada = (Entrada) it2.next();
                    total += entrada.getPrecio();
            %>
            <TR>
                <TD><%= peli.getNombre()%> </TD>
                <TD><%= entrada.getId()%> </TD>
                <TD><%=entrada.getSesion().getIdSesion()%></TD>
                <TD><%=entrada.getFila()%></TD>
                <TD><%= entrada.getColumna()%></TD>
                <TD><%= entrada.getPrecio()%></TD>
                <TD><%= total%></TD>



            </TR>

            <%}
                    }
                }
            %>
        </TABLE> 

        <% }%>
        <%if (opcion.equals("7")) {
                ArrayList<Pelicula> peliculas = bd.getPeliculas();
        %>

        <TABLE border= "1" WIDTH= "400">
            <TD aling= "rigth">Nombre Pelicula</TD>
            <TD aling= "rigth">Id Sesion</TD>
            <TD aling= "rigth">Id Sala</TD>
            <TD aling= "rigth">Hora</TD>
            <TD aling= "rigth">Dia semana</TD>
            <TD aling= "rigth">Dia mes</TD>
            <TD aling= "rigth">Mes</TD>




            <%  EstrategiaNombre en = new EstrategiaNombre();

                Contexto con = new Contexto(en, peliculas);

                con.ejecutaEstrategia();
                Iterator it = peliculas.iterator();
                Pelicula peli = new Pelicula();
                ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
                Iterator it2;
                Sesion sesion = new Sesion();
                double total = 0;
                while (it.hasNext()) {
                    peli = (Pelicula) it.next();
                    sesiones = bd.getSesionesPelicula(peli.getNombre());
                    if (sesiones == null || sesiones.isEmpty()) {
            %>
            <TD><%= peli.getNombre()%> </TD>
            <TD>- </TD>
            <TD>-</TD>
            <TD>-</TD>
            <TD>-</TD>
            <TD>-</TD>
            <TD>-</TD>


            <%
            } else {
                it2 = sesiones.iterator();
                total = 0;
                while (it2.hasNext()) {
                    sesion = (Sesion) it2.next();

            %>
            <TR>
                <TD><%= peli.getNombre()%> </TD>
                <TD><%= sesion.getIdSesion()%> </TD>
                <TD><%=sesion.getSala()%></TD>
                <TD><%=sesion.getHora()%></TD>
                <TD><%= sesion.getDiaSemana()%></TD>
                <TD><%= sesion.getDiaMes()%></TD>
                <TD><%= sesion.getMes()%></TD>



            </TR>

            <%}
                    }
                }
            %>
        </TABLE> 

        <% }%>
        <%
            if (opcion.equals("8")) {
                ArrayList<Usuario> usuarios = bd.getUsuarios();
                Iterator it = usuarios.iterator();
                ArrayList<Reserva> reservas = new ArrayList<Reserva>();
                Iterator it2;
                Reserva reserva = new Reserva();

        %>
        <TABLE border= "1" WIDTH= "400">
            <TD aling= "rigth">Id Usuario</TD>
            <TD aling= "rigth">Id Reserva</TD>
            <TD aling= "rigth">Id Entrada</TD>





            <%                Usuario user = new Usuario();

                while (it.hasNext()) {
                    user = (Usuario) it.next();
                    reservas = bd.getReservas(user.getIdUsuario());
                    if (reservas == null || reservas.isEmpty()) {
            %>
            <TD><%= user.getIdUsuario()%> </TD>
            <TD>- </TD>
            <TD>-</TD>
            


            <%
            } else {
                it2 = reservas.iterator();

                while (it2.hasNext()) {
                    reserva = (Reserva) it2.next();

            %>
            <TR>
                <TD><%= user.getIdUsuario()%> </TD>
                <TD><%= reserva.getIdReserva()%> </TD>
                <TD><%=reserva.getEntrada().getId()%></TD>

            </TR>

            <%}
                    }
                }
            %>
        </TABLE> 

        <% }%>
    </body>

</html>
