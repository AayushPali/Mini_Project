package service;

import java.util.ArrayList;

import dao.RoleDao;
import dao.RoleInterfaceDao;

public class RoleService implements RoleInterfaceService{

	RoleInterfaceDao roleInterfaceObj= new RoleDao();
	@Override
	public ArrayList<String> getValidRoles(String rName) {
		
		return roleInterfaceObj.getValidRoles(rName);
	}
	@Override
	public boolean isValidRole(String rName) {
		return roleInterfaceObj.isRoleValid(rName);
		
	}
	@Override
	public ArrayList<String> getAllRole() {
		return roleInterfaceObj.getRoleSet();
	}
	@Override
	public boolean modifyRole(int currentroleId, String modifiedRoleName) {
		return roleInterfaceObj.modifyRole(currentroleId, modifiedRoleName);
		
	}
	@Override
	public boolean addNewRole(int newRoleId, String newRoleName) {
		return roleInterfaceObj.addNewRole(newRoleId, newRoleName);
	}
	@Override
	public boolean deleteRole(int roleId) {
		return roleInterfaceObj.deleteRole(roleId);
	}

}
