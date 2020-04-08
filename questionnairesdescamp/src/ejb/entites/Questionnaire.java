package ejb.entites;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Table (name="Questionnaire")
@Entity
public class Questionnaire implements java.io.Serializable{
	@Id private String nom;
	@OneToMany(fetch=FetchType.EAGER) private Set<QuestionFermee> questionsFermees ; 
	@OneToMany(fetch=FetchType.EAGER) private Set<QuestionOuverte> questionsOuvertes ; 
	
	
	
	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public Set<QuestionOuverte> getQuestionsOuvertes() {
		return questionsOuvertes;
	}
	
	public Set<QuestionFermee> getQuestionsFermees() {
		return questionsFermees;
	}



	public void setQuestionsOuvertes(Set<QuestionOuverte> questions) {
		this.questionsOuvertes = questions;
	}

	public void setQuestionsFermees(Set<QuestionFermee> questions) {
		this.questionsFermees = questions;
	}

	

	public void addQuestionOuverte(QuestionOuverte question) {
		questionsOuvertes.add(question);
	}
	
	public void addQuestionFermee(QuestionFermee question) {
		questionsFermees.add(question);
	}

	public Questionnaire() {}
}
