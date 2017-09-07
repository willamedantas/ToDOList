<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login - ToDoList</title>
	<link rel="stylesheet" href="css/global.css"/>
</head>
<body>
	<form method="post" id="login_form" action="main?acao=login">
			
		<jsp:include page="msg.jsp"/>
		<fieldset id="fieldset_login">
			<legend>Login do Sistema</legend>
			
			<table>
				<tr>
					<td><label for="login">Login: </label></td>
					<td><input type="email" id="email" name="email"></td> 
				</tr>
				<tr>
					<td><label for="senha">Senha: </label></td>
					<td><input type="password" id="senha" name="senha"/></td> 
				</tr>
				<tfoot>
					<td colspan="2" align="right"><input type="submit" value="Logar"/></td>
				</tfoot>
			</table>
		</fieldset>
	</form>
</body>
</html>