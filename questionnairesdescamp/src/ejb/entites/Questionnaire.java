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
	@OneToMany(fetch=FetchType.EAGER) Set <Question> questions ; 
	
	
	
	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public Set<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}


	public void addQuestion(Question question) {
		questions.add(question);
	}

	public Questionnaire() {}
}
