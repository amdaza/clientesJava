<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="izq">
		<h2>Gestión de Clientes</h2>
	</div>
	<div class="der">
		<% 
			String user = (String) session.getAttribute("user");
			if (user == null) {
		%>
				<form action="login.do" method="post">
				<table>
					<tr>
						<td>Login</td>
						<td><input type="text" name="login"></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="pass"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Login"></td>
					</tr>
				</table>
			</form>
		<%
			} else{
	 	%>
				<h5>Usuario conectado: <%=user%></h5>
				<a href="logout.do">Cerrar conexión</a>
		<%	
			}
		%>
	</div>
</body>
</html>