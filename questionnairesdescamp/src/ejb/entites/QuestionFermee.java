package ejb.entites;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table (name="QuestionFermee")
@Entity 
@SuppressWarnings("serial")
public abstract class QuestionFermee implements java.io.Serializable {
	@GeneratedValue @Id private int num;
	private String intitule;
	@OneToMany(fetch=FetchType.EAGER) private Set<Reponse> reponses;

	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Set<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(Set<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	public QuestionFermee() {}
}
