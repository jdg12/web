<!--Se puede probar con los siguientes nombres de profesores:
Jesus
Juan Diego
David
-->
<HTML>
    <HEAD>
        <TITLE>Busqueda alumnos</TITLE>
    </HEAD>

    <BODY>
        <%@ page import="java.sql.*" %>
        <%!
            //Atributos usados para conectarnos con la bbdd
            Connection c;
            Statement s;
            ResultSet rs;
            ResultSetMetaData rsmd;
        %>
        <%
            if (request.getParameterNames() != null) {
                //Datos de la bbdd
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                c = DriverManager.getConnection("jdbc:derby://localhost:1527/universidad", "usuario", "pass");
                s = c.createStatement();
                //Ejecución de la consulta
                rs = s.executeQuery("SELECT * FROM ALUMNO WHERE NIFALUMNO IN( SELECT NIFALUMNO FROM RELACION WHERE NIFPROFESOR IN (SELECT NIFPROFESOR FROM PROFESOR WHERE NOMBRE = '"
                        + request.getParameter("nombreProfesor") + "'))");
                rsmd = rs.getMetaData();
            }
        %>

        <!--Formulario donde buscar por el profesor-->
        <FORM NAME="form1" METHOD="POST">
            <INPUT TYPE="HIDDEN" NAME="nombreProfesor">
            <label>Nombre del profesor: </label>
            <input TYPE="text" id="nombre">
            <INPUT TYPE="BUTTON" VALUE="Ver alumnos" ONCLICK="boton1()">
        </FORM><br>
        
        <!--Generamos la tabla-->
        <table width="100%" border="1">
            <!--Recorremos las columnas para recoger los nombres de la bbdd-->
            <tr>
                <% for (int i = 1; i <= rsmd.getColumnCount(); i++) {%>
                <th><%= rsmd.getColumnLabel(i)%></th>
                    <% } %>
            </tr>
            <% while (rs.next()) { %>
            <tr>
                <% for (int i = 1; i <= rsmd.getColumnCount(); i++) { %>
                <!--En el caso del sueldo tenemos que mostrar un int-->
                <!--En el resto de los casos con un String es suficiente-->
                <% if (i == 4) {%>
                <td><%= rs.getInt(i)%></td>
                <% } else {%>
                <td><%= rs.getString(i)%></td>
                <% }
                    } %>
            </tr>
            <% }%>
        </table>


        <SCRIPT LANGUAGE="JavaScript">
            <!--
            function boton1()
            {
                document.form1.nombreProfesor.value = document.getElementById("nombre").value
                form1.submit()
            }
            // --> 
        </SCRIPT>
    </BODY>
</HTML>
