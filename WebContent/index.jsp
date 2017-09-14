<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Home</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	
</head>
<body>
	<jsp:include page="cabecalho.jsp"/>

	<div class="main">
		<jsp:include page="menu.jsp"/>
		<div class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<jsp:include page="msg.jsp" />
						<h1>Olá, seja bem vindo ${usuario.nome}.</h1>

						<div class="panel panel-primary ">
							<div class="panel-heading">
								<h3 class="panel-title">Lista de Tarefas</h3>
							</div>
							<div class="panel-body">
								<table id="tableTarefa" class="table table-hover">
									<thead>
										<th>Id</th>
										<th>Titulo</th>
										<th>Descricao</th>
										<th>Status</th>
										<th class="hidden-xs">Ação</th>
									</thead>
									<tbody class="tableTarefaTdTh">
										<c:forEach var="t" items="${tarefas}">
											<tr>
												<td><c:out value="${t.id}" /></td>
												<td><c:out value="${t.titulo}" /></td>
												<td><c:out value="${t.descricao}" /></td>
												<td><c:out value="${t.status.descricao}" /></td>
												<td>
													<a href="main?acao=editarTarefa&id_tarefa=${t.id}"	title="Editar"> 
														<img alt="Editar tarefa" src="img/edit.png" />
													</a> 
													<a href="main?acao=removerTarefa&id_tarefa=${t.id}"	title="Excluir"> 
														<img alt="Editar tarefa" src="img/delete.png" />
													</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="rodape.jsp"/>

</body>
</html>