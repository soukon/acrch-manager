package co.acrch.system.dao;

import java.util.List;

import co.acrch.common.config.MyMapper;
import co.acrch.system.domain.User;
import co.acrch.system.domain.UserWithRole;
import co.acrch.system.domain.User;
import co.acrch.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}