package ejb.sessions;

import java.util.Collection;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import ejb.entites.Question;
import ejb.entites.QuestionCheckbox;
import ejb.entites.QuestionOuverte;
import ejb.entites.QuestionRadio;
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
	public Collection<Question> getQuestions(){
		String req = "from Question";
		Query q = em.createQuery(req);
		Collection<Question> result = (Collection<Question>)q.getResultList();
		return result;
	}
	

	@Override
	public void addReponse( int question, String reponse, boolean valide ) throws QuestionInconnueException, ReponseDejaAjouteeException {
		for ( Reponse r : this.getQuestion(question).getReponses())
		{
			if (r.getReponse()==reponse) 
				throw new ReponseDejaAjouteeException();
		}
		Reponse r  = new Reponse();
		r.setValide(valide);
		r.setReponse(reponse);
		this.getQuestion(question).getReponses().add(r);
		em.persist(r);
	}
	

	
	@Override
	public void addQuestion(String questionnaire, TypeSpec type, String intitule ) throws QuestionnaireInconnuException, QuestionDejaAjouteeException {
		for ( Question q : this.getQuestionnaire(questionnaire).getQuestions() )
		{
			if (q.getIntitule()==intitule) 
				throw new QuestionDejaAjouteeException();
			
		}
		Question q = null;
		switch(type) {
		case CHECKBOX:
			q = new QuestionCheckbox();
			break;
		case RADIO:
			q = new QuestionRadio();
			break;
		case OUVERTE:
			q = new QuestionOuverte();
			break;
		}
		q.setIntitule(intitule);
		this.getQuestionnaire(questionnaire).getQuestions().add(q);
		em.persist(q);
		
	}
		

		

}
