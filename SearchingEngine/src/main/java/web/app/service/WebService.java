package com.example.service;

import java.util.List;

import com.example.entities.WebPage;

public interface WebService {
	
	List<WebPage> sendRequest(String request);
	
}
