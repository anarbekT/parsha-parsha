package com.example;

import com.example.ui.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI; 

@SpringUI
@Theme("valo")
@UIScope
public class DemoUI extends UI {

    private static final long serialVersionUID = 1L;

    
    private Navigator navigator;
        
    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this,this);
        navigator.addView("", new MainView());
    }


    public Navigator getNavigator(){
        return navigator;
    }

}
