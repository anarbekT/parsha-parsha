package com.example.dao.impl;

import org.springframework.stereotype.Repository;

import com.example.dao.QueryWordDao;
import com.example.dao.generic.AbstractBaseDao;
import com.example.entities.QueryWord;

@Repository
public class QueryWordDaoImpl extends AbstractBaseDao<QueryWord, Long> implements QueryWordDao {

}
