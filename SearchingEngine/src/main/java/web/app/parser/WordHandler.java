package web.app.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import web.app.entities.QueryWord;

public class WordHandler {

	private static final String BASE_DIR = "src\\main\\resources\\";

	private static final String[] vowelSounds = { "а", "ә", "о", "ө", "е", "э", "ы", "і", "и", "у", "ұ", "ү" };

	private static List<String> stopWordsList;
	private static final String STOP_WORDS_FILE = "stop_words.txt";
	private static List<String> zhiktikZhalgauList;
	private static final String ZHIKTIK_ZHALGAU_FILE = "zhiktik_zhalgaular.txt";
	private static List<String> septikZhalgauList;
	private static final String SEPTIK_ZHALGAU_FILE = "septik_zhalgaular.txt";
	private static List<String> koptikZhalgauList;
	private static final String KOPTIK_ZHALGAU_FILE = "koptik_zhalgaular.txt";
	private static List<String> taueldikZhalgauList;
	private static final String TAUELDIK_ZHALGAU_FILE = "taueldik_zhalgaular.txt";

	public WordHandler() {
		stopWordsList = initArrayList(STOP_WORDS_FILE);
		koptikZhalgauList = initArrayList(KOPTIK_ZHALGAU_FILE);
		zhiktikZhalgauList = initArrayList(ZHIKTIK_ZHALGAU_FILE);
		taueldikZhalgauList = initArrayList(TAUELDIK_ZHALGAU_FILE);
		septikZhalgauList = initArrayList(SEPTIK_ZHALGAU_FILE);
	}

	public List<String> initArrayList(String fileName) {
		List<String> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(BASE_DIR + fileName))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				list.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String removeStopWords(String text) {
		for (String stopWord : stopWordsList) {
			String regex = "\\s*\\b" + stopWord + "\\b";
			text = text.replaceAll(regex, "");
		}
		return text;
	}

	public String removeKoptikZhalgau(String word) {
		if(isLengthShorter(word)){
			return word;
		}
		for (String koptikZalgau : koptikZhalgauList) {
			String regex = koptikZalgau + "$";
			if (word.endsWith(koptikZalgau)) {
				word = word.replaceAll(regex, "");
				return word;
			}
		}
		return null;
	}

	public String removeZhiktikZhalgau(String word) {
		if(isLengthShorter(word)){
			return word;
		}
		for (String zhiktikZalgau : zhiktikZhalgauList) {
			String regex = zhiktikZalgau + "$";
			if (word.endsWith(zhiktikZalgau)) {
				word = word.replaceAll(regex, "");
				return word;
			}

		}
		return null;
	}

	public String removeTaueldikZhalgau(String word) {
		if(isLengthShorter(word)){
			return word;
		}
		for (String taueldikZalgau : taueldikZhalgauList) {
			String regex = taueldikZalgau + "$";
			if (word.endsWith(taueldikZalgau)) {
				word = word.replaceAll(regex, "");
				return word;
			}
		}
		return null;
	}

	public String removeSeptikZhalgau1(QueryWord word) {
		// if (text.length() <= 4) {
		// if(isEndsWithVowel(text)){
		// return text;
		// }
		// }
		String text = word.getInitialWord();
		boolean isZhanlgauFind = false;
		for (String septikZalgau : septikZhalgauList) {
			String regex = septikZalgau + "$";
			if (text.endsWith(septikZalgau)) {
				isZhanlgauFind = true;
				String tempStem = text.replaceAll(regex, "");
				word.getStemOfWord().add(tempStem);
			}
		}
		if (isZhanlgauFind) {
			return text;
		} else {
			return null;
		}
	}

	public String removeSeptikZhalgau(String word) {
		if(isLengthShorter(word)){
			return word;
		}
		for (String septikZalgau : septikZhalgauList) {
			String regex = septikZalgau + "$";
			if (word.endsWith(septikZalgau)) {
				word = word.replaceAll(regex, "");
				return word;
			}
		}
		return null;

	}

	private boolean isEndsWithVowel(String text) {
		for (String vowel : vowelSounds) {
			boolean endsWithVowel = text.endsWith(vowel);
			if (endsWithVowel) {
				return true;
			}
		}
		return false;
	}

	public boolean isEndsWithSeptik() {
		return false;
	}
	
	private boolean isLengthShorter(String word){
		if(word.length()<4){
			return true;
		}else{
			return false;
		}
	}

}
