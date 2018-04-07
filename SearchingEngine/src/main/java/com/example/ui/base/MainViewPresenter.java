package com.example.ui.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entities.MyRequest;
import com.example.parser.Stemmer;
import com.example.service.MyRequestService;
import com.example.webservice.WebScrap;

public class MainViewPresenter extends AbstractBasePresenter{
	
    @Autowired
    private MyRequestService service;
    
    @Autowired
    private Stemmer stemmer;
    
    private WebScrap webService = new WebScrap();
    
    public MainViewPresenter() {
        super();         
        System.out.println(service.toString());
    }
    
    public MyRequest sendRequest(String request){
    	int numberOfResult = webService.sendRequest(request);
    	MyRequest entity = generateRequest(request, numberOfResult);
    	saveRequest(entity);
    	return saveRequest(entity);
    }
    
    private MyRequest generateRequest(String request, int numberOfResult){
    	
    	MyRequest entity = new MyRequest();
    	entity.setNumberOfResult(numberOfResult);
    	entity.setRequest(request);
    	
    	return entity;
    }
    
    public MyRequest saveRequest(MyRequest entity){
    	return service.save(entity);
    }

	public MyRequest analiseAndSendRequest(String requestedText) {
		String removeStopWords = stemmer.removeStopWords(requestedText);
		MyRequest sendRequest = sendRequest(removeStopWords);
		return sendRequest;
	}
}
