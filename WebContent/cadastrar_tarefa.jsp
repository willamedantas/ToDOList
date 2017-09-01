<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Tarefa</title>
<link rel="stylesheet" href="css/global.css"/>
<script type="text/javascript">
	function cadastrar() {
		var formCadastro = document.forms[0];
		formCadastro.action='main?acao=cadastrarTarefa';
		formCadastro.submit();
	}
</script>
</head>
<body>
	<jsp:include page="cabecalho.jsp"/>
		
		<h1>Cadastros</h1>
	
		<div class="main">
			<form action="main?cadastrarTarefa" method="post">
				<div class="avisoDiv" style="display: ${msgAviso != null ? 'block' : 'none'}">
					${msgAviso != null ? msgAviso : ''}
				</div>

				<fieldset>
					<legend>Cadastrar Tarefa</legend>
					 
					<table cellpadding="5">
						<tr>
							<td>Titulo*:</td>
							<td><input type="text" name="titulo" maxlength="45" value="${param.titulo}"/></td>
						</tr>
						<tr>
							<td>Descrição*:</td>
							<td>
								<textarea rows="5" cols="35" maxlength="254" name="descricao">${param.miniBio}</textarea>
							</td>
						</tr>
						
						<tr>
							<td>Status*:</td>
							<td>
								<select name="status" id="status">
									<option value="0">Selecione...</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>Usuário*:</td>
							<td>
								<select name="usuarioId" id="usuarioId" >
									<option value="0">Selecione...</option>
								</select>
							</td>
						</tr>
					</table>
				</fieldset>
				<span>* Campos obrigatórios</span>
				<input type="reset" value="Limpar"/>
				<input type="button" value="Cadastrar" onclick="cadastrar()"/>
			</form>
		</div>
	
	<jsp:include page="rodape.jsp"/>

</body>
</html>