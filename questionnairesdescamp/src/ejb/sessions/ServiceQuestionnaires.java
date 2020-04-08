package ejb.sessions;

import java.util.Collection;
import java.util.Set;

import ejb.entites.*;
import ejb.sessions.ServiceQuestionnaires.TypeSpec;

public interface ServiceQuestionnaires {
	
	public void creerQuestionnaire(String nom) throws QuestionnaireDejaCreeException;
	
	public Questionnaire getQuestionnaire(String nom) throws QuestionnaireInconnuException;
	
	public Collection<Questionnaire> getQuestionnaires() ;
	
	public void addQuestionFermee(String questionnaire, TypeSpec type, String intitule )  throws QuestionnaireInconnuException, QuestionDejaAjouteeException ;
	
	public void addQuestionnaire(String questionnaire) throws QuestionnaireDejaCreeException;
	
	public void addQuestionOuverte(String questionnaire, String intitule, String reponse) throws QuestionnaireInconnuException, QuestionDejaAjouteeException; //Ajouter au bean
	
	public void addReponseFermee( int question, String reponse, boolean valide ) throws QuestionInconnueException, ReponseDejaAjouteeException, UneReponseValideParQuesitonRadioException ;
	
	public QuestionFermee getQuestionFermee(int id) throws QuestionInconnueException;

	public QuestionOuverte getQuestionOuverte(int id) throws QuestionInconnueException ;
	
	public Collection<QuestionFermee> getQuestionsFermees();
	
	public Collection<Reponse> getReponses();
	
	public boolean testReponseFermee(int num, String[] reponses)throws QuestionInconnueException;
	
	public boolean testReponseOuverte(int num, String reponse) throws QuestionInconnueException ; 
	
	
	public enum TypeSpec {OUVERTE, RADIO, CHECKBOX} ;
}
	

