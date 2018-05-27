package web.app.service;

import java.util.List;

import web.app.entities.WebPage;

public interface WebService {
	
	List<WebPage> sendRequest(String request);
	
}
