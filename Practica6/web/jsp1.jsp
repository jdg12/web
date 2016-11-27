<%-- 
    Document   : jsp1
    Created on : 27-nov-2016, 15:31:27
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
            String prueba = (String) session.getAttribute("datoPrueba");
        %>
        
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hola</h1>
        <p><%=prueba%></p>
    </body>
</html>
