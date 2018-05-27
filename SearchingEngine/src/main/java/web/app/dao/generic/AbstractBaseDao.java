package web.app.dao.generic;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseDao <T extends Serializable, K extends Serializable> implements BaseDao<T	, K> {

	@Autowired
	private SessionFactory sf;
	protected   Class<T> type; 
	
	@Override
	public T get(K id) {
		Session session = getCurrentSession();
		T entity = session.get(type, id);
		return entity;
	}

	@Override
	public List<T> findAll() {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(type);
		List<T> result = criteria.list();
		return result;
	}
 
	@Override
	public T save(T entity) {
		Session session = getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		Session session = getCurrentSession();
		session.update(entity);
		return entity;
	}

	@Override
	public boolean delete(T entity) {
		Session session = getCurrentSession();
		session.delete(entity);
		
		return true;
	}
	 
	
	protected Session getCurrentSession(){
		return sf.getCurrentSession();
	}

}
