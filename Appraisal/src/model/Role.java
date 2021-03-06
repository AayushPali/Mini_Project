package model;

public class Role {
	/*
	 * role_id,role_name
	 */

	private int roleId;
	private String roleName;

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "| Role Id-->" + roleId + " | Role Name=" + roleName + "|";
	}

	
}
