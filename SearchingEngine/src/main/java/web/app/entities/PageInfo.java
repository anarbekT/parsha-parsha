package web.app.entities;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class PageInfo implements Comparable<PageInfo>, Serializable{

	private WebPage webPage;
	
	
	private int realQueryHitTitleNum = 0;
	private int extraQueryHitTitleNum = 0;
	private int realQueryHitParagraphNum = 0;
	private int extraQueryHitParagraphNum = 0;
	
    public ArrayList realHitWords = new ArrayList();
    public ArrayList additionalHitWords = new ArrayList();
	
	public PageInfo(){
		
	}

	public PageInfo(String url, String title, String paragraph) {
		webPage  = new WebPage();
		webPage.setWebUrl(url);
		webPage.setWebTitle(title);
		webPage.setWebParagraph(paragraph);
	}

	public void addRealTitleNum(int num) {
		this.realQueryHitTitleNum = this.realQueryHitTitleNum + num;
	}

	public void addAdditionalTitleNum(int num) {
		this.extraQueryHitTitleNum = this.extraQueryHitTitleNum + num;
	}

	public void addRealParagraphNum(int num) {
		this.realQueryHitParagraphNum = this.realQueryHitParagraphNum + num;
	}

	public void addAdditionalParagraphNum(int num) {
		this.extraQueryHitParagraphNum = this.extraQueryHitParagraphNum + num;
	}

	public int getRealValue() {
		return realQueryHitTitleNum + realQueryHitParagraphNum;
	}

	public int getAdditionalValue() {
		return extraQueryHitTitleNum + extraQueryHitParagraphNum;
	}

	public String toString() {
		return "Class to string" + " title: " + webPage.getWebTitle() + "\n paragraph: " + webPage.getWebParagraph();
	}

	@Override
	public int compareTo(PageInfo o) {
		int compare = Integer.compare(getRealValue(), o.getRealValue());
		if (compare == 0) {
			compare = Integer.compare(getAdditionalValue(), o.getAdditionalValue());
		}
		return compare;
	}

	private static void log(String string) {
		System.out.println(string);
	}
}
