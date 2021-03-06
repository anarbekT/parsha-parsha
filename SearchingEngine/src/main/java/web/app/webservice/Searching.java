package web.app.webservice;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import web.app.entities.PageInfo;
import web.app.entities.WebPage;
import web.app.webservice.presenter.SearchingPresenter;

//Searching деген класс query ді аладыда соған қатысты базадан іздейді
public class Searching {
	private SearchingPresenter presenter;

	private String specialUrl; // site:url деп жазғанда urlді теңестіреді
	private ArrayList<PageInfo> pageInfo;// іздемес бұрын базадан осыған аламыз

	// Уақытша
	ArrayList<String> realQueryWords = new ArrayList<String>();
	ArrayList<String> additionalQueryWords = new ArrayList<String>();

	// Уақытша бұл Анарбек беретін екі Array
	public void setQueryWords(ArrayList<String> realQueryWords, ArrayList<String> additionalQueryWords) {
		this.realQueryWords = realQueryWords;
		this.additionalQueryWords = additionalQueryWords;
		// for (int i = 0; i < 20; i++) {
		// realQueryWords.add("Әлем"); //0
		// realQueryWords.add("әлемнің"); //4 parag
		// realQueryWords.add("әлемдегі");// 1 title 1 parag
		// realQueryWords.add("Елбасының"); //1 parag Елбасының
		// additionalQueryWords.add("тартылды"); //1 parag
		// additionalQueryWords.add("бұғаттауға"); //2 parag
		// additionalQueryWords.add("берген"); //1 parag
		// additionalQueryWords.add("мәслихатының"); // 1 parag
		/*
		 * realQueryWords.add("қанша"); realQueryWords.add("білім");
		 * realQueryWords.add("гранты"); realQueryWords.add("бөлінгені");
		 * realQueryWords.add("жыл"); realQueryWords.add("Әлемдегі");
		 * realQueryWords.add("киллер"); realQueryWords.add("Полицей");
		 * realQueryWords.add("ең"); realQueryWords.add("Ірі");
		 * realQueryWords.add("алматының"); realQueryWords.add("бюджеті");
		 * realQueryWords.add("нақты"); additionalQueryWords.add("ғылым");
		 * additionalQueryWords.add("білімі");
		 * additionalQueryWords.add("грант");
		 * additionalQueryWords.add("бөлінсе");
		 */

	}

	public Searching() {
		presenter = new SearchingPresenter();
		// doSearch(query);
	}

	public void doSearch(String query) {
		if (query.toLowerCase().contains("site:")) {
			String regex = "(site:)(\\S+)";
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(query.toLowerCase());
			if (matcher.find()) {
				this.specialUrl = matcher.group(2);
			}
		}
		log("SpecialUrl: " + specialUrl);
		// Анарбектің екі Array-і
		// setQueryWords();
		pageInfo = presenter.getDB(specialUrl);
		// 160 query words from 10 000 html 1600000 loop Search 119.973 sec /
		// build 5 minutes 27 seconds with json print
		// 160 query words from 1 000 html 160000 loop Search
		// 10.955(8.828)(6.837 егер spe url) sec / build 33 seconds with json
		// print
		// 160 query words from 100 html 16000 loop Search 1.346(1.059) sec /
		// build 6 seconds with json print6

		for (String currentWord : realQueryWords) {
			for (PageInfo currentPage : pageInfo) {
				String regax = currentWord.toLowerCase() + "\\S+";// +"\\s+";
				Pattern pattern = Pattern.compile(regax);
				Matcher matcherTitle = pattern.matcher(currentPage.getWebPage().getWebTitle().toLowerCase());
				Matcher matcherParagraph = pattern.matcher(currentPage.getWebPage().getWebParagraph().toLowerCase());
				Boolean find = false;
				while (matcherTitle.find()) {
					currentPage.addRealTitleNum(1);
					currentPage.realHitWords.add(currentWord + " - " + matcherTitle.group() + " - RealFromTitle");

					find = true;
				}
				while (matcherParagraph.find()) {
					currentPage.addRealParagraphNum(1);
					currentPage.realHitWords
							.add(currentWord + " - " + matcherParagraph.group() + " - RealFromParagraph");
					find = true;
				}
			}
		}

		for (String currentWord : additionalQueryWords) {
			for (PageInfo currentPage : pageInfo) {
				String regax = currentWord.toLowerCase() + "\\s+";
				Pattern pattern = Pattern.compile(regax);
				Matcher matcherTitle = pattern.matcher(currentPage.getWebPage().getWebTitle().toLowerCase());
				Matcher matcherParagraph = pattern.matcher(currentPage.getWebPage().getWebParagraph().toLowerCase());
				Boolean find = false;
				while (matcherTitle.find()) {
					currentPage.addAdditionalTitleNum(1);
					currentPage.additionalHitWords.add(currentWord + " - " + matcherTitle.group() + " - AddFromTitle");
					find = true;
				}
				while (matcherParagraph.find()) {
					currentPage.addAdditionalParagraphNum(1);
					currentPage.additionalHitWords
							.add(currentWord + " - " + matcherParagraph.group() + " - AddFromParagraph");
					find = true;
				}
			}
		}
		if (specialUrl == null) {
			pageInfo.removeIf(obj -> obj.getRealValue() == 0 && obj.getAdditionalValue() == 0);
		}
	}

	public ArrayList<PageInfo> result() {
		return pageInfo;
	}

	private static void log(String string) {
		System.out.println(string);
	}
}
