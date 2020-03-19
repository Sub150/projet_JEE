package ejb.entites;

import java.awt.List;
import java.util.ArrayList;
import java.util.Set;

@SuppressWarnings("serial")
public class QuestionRadio extends Question implements java.io.Serializable {
	private ArrayList<String> reponses = new ArrayList<String>();
	private String bonneReponse ;
	public QuestionRadio() {}
}
