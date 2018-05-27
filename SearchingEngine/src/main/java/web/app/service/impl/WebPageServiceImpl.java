package com.example.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.WebPageDao;
import com.example.entities.WebPage;
import com.example.service.WebPageService;

@Service
@Transactional
public class WebPageServiceImpl implements WebPageService {

	@Autowired
	private WebPageDao dao;

	@Override
	public WebPage save(WebPage entity) {
		WebPage page = dao.save(entity);
		dao.sessionFlush();
		return page;
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
	public List<WebPage> getWebPagesByUrl(String url) {
		List<WebPage> webPagesByUrl = dao.getWebPagesByUrl(url);
		return webPagesByUrl;
	}

	@Override
	public WebPage getById(Long id) {
		return dao.get(id);
	}

	@Override
	public List<WebPage> findWebPagesByUrl(String url) {
		List<WebPage> webPagesByUrl = dao.findByUrl(url);
		return webPagesByUrl;
	}

	@Override
	public List<WebPage> findWebPagesByUrlAndKeyword(String url, String keyword) {
		List<WebPage> webPages = dao.findByUrlAndKeyword(url, keyword);
		return webPages;
	}

	@Override
	public boolean isUrlExist(String url) {
		List<WebPage> webPagesByUrl = dao.findByUrl(url);
		if (webPagesByUrl.isEmpty()) {
			return false;
		}
		return true;
	}

}
