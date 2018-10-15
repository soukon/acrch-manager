package co.acrch.system.service;

import co.acrch.common.service.IService;
import co.acrch.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
