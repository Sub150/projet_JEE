package ejb.entites;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class QuestionOuverte extends Question implements  java.io.Serializable {
	@OneToOne(fetch=FetchType.EAGER) private Reponse reponse;
	
	public Reponse getReponse() {
		return reponse;
	}
	public void setReponse(Reponse reponse) {
		this.reponse = reponse;
	}
	public QuestionOuverte() {}
	
}
