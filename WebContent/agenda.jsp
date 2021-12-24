<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%
	@ SuppressWarnings({"unchecked"})
	ArrayList<String[]> contatos = (ArrayList<String[]>) request.getAttribute("cont");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>Agenda</title>
</head>
<body>
	<h1>Agenda</h1>
	<div class="d-flex flex-row">
		<a href="novo.html" class="btn btn-light">Criar contato</a>
		<a href="relatorio" class="btn btn-danger">Gerar Relatório</a>
	</div>
	
	<table class="table table-bordered" style="width:50%;">
		<thead>
			<tr>
				<th>id</th>
				<th>nome</th>
				<th>telefone</th>
				<th>email</th>
			</tr>
		</thead>
		<tbody>
			<%for (int i=0; i<contatos.size();i++) {%>
				<tr>
					<td><%=contatos.get(i)[0]%></td>
					<td><%=contatos.get(i)[1]%></td>
					<td><%=contatos.get(i)[2]%></td>
					<td><%=contatos.get(i)[3]%></td>
					<td><a href="pagina_altera?_id=<%= contatos.get(i)[0]%>" class="btn btn-info">Alterar</a></td>
					<td><a href="deletar?_id=<%=contatos.get(i)[0]%>" class="btn btn-danger">Deletar</a></td>
				</tr>
			<%} %>
		</tbody>
	</table>
	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>