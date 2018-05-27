package web.app.dao.impl;

import org.springframework.stereotype.Repository;

import web.app.dao.QueryWordDao;
import web.app.dao.generic.AbstractBaseDao;
import web.app.entities.QueryWord;

@Repository
public class QueryWordDaoImpl extends AbstractBaseDao<QueryWord, Long> implements QueryWordDao {

}
