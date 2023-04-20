<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:text name="label.titulo.pagina.consulta" /></title>
<link rel='stylesheet'
	href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">
	<c:import url="header-menu.jsp" />
	<div class="container">
		<div class="row mt-5 mb-2">
			<div class="col-sm p-0">
				<s:form action="/filtrarFicha.action">
					<div class="input-group">
						<span class="input-group-text"> <strong><s:text
									name="label.buscar.por" /></strong>
						</span>
						<s:select cssClass="form-select" name="filtrar.opcoesCombo"
							list="listaOpcoesCombo" headerKey="" headerValue="Escolha..."
							listKey="%{codigo}" listValueKey="%{descricao}"
							value="filtrar.opcoesCombo.codigo" />

						<s:textfield cssClass="form-control" id="nome"
							name="filtrar.valorBusca" />
						<button class="btn btn-primary" type="submit">
							<s:text name="label.pesquisar" />
						</button>
					</div>
				</s:form>
			</div>

		</div>

		<div class="row">
			<table class="table table-light table-striped align-middle">
				<thead>
					<tr>
						<th>Nº Ficha</th>
						<th>Exame</th>
						<th>Funcionário</th>
						<th>Data Exame</th>
						<th class="text-end mt-5"><s:text name="label.acao" /></th>
					</tr>
				</thead>

				<tbody>
					<s:iterator value="fichas">
						<tr>
							<td>${id_ficha}</td>
							<td>${nome_exame}</td>
							<td>${nome_funcionario}</td>
							<td>${data_exame}</td>
							<td class="text-end"><s:url action="editarFicha"
									var="editar">
									<s:param name="fichaVo.id_ficha" value="id_ficha"></s:param>
								</s:url> <a href="${editar}" class="btn btn-warning text-white"> <s:text
										name="label.editar" />

							</a> <s:url action="excluirFicha" var="excluir">
									<s:param name="fichaVo.id_ficha" value="id_ficha"></s:param>
								</s:url>
							 <a href="${excluir}" class="btn btn-danger"> <s:text
										name="label.excluir" />
							</a></td>
						</tr>
					</s:iterator>
				</tbody>

				<tfoot class="table-secondary">
					<tr>
						<td colspan="5"><s:url action="novoFicha" var="novo" /> <a
							href="${novo}" class="btn btn-success"> <s:text
									name="label.novo" />
						</a>
						<s:url action="relatorioRealizadosPeriodoFicha" var="relatorio" /> <a
							href="${relatorio}" class="btn btn-success"> <s:text
									name="label.relatorioExamesPeriodo" />
						</a>
						
						<s:url action="relatorioTOPRealizadosFicha" var="relatorio" /> <a
							href="${relatorio}" class="btn btn-success"> <s:text
									name="label.relatorioMaisRealizados" />
						</a>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>

		<div class="row"></div>
	</div>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>