package web.app.webservice;

// Жасыл
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import web.app.entities.PageInfo;

public class Ordering {
    Searching searching;
    public Ordering(Searching searching){
        this.searching = searching;
        Collections.sort(searching.result());
        Collections.reverse(searching.result());
    }
    
    public ArrayList<PageInfo> result(){
        return searching.result();
    }
    
    private static void log(String string){
        System.out.println(string);
    }
}
