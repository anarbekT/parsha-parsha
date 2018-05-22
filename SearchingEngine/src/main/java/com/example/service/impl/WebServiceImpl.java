package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.WebPage;
import com.example.service.WebPageService;
import com.example.service.WebService;
import com.example.webservice.PageInfo;
import com.example.webservice.ScrapFromWeb;
import com.example.webservice.Searching;

@Service
@Transactional
public class WebServiceImpl implements WebService {

	@Autowired
	private WebPageService webPageService;

	private List<WebPage> webPagesByURL;

	@Override
	public List<WebPage> sendRequest(String request) {
		String[] URL_Keyword = getURL(request);
		// Scrap URL goes here
		boolean urlExist = webPageService.isUrlExist(URL_Keyword[1]);
		if (!urlExist) {
			new ScrapFromWeb(URL_Keyword[1]);
		}
		return doSearch(URL_Keyword);
	}

	private List<WebPage> doSearch(String[] URL_Keyword) { // 0: keyword; 1: url
		// search logic
		 return webPageService.findWebPagesByUrlAndKeyword(URL_Keyword[1], URL_Keyword[0]);
	}

	private String[] getURL(String request) {
		request = request.toLowerCase();
		String[] URL_Keyword = null;
		if (request.contains("site:")) {
			URL_Keyword = request.split("site:");
		} else {
			URL_Keyword = new String[2];
			URL_Keyword[0] = request;
			URL_Keyword[1] = null;
		}

		System.out.println("SpecialUrl: " + URL_Keyword[1]);
		return URL_Keyword;
	}

}
