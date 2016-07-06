<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insertar cliente</title>
</head>
<body>
	<h3>Insertar cliente</h3>
	<form name="datosCliente" method="post" action="insertarCliente.do">
	
		Nombre: <input type="text" name="nombre">
		Pais: <input type="text" name="nombre">
		Carnet: <input type="checkbox" name="carnet" value="1">
		Música: <input type="checkbox" name="musica" value="1">
		Cine: <input type="checkbox" name="cine" value="1">
		Deporte: <input type="checkbox" name="deporte" value="1">
		Montaña: <input type="checkbox" name="montanya" value="1">
	</form>
</body>
</html>