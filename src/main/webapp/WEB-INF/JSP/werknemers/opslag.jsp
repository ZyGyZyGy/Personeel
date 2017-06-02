<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Opslag'/>
</head>
<body>
	<v:menu />
	<c:choose>
	
		<c:when test="${not empty werknemer}">
			<h1>Opslag voor ${werknemer.voornaam} ${werknemer.familienaam}</h1>
			<dl>
				<dt>Huidige salaris</dt>
				<dd>&euro;<spring:eval expression="werknemer.salaris"/></dd>
			</dl>
			
			<spring:url value="/werknemers/{id}/opslag" var="url">
				<spring:param name="id" value="${werknemer.id}"/>
			</spring:url>
			
			<form:form action="${url}" commandName="werknemer" id="opslagForm">
				<form:label path="opslag">Bedrag<form:errors path="opslag"/>
				</form:label>
				<form:input path="opslag" autofocus="autofocus" />
				<form:hidden path="versie"/>
				<input type="submit" value="Opslag" id="submitknop">
			</form:form>
			<script>
				document.getElementById('opslagForm').onsubmit = function() {
					document.getElementById('submitknop').disabled = true;
				};
			</script>

		</c:when>
		<c:otherwise>
			<div class="fout">Werknemer niet gevonden</div>
		</c:otherwise>
		
	</c:choose>
	
</body>
</html>
