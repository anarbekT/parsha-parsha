package com.example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
//	@Column(name = "SOURCE")
//	private String webSource;
	
	@Column(name = "TITLE")
	private String webTitle;
	
	@Column(name = "PARAGRAPH", length =65535,  columnDefinition = "text")
	private String webParagraph;

}
