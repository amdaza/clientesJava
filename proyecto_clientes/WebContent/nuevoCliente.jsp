<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/estilos.css" />
</head>
<body>
	<% 
		String user = (String) session.getAttribute("user");
		if (user == null) {
			// Usuario no registrado, ir al inicio
			response.sendRedirect("index.jsp?fail_login=1");
		}
	%>
	<div id="contenedor">
		<div id="cabecera"><jsp:include page="vistas/cabecera.jsp"></jsp:include></div>
		<div id="centro">
			<div id="menu"><jsp:include page="vistas/menu.html"></jsp:include></div>
			<div id="cuerpo">
				<jsp:include page="vistas/formCliente.jsp"></jsp:include>
			</div>
		</div>
		<div class="clear"></div>
		<div id="pie"><jsp:include page="vistas/pie_pagina.html"></jsp:include></div>
	</div>
</body>
</html>