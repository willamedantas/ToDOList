<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cadastrar Tarefa</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">

<script type="text/javascript">
	function cadastrar() {
		var formCadastro = document.forms[0];
		formCadastro.action = 'main?acao=cadastrarTarefa';
		formCadastro.submit();

	}
</script>
</head>
<body>
	<jsp:include page="cabecalho.jsp" />

	<div class="main">
		<jsp:include page="menu.jsp" />
		<div class="content container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="content-box big-box box-shadow">
						<div class="panel panel-default">
							<div class="panel-heading">Cadastrar Tarefa</div>
							<div class="panel-body">
								<form class="content-form form-horizontal"
									action="main?cadastrarTarefa" method="post">

									<jsp:include page="msg.jsp" />
									<input type="hidden" id="id" name="id" value="${tarefa != null ? tarefa.id : 0}" />

									<div class="form-group">
										<label for="titulo" class="col-sm-2 control-label">Título*:</label>
										<div class="col-sm-6">
											<input type="text" class="form-control material"
												name="titulo" maxlength="45"
												value="${tarefa != null ? tarefa.titulo : param.titulo}" />
										</div>
									</div>

									<div class="form-group">
										<label for="descricao" class="col-sm-2 control-label">Descrição*:</label>
										<div class="col-sm-6">
											<textarea class="form-control" rows="5" cols="35"
												maxlength="254" name="descricao">${tarefa != null ? tarefa.descricao : param.descricao}</textarea>
										</div>
									</div>

									<div class="form-group">
										<label for="status" class="col-sm-2 control-label">Status*:
										</label>
										<div class="col-sm-6">
											<select class="form-control" name="status" id="status">
												<option value="0" disabled="disabled">Selecione...</option>
												<c:forEach var="i" items="${statusTarefas}">
													<c:choose>
														<c:when test="${tarefa != null}">
															<option value="${i.sigla}"
																${(tarefa.status.sigla eq i.sigla) ? 'selected=true' : ''}>
																${i.descricao}</option>
														</c:when>
														<c:otherwise>
															<option value="${i.sigla}">${i.descricao}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label">Usuário*:</label>
										<div class="col-sm-6">
											<select class="form-control" name="usuarioId" id="usuarioId">
												<option value="0" disabled="disabled">Selecione...</option>
												<c:forEach var="i" items="${usuarios}">
													<c:choose>
														<c:when test="${tarefa != null}">
															<option value="${i.id}"
																${(tarefa.usuarioId eq i.id) ? 'selected=true' : ''}>
																${i.nome}</option>
														</c:when>
														<c:otherwise>
															<option value="${i.id}" label="${i.nome}" />
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="input-group-btn">
										<div class="col-md-offset-6">
											<input type="reset" class="btn btn-warning waves text-uppercase" value="Limpar" />
											<input type="button" class="btn btn-success waves text-uppercase" value="Salvar" onclick="cadastrar()" />
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="rodape.jsp" />
</body>
</html>
















