<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reponse questionnaire</title>
</head>
<body>
<c:if test = "${not empty requestScope.error}">
	<h1>${requestScope.error}</h1>
</c:if>
<c:if test = "${empty requestScope.Error}">
	<h1>${requestScope.Error}</h1>

	<h1>Reponses au questionnaire ${requestScope.nomQuest}</h1>
	
	<c:forEach items="${requestScope.ListeQuest}" var="quest"> 
		<h3>${quest.intitule}</h3>
		Vous avez repondu ${requestScope.repD[quest.num] }</br>
		Votre reponse est <b>${requestScope.testRep[quest.num]}</b>
	</c:forEach>
</c:if>
</body>
</html>
