package ejb.entites;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class QuestionFermee extends Question implements  java.io.Serializable {
	@OneToMany(fetch=FetchType.EAGER) private Set<Reponse> reponses;
	private boolean multiple; 
	
	public Set<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(Set<Reponse> reponses) {
		this.reponses = reponses;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	public QuestionFermee() {}
	
}
