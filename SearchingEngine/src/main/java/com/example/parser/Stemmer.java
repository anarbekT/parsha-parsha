package com.example.parser;

import java.util.List;

import com.example.entities.QueryWord;

public interface Stemmer {
	
	String removeStopWords(String text);
	
	List<QueryWord> getWordsArray(String text);
	
	List<QueryWord> removeSuffixes(List<QueryWord>	words);
	
	List<QueryWord> generateAllPossibleVariants(List<QueryWord> words);
}
