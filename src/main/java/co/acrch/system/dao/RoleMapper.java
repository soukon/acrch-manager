package co.acrch.system.dao;

import java.util.List;

import co.acrch.common.config.MyMapper;
import co.acrch.system.domain.Role;
import co.acrch.system.domain.RoleWithMenu;
import co.acrch.system.domain.Role;
import co.acrch.system.domain.RoleWithMenu;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}