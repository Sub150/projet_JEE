package ejb.entites;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table (name="Question")
@Entity 
@SuppressWarnings("serial")
public abstract class Question implements java.io.Serializable {
	
	@GeneratedValue @Id private int num;
	private String intitule;
	
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
}
