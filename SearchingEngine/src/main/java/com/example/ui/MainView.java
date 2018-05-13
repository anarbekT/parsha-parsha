package com.example.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.entities.MyRequest;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainView extends VerticalLayout implements View {
	static final long serialVersionUID = 1L;

	private Button btnSearch;
	private TextField textSearch;

	private Grid<MyRequest> leftGrid;
	private Grid<MyRequest> rightGrid;
	List<MyRequest> lemmatizedRequests;
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
		leftGrid = new Grid<>("Non-lemmatized");
		leftGrid.setItems(nonLemmatizedRequests);
		leftGrid.addColumn(MyRequest::getRequest).setCaption("Request");
		leftGrid.addColumn(MyRequest::getNumberOfResult).setCaption("Number Of Result");

		// Create a grid for Processed Text
		rightGrid = new Grid<>("Lemmatized");
		rightGrid.setItems(lemmatizedRequests);
		rightGrid.addColumn(MyRequest::getRequest).setCaption("Request");
		rightGrid.addColumn(MyRequest::getNumberOfResult).setCaption("Number Of Result");
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

		GridLayout gridLayout = new GridLayout(2, 1);
		gridLayout.setSpacing(true);

		gridLayout.addComponent(leftGrid, 0, 0);
		gridLayout.addComponent(rightGrid, 1, 0);

		addComponent(gridLayout);

	}

	private void doSearch() {
		String requestedText = textSearch.getValue().trim();
		MyRequest request = presenter.sendRequest(requestedText);
		nonLemmatizedRequests.add(request);
		
		request = presenter.analiseAndSendRequest(requestedText);
		lemmatizedRequests.add(request);
		
		updateGridList();
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public void updateGridList() {
		leftGrid.setItems(nonLemmatizedRequests);
		rightGrid.setItems(lemmatizedRequests);
	}

}
