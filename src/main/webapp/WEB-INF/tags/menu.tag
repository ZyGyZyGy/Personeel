<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<ul>
		<li><a href="<c:url value='/'/>">&#8962;</a></li>
		<li><a href="#">Werknemers</a>
			<ul>
				<li><a href="<c:url value='/werknemers/werknemershierarchie'/>">Werknemershi&euml;rarchie</a></li>
				<li><a href="<c:url value='/werknemers/jobtitels'/>">Jobtitels</a>
			</ul>
		</li>
	</ul>
</nav>
