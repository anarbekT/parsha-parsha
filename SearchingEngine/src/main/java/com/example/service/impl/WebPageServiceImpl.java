package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.WebPageDao;
import com.example.entities.WebPage;
import com.example.service.WebPageService;
import com.example.webservice.PageInfo;

@Service
@Transactional
public class WebPageServiceImpl implements WebPageService{

	@Autowired
	private WebPageDao dao;
	
	@Override
	public WebPage save(WebPage entity) {
		if(entity==null){
			return null;
		}
			return dao.save(entity);
	}

	@Override
	public WebPage update(WebPage entity) {
		return dao.update(entity);
	}

	@Override
	public boolean delete(WebPage entity) {
		return dao.delete(entity);
	}

	@Override
	public List<WebPage> findAll() {
		return dao.findAll();
	}

	@Override
	public List<WebPage> getWepPagesByUrl(String url) {
		ArrayList<WebPage> pageInfo = new ArrayList<>();
		dao.getWebPagesByUrl(url);
		return null;
	}

}
