package dao;

import java.util.ArrayList;

public interface RoleInterfaceDao {
	
	public int getRoleId(String rname);
	public ArrayList<String> getValidRoles(String rName);
	public boolean isRoleValid(String rName);
	public ArrayList<String> getRoleSet();
	public boolean addNewRole(int newRoleId,String newRoleName);
	public boolean modifyRole(int currentroleId,String modifiedRoleName);
	public boolean deleteRole(int roleId);
}
