package web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.app.entities.MyRequest;
import web.app.entities.PageInfo;
import web.app.service.WebPageService;
import web.app.service.WebService;

@Service
@Transactional
public class WebServiceImpl implements WebService {

	@Autowired
	private WebPageService webPageService;

	@Override
	public MyRequest sendRequest(MyRequest request) {
		List<PageInfo> listOfPageInfo = new ArrayList<>();
		String[] URL_Keyword = getURL(request.getRequest());
		// Scrap URL goes here
		boolean withURL;
		if (URL_Keyword[1] != null) {
			withURL=true;
			listOfPageInfo = webPageService.findWebPagesByUrl(URL_Keyword[1]);
		} else {
			withURL=false;
			listOfPageInfo = webPageService.findAll();
		}

		request.setWebPageOfResult(listOfPageInfo);
		request.setWebPageOfResult(doSearch(request,withURL));
		return request;
	}

	public List<PageInfo> doSearch(MyRequest request, boolean withURL) {

		ArrayList<String> realQueryWords = request.getRealQueries();
		ArrayList<String> additionalQueryWords = request.getExtraQueries();
		List<PageInfo> pageInfo = request.getWebPageOfResult();

		for (String currentWord : realQueryWords) {
            for (PageInfo currentPage : pageInfo) {
                String regax = "((?:\\S+\\s)?\\S*(?:\\S+\\s)?\\S*)(\\b" + currentWord.toLowerCase() + "\\b)(\\S*(?:\\s\\S+)?\\S*(?:\\s\\S+)?)";
                Pattern pattern = Pattern.compile(regax);
                Matcher matcherTitle = pattern.matcher(currentPage.getWebPage().getWebTitle().toLowerCase());
                Matcher matcherParagraph = pattern.matcher(currentPage.getWebPage().getWebParagraph().toLowerCase());
                while (matcherTitle.find()) {
                    currentPage.addRealTitleNum(1);
                    currentPage.realHitWords.add(matcherTitle.group(2));
                }
                while (matcherParagraph.find()) {
                    currentPage.addRealParagraphNum(1);
                    currentPage.realHitWords.add(matcherParagraph.group(2));
                    String tempText= currentPage.getWebPage().getWebParagraph().substring(matcherParagraph.start(1), matcherParagraph.end(1)) + "<b>" + currentPage.getWebPage().getWebParagraph().substring(matcherParagraph.start(2), matcherParagraph.end(2)) + "</b>" + currentPage.getWebPage().getWebParagraph().substring(matcherParagraph.start(3), matcherParagraph.end(3)) + " ... ";
                    currentPage.getWebPage().addToViewText(tempText);
                }
            }
        }

        for (String currentWord : additionalQueryWords) {
            for (PageInfo currentPage : pageInfo) {
                String regax = "((?:\\S+\\s)?\\S*(?:\\S+\\s)?\\S*)(" + currentWord.toLowerCase() + ")(\\S+)(\\S*(?:\\s\\S+)?\\S*(?:\\s\\S+)?)";
                Pattern pattern = Pattern.compile(regax);
                Matcher matcherTitle = pattern.matcher(currentPage.getWebPage().getWebTitle().toLowerCase());
                Matcher matcherParagraph = pattern.matcher(currentPage.getWebPage().getWebParagraph().toLowerCase());
                while (matcherTitle.find()) {
                    if (!currentPage.realHitWords.contains(matcherTitle.group(2) + matcherTitle.group(3))) {
                        currentPage.addAdditionalTitleNum(1);
                        currentPage.additionalHitWords.add(matcherTitle.group(2) + matcherTitle.group(3));
                    }
                }
                while (matcherParagraph.find()) {
                    if (!currentPage.realHitWords.contains(matcherParagraph.group(2) + matcherParagraph.group(3))) {
                        currentPage.addAdditionalParagraphNum(1);
                        currentPage.additionalHitWords.add(matcherParagraph.group(2) + matcherParagraph.group(3));
                        String tempText= currentPage.getWebPage().getWebParagraph().substring(matcherParagraph.start(1), matcherParagraph.end(1)) + "<b>" + currentPage.getWebPage().getWebParagraph().substring(matcherParagraph.start(2), matcherParagraph.end(2)) + "</b>" + currentPage.getWebPage().getWebParagraph().substring(matcherParagraph.start(3), matcherParagraph.end(3)) + currentPage.getWebPage().getWebParagraph().substring(matcherParagraph.start(4), matcherParagraph.end(4)) + " ... ";
                        currentPage.getWebPage().addToViewText(tempText);
                    }
                }
                //Тексеру үшін
//                log(currentPage.viewText);
            }
        }
		if (!withURL) {
			pageInfo.removeIf(obj -> obj.getRealValue() == 0 && obj.getAdditionalValue() == 0);
		}
		return pageInfo;
	}

	private String[] getURL(String request) {
		request = request.toLowerCase();
		String[] URL_Keyword = null;
		if (request.contains("site:")) {
			URL_Keyword = request.split("site:");
		} else {
			URL_Keyword = new String[2];
			URL_Keyword[0] = request;
			URL_Keyword[1] = null;
		}

		System.out.println("SpecialUrl: " + URL_Keyword[1]);
		return URL_Keyword;
	}

}
