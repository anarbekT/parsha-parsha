package web.app.entities;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class QueryWord implements Serializable {

	private String initialWord;
	private ArrayList<String> stemOfWord;
	private String tempStemOfWord;

	public QueryWord() {
		stemOfWord = new ArrayList<String>();
	}

	public String getTempStemOfWord(){
		return stemOfWord.get(0);
//		return initialWord;
	}
}
