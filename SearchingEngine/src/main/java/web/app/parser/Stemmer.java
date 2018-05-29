package web.app.parser;

import java.util.List;

import web.app.entities.QueryWord;

public interface Stemmer {

	String removeStopWords(String text);

	List<QueryWord> getWords(String text);

	List<QueryWord> removeSuffixes(List<QueryWord> words);

}
