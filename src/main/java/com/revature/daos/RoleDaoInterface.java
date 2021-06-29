package com.revature.daos;

import java.util.List;

import com.revature.models.Role;

public interface RoleDaoInterface {

	public List<Role> getRoleType(int enemyId, String type);

	public void updateRoleType(int enemyId, String type);
}