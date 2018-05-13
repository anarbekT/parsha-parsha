package com.example.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StopWords {

	private static final String BASE_DIR = "src\\main\\resources\\";
	private static List<String> stopWordsList;
	private static final String STOP_WORDS_FILE = "stop_words.txt";
	private static List<String> zhiktikZhalgauList;
	private static final String ZHIKTIK_ZHALGAU_FILE = "zhiktik_zhalgaular.txt";

	public StopWords() {
		stopWordsList = initStopWordsListArray(STOP_WORDS_FILE);
		zhiktikZhalgauList = initStopWordsListArray(ZHIKTIK_ZHALGAU_FILE);
	}

	public List<String> initStopWordsListArray(String fileName) {
		List<String> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(BASE_DIR + fileName))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				list.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}
	//
	// public static void main(String arg[]) {
	// StopWords o = new StopWords();
	// o.initStopWordsListArray();
	//
	// System.out.println(o.removeStopWords("Отан үшін, жер үшінге бәрін де"));
	// }

	public String removeStopWords(String text) {
		for (String stopWord : stopWordsList) {
			String regex = "\\s*\\b" + stopWord + "\\b";
			text = text.replaceAll(regex, "");
		}
		return text;
	}
	
	public String removeZhiktikZhalgau(String text){
		for (String zhiktikZalgau : zhiktikZhalgauList) {
			String regex = zhiktikZalgau +"$";
			text = text.replaceAll(regex, "");
		}
		return text;
	}

}
