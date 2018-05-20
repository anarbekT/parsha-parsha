package com.example.webservice;

//Жасыл
import java.io.Serializable;

public class DB implements Serializable {
    
    private String webUrl;
    private String webSource;
    private String webTitle;
    private String webHeader;
    private String webParagraph;     
    
    public DB(String webUrl, String webSource, String webTitle, String webHeader, String webParagraph){
        //log("Class DB/Site indexed by url: "+webUrl);
        this.webUrl = webUrl;
        this.webSource = webSource;
        this.webTitle = webTitle;
        this.webHeader = webHeader;
        this.webParagraph = webParagraph;
    }
    
    public String getUrl(){
        return this.webUrl;
    }
    
    public String getTitle(){
        return this.webTitle;
    }
    
    public String getParagraph(){
        return this.webParagraph;
    }
    
    @Override
    public String toString() {
	return " webUrl: " + webUrl + "\n webTitle: " + webTitle + "\n webParagraph: " + webParagraph + "\n webHeader: " + webHeader + "\n webSource: ";// + webSource;
    }
    
    private static void log(String string){
        System.out.println(string);
    }
}
