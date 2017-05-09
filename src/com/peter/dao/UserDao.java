package com.peter.dao;

import java.util.List;

import com.peter.bean.User;

public interface UserDao {
	public void save(User user);

    /*public User findByIdGet(String id);

    public User findByIdLoad(String id);*/
	
	public User findById(String id);

    public List<User>  findByHQL(String hql);

    public void delete(User user);

    public void update(User user);
}
