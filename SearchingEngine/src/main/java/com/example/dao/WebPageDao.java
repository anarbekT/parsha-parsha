package com.example.dao;

import java.util.List;

import com.example.dao.generic.BaseDao;
import com.example.entities.WebPage;

public interface WebPageDao extends BaseDao<WebPage, Long>{

	List<WebPage> getWebPagesByUrl(String url);

}
