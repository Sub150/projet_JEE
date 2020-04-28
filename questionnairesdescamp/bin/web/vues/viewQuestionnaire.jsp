<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Affichage questionnaire</title>
</head>
<body>

<c:if test = "${not empty requestScope.error}">
			<h3>${requestScope.error}</h3>
			<form action="creerQuestionnaire" method="get">
	<input type="hidden" name="nomQ" value="${requestScope.nomQ}">
	<input type="submit" value="Creer le questionnaire ${requestScope.nomQ}" class="bouton" />
</form>
</c:if>
<c:if test = "${empty requestScope.error}">
			<h1>Consulter questionnaire ${requestScope.nomQ}</h1>
			
			
		
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
	<div id=gauche>		
		<h1>Ajouter une question au questionnaire</h1>
		<form action="addQuestionQuestionnaire" method="get">
			<input type="hidden" name="nomQ" value="${requestScope.nomQ}"/>
			<h3>Intitule question</h3>
			<input type="text" required value="" name="intitule" />
			<h3>Ajouter une bonne reponse</h3>
			<input type="text" required value="" name="bRep" />
			
			<h3>Type</h3>
			Question ouverte ( une seule reponse )<input type="radio"  name="type" value="OUVERTE"></br>
			Question a choix multiple <input type="radio"  name="type" value="CHECKBOX" checked></br>
			Question a reponse unique <input type="radio"  name="type" value="RADIO"></br>
		<input type="submit" value="Ajouter la question" class="bouton" />
		</form>
	</div>
	<div id=droite>	
		<h1>Ajouter une reponse à  une question</h1>
		<form action="addReponseQuestion" method="get">
		<input type="hidden" name="nomQ" value="${requestScope.nomQ}"/>
			<h3>Choix question</h3>
			<c:forEach items="${requestScope.ListQuestF}" var="quest"> 
						<input type="radio"  name="choix" value="${quest.num}" checked>${quest.intitule}</br>
			</c:forEach>
			
			<h3>Type de reponse</h3>
			<input type="text" name="reponse">
			<input type="radio"  name="valide" value="true" checked>Bonne reponse
			<input type="radio"  name="valide" value="false">Mauvaise reponse </br>
		<input type="submit" value="Ajouter la reponse"  class="bouton"/>
		</form>
	</div>
	
		
			
</c:if>





</body>
</html>
