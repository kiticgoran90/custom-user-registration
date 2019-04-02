package com.gorankitic.userregistration.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gorankitic.userregistration.entity.User;
import com.gorankitic.userregistration.user.CrmUser;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);
	
	void save(CrmUser crmUser);

}
