package com.example.webservice.presenter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entities.WebPage;
import com.example.service.WebPageService;
import com.example.ui.base.AbstractBasePresenter;
import com.example.webservice.PageInfo;
import com.example.webservice.ScrapFromWeb;

public class SearchingPresenter extends AbstractBasePresenter{
	
    @Autowired
    private WebPageService webService;
	
	public SearchingPresenter(){
		super();
	}
	
	
	public void saveWebPagesList(ArrayList<WebPage> dbList){
		for(WebPage page:dbList){
			webService.save(page);
		}
	}
	
	  public ArrayList<PageInfo> getDB(String specialUrl){
		  ArrayList<PageInfo> pageInfo = new ArrayList<>();
		  
	        if(specialUrl!=null){
	            for (WebPage currentDb: ScrapFromWeb.dbList) {
	                if(currentDb.getWebUrl().contains(specialUrl)){
	                    pageInfo.add(new PageInfo(currentDb.getWebUrl(), currentDb.getWebTitle(), currentDb.getWebParagraph()));
	                }
	            }
	            if(pageInfo.isEmpty()){
	                ScrapFromWeb scrapper = new ScrapFromWeb(specialUrl);
	                getDB(specialUrl);
	            }
	        }else{
	        for (WebPage currentDb: ScrapFromWeb.dbList) {
	                pageInfo.add(new PageInfo(currentDb.getWebUrl(), currentDb.getWebTitle(), currentDb.getWebParagraph()));
	            }
	        }
	    }
	

}
