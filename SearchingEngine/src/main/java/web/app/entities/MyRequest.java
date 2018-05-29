package web.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class MyRequest implements Serializable {

	private String request;

	private int numberOfResults;

	private List<QueryWord> queryWords;

	private List<PageInfo> webPageOfResult;
	
	public ArrayList<String> getRealQueries(){
		ArrayList<String> realQueryWords = new ArrayList<>();
		for(QueryWord queryWord: queryWords){
			realQueryWords.add(queryWord.getInitialWord());
		}
		return realQueryWords;
	}
	
	public ArrayList<String> getExtraQueries(){
		ArrayList<String> extraQueryWords = new ArrayList<>();
		for(QueryWord queryWord: queryWords){
			//TODO CHANGE
			extraQueryWords.add(queryWord.getTempStemOfWord());
		}
		return extraQueryWords;
	}

}
