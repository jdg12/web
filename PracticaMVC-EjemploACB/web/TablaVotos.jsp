<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head><title>Votaci&oacute;n mejor jugador liga ACB</title></head>
    <body>
        <font size=10>
        Votaci&oacute;n al mejor jugador de la liga ACB 2013/2014
        <hr>
        <%
            String nombreP = (String) session.getAttribute("nombreCliente");
            ResultSet rs = (ResultSet) session.getAttribute("votos");
            ResultSetMetaData rsmd = (ResultSetMetaData) session.getAttribute("metadata");
        %>
        <br>Muchas gracias <%=nombreP%> por su voto
        </font>
        <table width="100%" border="1">
            <tr>
                <%-- Obtenemos los nombres de las columnas y los colocamos
                como cabecera de la tabla --%>
                <th>Nombre</th>
                <th>Votos</th>
            </tr>
            <% while (rs.next()) { %>
            <tr>
                <% for (int i = 1; i <= rsmd.getColumnCount(); i++) { %>
                <%-- Recuperamos los valores de las columnas que
                corresponden a cada uno de los registros de la
                tabla. Hay que recoger correctamente el tipo de
                dato que contiene la columna --%>
                <% if (i == 3) {%>
                <td><%= rs.getInt(i)%></td>
                <% } else {%>
                <td><%= rs.getString(i)%></td>
                <% }
                    } %>
            </tr>
            <% }%>
        </table>
        <br>
        <br> <a href="/PracticaMVC-EjemploACB/index.html"> Ir al comienzo</a>
    </body>
</html>
