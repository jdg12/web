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
        <link rel="stylesheet" href="style/style.css">
        <title>Informes</title>
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">

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
            <li><a href="perfil.jsp">Mi perfil</a></li>
            <li><a href="/PracticaFinalWeb/SalirServlet">Salir</a></li>
        </ul><br><br/>
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
                <a class="user">Bienvenido, <%=usuarioActual.getIdUsuario()%></a>  <!-- AQUÍ VA EL MÉTODO DE COGER EL USUARIO-->
            </div>
        </div>
        <div class="linea">
        </div>
        <h1>Informes</h1>
        <h2>Datos</h2>
        <%
            String opcion = (String) session.getAttribute("opcion");
            System.out.println(opcion);
            if (opcion == null) {
                opcion = "1";
            }
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
            <TH aling= "rigth">Id Usuario</TH>
            <TH aling= "rigth">Nombre</TH>
            <TH aling= "rigth">Apellidos</TH>
            <TH aling= "rigth">Direccion</TH>
            <TH aling= "rigth">Correo</TH>
            <TH aling= "rigth">Cuenta</TH>

            <%                
                Usuario user = new Usuario();
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
            <TH aling= "rigth">Nombre Pelicula</TH>
            <TH aling= "rigth">Id Entrada</TH>
            <TH aling= "rigth">IdSesion</TH>
            <TH aling= "rigth">fila</TH>
            <TH aling= "rigth">columna</TH>
            <TH aling= "rigth">Precio</TH>
            <TH aling= "rigth">Total Recaudado</TH>

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
            <TH aling= "rigth">Nombre Pelicula</TH>
            <TH aling= "rigth">Id Sesion</TH>
            <TH aling= "rigth">Id Sala</TH>
            <TH aling= "rigth">Hora</TH>
            <TH aling= "rigth">Dia semana</TH>
            <TH aling= "rigth">Dia mes</TH>
            <TH aling= "rigth">Mes</TH>
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
            <TH aling= "rigth">Id Usuario</TH>
            <TH aling= "rigth">Id Reserva</TH>
            <TH aling= "rigth">Id Entrada</TH>

            <%                Usuario user = new Usuario();

                while (it.hasNext()) {
                    user = (Usuario) it.next();
                    reservas = bd.getReservas(user.getIdUsuario());
                    if (reservas == null || reservas.isEmpty()) {
            %>
            <tr>
            <TD><%= user.getIdUsuario()%> </TD>
            <TD>-</TD>
            <TD>-</TD>
            </tr>
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
        <h2>Tipo informes</h2>
        <div class="opciones">
            <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario" method="POST">
                <label>Peliculas por Nombre</label>
                <br>
                <input type="hidden" id="thisField" name="informe" value="1">
                <input class="boton" type="submit" value="Ver Informe">
            </form>
            <BR>
            <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario2" method="POST">
                <label>Peliculas por Genero</label>
                <br>
                <input type="hidden" id="thisField2" name="informe" value="2">
                <input class="boton" type="submit" value="Ver Informe">
            </form>
            <BR>
            <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario3" method="POST">
                <label>Peliculas por Año</label>
                <br>
                <input type="hidden" id="thisField3" name="informe" value="3">
                <input class="boton" type="submit" value="Ver Informe">
            </form>
            <BR>
            <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario4" method="POST">
                <label>Peliculas por Clasificación</label><br>
                <input type="hidden" id="thisField4" name="informe" value="4">
                <input class="boton" type="submit" value="Ver Informe">
            </form>
            <BR>
            <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario5" method="POST">
                <label>Usuarios por Id</label><br>
                <input type="hidden" id="thisField5" name="informe" value="5">
                <input class="boton" type="submit" value="Ver Informe">
            </form>
            <BR>
            <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario6" method="POST">
                <label>Entradas por Pelicula y Recaudacion</label><br>
                <input type="hidden" id="thisField6" name="informe" value="6">
                <input class="boton" type="submit" value="Ver Informe">
            </form>
            <BR>
            <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario7" method="POST">
                <label>Sesiones por pelicula</label><br>
                <input type="hidden" id="thisField7" name="informe" value="7">
                <input class="boton" type="submit" value="Ver Informe">
            </form>
            <BR>
            <form action="/PracticaFinalWeb/informeServlet" class="verMas" id="formulario8" method="POST">
                <label>Reservas Por Usuario</label><br>
                <input type="hidden" id="thisField8" name="informe" value="8">
                <input class="boton" type="submit" value="Ver Informe">
            </form>
            <BR>
        </div>
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
