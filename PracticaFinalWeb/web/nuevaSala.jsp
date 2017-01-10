<%@page import="bbdd.Usuario"%>
<%@page import="bbdd.modeloDatos"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Nueva sala</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style/style.css">
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#formulario").submit(function () {
                    if ($("#nombre").val().length > 20) {
                        alert("El nombre es demasiado largo");
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
            <li><a href="perfil.jsp">Mi perfil</a></li>
            <li><a href="/PracticaFinalWeb/SalirServlet">Salir</a></li>
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
                <a class="user">Bienvenido, admin</a>  <!-- AQU� VA EL M�TODO DE COGER EL USUARIO-->
            </div>
        </div>

        <div class="linea">
        </div>
        <!--
        Aqu� acaba la parte predefinida del header que se repite
        -->
        <h1>Nueva sala</h1>
        <!--Formulario para a�adir la sala-->
        <!--Con JQUERY comprobaremos que no nos excedemos de la longitud de la BBDD-->
        <form action="/PracticaFinalWeb/modificarSala" name="formulario" id="formulario">
            <br /><label>Nombre: </label><br><input id="nombre" type="text" name="nombre" autofocus required />
            <br /><label>Filas: </label><br><input id="filas" type="number" min="1" max="20" name="filas"  autofocus required/>
            <br /><label>Columnas: </label><br><input id="columnas" type="number" min="1" max="20" name="columnas"  autofocus required/>
            <input type="hidden" id="thisField" name="inputName" value="anadir">
            <br /><br><input class="boton" type="submit" value="A�adir">
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
                <p>Est�s en el sitio web de Chinch�n Multicines Madrid. Aqu� puedes acceder al aviso legal. � 1997 AWESOME MULTICINES</p><br></br>
            </div>
        </div>

        <div class="containerRestFooter">
            <h3>Aviso Sobre Cookies</h3>
            <br><br/>
            <div class="footer">
                <p>Cuando visite nuestra p�gina, podemos enviar a su computadora una o m�s cookies, un peque�o archivo de texto que contiene una cadena de caracteres alfanum�ricos, que identifica de forma exclusiva su navegador y le permite conectarse m�s r�pido y mejorar su navegaci�n a trav�s del sitio. Una cookie no recopila informaci�n personal sobre usted. Este sitio utiliza cookies de sesi�n y cookies persistentes. Una cookie persistente permanece en su disco duro despu�s de cerrar su navegador.</p><br></br>
            </div>
        </div>

        <div class="containerRightFooter">
            <div class="footer">
                <img src="style/escudo.png" alt="" class="unstyled">
            </div>
        </div>
    </body>
</html>
