<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home</title>
	<link rel="stylesheet" href="css/global.css"/>
</head>
<body>

	<jsp:include page="cabecalho.jsp"/>
	
	<div class="avisoDiv" style="display: ${msgAviso != null ? 'block' : 'none'}">
					${msgAviso != null ? msgAviso : ''}
	</div>
	
	<div class="main">
		<h1>Olá, seja bem vindo ${usuario.nome}.</h1>
		<fieldset>
			<legend>Lista de Tarefas</legend> 
			<table id="tableTarefa">
				<thead>
					<th>Id</th>
					<th>Titulo</th>
					<th>Descricao</th>
					<th>Status</th>
					<th>Ação</th>
				</thead>
				<tbody class="tableTarefaTdTh">
					<c:forEach var="t" items="${tarefas}">
						<tr>
						   <td><c:out value="${t.id}" /></td>
						   <td><c:out value="${t.titulo}" /></td>
						   <td><c:out value="${t.descricao}" /></td>
						   <td><c:out value="${t.status.descricao}" /></td>
						   <td>
						   		<a href="main?acao=editarTarefa&id_tarefa=${t.id}" title="Editar">
									<img alt="Editar tarefa" src="img/edit.png"/>
								</a>
								<a href="main?acao=excluirTarefa&id_tarefa=${t.id}" title="Excluir">
									<img alt="Editar tarefa" src="img/delete.png"/>
								</a>
						   </td>
						</tr>
					</c:forEach>	
				</tbody>
			</table>
		</fieldset>
	</div>
	<jsp:include page="rodape.jsp"/>

</body>
</html>