<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto clientes</title>
<link rel="stylesheet" type="text/css" href="css/estilos.css" />
</head>
<body>
	<div id="contenedor">
		<div id="cabecera"><jsp:include page="vistas/cabecera.jsp"></jsp:include></div>
		<div id="centro">
			<div id="menu"><jsp:include page="vistas/menu.html"></jsp:include></div>
			<div id="cuerpo"></div>
				<% 
					String failLogin = (String) request.getParameter("fail_login");
					if (failLogin != null) {
				%>
					<h2>Necesita iniciar sesión para ver este contenido</h2>
				<% } %>
				<p>Aquí va el cuerpo de la página</p>
		</div>
		<div class="clear"></div>
		<div id="pie"><jsp:include page="vistas/pie_pagina.html"></jsp:include></div>
	</div>
</body>
</html>