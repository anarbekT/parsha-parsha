package com.example.webservice;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebScrap {

	Document website = null;
	String json = null;

	public int getNumberOfResult(String url, String host) {

		try {
			website = (Document) Jsoup.connect(url).ignoreContentType(true).get();
			json = website.toString();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println(json);
		int indexResult = json.indexOf("totalResults") + 16;
		String result = "";
		for (int i = indexResult; i < 1000000; i++) {
			if (json.charAt(i) != '"') {
				result += json.charAt(i);
			} else
				break;
		}

		System.out.println(result);
		return Integer.parseInt(result);
	}

	public int sendRequest(String request) {
		
		String url = "https://www.googleapis.com/customsearch/v1?key=AIzaSyCH5mC71Y0ERjO2z"
				+ "8Kv3rC6cMt_u2f1DIE&cx=017807609244620165454:jyeojq8hzjg&q=" + request;
		int numberOfResult = getNumberOfResult(url, "");
		return numberOfResult;
	}
}
