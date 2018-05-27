package web.app.webservice;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PageInfo implements Comparable<PageInfo> {
	public String url;
	public String title;
	public String paragraph;

	private int realTitleNum = 0;
	private int additionalTitleNum = 0;
	private int realParagraphNum = 0;
	private int additionalParagraphNum = 0;

	public ArrayList realHitWords = new ArrayList();
	public ArrayList additionalHitWords = new ArrayList();

	public PageInfo(String url, String title, String paragraph) {
		this.url = url;
		this.title = title;
		this.paragraph = paragraph;
	}

	public void addRealTitleNum(int num) {
		this.realTitleNum = this.realTitleNum + num;
	}

	public void addAdditionalTitleNum(int num) {
		this.additionalTitleNum = this.additionalTitleNum + num;
	}

	public void addRealParagraphNum(int num) {
		this.realParagraphNum = this.realParagraphNum + num;
	}

	public void addAdditionalParagraphNum(int num) {
		this.additionalParagraphNum = this.additionalParagraphNum + num;
	}

	public int getRealValue() {
		return realTitleNum + realParagraphNum;
	}

	public int getAdditionalValue() {
		return additionalTitleNum + additionalParagraphNum;
	}

	public String toString() {
		return "Class to string" + " title: " + this.title + "\n paragraph: " + this.paragraph;
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
