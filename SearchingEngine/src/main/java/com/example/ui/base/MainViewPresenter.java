package com.example.ui.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entities.MyRequest;
import com.example.entities.QueryWord;
import com.example.entities.WebPage;
import com.example.service.MyRequestService;
import com.example.service.WebPageService;
import com.example.webservice.ScrapFromWeb;

public class MainViewPresenter extends AbstractBasePresenter{
	
    @Autowired
    private MyRequestService service;
    
//    private WebScrap webService = new WebScrap();
    
    public MainViewPresenter() {
        super();
        System.out.println(service.toString());
    }
    
    public MyRequest sendRequest(String request){
//    	int numberOfResult = webService.sendRequest(request);
//    	MyRequest entity = generateRequest(request, numberOfResult);
//    	saveRequest(entity);
    	return null;//saveRequest(entity);
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
		List<QueryWord> allPossibleQuery = service.getAllPossibleQuery(requestedText);
		doScrap();
		MyRequest request = new MyRequest();
		request.setAllPossibleQuery(allPossibleQuery);
		return request;
	}
	
	
	private void doScrap(){
		String[] urls = new String[]{
				"http://www.qamshy.kz/article/polyseyler-alemdegi-enh-iri-kyller-saytti-bughattadi.html"
				// "http://www.qamshy.kz/article/2018-zhili-qansha-bilim-granti-bolingeni-belgili-boldi.html",
				// "http://www.qamshy.kz/article/almatininh-2018-zhilghi-bywdzheti-naqtilandi.html"
		};

		// site ты indexing жасайтын класқа url ді бердім
		for (int i = 0; i < urls.length; i++) {
			ScrapFromWeb scrapper = new ScrapFromWeb(urls[i]);
		}
	}
}
