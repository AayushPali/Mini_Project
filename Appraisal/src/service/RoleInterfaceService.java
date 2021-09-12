package service;

import java.util.ArrayList;

public interface RoleInterfaceService {

	public ArrayList<String>  getValidRoles(String rName);
	public boolean isValidRole(String rName);
	public ArrayList<String>  getAllRole();
	public boolean modifyRole(int currentroleId,String modifiedRoleName);
	public boolean addNewRole(int newRoleId,String newRoleName);
	public boolean deleteRole(int roleId);
}
