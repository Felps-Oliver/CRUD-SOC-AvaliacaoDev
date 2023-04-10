<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:text name="label.titulo.pagina.telainicial" /></title>
<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
<link rel='stylesheet' href='styles/tela_inicial.css'>
</head>

<body>
	<div class="box">
		<div class="estiliza" align="center">
			<h2>O que deseja fazer?</h2>

			<div class="list-group" align="center">
				<s:url action="todosExames" var="todos" />
				<a href="${todos}" class="list-group-item list-group-item-action">
					<s:text name="label.opcao.exames" />
				</a>
				<s:url action="todosFuncionarios" var="todos" />
				<a href="${todos}" class="list-group-item list-group-item-action">
					<s:text name="label.opcao.funcionarios" />
				 </a>
				<s:url action="todosFichas" var="todos" />
				<a href="${todos}" class="list-group-item list-group-item-action">
					<s:text name="label.opcao.fichas" />
				</a>

			</div>
			<br>
			<div class="list-group" align="center">
				<s:url action="relatorioRealizadosPeriodoFicha" var="relatorio" />
				<a href="${relatorio}"
					class="list-group-item list-group-item-action">
						<s:text name="label.opcao.relatorioexamesrealizados" />
					</a>
				<s:url action="relatorioTOPRealizadosFicha" var="relatorio" />
				<a href="${relatorio}"
					class="list-group-item list-group-item-action">
						<s:text name="label.opcao.relatoriotop5" />
					</a>
			</div>
		</div>
	</div>
</body>
</html>