package com.example.parser;

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

}
