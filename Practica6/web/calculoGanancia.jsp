<%-- 
    Document   : calculoGanancia
    Created on : 01-dic-2016, 19:37:19
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculo de Ganancia</title>
    </head>
    
    <body>
        <% String nomCoche = (String) session.getAttribute("coche"); 
           String nomCircuito = (String) session.getAttribute("circuito");
           Double res = (Double) session.getAttribute("resultado");
        %>
        <h1>Ganancia del coche <%= nomCoche%> , en el circuito <%= nomCircuito%></h1>
        <br>
        <%= res%> 
        
    <br> <a href="/Practica6/index.html"> Ir al comienzo</a>
    </body>
</html>
