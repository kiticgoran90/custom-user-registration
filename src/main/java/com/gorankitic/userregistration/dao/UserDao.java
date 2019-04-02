package com.gorankitic.userregistration.dao;

import com.gorankitic.userregistration.entity.User;

public interface UserDao {
	
	User findByUserName(String userName);
    
    void save(User user);

}
