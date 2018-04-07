package com.example.dao.impl;

import org.springframework.stereotype.Repository;

import com.example.dao.MyRequestDao;
import com.example.dao.generic.AbstractBaseDao;
import com.example.entities.MyRequest;

@Repository
public class MyRequestDaoImpl extends AbstractBaseDao<MyRequest, Long> implements MyRequestDao {

	public MyRequestDaoImpl() {
		this.type = MyRequest.class;
	}
}
