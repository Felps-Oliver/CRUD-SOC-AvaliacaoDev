<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="label.titulo.pagina.cadastro"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container">
			<s:form action="/novoFicha.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosFichas" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Exames Realizados do Funcionario</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Novo Exame para o Funcionario</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
					
						<div class="row align-items-center">
							<label for="id_ficha" class="col-sm-2 col-form-label text-center">
								Nº Ficha:
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id_ficha" name="fichaVo.id_ficha" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="id_funcionario" class="col-sm-2 col-form-label text-center">
								Código Funcionário:
							</label>	

							<div class="col-sm-2">
								<s:select cssClass="form-select" name="opcaoFuncionario"
								list="funcionarios" headerKey="" headerValue="Funcionário..."
								listKey="%{rowid}" listValueKey="%{nome}"
								value="%{fichaVo.id_funcionario}" />
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="id_exame" class="col-sm-2 col-form-label text-center">
								Código Exame:
							</label>	

							<div class="col-sm-2">
								<s:select cssClass="form-select" name="opcaoExame"
								list="exames" headerKey="" headerValue="Exame..."
								listKey="%{rowid}" listValueKey="%{nome}"
								value="%{fichaVo.id_exame}" />							
							</div>	
						</div>
						
						
						<div class="row align-items-center mt-3">
							<label for="data_nome" class="col-sm-2 col-form-label text-center">
								Data Exame:
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" format="dd-MM-yyyy"
								type="date" id="data_nome" name="fichaVo.data_exame" />							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>