package ejb.sessions;


import java.util.Collection;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import ejb.entites.QuestionFermee;
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
	
/*	@Override
	public Collection<Reponse> getReponses() {
		String req = "from Reponse";
		Query q = em.createQuery(req);
		Collection<Reponse> result = (Collection<Reponse>)q.getResultList();
		return result;
	} */
	
	@Override
	public QuestionFermee getQuestionFermee(int id) throws QuestionInconnueException {
		QuestionFermee q = (QuestionFermee) em.find(QuestionFermee.class, id);
		if (q==null) 
			throw new QuestionInconnueException();
		return q;
	}
	
	@Override 
	public QuestionOuverte getQuestionOuverte(int id) throws QuestionInconnueException {
		QuestionOuverte q = (QuestionOuverte) em.find(QuestionOuverte.class, id);
		if (q==null) 
			throw new QuestionInconnueException();
		return q;
	}
	
	@Override
	public Collection<QuestionFermee> getQuestionsFermees(){
		String req = "from Question";
		Query q = em.createQuery(req);
		Collection<QuestionFermee> result = (Collection<QuestionFermee>)q.getResultList();
		return result;
	}
	

	@Override
	public void addReponseFermee( int question, String reponse, boolean valide ) throws QuestionInconnueException, ReponseDejaAjouteeException, UneReponseValideParQuesitonRadioException {
		for ( Reponse r : this.getQuestionFermee(question).getReponses())
		{
			if (r.getReponse()==reponse) 
				throw new ReponseDejaAjouteeException();
		}
		Reponse r  = new Reponse();
		if ( (this.getQuestionFermee(question) instanceof QuestionRadio) ) {
			r.setValide(valide);
			for (Reponse rep : this.getQuestionFermee(question).getReponses()) 
				if (valide && rep.isValide())
					throw new UneReponseValideParQuesitonRadioException();
			
		}
		else {
		r.setValide(valide);
		}
		r.setReponse(reponse);
		this.getQuestionFermee(question).getReponses().add(r);
		em.persist(r);
	}
	
	@Override 
	public void addQuestionOuverte(String questionnaire, String intitule, String reponse) throws QuestionnaireInconnuException, QuestionDejaAjouteeException {
		for ( QuestionOuverte q : this.getQuestionnaire(questionnaire).getQuestionsOuvertes() )
		{
			if (q.getIntitule().equals(intitule)) 
				throw new QuestionDejaAjouteeException();
		}
		QuestionOuverte q = new QuestionOuverte();
		q.setIntitule(intitule);
		q.setReponse(reponse);
		this.getQuestionnaire(questionnaire).getQuestionsOuvertes().add(q);
		em.persist(q);
	}
	
	@Override
	public void addQuestionFermee(String questionnaire, TypeSpec type, String intitule ) throws QuestionnaireInconnuException, QuestionDejaAjouteeException {
		for ( QuestionFermee q : this.getQuestionnaire(questionnaire).getQuestionsFermees() )
		{
			if (q.getIntitule().equals(intitule)) 
				throw new QuestionDejaAjouteeException();
			
		}
		QuestionFermee q = null;
		switch(type) {
		case CHECKBOX:
			q = new QuestionCheckbox();
			break;
		case RADIO:
			q = new QuestionRadio();
			break;
		}
		q.setIntitule(intitule);
		this.getQuestionnaire(questionnaire).getQuestionsFermees().add(q);
		em.persist(q);
		
	}
	
/*	@Override 
	public void addQuestionnaire(String questionnaire) throws QuestionnaireDejaCreeException {
		for (Questionnaire q : this.getQuestionnaires()) {
			if (q.getNom().equals(questionnaire)) {
				throw new QuestionnaireDejaCreeException();
			}
		}
		Questionnaire q = new Questionnaire();
		q.setNom(questionnaire);
		em.persist(q);
	}
	*/
	@Override 
	public boolean testReponseOuverte(int num, String reponse) throws QuestionInconnueException {
		QuestionOuverte q = this.getQuestionOuverte(num);

		return q.getReponse().equals(reponse);
		
	}
	
	@Override
	public boolean testReponseFermee(int num, String[] reponses) throws QuestionInconnueException {
		QuestionFermee q = this.getQuestionFermee(num);
		int cpt = 0;
		for (String r : reponses) {
			for ( Reponse rp : q.getReponses()) {
				if ( r.equals(rp.getReponse()) && !(rp.isValide()) ) {
					return false;
				}
				else if ( r.equals(rp.getReponse()) && (rp.isValide()) ) {
					cpt ++;
				}
			}
		}
		int nbRep=0;
		for (Reponse rp : q.getReponses()) {
			if (rp.isValide())
				nbRep++;
		}
		return (nbRep==cpt);
	}

		

}
