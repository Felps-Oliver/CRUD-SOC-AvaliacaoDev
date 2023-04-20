<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<title><s:text name="label.titulo.pagina.edicao" /></title>
<link rel="icon" type="image/png"
	href="https://www.soc.com.br/wp-content/uploads/2020/12/logo-soc.svg" />
<link rel="stylesheet"
	href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" />
</head>
<body class="bg-light">

	<div class="container">
		<s:form action="/editarFicha.action">
			<div class="card mt-5">
				<div class="card-header">
					<div class="row">
						<div class="col-sm-5">
							<s:url action="todosFichas" var="todos" />
							<a href="${todos}" class="btn btn-warning">Fichas</a>
						</div>

						<div class="col-sm">
							<h5 class="card-title" style="color: #3f8c97">Editar Ficha</h5>
						</div>
					</div>
				</div>

				<div class="card-body">

					<div class="row align-items-center">
						<label for="id_ficha" class="col-sm-1 col-form-label">
							ID Ficha: </label>

						<div class="col-sm-2">
							<s:textfield cssClass="form-control text-center" id="id_ficha"
								name="fichaVo.id_ficha" readonly="true" />
						</div>
					</div>

					<div class="row align-items-center">
						<label for="id_funcionario" class="col-sm-1 col-form-label text-center">
							ID Funcionario: </label>

						<div class="col-sm-2">
							<s:textfield cssClass="form-control" id="id_funcionario"
								name="fichaVo.id_funcionario"/>
						</div>
					</div>

					<div class="row align-items-center mt-3">
						<label for="id_exame" class="col-sm-1 col-form-label text-center">
							ID Exame: </label>

						<div class="col-sm-5">
							<s:textfield cssClass="form-control" id="id_exame"
								name="fichaVo.id_exame" />
						</div>
					</div>
					
					<div class="row align-items-center mt-3">
						<label for="data_exame" class="col-sm-1 col-form-label text-center">
							Data Exame: </label>

						<div class="col-sm-5">
							<s:textfield cssClass="form-control" id="data_exame"
								name="fichaVo.data_exame" />
						</div>
					</div>
				</div>

				<div class="card-footer">
					<div class="form-row">
						<button style="background-color: #3f8c97; border: none"
							class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
						<button type="reset"
							class="btn btn-outline-dark col-sm-4 offset-sm-2">Limpar
							Formulário</button>
					</div>
				</div>
			</div>
		</s:form>
	</div>

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
