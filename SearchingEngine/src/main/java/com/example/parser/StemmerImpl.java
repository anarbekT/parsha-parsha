package com.example.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class StemmerImpl implements Stemmer{

	@Override
	public String removeStopWords(String text) {
		StopWords stopWords = new StopWords();
				
		String withOutStopWords = stopWords.removeStopWords(text);
		return withOutStopWords;
	}

	@Override
	public List<String> getWordsArray(String text) {
		List<String> words = new ArrayList<>();
		Scanner scan=new Scanner(text);
		while(scan.hasNext()){
			String word = scan.next();
			words.add(word);
		}
		return words;
	}

	@Override
	public List<String> generateAllPossibleVariants(List<String> words) {
		ArrayList<String> allOptions = new ArrayList<>();
	    for(int i=1; i<=words.size(); i++){
	    	ArrayList<String> result = getAllLists(words, i);
	    	allOptions.addAll(result);
	    }		 
		return allOptions;
	}

	private ArrayList<String> getAllLists(List<String> words, int numOfWords) {
	    //initialize our returned list
	    ArrayList<String> allList = new ArrayList<>();

	    //lists of length 1 are just the original elements
	    if(numOfWords == 1) return  (ArrayList<String>) words; 
	    else {
	        //the recursion--get all lists of length 3, length 2, all the way up to 1
	    	ArrayList<String> allSublists = getAllLists(words, numOfWords - 1);

	        for(int i = 0; i < words.size(); i++){
	            for(int j = 0; j < allSublists.size(); j++){
	                //add the newly appended combination to the list

	            	if(words.get(i).equals(allSublists.get(j))  || allSublists.get(j).contains(words.get(i)))
	            	{
	            		continue;
	            	}
	            	allList.add(words.get(i) + allSublists.get(j));
	            }
	        }
	        return allList;
	    }
	}
	
	
	

}
