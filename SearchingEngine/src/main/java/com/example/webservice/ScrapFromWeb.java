package com.example.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Жасыл бірақ db connect
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entities.WebPage;
import com.example.service.WebPageService;
import com.example.webservice.presenter.SearchingPresenter;

public class ScrapFromWeb {
	
    @Autowired
    private WebPageService webService;
    
	private SearchingPresenter presenter;
    
    Document website = null;
    private String webUrl;
    private String webSource;
    private String webTitle;
    private String webHeader;
    private String webParagraph;
    //Уақытша база себебі оның орнында SQL база тұр соған кетет бірден 
    public  ArrayList<WebPage> dbList = new ArrayList<WebPage>();
    
    //index жасайтын method Jsoup ты пайдаланып
    public void doScrapping(String url){
        try {
            website = (Document) Jsoup.connect(url).ignoreContentType(true).get();
            Elements title = website.getElementsByTag("title");
            Elements header = website.getElementsByTag("h1");
            Elements body = website.getElementsByTag("body");
            Elements paragraphs = website.getElementsByTag("p");
            this.webTitle = title.text();
            this.webUrl = url;
            this.webParagraph = paragraphs.text();
            this.webSource = website.toString();
            this.webHeader = header.text();
            //DB class ол базада html қалай сақталады сондай форматта
            WebPage webPage = new WebPage();
//            this.webUrl,this.webSource,this.webTitle,this.webHeader,this.webParagraph
            webPage.setWebUrl(webUrl);
//            webPage.setWebSource(webSource);
            webPage.setWebTitle(webTitle);
            webPage.setWebParagraph(webParagraph);
            
            //базаға кетті
            dbList.add(webPage);
            
        } catch (IOException ex) {
            Logger.getLogger(ScrapFromWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public ArrayList<WebPage> getListOfWebPages(){
		return dbList;    	
    }

	//object құрылғанда бірден index жасайтын method қа url жібереді
    public ScrapFromWeb(String url) {
        doScrapping(url);
        presenter.saveWebPagesList(dbList);
    }
    
	

    
    private static void log(String string){
        System.out.println(string);
    }
}
