package ejb.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table (name="Reponse")
@SuppressWarnings("serial")
@Entity 
public class Reponse implements java.io.Serializable {
	@GeneratedValue @Id int num;
	public String reponse;
	
	
	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Reponse() {}
}
