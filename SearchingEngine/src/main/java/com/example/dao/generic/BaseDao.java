package com.example.dao.generic;

import java.io.Serializable;
import java.util.List;

public interface BaseDao <T extends Serializable, K extends Serializable>{
	public T get(K id);
	public T save(T entity);
	public T update(T entity);
	public boolean delete(T entity); 
	List<T> findAll();
}
