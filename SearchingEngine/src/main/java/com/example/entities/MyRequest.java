package com.example.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MY_REQUEST")
@SuppressWarnings("serial")
public class MyRequest implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REQUEST_ID")
	private Long id;

	@Column(name = "REQUEST_NAME", length = 200)
	private String request;
	
	@Column(name = "NUMBER_OF_RESULT", length = 200)
	private int numberOfResult;
	
	@OneToMany(mappedBy = "myRequest")
	private List<QueryWord> allPossibleQuery;


	
}
