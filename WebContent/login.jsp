<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login - ToDoList</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
</head>
<body>
	
	<div id="form-container">
		<div class="panel" id="form-box">
			<form method="post" id="login" action="main?acao=login">
				<jsp:include page="msg.jsp"/>
				<h1 class="text-center">ToDoList</h1>
				<br>
				
				<div class="form-group">
					<label class="sr-only" for="login">Login</label>
					<div class="input-group">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-user"></span>
						</div>
						<input type="text" name="email" class="form-control" placeholder="Digite seu email">
					</div>
				</div>

				<div class="form-group">
					<label class="sr-only" for="senha">Senha</label>
					<div class="input-group">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-lock"></span>
						</div>
						<input type="password" name="senha" class="form-control" placeholder="Digite sua senha.">
					</div>
				</div>

				<div class="form-group">
					<input type="submit" value="Logar" class="btn btn-success form-control" />
				</div>

			</form>
		</div>
	</div>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
</body>
</html>