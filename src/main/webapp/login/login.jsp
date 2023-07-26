<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><s:text name="label.titulo.pagina.login" /></title>
<link rel='stylesheet' href='styles/login.css'>
</head>

<body>
	<div class="box">
		<div class="form">
			<s:form action="/validaLogin.action">
				<h2>Entrar</h2>
				<div class="inputBox">
					<span>Login</span>
					<s:textfield cssClass="inputBox input" id="login"
						name="usuarioVo.login" required="required" placeholder="Login" />
					<i></i>
				</div>
				<div class="inputBox">
					<span>Senha</span>
					<s:password cssClass="inputBox input" id="password"
						name="usuarioVo.password" required="required" placeholder="Senha" />
					<i></i>
				</div>
				<center>
					<s:submit cssClass="btn btn-black" value="Entrar" />
				</center>
			</s:form>
		</div>
	</div>
</body>
</html>