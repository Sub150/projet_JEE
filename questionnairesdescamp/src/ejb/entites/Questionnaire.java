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
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "questionnaire") Set <Question> questions ; 

	public Questionnaire() {}
}
