package web.app;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

import web.app.ui.MainView;

@SuppressWarnings("serial")
@SpringUI
@Theme("valo")
@UIScope
public class WebAppUI extends UI {

	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this, this);
		navigator.addView("", new MainView());
	}

	public Navigator getNavigator() {
		return navigator;
	}

}
