
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/style.css">
        <title>Calculo de Ganancia</title>
    </head>
    
    <body>
        <ul class="menu">
            <li><a href="/Practica6/index.html">Inicio</a></li>
            <li><a href="/Practica6/nuevoCircuito.html">Nuevo circuito</a></li>
            <li><a href="/Practica6/nuevoCoche.html">Nuevo coche</a></li>
            <li><a href="/Practica6/verTodoServlet">Ver todos</a></li>
            <li><a  href="/Practica6/potenciaServlet">Calculo Kers</a></li>
        </ul>
        <% String nomCoche = (String) session.getAttribute("coche"); 
           String nomCircuito = (String) session.getAttribute("circuito");
           Double res = (Double) session.getAttribute("resultado");
        %>
        <h2>Ganancia del coche <%= nomCoche%> , en el circuito <%= nomCircuito%></h2>
        <br>
        <%= res%> KW
        
    </body>
</html>
