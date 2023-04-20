<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:text name="label.titulo.pagina.relatorioExamesRealizados" /></title>
<link rel='stylesheet'
	href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">

	<div class="container">
		<s:form action="/relatorio_ExamesRealizadosFicha.action">

			<div class="card mt-5">
				<div class="card-header">
					<div class="row">
						<div class="col-sm-5">
							<s:url action="todosFichas" var="todos" />
							<a href="${todos}" class="btn btn-success">
							<s:text	name="label.ficha" />
							</a>
							
							<s:url action="relatorio_MaisRealizadosFicha" var="relatorioMaisRealizados" />
							<a href="${relatorioMaisRealizados}" class="btn btn-success">
							<s:text	name="label.relatorioMaisRealizados" />
							</a>
						</div>

						<div class="col-sm">
							<h5 class="card-title">Relatório Exames Realizados no Período</h5>
						</div>
					</div>
				</div>

				<div class="card-body">
					<div class="row align-items-center">
						<label for="id" class="col-sm-1 col-form-label text-center">
							Data Início: </label>

						<div class="col-sm-2">
							<s:textfield cssClass="form-control" format="dd-MM-yyyy"
								type="date" id="fichaDataInicio" name="fichaVo.data_inicio" />
						</div>
					</div>

					<div class="row align-items-center mt-3">
						<label for="nome" class="col-sm-1 col-form-label text-center">
							Data Fim: </label>

						<div class="col-sm-2">
							<s:textfield cssClass="form-control" format="dd-MM-yyyy"
								type="date" id="fichaDataFim" name="fichaVo.data_fim" />
						</div>
					</div>
				</div>

				<div class="card-footer">
					<div class="form-row">
						<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
						<button type="reset"
							class="btn btn-secondary col-sm-4 offset-sm-2">Limpar
							Formulario</button>
					</div>
				</div>
			</div>
		</s:form>

		<div class="row2">
			<table class="table table-light table-striped align-middle">
				<thead>
					<tr>
						<th>Cód. Funcionário</th>
						<th>Nome Funcionário</th>
						<th>Cód. Exame</th>
						<th>Nome Exame</th>
						<th>Data Exame</th>
						<th class="text-end mt-5"><s:text name="label.acao" /></th>
					</tr>
				</thead>

				<tbody>
					<s:iterator value="fichas">
						<tr>
							<td class="text-center">${id_funcionario}</td>
							<td class="text-center">${nome_funcionario}</td>
							<td class="text-center">${id_exame}</td>
							<td class="text-center">${nome_exame}</td>
							<td class="text-center">${data_exame}</td>
						</tr>
					</s:iterator>
				</tbody>

			</table>
		</div>

		<div class="row"></div>

	</div>

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>