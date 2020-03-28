package ejb.sessions;

import java.util.Collection;
import java.util.Set;

import ejb.entites.*;
import ejb.sessions.ServiceQuestionnaires.TypeSpec;

public interface ServiceQuestionnaires {
	
	public void creerQuestionnaire(String nom) throws QuestionnaireDejaCreeException;
	
	public Questionnaire getQuestionnaire(String nom) throws QuestionnaireInconnuException;
	
	public Collection<Questionnaire> getQuestionnaires() ;
	
	public void addQuestion(String questionnaire, TypeSpec type, String intitule )  throws QuestionnaireInconnuException, QuestionDejaAjouteeException ;
	
	public void addReponse( int question, String reponse, boolean valide ) throws QuestionInconnueException, ReponseDejaAjouteeException, UneReponseParQuestionOuverteException, ReponseValideUniquementException, UneReponseValideParQuesitonRadioException ;
	
	public Question getQuestion(int id) throws QuestionInconnueException;
	
	public Collection<Question> getQuestions();
	
	public Collection<Reponse> getReponses();
	
	public boolean testReponse(int num, String[] reponses)throws QuestionInconnueException;
	
	public enum TypeSpec {OUVERTE, RADIO, CHECKBOX} ;
}
	

