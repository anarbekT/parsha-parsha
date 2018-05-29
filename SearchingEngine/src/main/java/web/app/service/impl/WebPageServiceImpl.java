package web.app.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.app.dao.WebPageDao;
import web.app.entities.PageInfo;
import web.app.entities.WebPage;
import web.app.service.WebPageService;
import web.app.webservice.ScrapFromWeb;

@Service
@Transactional
public class WebPageServiceImpl implements WebPageService {

	@Autowired
	private WebPageDao dao;

	@Override
	public WebPage save(WebPage entity) {
		WebPage page = dao.save(entity);
		dao.sessionFlush();
		return page;
	}

	@Override
	public WebPage update(WebPage entity) {
		return dao.update(entity);
	}

	@Override
	public boolean delete(WebPage entity) {
		return dao.delete(entity);
	}

	@Override
	public List<PageInfo> findAll() {
		 List<WebPage> allWebPages = dao.findAll();
		 return webPageToPageInfo(allWebPages);
	}

	@Override
	public List<WebPage> getWebPagesByUrl(String url) {
		List<WebPage> webPagesByUrl = dao.getWebPagesByUrl(url);
		return webPagesByUrl;
	}

	@Override
	public WebPage getById(Long id) {
		return dao.get(id);
	}

	@Override
	public List<PageInfo> findWebPagesByUrl(String url) {
		List<WebPage> webPages = dao.findByUrl(url);
		if (webPages.isEmpty()) {
			webPages = scrapFromWeb(url);
		}
		return webPageToPageInfo(webPages);
	}

	private List<PageInfo> webPageToPageInfo(List<WebPage> webPages) {
		List<PageInfo> pageInfoList = new ArrayList<>();
		for (WebPage webPage : webPages) {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setWebPage(webPage);
			pageInfoList.add(pageInfo);
		}
		return pageInfoList;
	}

	@Override
	public List<WebPage> findWebPagesByUrlAndKeyword(String[] URL_keyword) {
		List<WebPage> webPages = null;
		return webPages;
	}

	@Override
	public boolean isUrlExist(String url) {
		List<WebPage> webPagesByUrl = dao.findByUrl(url);
		if (webPagesByUrl.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public List<WebPage> scrapFromWeb(String url) {
		List<WebPage> webPages = new ArrayList<>();
		try {
			Document website = (Document) Jsoup.connect(url).ignoreContentType(true).get();
			Elements title = website.getElementsByTag("title");
			Elements header = website.getElementsByTag("h1");
			Elements body = website.getElementsByTag("body");
			Elements paragraphs = website.getElementsByTag("p");
			String webTitle = title.text();
			String webUrl = url;
			String webParagraph = paragraphs.text();
			// DB class ол базада html қалай сақталады сондай форматта
			WebPage webPage = new WebPage();
			webPage.setWebUrl(webUrl);
			webPage.setWebTitle(webTitle);
			webPage.setWebParagraph(webParagraph);

			webPages.add(webPage);

		} catch (IOException ex) {
			Logger.getLogger(ScrapFromWeb.class.getName()).log(Level.SEVERE, null, ex);
		}
		return webPages;
	}

}
