package ejb.sessions;

import java.util.Collection;
import java.util.Set;

import ejb.entites.*;
import ejb.sessions.ServiceQuestionnaires.TypeSpec;

public interface ServiceQuestionnaires {
	
	public void creerQuestionnaire(String nom) throws QuestionnaireDejaCreeException;
	
	public Questionnaire getQuestionnaire(String nom) throws QuestionnaireInconnuException;
	
	public Collection<Questionnaire> getQuestionnaires() ;
	
	public void addQuestion(String questionnaire, int id, TypeSpec type, String intitule )  throws QuestionnaireInconnuException, QuestionDejaAjouteeException ;
	
	public void addReponse(int question, String reponse ) throws QuestionInconnueException, ReponseDejaAjouteeException ;
	
	public void addBonneReponse(int question, String reponse ) throws QuestionInconnueException, ReponseDejaAjouteeException, ReponseInconnueException ;
	
	
	
	public enum TypeSpec {OUVERTE, RADIO, CHECKBOX} ;
}
	

