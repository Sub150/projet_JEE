package ejb.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table (name="QuestionOuverte")
@Entity 
@SuppressWarnings("serial")
public class QuestionOuverte implements  java.io.Serializable {
	@GeneratedValue @Id private int num;
	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	String intitule;
	
	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public String getReponse() {
		return reponse;
	}


	public void setReponse(String reponse) {
		this.reponse = reponse;
	}


	String reponse;
	
	
	public QuestionOuverte() {}
	
}
