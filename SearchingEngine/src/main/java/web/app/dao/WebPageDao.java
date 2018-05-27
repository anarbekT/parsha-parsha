package web.app.dao;

import java.util.List;

import web.app.dao.generic.BaseDao;
import web.app.entities.WebPage;

public interface WebPageDao extends BaseDao<WebPage, Long>{

	List<WebPage> getWebPagesByUrl(String url);
	
	List<WebPage> findByUrl(String url);
	
	List<WebPage> findByUrlAndKeyword(String url, String keyword);
	
	void sessionFlush();

}
