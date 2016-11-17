<HTML><head> <title> Ejemplo de Sesión </title> </head> <body>
        <%String nombre = (String)session.getAttribute("Nombre");%>
<center> <h1>Ejemplo de Sesión</h1>
Hola, 
<%=nombre%>
Bienvenido a la página 1
</body> </HTML> 