package com.example.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MyRequestDao;
import com.example.entities.MyRequest;
import com.example.entities.QueryWord;
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
	public List<QueryWord> getAllPossibleOptions(String mainRequest) {
		mainRequest = stemmer.removeStopWords(mainRequest);
		List<QueryWord> wordsArray = stemmer.getWordsArray(mainRequest);
		List<QueryWord> wordsWithOutSuffixes = stemmer.getWordsArray(mainRequest);
		List<QueryWord> generateAllPossibleVariants = stemmer.generateAllPossibleVariants(wordsArray);
		
		return generateAllPossibleVariants;
	}
	

}
