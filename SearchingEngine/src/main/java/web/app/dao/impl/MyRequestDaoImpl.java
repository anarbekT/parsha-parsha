package web.app.dao.impl;

import org.springframework.stereotype.Repository;

import web.app.dao.MyRequestDao;
import web.app.dao.generic.AbstractBaseDao;
import web.app.entities.MyRequest;

@Repository
public class MyRequestDaoImpl extends AbstractBaseDao<MyRequest, Long> implements MyRequestDao {

	public MyRequestDaoImpl() {
		this.type = MyRequest.class;
	}
}
