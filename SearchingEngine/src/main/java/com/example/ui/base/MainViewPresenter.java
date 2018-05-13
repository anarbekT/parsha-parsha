package com.example.ui.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entities.MyRequest;
import com.example.service.MyRequestService;
import com.example.webservice.WebScrap;

public class MainViewPresenter extends AbstractBasePresenter{
	
    @Autowired
    private MyRequestService service;
    
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
//		String removeStopWords = stemmer.removeStopWords(requestedText);
//		MyRequest sendRequest = sendRequest(removeStopWords);
		return null;
	}
	
	public MyRequest analiseAndSendRequestTemp(String requestedText) {
		List<String> allPossibleOptions = service.getAllPossibleOptions(requestedText);
		MyRequest request = new MyRequest();
		request.setAllPossibleQuery(allPossibleOptions);
		return null;
	}
}
