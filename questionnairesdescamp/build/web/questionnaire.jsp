<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
  <title>questionnaire</title>
</head>

<body>
<c:if test = "${not empty requestScope.Error}">
	<h1>${requestScope.Error}</h1>
</c:if>
<c:if test = "${not empty requestScope.nomQ}">
	<h1>Questionnaire ${requestScope.nomQ}</h1>
	<ul>
		<c:forEach items="${requestScope.ListQuest}" var="quest"> 
			<li><h3>${quest.intitule}</h3></li>
		</c:forEach>
	</ul>
</c:if>

</body>
</html>
    
