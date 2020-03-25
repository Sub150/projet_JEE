package ejb.sessions;

import java.util.Collection;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import ejb.entites.Question;
import ejb.entites.QuestionFermee;
import ejb.entites.QuestionOuverte;
import ejb.entites.Questionnaire;
import ejb.entites.Reponse;

@Stateless
public class ServiceQuestionnairesBean implements ServiceQuestionnairesRemote, ServiceQuestionnairesLocal {

	@PersistenceContext(unitName="questionnaire")
	protected EntityManager em;
	public ServiceQuestionnairesBean(){}
	@Override
	public void creerQuestionnaire(String nom) throws QuestionnaireDejaCreeException {
		try {
			this.getQuestionnaire(nom);
			throw new QuestionnaireDejaCreeException();
		} catch ( QuestionnaireInconnuException e) {
			Questionnaire q = new Questionnaire();
			q.setNom(nom);
			em.persist(q);
		}
		
	}

	@Override
	public Questionnaire getQuestionnaire(String nom) throws QuestionnaireInconnuException {
		Questionnaire q = (Questionnaire) em.find(Questionnaire.class, nom);
		if (q==null) 
			throw new QuestionnaireInconnuException();
		return q;
	}

	@Override
	public Collection<Questionnaire> getQuestionnaires() {
		String req = "from Questionnaire";
		Query q = em.createQuery(req);
		Collection<Questionnaire> result = (Collection<Questionnaire>)q.getResultList();
		return result;
	}
	
	@Override
	public Collection<Reponse> getReponses() {
		String req = "from Reponse";
		Query q = em.createQuery(req);
		Collection<Reponse> result = (Collection<Reponse>)q.getResultList();
		return result;
	}
	
	@Override
	public Question getQuestion(int id) throws QuestionInconnueException {
		Question q = (Question) em.find(Question.class, id);
		if (q==null) 
			throw new QuestionInconnueException();
		return q;
	}
	
	@Override
	public QuestionOuverte getQuestionOuverte(int id) throws QuestionInconnueException{
		return (QuestionOuverte)getQuestion(id);
	}
	
	@Override
	public QuestionFermee getQuestionFermee(int id) throws QuestionInconnueException{
		return (QuestionFermee)getQuestion(id);
	}
	
	@Override
	public Collection<Question> getQuestions(){
		String req = "from Question";
		Query q = em.createQuery(req);
		Collection<Question> result = (Collection<Question>)q.getResultList();
		return result;
	}
	

	@Override
	public void addReponse( int question, String reponse, boolean valide ) throws QuestionInconnueException, ReponseDejaAjouteeException {
		Reponse rep = new Reponse();
		if (this.getQuestion(question) instanceof QuestionOuverte) {
			if (this.getQuestionOuverte(question) != null ) {
				throw new ReponseDejaAjouteeException();
			}
			rep.setValide(true);
			rep.setReponse(reponse);
			this.getQuestionOuverte(question).setReponse(rep);
			em.persist(rep);
			System.out.println("persist ouverte lol");
		}
		else if ( this.getQuestion(question) instanceof QuestionFermee ) {
			for ( Reponse r : this.getQuestionFermee(question).getReponses()) {
				if ( r.getReponse().equals(reponse)) {
					throw new ReponseDejaAjouteeException();
				}
			}		
			rep.setValide(valide);
			rep.setReponse(reponse);
			this.getQuestionFermee(question).getReponses().add(rep);
			em.persist(rep);
			System.out.println("persist fermee lol");
		
			
		}
		else System.out.println("erreur lol");
	}
	

	
	@Override
	public void addQuestion(String questionnaire, TypeSpec type, String intitule, boolean multiple) throws QuestionnaireInconnuException, QuestionDejaAjouteeException {
		for ( Question q : this.getQuestionnaire(questionnaire).getQuestions() )
		{
			if (q.getIntitule()==intitule) 
				throw new QuestionDejaAjouteeException();
			
		}
		switch(type) {
		case FERMEE:
			QuestionFermee q1 = new QuestionFermee();
			q1.setMultiple(multiple);
			q1.setIntitule(intitule);
			this.getQuestionnaire(questionnaire).getQuestions().add(q1);
			em.persist(q1);
			break;
		case OUVERTE:
			QuestionOuverte q2 = new QuestionOuverte();
			q2.setIntitule(intitule);
			this.getQuestionnaire(questionnaire).getQuestions().add(q2);
			em.persist(q2);
			break;
		}
	}
		

		

}
