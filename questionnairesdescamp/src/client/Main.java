package client ;


 import javax.naming.InitialContext;
import javax.naming.NamingException ;

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
			 service.creerQuestionnaire("Le premier questionnaire");
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
			 System.out.println("Ajoutons maintenant des questions au questionnaire :"+ service.getQuestionnaire("Le second questionnaire").getNom());
		 } catch ( QuestionnaireInconnuException e) {
			 System.err.println("Questionnaire inconnu");
		 } 
		 try {
			 service.addQuestion("Le second questionnaire", 1, TypeSpec.OUVERTE, "Combien de questionnaires avons nous cree?");
		 } catch ( QuestionDejaAjouteeException e) {
			 System.err.println("Question deja ajoutee");
		 } catch ( QuestionnaireInconnuException e) {
			 System.err.println("Question inconnue");
		 }
		 try {
			 service.addReponse(1, "2");
		 } catch ( ReponseDejaAjouteeException e) {
			 System.err.println("Reponse deja ajoutee");
		 } catch ( QuestionInconnueException e) {
			 System.err.println("Reponse deja ajoutee");
		 }
		 try {
			 service.addBonneReponse(1, "2");
		 } catch ( ReponseDejaAjouteeException e) {
			 System.err.println("Reponse deja ajoutee");
		 } catch ( QuestionInconnueException e) {
			 System.err.println("Reponse deja ajoutee");
		 } catch ( ReponseInconnueException e) {
			 System.err.println("Reponse inconnue");
		 }
	 
	 } catch (NamingException e) {
		System.err.println("Erreur:" + e.getMessage());
	 }
	 
 }  
}

