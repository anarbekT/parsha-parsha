package web.app.webservice;

import java.util.Scanner;

//Жасыл
public class Parts {
    public static void main(String[] args) {
        
        String[] urls = new String[]{
            "http://www.qamshy.kz/article/polyseyler-alemdegi-enh-iri-kyller-saytti-bughattadi.html"
            //"http://www.qamshy.kz/article/2018-zhili-qansha-bilim-granti-bolingeni-belgili-boldi.html",
            //"http://www.qamshy.kz/article/almatininh-2018-zhilghi-bywdzheti-naqtilandi.html"
        };
        
        
        //site ты indexing жасайтын класқа url ді бердім 
        for (int i = 0; i < urls.length; i++) {
            ScrapFromWeb scrapper = new ScrapFromWeb(urls[i]);
        }
        
        //String query  = "деген я site:https://stackoverflow.com/questions/11871520/how-can-i-read-input-from-the-console-using-the-scanner-class-in-java?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa";
        
        while(true){
        System.out.print("\n\n**********************************"
                + "*****************************************\n\n\nEnter your query: ");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();
        
        
        //Web service ты тексеруге query жіберілді
        WebService2 webService = new WebService2(query);
        //log(">>> Web service result/Json \n"+webService.result());
        log(webService.result());
        }
    }
    
    private static void log(String string){
        System.out.println(string);
    }
}
