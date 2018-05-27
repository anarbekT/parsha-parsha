package com.example.service;

import java.util.List;

import com.example.entities.WebPage;

public interface WebPageService {
	
	WebPage getById(Long id);

	WebPage save(WebPage entity);

	WebPage update(WebPage entity);

	boolean delete(WebPage entity);

	List<WebPage> findAll();
	
	List<WebPage> getWebPagesByUrl(String url);
	
	List<WebPage> findWebPagesByUrl(String url);
	
	List<WebPage> findWebPagesByUrlAndKeyword(String url, String keyword);

	boolean isUrlExist(String url);
}
