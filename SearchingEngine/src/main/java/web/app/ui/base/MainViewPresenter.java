package com.example.ui.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entities.MyRequest;
import com.example.entities.QueryWord;
import com.example.entities.WebPage;
import com.example.service.MyRequestService;
import com.example.service.WebPageService;
import com.example.service.WebService;
import com.example.webservice.PageInfo;

public class MainViewPresenter extends AbstractBasePresenter {

	@Autowired
	private MyRequestService requestService;
	
	@Autowired
	private WebPageService webPageService;

	@Autowired
	private WebService webService;

	public MainViewPresenter() {
		super();
		System.out.println(requestService.toString());
	}

	public MyRequest saveRequest(MyRequest entity) {
		return requestService.save(entity);
	}

	public MyRequest analiseAndSendRequestTemp(String requestedText) {
		MyRequest request = new MyRequest();
		List<QueryWord> allPossibleQuery = requestService.getAllPossibleQuery(requestedText);
		request.setAllPossibleQuery(allPossibleQuery);
		
		List<WebPage> listOfWebPages = webService.sendRequest(requestedText);
		request.setListOfResults(listOfWebPages);
		
		return request;
	}

	public void testMethod() {
		
		MyRequest req =  new MyRequest();
		req.setNumberOfResult(213);
		req.setRequest("asdasd");
		List<QueryWord> allPossibleQuery = new ArrayList<>();
		QueryWord q = new QueryWord();
		q.setWord("asdasd");
		allPossibleQuery.add(q);
		req.setAllPossibleQuery(allPossibleQuery );
		requestService.save(req);
		
		WebPage entity = new WebPage();
		entity.setWebParagraph("TESt");
		entity.setWebTitle("TEstdf");
		entity.setWebUrl("sfdasgsad");
		webPageService.save(entity );
	}

}
