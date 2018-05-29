package web.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.app.dao.MyRequestDao;
import web.app.entities.MyRequest;
import web.app.entities.QueryWord;
import web.app.parser.Stemmer;
import web.app.service.MyRequestService;

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
	public List<QueryWord> getAllQueryWordsFromRequest(String mainRequest) {
		mainRequest = stemmer.removeStopWords(mainRequest);
		List<QueryWord> wordsArray = stemmer.getWords(mainRequest);
		List<QueryWord> wordsWithOutSuffixes = stemmer.removeSuffixes(wordsArray);
		
		return wordsWithOutSuffixes;
	}
	

}
