package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "WEB_PAGE")
public class WebPage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "URL")
	private String webUrl;

	// @Column(name = "SOURCE")
	// private String webSource;

	@Column(name = "TITLE")
	private String webTitle;

	@Column(name = "PARAGRAPH", length = 65535, columnDefinition = "text")
	private String webParagraph;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "REQUEST_ID")
	// private MyRequest myRequest;

	@Transient
	private int realTitleNum = 0;
	@Transient
	private int additionalTitleNum = 0;
	@Transient
	private int realParagraphNum = 0;
	@Transient
	private int additionalParagraphNum = 0;

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
}
