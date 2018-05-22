package com.example.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.entities.MyRequest;
import com.example.entities.QueryWord;
import com.example.entities.WebPage;
import com.example.ui.base.MainViewPresenter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout implements View {
	static final long serialVersionUID = 1L;

	private Button btnSearch;
	private TextField textSearch;

	private Grid<WebPage> resultGrid;
	private Grid<QueryWord> rightGrid;
	List<QueryWord> lemmatizedRequests;
	List<WebPage> listOfResults;
	
	private VerticalLayout verLayout;

	private MainViewPresenter presenter;

	public MainView() {
		init();
		generateUI();
		bindEvent();
	}

	private void bindEvent() {
		btnSearch.addClickListener(event -> doSearch());
	}

	private void init() {
		lemmatizedRequests = new ArrayList<>();
		listOfResults = new ArrayList<>();
		presenter = new MainViewPresenter();
		initGrids();
	}

	private void initGrids() {
		// Create a grid for not Processed Text
		resultGrid = new Grid<>("Results");
		resultGrid.addColumn(WebPage::getWebUrl).setCaption("URL");
		resultGrid.addColumn(WebPage::getWebTitle).setCaption("TITLE");

		// Create a grid for Processed Text
		// rightGrid = new Grid<>("Lemmatized");
		// rightGrid.setItems(lemmatizedRequests);
		// rightGrid.addColumn(QueryWord::getWord).setCaption("Query");
	}

	public void generateUI() {

		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		HorizontalLayout horLayout = new HorizontalLayout();
		horLayout.setSpacing(true);
		horLayout.setWidth("80%");

		textSearch = new TextField("");
		textSearch.setWidth("100%");

		btnSearch = new Button("Search");

		btnSearch.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSearch.setIcon(VaadinIcons.SEARCH);

		textSearch.focus();
		btnSearch.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		horLayout.addComponentsAndExpand(textSearch);
		horLayout.addComponent(btnSearch);
		horLayout.setComponentAlignment(btnSearch, Alignment.BOTTOM_RIGHT);

		addComponent(horLayout);

		HorizontalLayout horLayout2 = new HorizontalLayout();
		horLayout2.setSpacing(true);
		horLayout2.setWidth("80%");

		Link linkOfGoogle = new Link("Google", new ExternalResource("http://google.kz/"));
		linkOfGoogle.setTargetName("_blank");

		Link linkOfYandex = new Link("Yandex", new ExternalResource("http://yandex.kz/"));
		linkOfYandex.setTargetName("_blank");

		horLayout2.addComponent(linkOfGoogle);
		horLayout2.addComponent(linkOfYandex);

		addComponent(horLayout2);
//
//		GridLayout gridLayout = new GridLayout();
//		gridLayout.setSpacing(true);
//
//		gridLayout.addComponent(resultGrid);
//		// gridLayout.addComponent(rightGrid, 1, 0);
//
//		addComponent(gridLayout);
		
		verLayout = new VerticalLayout();
		verLayout.setSpacing(true);
		verLayout.setSizeFull();
		verLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addComponent(verLayout);


	}

	private void doSearch() {
		String requestedText = textSearch.getValue().trim();

		// presenter.testMethod();
		MyRequest request = presenter.analiseAndSendRequestTemp(requestedText);
		// rightGrid.setItems(request.getAllPossibleQuery());
		showResultList(request.getListOfResults());
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	public void updateGridList() {
		// resultGrid.setItems(listOfResults);
		rightGrid.setItems(lemmatizedRequests);
	}

	private void showResultList(List<WebPage> listOfResults) {
		
		for (WebPage page : listOfResults) {
			String text = "<h3>"+page.getWebTitle()+"</h3>";
			text+="<a href=\""+page.getWebUrl()+"\" target=\"_blank\">"+page.getWebUrl()+"</a>"; //<a href="https://www.w3schools.com">Visit W3Schools.com!</a>
			text+="<p>"+page.getWebParagraph()+"</p>";
			Label label = new Label(text, ContentMode.HTML);
			label.setWidth("100px");
			verLayout.addComponent(label);
		}
		
	}

}
