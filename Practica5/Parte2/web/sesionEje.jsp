<HTML>
<head> <title> Ejemplo de Sesi�n </title> </head>
<body>
<%
String val = request.getParameter("nombre");if (val != null) session.setAttribute("Nombre",val);
%>
<center> <h1>Ejemplo de Sesi�n</h1>
Donde quieres ir!!!
<a href="sesionEje1.jsp">Ir a P�gina 1</a>
<a href="sesionEje2.jsp">Ir a P�gina 2</a>
</body>
</HTML> 