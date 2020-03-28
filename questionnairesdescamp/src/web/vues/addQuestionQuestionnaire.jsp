<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajout question questionnaire</title>
</head>
<body>
<c:if test = "${not empty requestScope.error}">
	<h1>${requestScope.error}</h1>
</c:if>
<c:if test = "${empty requestScope.error}">
	<h1>Question "${requestScope.question}" ajoutée</h1>
	Vous allez être redirigé. 
	<meta http-equiv="refresh" content="5;URL=http://localhost:8080/projet/admin">
</c:if>
</body>
</html>
