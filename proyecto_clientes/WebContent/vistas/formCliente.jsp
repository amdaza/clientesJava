<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,es.curso.clientes.beans.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insertar nuevo cliente</title>
</head>
<body>
	<h3>Insertar nuevo cliente</h3>
	<form name="datos" action="insertarCliente.do" method="post">
		<table>
			<tr>
				<td>Id Cliente</td>
				<td>
					<input type="text" 
						name="idcliente" 
						value=${ cliente.idcliente }>
					</td>
			</tr>
			<tr>
				<td>Nombre</td>				
				<td><input type="text" name="nombre" value="${ cliente.nombre }"></td>
			</tr>
			<tr>
				<td>País</td>
				<td>
					<select name="pais">
						<c:forEach items="${ paises }" var="p">
							<c:if test="${cliente.pais.equals(p)}">
								<option selected>${ p }</option>
							</c:if>
							<c:if test="${!cliente.pais.equals(p)}">
								<option>${ p }</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<input type="submit" value="Grabar">
	</form>
</body>
</html>