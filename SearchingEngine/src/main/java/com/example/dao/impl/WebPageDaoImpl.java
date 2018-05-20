package com.example.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.dao.WebPageDao;
import com.example.dao.generic.AbstractBaseDao;
import com.example.entities.WebPage;

@Repository
public class WebPageDaoImpl extends AbstractBaseDao<WebPage, Long> implements WebPageDao {
	
	public WebPageDaoImpl() {
		this.type = WebPage.class;
	}

	@Override
	public List<WebPage> getWebPagesByUrl(String url) {
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<WebPage> cq = cb.createQuery(WebPage.class);
		Root<Pet> pet = cq.from(Pet.class);
		cq.where(cb.equal(pet.get(Pet_.name), "a"))
		
		return null;
	}
}
