<%@page import="bbdd.Usuario"%>
<%@page import="bbdd.modeloDatos"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style/style.css">
        <script src="jquery-3.1.1.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            var lat_restaurante = 40.140874;
            var long_restaurante = -3.422299;

            function getLocation() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(showPosition, showError);
                } else {
                    alert("Geolocation is not supported by this browser.");
                }
            }

            function showPosition(position) {
                var lat = position.coords.latitude;
                var long = position.coords.longitude;

                this.cargarMapa(lat, long);
            }

            function showError(error) {
                switch (error.code) {
                    case error.PERMISSION_DENIED:
                        alert("El usuario se ha negado a utilizar la Geolocalización.");
                        break;
                    case error.POSITION_UNAVAILABLE:
                        alert("Location information is unavailable.");
                        break;
                    case error.TIMEOUT:
                        alert("The request to get user location timed out.");
                        break;
                    case error.UNKNOWN_ERROR:
                        alert("An unknown error occurred.");
                        break;
                }
            }

            function myMap() {

                var miPosicion = new google.maps.LatLng(lat_restaurante, long_restaurante);
                var mapCanvas = document.getElementById("map");
                var mapOptions = {       
                    center: miPosicion,
                          zoom: 16,
                           mapTypeId: google.maps.MapTypeId.ROADMAP  
                }
                var map = new google.maps.Map(mapCanvas, mapOptions);
                var marker = new google.maps.Marker({
                    position: miPosicion,
                    animation: google.maps.Animation.BOUNCE
                });
                marker.setMap(map);
                var infowindow = new google.maps.InfoWindow({
                    content: "Aquí Estamos!"
                });
                infowindow.open(map, marker);
            }

            function cargarMapa(lat, long) {
                var directionsService = new google.maps.DirectionsService;
                var directionsDisplay = new google.maps.DirectionsRenderer;
                var miPosicion = new google.maps.LatLng(lat, long);
                var mapCanvas = document.getElementById("map");
                var mapOptions = {       
                    center: miPosicion,
                          zoom: 12,
                           mapTypeId: google.maps.MapTypeId.ROADMAP  
                }
                var map = new google.maps.Map(mapCanvas, mapOptions);
                directionsDisplay.setMap(map);
                var marker = new google.maps.Marker({
                    position: miPosicion,
                    animation: google.maps.Animation.BOUNCE
                });
                marker.setMap(map);
                var infowindow = new google.maps.InfoWindow({
                    content: "Tu posición!"
                });
                infowindow.open(map, marker);
                this.calculateAndDisplayRoute(directionsService, directionsDisplay, miPosicion);
            }

            function calculateAndDisplayRoute(directionsService, directionsDisplay, origen) {
                directionsService.route({
                    origin: origen,
                    destination: new google.maps.LatLng(lat_restaurante, long_restaurante),
                    travelMode: google.maps.TravelMode.DRIVING
                }, function (response, status) {
                    if (status === google.maps.DirectionsStatus.OK) {
                        directionsDisplay.setDirections(response);
                    } else {
                        window.alert('Directions request failed due to ' + status);
                    }
                });
            }
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
                        System.out.println("Id: " + idUsuario);
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

        <div class="swip pic" id="second">
            <img max-width="400" max-height="300" width='100%' height='100%' src="style/mejor.jpg">
            </img>
        </div>

        <div class="container">
            <h2>HORARIO</h2>
            <br><br/>
            <div class="info">
                <p>L-V: Primer pase a las 17:30 horas</p><br></br>
                <p>S-D: Primer pase a las 16:00 horas</p>
            </div>
        </div>

        <div class="swip pic">
            <img max-width="400" max-height="300" width='100%' height='100%' src="style/esperada.jpg">
            </img>
        </div>

        <div class="container">
            <h2>PRECIOS</h2>
            <br><br/>
            <div class="info">
                <p>Entrada estándar: 5.90?</p><br></br>
                <p>Entrada reducida: 4.00?</p><br></br>
                <p>Reserva entrada:  6.90?</p>
            </div>
        </div>

        <div class="linea" >
        </div>

        <div class="containerL">
            <h2>CÓMO ENCONTRARNOS</h2>
            <br><br/>
            <div class="infoL">
                <p>Nos puede encontrar el la siguiente dirección:</p><br></br>
                <p>C/ Calle de la Iglesia 2, 28370 Chinchón</p><br></br>
                <p>En el siguiente teléfono:</p><br></br>
                <p>T: 915666123</p><br></br>
                <p>Y para reclamaciones y preguntas en el siguiente correo:</p><br></br>
                <a href="mailto:porfavornolohaga@cineschinchon.es">porfavornolohaga@cineschinchon.es</a>
            </div>
        </div>

        <div class="box">
            <div id="map" style="width:400px;height:400px;background:transparent"></div>
            <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBfdjYUNF2nCtJfQsWbDLFnjAVLAKfwBY&callback=myMap"></script>
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
