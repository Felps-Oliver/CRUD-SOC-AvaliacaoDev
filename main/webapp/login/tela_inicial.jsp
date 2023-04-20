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
					Exames </a>
				<s:url action="todosFuncionarios" var="todos" />
				<a href="${todos}" class="list-group-item list-group-item-action">
					Funcionários </a>
				<s:url action="todosFichas" var="todos" />
				<a href="${todos}" class="list-group-item list-group-item-action">
					Exames Realizados (Fichas) </a>

			</div>
			<br>
			<div class="list-group" align="center">
				<s:url action="relatorioFicha" var="relatorio" />
				<a href="${relatorio}"
					class="list-group-item list-group-item-action"> Relatório
					Exames Realizados </a>
				<s:url action="relatorio_MaisRealizadosFicha" var="relatorio" />
				<a href="${relatorio}"
					class="list-group-item list-group-item-action"> Relatório TOP
					Exames Realizados </a>
			</div>
		</div>
	</div>
</body>
</html>