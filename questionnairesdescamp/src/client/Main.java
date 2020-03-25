package client ;


 import javax.naming.InitialContext;
import javax.naming.NamingException ;

import ejb.entites.Question;
import ejb.entites.Questionnaire;
import ejb.entites.Reponse;
import ejb.sessions.QuestionDejaAjouteeException;
import ejb.sessions.QuestionInconnueException;
import ejb.sessions.QuestionnaireDejaCreeException;
import ejb.sessions.QuestionnaireInconnuException;
import ejb.sessions.ReponseDejaAjouteeException;
import ejb.sessions.ReponseInconnueException;
import ejb.sessions.ServiceQuestionnaires;
import ejb.sessions.ServiceQuestionnaires.TypeSpec;
import ejb.sessions.ServiceQuestionnairesRemote;




public class Main {
	
	
 public static void main(String[] args) { 
	 String appName = "questionnaire";
	 String moduleName="questionnaireSessions";
	 String distinctName ="";
	 String beanName = "ServiceQuestionnairesBean";
	 String remoteInterfaceName=ServiceQuestionnairesRemote.class.getName();
	 String adresseJNDI = "ejb:"+appName+"/"+moduleName+"/"+distinctName+"/"+beanName+"!"+remoteInterfaceName; 
	 try { 
		 InitialContext ctx = new InitialContext();
		 System.out.println("Acces au service distant");
		 Object obj = ctx.lookup(adresseJNDI);
		 ServiceQuestionnaires service = (ServiceQuestionnaires) obj;
		 System.out.println("Creation du premier questionnaire");
		 try {
			 service.creerQuestionnaire("serieTV");
		 } catch ( QuestionnaireDejaCreeException e) {
			 System.err.println("Questionnaire deja cree");
		 }
		 System.out.println("Creation du second questionnaire");
		 try {
			 service.creerQuestionnaire("Le second questionnaire");
		 } catch ( QuestionnaireDejaCreeException e) {
			 System.err.println("Questionnaire deja cree");
		 }
		 System.out.println("Imprimons maintenant les questionnaires");
		 for ( Questionnaire q : service.getQuestionnaires()) {
			 System.out.println("Questionnaire : "+q.getNom());
		 }
		 try {
			 System.out.println("Ajoutons maintenant des questions au questionnaire :"+ service.getQuestionnaire("serieTV").getNom());
		 } catch ( QuestionnaireInconnuException e) {
			 System.err.println("Questionnaire inconnu");
		 } 
		 try {
			 service.addQuestion("serieTV", TypeSpec.OUVERTE, "Combien de sucres met Jhon Steed dans son the?");
		 } catch ( QuestionDejaAjouteeException e) {
			 System.err.println("Question deja ajoutee");
		 } catch ( QuestionnaireInconnuException e) {
			 System.err.println("Question inconnue");
		 }
		 try {
			 service.addQuestion("serieTV", TypeSpec.RADIO, "Quel est le titre original de la serie 'Chapeau Melon et bottes de cuir'");
		 } catch ( QuestionDejaAjouteeException e) {
			 System.err.println("Question deja ajoutee");
		 } catch ( QuestionnaireInconnuException e) {
			 System.err.println("Question inconnue");
		 }
		 try {
			 service.addQuestion("serieTV", TypeSpec.CHECKBOX, "Est-ce que les actrices suivantes ont joue dans la serie 'Chapeau Melon et bottes de cuir'");
		 } catch ( QuestionDejaAjouteeException e) {
			 System.err.println("Question deja ajoutee");
		 } catch ( QuestionnaireInconnuException e) {
			 System.err.println("Question inconnue");
		 }
		 
		 System.out.println("Affichons la liste des questions de serieTV");
		 try {
			 for ( Question q : service.getQuestionnaire("serieTV").getQuestions() )
				 System.out.println("Question : "+q.getIntitule());
		 } catch (QuestionnaireInconnuException e) {
			 System.err.println("Questionnaire inconnu");
		 }
		 
		 
		 System.out.println("Ajoutons maintenant des reponses aux questions");
		 
		 try {
			 service.addReponse(1, "3", true);
			 service.addReponse(2, "Dexter", false);
			 service.addReponse(2, "Wild wild west", false);
			 service.addReponse(2, "The Avengers", true);
			 service.addReponse(3, "Linda Thornson", true);
			 service.addReponse(3, "Uma Thruman", false);
			 service.addReponse(3, "Diana Rigg", true);
		 } catch ( ReponseDejaAjouteeException e) {
			 System.err.println("Reponse deja ajoutee");
		 } catch ( QuestionInconnueException e) {
			 System.err.println("Reponse deja ajoutee");
		 }

	
	 } catch (NamingException e) {
		System.err.println("Erreur:" + e.getMessage());
	 }
	 
 }  
}

