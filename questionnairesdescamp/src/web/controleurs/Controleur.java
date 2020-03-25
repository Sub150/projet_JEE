package web.controleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.entites.Questionnaire;
import ejb.sessions.QuestionnaireInconnuException;
import ejb.sessions.ServiceQuestionnairesLocal;

//import ejb.entites.*;
//import ejb.sessions.*;

@WebServlet(value={"index","afficheQuestionnaire","questionnaire"})
public class Controleur extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @javax.ejb.EJB
  private ServiceQuestionnairesLocal service;
  
  
  public Controleur() {}
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
	  String url = request.getRequestURL().toString();
	  String maVue = "/index.jsp"; // default
	  if (url.endsWith("/index")) {
		maVue = "/index.jsp";
	  }
	  else if (url.endsWith("/afficheQuestionnaire")) {
		  maVue = "/afficheQuestionnaire.jsp"; //
		  String questionnaireStr = request.getParameter("nom");
		  try {
			Questionnaire q = service.getQuestionnaire(questionnaireStr);
			request.setAttribute("nomQ",q.getNom());
			request.setAttribute("ListQuest",q.getQuestions());
			
		} catch (QuestionnaireInconnuException e) {
			request.setAttribute("error", "Questionnaire" + questionnaireStr + "  inexistant");
		}
	
		  
	  }
	  else if (url.endsWith("/questionnaire")) {
		  maVue="/questionnaire.jsp";
		  String questionnaire = request.getParameter("nom");
		  
		  try {
			  Questionnaire quest = service.getQuestionnaire(questionnaire);
			  request.setAttribute("ListQuest",quest.getQuestions());
			  request.setAttribute("nomQ", quest.getNom());
		  } catch (QuestionnaireInconnuException e) {
			  request.setAttribute("Error", "Questionnaire " + questionnaire + "  inexistant");
		  }
	  }
	  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(maVue);
	  dispatcher.forward(request, response);
  }
}
