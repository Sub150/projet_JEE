<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Affichage questionnaire</title>
</head>
<body>
<c:if test = "${not empty requestScope.error}">
	<h3>${requestScope.error}</h3>
</c:if>
<c:if test = "${not empty requestScope.error}">>
	<h1> Affichage du questionnaire ${requestScope.nomQ}</h1>
	<ul>
		<c:forEach items="${requestScope.listQuest}" var="quest">
			<li><h3>1 question</h3></li>
		</c:forEach>
	</ul>
</c:if>


</body>
</html>
