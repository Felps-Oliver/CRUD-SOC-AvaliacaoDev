<script src="scripts/login.js"></script>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<body>
<nav class="navbar navbar-dark bg-dark fixed-top">
		<div class="container-fluid">
			<span class="navbar-brand">
				<s:text name="label.bemvindo" />
				<span id="loginUsuario"></span>
			</span>
			<script>
				document.getElementById("loginUsuario").innerHTML = getCookie('login');
			</script>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar"
				aria-controls="offcanvasDarkNavbar" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1"
				id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
				<div class="offcanvas-header">
					<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">
							<s:text name="label.menu.menu" />
					</h5>
					<button type="button" class="btn-close btn-close-white"
						data-bs-dismiss="offcanvas" aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
						
						<s:url action="todosExames" var="todos" />
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="${todos}">
								<s:text name="label.menu.exame" />
							</a>
						</li>
						
						<s:url action="todosFuncionarios" var="todos" />
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="${todos}">
								<s:text name="label.menu.funcionario" />
							</a>
						</li>
								
						<s:url action="todosFichas" var="todos" />
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="${todos}">
								<s:text name="label.menu.ficha" />
							</a>
						</li>
								
						<s:url action="relatorioRealizadosPeriodoFicha" var="relatorio" />
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="${relatorio}">
								<s:text name="label.menu.relexamesrealizados" />
							</a>
						</li>
								
						<s:url action="relatorioTOPRealizadosFicha" var="relatorio" />
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="${relatorio}">
								<s:text name="label.menu.reltop5exames" />
							</a>
						</li>
						
						<s:url action="logoutLogin" var="logout" />
						<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="${logout}">
								<s:text name="label.menu.logout" />
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<br>
</body>