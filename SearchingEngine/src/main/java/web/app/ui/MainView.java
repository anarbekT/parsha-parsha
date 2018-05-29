package web.app.ui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import web.app.entities.MyRequest;
import web.app.entities.PageInfo;
import web.app.entities.QueryWord;
import web.app.entities.WebPage;

public class MainView extends VerticalLayout implements View {
	static final long serialVersionUID = 1L;

	private Button btnSearch;
	private TextField textSearch;
	private Button linkOfGoogle;
	private Button linkOfYandex;
	private Label results;

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

	@SuppressWarnings("serial")
	private void bindEvent() {
		btnSearch.addClickListener(event -> doSearch());
		linkOfGoogle.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				String value = textSearch.getValue();
				String URL = "";
				if (value.trim().length() == 0) {
					URL = "http://google.kz";
				} else {
					URL = "http://google.kz/search?q=" + value;
				}
				Page.getCurrent().open(URL, "_blank");
			}
		});
		
		linkOfYandex.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				String value = textSearch.getValue();
				String URL = "";
				if (value.trim().length() == 0) {
					URL = "http://yandex.kz";
				} else {
					URL = "http://yandex.kz/search/?lr=162&text=" + value;
				}
				Page.getCurrent().open(URL, "_blank");
			}
		});
	}

	private void init() {
		lemmatizedRequests = new ArrayList<>();
		listOfResults = new ArrayList<>();
		presenter = new MainViewPresenter();
		initGrids();
	}

	private void initGrids() {
		// Create a grid for not Processed Text
		// resultGrid = new Grid<>("Results");
		// resultGrid.addColumn(WebPage::getWebUrl).setCaption("URL");
		// resultGrid.addColumn(WebPage::getWebTitle).setCaption("TITLE");

		// Create a grid for Processed Text
		rightGrid = new Grid<>("Lemmatized");
		rightGrid.setItems(lemmatizedRequests);
		rightGrid.addColumn(QueryWord::getTempStemOfWord).setCaption("Query");
	}

	public void generateUI() {

		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		Label header = new Label("ІЗДЕУ.KZ");

		header.addStyleName(ValoTheme.LABEL_H1);
		header.setSizeUndefined();
		addComponent(header);

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

		linkOfGoogle = new Button("Google");
		linkOfGoogle.setStyleName("link");

		linkOfYandex = new Button("Yandex");
		linkOfYandex.setStyleName("link");

		horLayout2.addComponent(linkOfGoogle);
		horLayout2.addComponent(linkOfYandex);

		addComponent(horLayout2);
		

		HorizontalLayout horLayoutForResult = new HorizontalLayout();
		horLayout.setSpacing(true);
		horLayout.setWidth("80%");
		results = new Label();
		horLayoutForResult.addComponent(results);
		
		addComponent(horLayoutForResult);

		GridLayout gridLayout = new GridLayout();
		gridLayout.setSpacing(true);

		// gridLayout.addComponent(leftGrid, 0, 0);
		gridLayout.addComponent(rightGrid);

		addComponent(gridLayout);

		verLayout = new VerticalLayout();
		verLayout.setSpacing(true);
		verLayout.setSizeFull();
		verLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addComponent(verLayout);
	}

	private void doSearch() {
		MyRequest request = getBean();
		request = presenter.analiseAndSendRequestTemp(request);
		rightGrid.setItems(request.getQueryWords());
//		doTextTemp(request.getQueryWords());
		showResultList(request.getWebPageOfResult());
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	private MyRequest getBean() {
		String requestedText = textSearch.getValue().trim();
		MyRequest request = new MyRequest();
		request.setRequest(requestedText);
		return request;
	}

	public void doTextTemp(List<QueryWord> lemmatizedRequests) {
		String text = "";
		for (QueryWord word : lemmatizedRequests) {
			text += word.getInitialWord() + " ";
		}
		TextField textField = new TextField();
		textField.setSizeFull();
		textField.setValue(text);
		addComponent(textField);
	}

	public void updateGridList() {
		// resultGrid.setItems(listOfResults);
		rightGrid.setItems(lemmatizedRequests);
	}

	private void showResultList(List<PageInfo> listOfResults) {
		setNumberOfResults(listOfResults.size());
		verLayout.removeAllComponents();
		for (PageInfo page : listOfResults) {
			Panel p = new Panel("<h3>" + page.getWebPage().getWebTitle() + "</h3>");
			HorizontalLayout horL = new HorizontalLayout();
			horL.setSpacing(true);
			horL.setSizeUndefined();

			String text = ""; // "<h3>"+page.getWebTitle()+"</h3>";
			text += "<a href=\"" + page.getWebPage().getWebUrl() + "\" target=\"_blank\">"
					+ page.getWebPage().getWebUrl() + "</a>"; // <a
			// href="https://www.w3schools.com">Visit
			// W3Schools.com!</a>
			text += "<p>" + page.getWebPage().getViewText() + "</p>";
			Label label = new Label(text, ContentMode.HTML);
			label.setSizeFull();
			horL.addComponent(label);
			p.setContent(horL);
			verLayout.addComponent(p);
		}
	}

	private void setNumberOfResults(int size) {
		results.setCaption("Results: "+size);
	}

}
