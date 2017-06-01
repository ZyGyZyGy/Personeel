<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Werknemershi&euml;rarchie' />
</head>
<body>
	<v:menu />
	<c:choose>
		<c:when test="${not empty werknemer}">
			<h1>Werknemer ${werknemer.voornaam} ${werknemer.familienaam}</h1>
			<c:if test="${param.update}">
				<div class="success">Salaris werd succesvol aangepast.</div>
			</c:if>
			<c:if test="${param.optimisticlockingexception}">
				<div class="fout">Werknemer werd door andere gebruiker als volgt gewijzigd:</div>
			</c:if>
			<dl>
				<dt>Voornaam</dt>
				<dd>${werknemer.voornaam}
				<dt>Familienaam</dt>
				<dd>${werknemer.familienaam}</dd>
				<dt>Email adres</dt>
				<dd>${werknemer.email}</dd>
				<dt>Salaris</dt>
				<dd>&euro;<spring:eval expression="werknemer.salaris"/></dd>
				<dt>Jobtitel</dt>
				<dd>${werknemer.jobtitel.naam}</dd>
				<c:if test="${not empty werknemer.chef}">
					<spring:url value="/werknemers/werknemershierarchie/{id}" var="chefUrl">
						<spring:param name="id" value="${werknemer.chef.id}"/>
					</spring:url>
					<dt>Chef</dt>
					<dd><a href="${chefUrl}">${werknemer.chef.voornaam} ${werknemer.chef.familienaam}</a></dd>
				</c:if>
				<c:if test="${not empty werknemer.ondergeschikten}">
					<dt>Ondergeschikten</dt>
					<c:forEach items="${werknemer.ondergeschikten}" var="ondergeschikte">
						<spring:url value="/werknemers/werknemershierarchie/{id}" var="ondergeschikteUrl">
							<spring:param name="id" value="${ondergeschikte.id}"/>
						</spring:url>
						<dd><a href="${ondergeschikteUrl}">${ondergeschikte.voornaam} ${ondergeschikte.familienaam}</a></dd>
					</c:forEach>
				</c:if>
			</dl>
			<p>Foto</p>
			<img src="<c:url value='/images/${werknemer.id}.jpg'/>" 
					alt="foto ${werknemer.voornaam} ${werknemer.familienaam}">
			<spring:url value="/werknemers/{id}/opslag" var="opslagUrl">
				<spring:param name="id" value="${werknemer.id}"/>
			</spring:url>
			<p><a href="${opslagUrl}">Opslag</a></p>
		</c:when>
		<c:otherwise>
			<div class="fout">Werknemer niet gevonden</div>
		</c:otherwise>
	</c:choose>
	
</body>
</html>
