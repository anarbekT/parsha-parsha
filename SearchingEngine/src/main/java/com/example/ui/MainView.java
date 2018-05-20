package com.example.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.entities.MyRequest;
import com.example.entities.QueryWord;
import com.example.ui.base.MainViewPresenter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout implements View {
	static final long serialVersionUID = 1L;

	private Button btnSearch;
	private TextField textSearch;
	
	private Panel resultArea;

	private Grid<MyRequest> resultGrid;
	private Grid<QueryWord> rightGrid;
//	List<MyRequest> lemmatizedRequests;
	List<QueryWord> lemmatizedRequests;
	List<MyRequest> nonLemmatizedRequests;

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
		nonLemmatizedRequests = new ArrayList<>();
		presenter = new MainViewPresenter();
		initGrids();
	}

	private void initGrids() {
		// Create a grid for not Processed Text
//		resultGrid = new Grid<>("Non-lemmatized");
//		resultGrid.setItems(nonLemmatizedRequests);
//		resultGrid.addColumn(MyRequest::getRequest).setCaption("Request");
//		resultGrid.addColumn(MyRequest::getNumberOfResult).setCaption("Number Of Result");

		// Create a grid for Processed Text
		rightGrid = new Grid<>("Lemmatized");
		rightGrid.setItems(lemmatizedRequests);
		rightGrid.addColumn(QueryWord::getWord).setCaption("Query");
//		rightGrid.addColumn(MyRequest::getRequest).setCaption("Request");
//		rightGrid.addColumn(MyRequest::getNumberOfResult).setCaption("Number Of Result");
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
		
//		resultArea = new Panel("Result: 11");
//		
//		VerticalLayout verLayout = new VerticalLayout();
//		verLayout.setWidth("500px");
//		verLayout.setSpacing(true);
//		verLayout.addComponent(new Label("Demo1", ContentMode.HTML));
//		Label l = new Label("<hr>", ContentMode.HTML);
//		l.setSizeFull();
//		verLayout.addComponentsAndExpand(l);
//		verLayout.addComponent(new Label("Demo2", ContentMode.HTML));
//
//		resultArea.setContent(verLayout);
		
		horLayout.addComponentsAndExpand(textSearch);
		horLayout.addComponent(btnSearch);
		horLayout.setComponentAlignment(btnSearch, Alignment.BOTTOM_RIGHT);

		addComponent(horLayout);
//		addComponent(resultArea);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.setSpacing(true);

//		gridLayout.addComponent(leftGrid, 0, 0);
		gridLayout.addComponent(rightGrid);

		addComponent(gridLayout);

	}

	private void doSearch() {
		String requestedText = textSearch.getValue().trim();
		
		
		MyRequest request = presenter.analiseAndSendRequestTemp(requestedText);
		rightGrid.setItems(request.getAllPossibleQuery());

	}


	@Override
	public void enter(ViewChangeEvent event) {
	}

	public void updateGridList() {
		resultGrid.setItems(nonLemmatizedRequests);
		rightGrid.setItems(lemmatizedRequests);
	}

}
