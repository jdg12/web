<HTML><head> <title> Ejemplo de Sesi�n </title> </head> <body>
<%String nombre = (String)session.getAttribute("Nombre");%>
<center> <h1>Ejemplo de Sesi�n</h1>
Hola, 
<%=nombre%>
Bienvenido a la p�gina 2
</body> </HTML> 