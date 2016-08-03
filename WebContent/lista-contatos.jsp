<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title>Pagina com JSTL</title>
	</head>
	
	<body>
	
		<c:import url="cabecalho.jsp" />
		
		<!-- cria o DAO -->
		<jsp:useBean id="dao" class="br.com.caelum.jdbc.dao.ContatoDao"/>
		
		<table>
		  <!-- percorre contatos montando as linhas da tabela -->
		  <c:forEach var="contato" items="${dao.lista}">
		    <tr>
		      <td>${contato.nome}</td>
		      <td>${contato.email}</td>
		      <td>${contato.endereco}</td>
		      <td>
		      	<fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy" />
		      </td>
		    </tr>
		  </c:forEach>
		</table>
		
		<c:import url="rodape.jsp" />
	
	</body>	
</html>