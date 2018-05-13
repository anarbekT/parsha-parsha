package com.example.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MyRequestDao;
import com.example.entities.MyRequest;
import com.example.parser.Stemmer;
import com.example.service.MyRequestService;

@Service
@Transactional
public class MyRequestServiceImpl implements MyRequestService {

	@Autowired
	private MyRequestDao dao;
	
    @Autowired
    private Stemmer stemmer;

	@Override
	public MyRequest save(MyRequest entity) {
		return dao.save(entity);
	}

	@Override
	public MyRequest update(MyRequest entity) {
		return dao.update(entity);
	}

	@Override
	public boolean delete(MyRequest entity) {
		return dao.delete(entity);
	}

	@Override
	public List<MyRequest> findAll() {
		return dao.findAll();
	}

	@Override
	public List<String> getAllPossibleOptions(String mainRequest) {
		mainRequest = stemmer.removeStopWords(mainRequest);
		List<String> wordsArray = stemmer.getWordsArray(mainRequest);
		List<String> generateAllPossibleVariants = stemmer.generateAllPossibleVariants(wordsArray);
		return generateAllPossibleVariants;
	} 
	

}
