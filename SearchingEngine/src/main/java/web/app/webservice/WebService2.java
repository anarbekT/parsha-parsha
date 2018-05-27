package com.example.webservice;

//Жасыл бірақ бәрін көрсетет
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class WebService2 {
    private String responseJson;
    public WebService2(String query){
        long startTime2 = System.currentTimeMillis();
        //іздейтін класқа сұрақ жіберілді
        Searching searching = new Searching();
        //Тапқан нәтижені реттеуге жібердік
        Ordering ordering = new Ordering(searching);
        //Реттелген нәтижені ArrayList түрінде алып Json форматқа ауыстырдық 
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        responseJson = gson.toJson(ordering.result());
        //Filtering 
        //responseJson = gson.toJson(ordering.result().stream().filter(e -> e.url ).collect(Collectors.toList()));
        long endTime2 = System.currentTimeMillis();
        float totalTime2 = (endTime2 - startTime2);
        log("responseJson = gson.toJson(ordering.result()); time: "+Float.toString(totalTime2/1000));
    }
    
    public String result(){
        if(responseJson == null){
            return "Not responding!";
        }else return responseJson;
    }
    
    private static void log(String string){
        System.out.println(string);
    }
}
