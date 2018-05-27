package web.app.ui.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import web.app.entities.MyRequest;
import web.app.entities.QueryWord;
import web.app.entities.WebPage;
import web.app.service.MyRequestService;
import web.app.service.WebPageService;
import web.app.service.WebService;
import web.app.webservice.PageInfo;

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
		
//		List<WebPage> listOfWebPages = webService.sendRequest(requestedText);
//		request.setListOfResults(listOfWebPages);
		
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
