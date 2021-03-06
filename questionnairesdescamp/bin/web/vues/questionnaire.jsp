<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
	<link rel="stylesheet" href="style.css">
  <title>questionnaire</title>
</head>

<body>
<c:if test = "${not empty requestScope.Error}">
	<h1>${requestScope.Error}</h1>
</c:if>
<c:if test = "${empty requestScope.Error}">

	
</c:if>
<c:if test = "${not empty requestScope.nomQ}">
	<h1>Questionnaire ${requestScope.nomQ}</h1>
	<form action="reponseQuestionnaire" method="get">
	<input type="hidden" name="nomQ" value="${requestScope.nomQ}" />
	<ul>
		<c:forEach items="${requestScope.ListQuestF}" var="quest"> 
			<li><h3>${quest.intitule}</h3></li>
				<c:if  test = "${quest['class'].name.equals('ejb.entites.QuestionRadio')}">
					
						<c:forEach items="${quest.reponses}" var="r">
							<input type="radio" required name="${quest.num}" value="${r.reponse}">${r.reponse}</br>
						</c:forEach>
					
				</c:if>
				<c:if  test = "${quest['class'].name.equals('ejb.entites.QuestionCheckbox')}">
					
						<c:forEach items="${quest.reponses}" var="r">
							<input type="checkbox"  name="${quest.num}" value="${r.reponse}">${r.reponse}</br>
						</c:forEach>
					
				</c:if>
		</c:forEach>
		
		<c:forEach items="${requestScope.ListQuestO}" var="quest"> 
			<li><h3>${quest.intitule}</h3></li>
					<ul>
						
						<input type="text" required name="${quest.num}" />
						
					</ul>
		</c:forEach>
		
	</ul>
	<input type="submit" value="valider" class="bouton" />
	</form>
</c:if>

</body>
</html>
    
