<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Cadastrar Tarefa</title>
	<link rel="stylesheet" href="css/global.css"/>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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

			<jsp:include page="msg.jsp"/>
			<fieldset>
				<legend>Cadastrar Tarefa</legend>
				 
				<table cellpadding="5">
				
					<tr><input type="hidden" id="id" name="id" value="${tarefa != null ? tarefa.id : 0}" /> </tr>
					<tr>
						<td>Titulo*:</td>
						<td><input type="text" name="titulo" maxlength="45" value="${tarefa != null ? tarefa.titulo : param.titulo}"/></td>
					</tr>
					<tr>
						<td>Descrição*:</td>
						<td>
							<textarea rows="5" cols="35" maxlength="254" name="descricao">${tarefa != null ? tarefa.descricao : param.descricao}</textarea>
						</td>
					</tr>
					
					<tr>
						<td>Status*:</td>
						<td>
							<select name="status" id="status">
								<option value="0" disabled="disabled">Selecione...</option>
								<c:forEach var="i" items="${statusTarefas}">
									<c:choose>
										<c:when test="${tarefa != null}">
											<option value="${i.sigla}" 
												${(tarefa.status.sigla eq i.sigla) ? 'selected=true' : ''}>
													${i.descricao}
											</option>
										</c:when>
										<c:otherwise>
											<option value="${i.sigla}">${i.descricao}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>Usuário*:</td>
						<td>
							<select name="usuarioId" id="usuarioId" >
								<option value="0" disabled="disabled">Selecione...</option>
								<c:forEach var="i" items="${usuarios}">
									<c:choose>
										<c:when test="${tarefa != null}">
											<option value="${i.id}" 
												${(tarefa.usuarioId eq i.id) ? 'selected=true' : ''}>
													${i.nome}
											</option>
										</c:when>
										<c:otherwise>
											<option value="${i.id}" label="${i.nome}"/>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</fieldset>
			<span>* Campos obrigatórios</span>
			<input type="reset" value="Limpar"/>
			<input type="button" value="Salvar" onclick="cadastrar()"/>
		</form>
	</div>
	<jsp:include page="rodape.jsp"/>

</body>
</html>