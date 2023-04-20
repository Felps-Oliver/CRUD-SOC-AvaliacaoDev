<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:text name="label.titulo.pagina.relatorioTopExames" /></title>
<link rel='stylesheet'
	href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">

	<div class="container">
		<s:form action="/relatorio_MaisRealizadosFicha.action">

			<div class="card mt-5">
				<div class="card-header">
					<div class="row">
						<div class="col-sm-5">
							<s:url action="todosFichas" var="todos" />
							<a href="${todos}" class="btn btn-success">
							<s:text	name="label.ficha" />
							</a>
							
							<s:url action="relatorioFicha" var="relatorio" />
							<a href="${relatorio}" class="btn btn-success">
							<s:text	name="label.relatorioExamesPeriodo" />
							</a>
						</div>

						<div class="col-sm align-items-left">
							<h5 class="card-title">Relatório TOP 5 Exames</h5>
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
			<table class="table table-light table-striped align-middle text-center">
				<thead class="col-sm-4">
					<tr>
						<th>Total Exame</th>
						<th>ID Exame</th>
						<th>Nome Exame</th>
						<th class="text-end mt-6"></th>
					</tr>
				</thead>

				<tbody>
					<s:iterator value="fichas">
						<tr class="text-end mt-6">
							<td class="text-center">${total_exame}</td>
							<td class="text-center">${id_exame}</td>
							<td class="text-center">${nome_exame}</td>
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