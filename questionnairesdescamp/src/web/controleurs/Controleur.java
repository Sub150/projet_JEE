package web.controleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.entites.QuestionFermee;
import ejb.entites.QuestionOuverte;
import ejb.entites.Questionnaire;
import ejb.entites.Reponse;
import ejb.sessions.QuestionDejaAjouteeException;
import ejb.sessions.QuestionInconnueException;
import ejb.sessions.QuestionnaireDejaCreeException;
import ejb.sessions.QuestionnaireInconnuException;
import ejb.sessions.ReponseDejaAjouteeException;
import ejb.sessions.ServiceQuestionnairesLocal;
import ejb.sessions.UneReponseValideParQuesitonRadioException;
import ejb.sessions.ServiceQuestionnaires.TypeSpec;

//import ejb.entites.*;
//import ejb.sessions.*;

@WebServlet(value={"index","afficheQuestionnaire","questionnaire","reponseQuestionnaire","admin", "viewQuestionnaire","creerQuestionnaire","addReponseQuestion","addQuestionQuestionnaire"})
public class Controleur extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @javax.ejb.EJB
  private ServiceQuestionnairesLocal service;
  
  
  public Controleur() {}
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
	  String url = request.getRequestURL().toString();
	  String maVue = "/index.html"; // default
	  if (url.endsWith("/index")) {
		maVue = "/index.html";
	  }
	  else if (url.endsWith("/admin")) {
		  maVue=("/admin.html");
	  }
	  else if (url.endsWith("/creerQuestionnaire")) {
		  maVue=("/creerQuestionnaire.jsp");
		  
		  String nomq= request.getParameter("nomQ");
		  try {
			service.creerQuestionnaire(nomq);
			request.setAttribute("nomQ", nomq);
		} catch (QuestionnaireDejaCreeException e) {
			request.setAttribute("error", "Questionnaire deja cree");
		}
	  }
	  
	  else if (url.endsWith("/viewQuestionnaire")) {
		  maVue=("/viewQuestionnaire.jsp");
		  String nomQuestionnaire = request.getParameter("nomQ");
		  try {
			Questionnaire q = service.getQuestionnaire(nomQuestionnaire);
			request.setAttribute("nomQ", q.getNom());
			request.setAttribute("ListQuestF", q.getQuestionsFermees());
			request.setAttribute("ListQuestO", q.getQuestionsOuvertes());
		} catch (QuestionnaireInconnuException e) {
			request.setAttribute("error", "Questionnaire "+nomQuestionnaire+" inconnu");
			request.setAttribute("nomQ", nomQuestionnaire);
		}
	  }
	  else if (url.endsWith("/questionnaire")) {
		  maVue="/questionnaire.jsp";
		  String questionnaire = request.getParameter("nom");
		  
		  try {
			  Questionnaire quest = service.getQuestionnaire(questionnaire);
			  request.setAttribute("ListQuestF", quest.getQuestionsFermees());
				request.setAttribute("ListQuestO", quest.getQuestionsOuvertes());
			  request.setAttribute("nomQ", quest.getNom());
		  } catch (QuestionnaireInconnuException e) {
			  request.setAttribute("Error", "Questionnaire " + questionnaire + "  inexistant");
		  }
	  }
	  else if (url.endsWith("/addReponseQuestion")) {
		  maVue="/addReponseQuestion.jsp";
		  int idQ = Integer.parseInt(request.getParameter("choix"));
		  boolean valide = Boolean.valueOf(request.getParameter("valide"));
		  String reponse = request.getParameter("reponse");
		  String nomQuestionnaire = request.getParameter("nomQ");
		  
		  try {
			service.addReponseFermee(idQ, reponse, valide);
			request.setAttribute("reponse", reponse);
			request.setAttribute("nomQ",nomQuestionnaire );
		} catch (QuestionInconnueException e) {
			request.setAttribute("error", "Question inconnue. Cette erreur ne devrait pas arriver...");
		} catch (ReponseDejaAjouteeException e) {
			request.setAttribute("error", "Reponse deja ajoutee.");
	
		} catch (UneReponseValideParQuesitonRadioException e) {
			request.setAttribute("error","On ne peut ajotuer qu'une seule bonne reponse par question radio" );
		}
	  }
	  else if (url.endsWith("/addQuestionQuestionnaire")) {
		  maVue="/addQuestionQuestionnaire.jsp";
		  String nomQuestionnaire = request.getParameter("nomQ");
		  String typeQuestion = request.getParameter("type");
		  String intitule = request.getParameter("intitule");
		  String reponse = request.getParameter("bRep");
		  try {
			  if (typeQuestion.equals("OUVERTE")) {
				  
				  service.addQuestionOuverte(nomQuestionnaire, intitule, reponse); 
			  }
			  else if (typeQuestion.equals("RADIO")) {
				  service.addQuestionFermee(nomQuestionnaire, TypeSpec.RADIO, intitule);
				  for (QuestionFermee q : service.getQuestionnaire(nomQuestionnaire).getQuestionsFermees() ) {
					  if ( q.getIntitule().equals(intitule)) {
						  service.addReponseFermee(q.getNum(), reponse, true);
					  }
				  }
			  }
			  else if (typeQuestion.equals("CHECKBOX")) {
				  service.addQuestionFermee(nomQuestionnaire, TypeSpec.CHECKBOX, intitule);
				  for (QuestionFermee q : service.getQuestionnaire(nomQuestionnaire).getQuestionsFermees() ) {
					  if ( q.getIntitule().equals(intitule)) {
						  service.addReponseFermee(q.getNum(), reponse, true);
					  }
				  }
			  }
			  request.setAttribute("question", intitule);
			  request.setAttribute("nomQ", nomQuestionnaire);
		  } catch (QuestionnaireInconnuException e) {
			  
		  } catch (QuestionDejaAjouteeException e) {
			  request.setAttribute("error", "Question deja ajoutee.");

		  } catch (QuestionInconnueException e) {
			  request.setAttribute("error", "Question inconnue.");
			
		} catch (ReponseDejaAjouteeException e) {
			  request.setAttribute("error", "Reponse deja ajoutee.");
			
		} catch (UneReponseValideParQuesitonRadioException e) {
			  request.setAttribute("error", "une reponse valide par question radio");
		}
	  }
	  else if (url.endsWith("/reponseQuestionnaire")) {
		  maVue="/reponseQuestionnaire.jsp";
		  String nomQuestionnaire = request.getParameter("nomQ");
		  try {
			Questionnaire q = service.getQuestionnaire(nomQuestionnaire);
			request.setAttribute("nomQuest", q.getNom());
			request.setAttribute("ListeQuestF", q.getQuestionsFermees());
			request.setAttribute("ListeQuestO", q.getQuestionsOuvertes());
			String testRep[] = new String[100];
			String repD[] = new String[100];
			for (int i=0; i<100; i++)
				repD[i]="";
			/* Ajouter for question ouverete */
			for (QuestionFermee quest : q.getQuestionsFermees()) {
				String[] reponses = request.getParameterValues(String.valueOf(quest.getNum()));
				try {
					boolean test = service.testReponseFermee(quest.getNum(), reponses);
					for ( String r : reponses)
						repD[quest.getNum()]=repD[quest.getNum()]+r+" ";
					if (test) {
						testRep[quest.getNum()]= "correcte";
					}
					else {
						testRep[quest.getNum()]= "erronee";
					}
					
				} catch (QuestionInconnueException e) {
					request.setAttribute("error", "Question inconnue");
				}
			}
			
			
			
			
			for (QuestionOuverte quest : q.getQuestionsOuvertes()) {
				String reponse = request.getParameter(String.valueOf(quest.getNum()));
				try {
					boolean test = service.testReponseOuverte(quest.getNum(), reponse);
					//for ( String r : reponse)
						repD[quest.getNum()]=reponse;
						
					if (test) {
						testRep[quest.getNum()]= "correcte";
					}
					else {
						testRep[quest.getNum()]= "erronee";
					}
					
				} catch (QuestionInconnueException e) {
					request.setAttribute("error", "Question inconnue");
				}
			}
			
			
			
			
			
			
			request.setAttribute("repD",repD );
			request.setAttribute("testRep", testRep);
		} catch (QuestionnaireInconnuException e) {
			request.setAttribute("error", "Questionnaire inconnu");
		}
		  
		
		   
	  }
	 
	  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(maVue);
	  dispatcher.forward(request, response);
  }
}
