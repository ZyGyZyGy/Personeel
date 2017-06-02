<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Jobtitels' />
</head>
<body>
	<v:menu />
	<h1>Jobtitels</h1>
	<c:forEach items="${jobtitels}" var="jobtitel">
		<spring:url value="/werknemers/jobtitels/{id}" var="jobtitelUrl">
			<spring:param name="id" value="${jobtitel.id}"/>
		</spring:url>
		<h4 class="inlineHoofding"><a href="${jobtitelUrl}">${jobtitel.naam}</a></h4>
	</c:forEach>
	<h2>${aangeklikteJobtitel.naam}</h2>
	<c:if test="${not empty werknemers}">
		<ul>
			<c:forEach items="${werknemers}" var="werknemer">
				<spring:url value="/werknemers/werknemershierarchie/{id}" var="werknemerUrl">
					<spring:param name="id" value="${werknemer.id}"/>
				</spring:url>
				<li><a href="${werknemerUrl}">${werknemer.voornaam} ${werknemer.familienaam}</a></li>
			</c:forEach>
		</ul>
	</c:if>
	
</body>
</html>
