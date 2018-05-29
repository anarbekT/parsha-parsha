package web.app.service;

import java.util.List;

import web.app.entities.PageInfo;
import web.app.entities.WebPage;

public interface WebPageService {
	
	WebPage getById(Long id);

	WebPage save(WebPage entity);

	WebPage update(WebPage entity);

	boolean delete(WebPage entity);

	List<PageInfo> findAll();
	
	List<WebPage> getWebPagesByUrl(String url);
	
	List<PageInfo> findWebPagesByUrl(String url);
	
	List<WebPage> findWebPagesByUrlAndKeyword(String[] URL_keyword);

	boolean isUrlExist(String url);
	
	List<WebPage> scrapFromWeb(String url);
}
