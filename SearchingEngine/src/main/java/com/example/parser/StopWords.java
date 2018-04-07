package com.example.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StopWords {

	private static final String FILENAME = "T:\\CSSE-141k\\meridianSystems\\video\\task1\\demo_1\\src\\main\\resources\\stop_words.txt";
	private static List<String> stopWordsList;

	public StopWords() {
		stopWordsList = new ArrayList<>();
		initStopWordsListArray();
	}

	public void initStopWordsListArray() {

		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				stopWordsList.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
//
//	public static void main(String arg[]) {
//		StopWords o = new StopWords();
//		o.initStopWordsListArray();
//		
//		System.out.println(o.removeStopWords("Отан үшін, жер үшінге бәрін де"));
//	}

	public String removeStopWords(String text) {
		for (String stopWord : stopWordsList) {
			String regex = "\\s*\\b" + stopWord + "\\b";
			text = text.replaceAll(regex, "");
		}
		return text;

	}

}
