package ejb.entites;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class QuestionCheckbox extends Question implements java.io.Serializable {
	private ArrayList<String> reponses = new ArrayList<String>();
	private ArrayList<String> bonnesReponses = new ArrayList<String>(); 
	
	
}
