package web.app.webservice.presenter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import web.app.entities.WebPage;
import web.app.service.WebPageService;
import web.app.ui.base.AbstractBasePresenter;
import web.app.webservice.PageInfo;
import web.app.webservice.ScrapFromWeb;

public class SearchingPresenter extends AbstractBasePresenter {

	@Autowired
	private WebPageService webService;

	public SearchingPresenter() {
		super();
	}

	public void saveWebPagesList(WebPage page) {
			webService.save(page);
	}

	public ArrayList<PageInfo> getDB(String specialUrl) {
		ArrayList<PageInfo> pageInfo = new ArrayList<>();

		if (specialUrl != null) {
			List<WebPage> webPagesByUrl = webService.getWebPagesByUrl(specialUrl);
			if (!webPagesByUrl.isEmpty() || webPagesByUrl != null) {
				for (WebPage page : webPagesByUrl) {
					pageInfo.add(new PageInfo(page.getWebUrl(), page.getWebTitle(), page.getWebParagraph()));
				}
			}
			if (pageInfo.isEmpty()) {
				ScrapFromWeb scrapper = new ScrapFromWeb(specialUrl);
				getDB(specialUrl);
			}
		} else {
			List<WebPage> webPagesByUrl = webService.findAll();
			if (!webPagesByUrl.isEmpty() || webPagesByUrl != null) {
				for (WebPage page : webPagesByUrl) {
					pageInfo.add(new PageInfo(page.getWebUrl(), page.getWebTitle(), page.getWebParagraph()));
				}
			}
		}
		return pageInfo;
	}

}
