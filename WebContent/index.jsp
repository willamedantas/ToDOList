<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<title>Home</title>
	<link rel="stylesheet" href="css/global.css"/>

</head>
<body>

	<jsp:include page="cabecalho.jsp"/>

	<div id="content" class="content container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<jsp:include page="msg.jsp"/>
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
				
				<tr>
					   <td>1</td>
					   <td>Função Adicionar</td>
					   <td>Criar função para adicionar tarefa.</td>
					   <td>Concluido</td>
					   <td>
					   		<a href="main?acao=editarTarefa&amp;id_tarefa=1" title="Editar">
								<img alt="Editar tarefa" src="img/edit.png">
							</a>
							<a href="main?acao=removerTarefa&amp;id_tarefa=1" title="Excluir">
								<img alt="Editar tarefa" src="img/delete.png">
							</a>
					   </td>
					</tr>

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
								<a href="main?acao=removerTarefa&id_tarefa=${t.id}" title="Excluir">
									<img alt="Editar tarefa" src="img/delete.png"/>
								</a>
						   </td>
						</tr>
					</c:forEach>	
				</tbody>
			</table>
		</fieldset>

			</div>
		</div>
	</div>
		
	<jsp:include page="rodape.jsp"/>

</body>
</html>