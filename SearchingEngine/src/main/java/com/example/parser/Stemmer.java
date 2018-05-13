package com.example.parser;

import java.util.List;

public interface Stemmer {
	
	String removeStopWords(String text);
	
	List<String> getWordsArray(String text);
	
	List<String> generateAllPossibleVariants(List<String> words);
}
