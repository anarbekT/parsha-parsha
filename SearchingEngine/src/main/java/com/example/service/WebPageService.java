package com.example.service;

import java.util.List;

import com.example.entities.WebPage;

public interface WebPageService {

	WebPage save(WebPage entity);

	WebPage update(WebPage entity);

	boolean delete(WebPage entity);

	List<WebPage> findAll();
	
	List<WebPage> getWepPagesByUrl(String url);
	
}
