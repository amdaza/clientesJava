<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,es.curso.clientes.beans.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de clientes</title>
</head>
<body>
	<h3>Listado de clientes</h3>
	<table>
		<tr>
			<th>Id del cliente</th>
			<th>Nombre</th>
			<th>País</th>
			<th colspan=2></th>
		</tr>
		<c:forEach items="${ clientes }" var="cli">
			<tr>
				<td><c:out value="${ cli.idcliente }"></c:out></td>
				<td><c:out value="${ cli.nombre }"></c:out></td>
				<td><c:out value="${ cli.pais }"></c:out></td>
				<td>
					<a href="nuevoCliente.do?idcliente=${ cli.idcliente }"><img alt="editar" src="img/modificar.bmp"></a>
				</td>
				<td>
					<a href="eliminarCliente.do?idcliente=${ cli.idcliente }"><img alt="eliminar" src="img/eliminar.bmp"></a>
				</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>