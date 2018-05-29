package web.app.service;

import java.util.List;

import web.app.entities.MyRequest;
import web.app.entities.QueryWord;

public interface MyRequestService {

	MyRequest save(MyRequest entity);

	MyRequest update(MyRequest entity);

	boolean delete(MyRequest entity);

	List<MyRequest> findAll();
	
	List<QueryWord> getAllQueryWordsFromRequest(String mainRequest);

}
