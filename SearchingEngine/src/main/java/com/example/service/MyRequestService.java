package com.example.service;

import java.util.List;

import com.example.entities.MyRequest;

public interface MyRequestService {

	MyRequest save(MyRequest entity);

	MyRequest update(MyRequest entity);

	boolean delete(MyRequest entity);

	List<MyRequest> findAll();
	
	List<String> getAllPossibleOptions(String mainRequest);

}
