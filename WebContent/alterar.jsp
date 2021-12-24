<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String[] con = (String[]) request.getAttribute("con");
String id = con[0];
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alterar contato</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<h1>Alterar contato</h1>
	<form name="formContato" action="mudar" method="post">
		<div class="mb-3" style="width: 50%">
			<input type="text" name="id" value="<%=con[0]%>"
				class="form-control" id="id_contato" readonly>
		</div>
		<div class="mb-3" style="width: 50%">
			<label for="nome_contato" class="form-label">Nome</label> <input
				type="text" name="nome" value="<%=con[1]%>" class="form-control"
				id="nome_contato">
		</div>
		<div class="mb-3" style="width: 50%">
			<label for="telefone" class="form-label">Telefone</label> <input
				type="tel" name="telefone" value="<%=con[2]%>" class="form-control"
				id="telefone">
		</div>
		<div class="mb-3" style="width: 50%">
			<label class="form-label" for="email_contato">Email</label> <input
				type="email" name="email" value="<%=con[3]%>" class="form-control"
				id="email_contato">
		</div>
		<button class="btn btn-primary" onClick="validar()">Registrar</button>
	</form>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script src="scripts/validador.js"></script>
</body>
</html>