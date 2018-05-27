package web.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "QUERY_WORD")
public class QueryWord implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QUERY_ID")
	private Long id;

	@Column(name = "WORD")
	private String word;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUEST_ID")
	private MyRequest myRequest;

}
