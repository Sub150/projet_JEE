<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
  <title>votre vue</title>
</head>

<body>
<h1>Choix Questionnaire</h1>
<form action="afficheQuestionnaire" method="get">
	<select name="Questionnaire" id="quest">
		<c:forEach items="${ejb.sessions.ServiceQuestionnaireBean.getQuestionnaires()}" var="q">
			<option value="${q.nom}">${q.nom}</option>
		</c:forEach>
	</select>
<input type="submit" value="valider" />
</form>
</body>
</html>
    
