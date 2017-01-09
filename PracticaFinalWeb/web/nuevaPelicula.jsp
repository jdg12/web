<%@page import="bbdd.modeloDatos"%>
<%@page import="bbdd.Usuario"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>Nueva Pelicula</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style/style.css">
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var actores = "";

                $("#formulario").submit(function () {

                    document.getElementById("listaActores").value = actores;
                    if ($("#titulo").val().length > 20) {
                        alert("El titulo es demasiado largo");
                        return false;
                    }
                    if ($("#sinopsis").val().length > 100) {
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
                    if ($("#nombre").val().length > 20) {
                        alert("Los otros datos son demasiado largos");
                        return false;
                    }
                    return true;

                });

                function anadirActor() {
                    var nombreAux = $("#nombreA").val();
                    var apellidoAux = $("#apellidoA").val();
                    var res, flag = 0;

                    if (nombreAux == "" || apellidoAux == "") {
                        alert("rellena el nombre y apellido del actor")
                        return false;
                    }
                    var a = nombreAux + "," + apellidoAux;

                    if (actores != "") {

                        res = actores.split(";");
                        $.each(res, function (ind, elem) {
                            if (elem == a) {

                                flag = 1;
                                return false;
                            }
                        });
                        if (flag == 1) {
                            alert("Ya existe el actor: " + a)
                            return false;
                        }
                        actores += ";"

                    }

                    actores += a;

                    $('#seleccion').append(new Option(a, a));

                    document.getElementById("listaActores").value = actores;
                }

                $("#nuevo").click(function () {

                    var inpObj = document.getElementById("nombreA");
                    if (inpObj.checkValidity() == false) {
                        document.getElementById("demo").innerHTML = inpObj.validationMessage;
                        return false;
                    } else {
                        document.getElementById("demo").innerHTML = "Input OK";
                    }
                    var inpObj = document.getElementById("apellidoA");
                    if (inpObj.checkValidity() == false) {
                        document.getElementById("demo2").innerHTML = inpObj.validationMessage;
                        return false;
                    } else {
                        document.getElementById("demo2").innerHTML = "Input OK";
                    }
                    anadirActor();
                });

                $('#seleccion').change(function () {
                    var selectedValue = jQuery(this).val();
                    var actoresNuevo = "";
                    var res = actores.split(";");
                    $('#seleccion').empty();

                    $.each(res, function (ind, elem) {
                        if (elem != selectedValue && elem != "" && ind == 0) {
                            actoresNuevo += elem;
                        }
                        if (elem != selectedValue && elem != "" && ind != 0) {
                            actoresNuevo += ";" + elem;
                        }
                        if (elem != selectedValue && elem != "") {
                            $('#seleccion').append(new Option(elem, elem));
                        }


                    });
                    actores = actoresNuevo;
                    document.getElementById("listaActores").value = actores;
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
                <a class="user">Bienvenido, <%=usuarioActual.getIdUsuario()%></a>  <!-- AQUÍ VA EL MÉTODO DE COGER EL USUARIO-->
            </div>
        </div>

        <div class="linea">
        </div>
        <!--
        Aquí acaba la parte predefinida del header que se repite
        -->
        <div>
            <h1>Nueva Película</h1>
        </div>
        <div class="cuadrado">
            <form action="/PracticaFinalWeb/nuevaPeliServlet" class="nuevaPeli" id="formulario" method="POST">

                <label>Nombre: </label><br><input id="nombre" type="text" name="nombre" autofocus required />
                <br /><label>Sinopsis</label><br><textarea id="sinopsis" name="sinopsis" rows="6" cols="33" autofocus required></textarea>
                <br />
                <label>Web oficial: </label><br><input id="pagina" type="text" name="pagina" autofocus required />
                <br/>
                <label>Titulo Original: </label><br><input id="titulo" type="text" name="titulo" autofocus required />
                <br />
                <label>Género: </label><br><input id="genero" type="text" name="genero" autofocus required />
                <br />
                <label>Nacionalidad: </label><br><input id="nacionalidad" type="text" name="nacionalidad" autofocus required/>
                <br />
                <label>Duracion (minutos): </label><br><input id="duracion" type="number" name="duracion" autofocus required />
                <br />
                <label>Año: </label><br><input id="ano" type="number" name="ano" min="1887" max="2017" autofocus required />
                <br />
                <label>Distribuidora: </label><br><input id="distribuidora" type="text" name="distribuidora" autofocus required />
                <br />
                <label>Director: </label><br><input id="director" type="text" name="director" autofocus required />
                <br />
                <label>Clasificacion por Edad: </label><br>
                <input type="radio" id="clasificacion" name="clasificacion" value="7" checked><label>7</label>
                <input type="radio" id="clasificacion" name="clasificacion" value="13"><label>13</label>
                <input type="radio" id="clasificacion" name="clasificacion" value="18"><label>18</label>
                <br>
                <label>Otros datos</label><br><textarea id="otros" name="otros" rows="6" cols="33" autofocus></textarea>
                <br>
                <label>Actores </label><br>
                <select id="seleccion" name="seleccion" size=7 style="min-width: 150px;"></select><br>
                <label>Nombre: </label><br>
                <input id="nombreA" type="text" name="nombreA" pattern="[A-Za-z][ A-Za-z]*" autofocus />
                <p id=demo></p>
                <br />
                <label>Apellido: </label><br>

                <input id="apellidoA" type="text" name="apellidoA" pattern="[A-Za-z][ A-Za-z]*" autofocus />
                <p id=demo2></p>
                <br/>
                <input id="listaActores" name="listaActores" type="text" hidden/>
                <input class="boton" type="button" id="nuevo" value="Nuevo actor">
                <br> <br>
                <input class="boton" type="submit" value="Subir" />

            </form>
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
