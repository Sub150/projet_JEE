package ejb.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table (name="Question")
@Entity 
@SuppressWarnings("serial")
public class Question implements java.io.Serializable {
	@GeneratedValue @Id private int num;
	private String intitule;
	@ManyToOne private Questionnaire questionnaire;
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
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	} 
	
	public Question() {}
	
	
}
