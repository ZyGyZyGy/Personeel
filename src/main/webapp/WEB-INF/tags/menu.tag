<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<ul>
		<li><a href="<c:url value='/'/>">&#8962;</a></li>
		<li><a href="#">Werknemers</a>
			<ul>
				<li><a href="<c:url value='/werknemers/werknemershierarchie'/>">Werknemershi&euml;rarchie</a></li>
				<li><a href="<c:url value='/werknemers/jobtitels'/>">Jobtitels</a>
<%-- 			<li><a href="<c:url value='/werknemers/toevoegen'/>">Toevoegen</a></li> --%>
			</ul>
		</li>
		<c:if test='${pageContext.response.locale.language != "nl"}'>
			<c:url value='' var='nederlandsURL'>
				<c:param name='locale' value='nl_be' />
			</c:url>
			<li><a href='${nederlandsURL}'>Nederlands</a></li>
		</c:if>
		<c:if test='${pageContext.response.locale.language != "en"}'>
			<c:url value='' var='engelsURL'>
				<c:param name='locale' value='en_us' />
			</c:url>
			<li><a href='${engelsURL}'>Engels</a></li>
		</c:if>
	</ul>
</nav>
