package ejb.entites;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table (name="Question")
@Entity 
@SuppressWarnings("serial")
public abstract class Question implements java.io.Serializable {
	@GeneratedValue @Id private int num;
	private String intitule;
	@OneToMany(fetch=FetchType.EAGER) private Set<Reponse> reponses = new TreeSet<Reponse>();
	@OneToMany(fetch=FetchType.EAGER) private Set<Reponse> bonnesReponses = new TreeSet<Reponse>();
	
	public int getNum() {
		System.out.println("test");
		
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

	public Question() {}
	public Set<Reponse> getBonnesReponses() {
		return bonnesReponses;
	}
	public void setBonnesReponses(Set<Reponse> bonnesReponses) {
		this.bonnesReponses = bonnesReponses;
	}
	public Set<Reponse> getReponses() {
		return reponses;
	}
	public void setReponses(Set<Reponse> reponses) {
		this.reponses = reponses;
	}
	
	
}
