package com.gorankitic.userregistration.dao;

import com.gorankitic.userregistration.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
