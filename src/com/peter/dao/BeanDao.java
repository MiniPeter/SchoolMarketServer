package com.peter.dao;

import java.util.List;

public interface BeanDao {
	public <T> void save(T t);
	
	public <T> T findById(Class<T> clazz, String id);

    public <T> List<T>  findByHQL(String hql);

    public <T> void delete(T t);

    public <T> void update(T t);
}
