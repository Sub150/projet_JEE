package ejb.sessions;

import java.util.Collection;


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
	
	public Question getQuestion(int id) throws QuestionInconnueException {
		Question q = (Question) em.find(Question.class, id);
		if (q==null) 
			throw new QuestionInconnueException();
		return q;
	}
	

	@Override
	public void addReponse( String reponse ) throws QuestionInconnueException, ReponseDejaAjouteeException {
		for ( Reponse r : this.getQuestion(question).getReponses() )
		{
			if (r.getReponse()==reponse) 
				throw new ReponseDejaAjouteeException();
			
		}
		Reponse r  = new Reponse();
		r.setReponse(reponse);
		this.getQuestion(question).getReponses().add(r);
		em.persist(r);
	}
	
	@Override
	public void addBonneReponse(int question, String reponse ) throws QuestionInconnueException, ReponseDejaAjouteeException, ReponseInconnueException {
		Reponse rep = null;
		for ( Reponse r : this.getQuestion(question).getReponses() )
		{
			if (r.getReponse()==reponse) 
				rep = r;
		}
		if ( rep == null )
			throw new ReponseInconnueException();
		this.getQuestion(question).getBonnesReponses().add(rep);
		em.persist(rep);
	}
	
	@Override
	public void addQuestion(String questionnaire, int id, TypeSpec type, String intitule ) throws QuestionnaireInconnuException, QuestionDejaAjouteeException {
		for ( Question q : this.getQuestionnaire(questionnaire).getQuestions() )
		{
			if (q.getNum()==id) 
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
		q.setNum(id);
		q.setIntitule(intitule);
		q.setReponses(null);
		q.setBonnesReponses(null);
		em.persist(q);
		
	}
		

		

}
