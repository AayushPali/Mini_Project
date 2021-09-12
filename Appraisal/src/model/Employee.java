package model;

public class Employee {
	/*
	 * Table Columns
	 * eid, name, gender, mobile, email,city, date_of_joining
	 * current_role_id , department &  salary
	 *
	 */
	private int eid;
	private String name;
	private int cRoleId;
	private String roleName;						//Not in employee table, used as temporary container
	private int salary;							
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(int eid, String name,int cRoleId,String roleName,int salary) {
		super();
		this.eid = eid;
		this.name= name;
		this.cRoleId = cRoleId;
		this.roleName = roleName;
		this.salary= salary;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getcRoleId() {
		return cRoleId;
	}
	public void setcRoleId(int cRoleId) {
		this.cRoleId = cRoleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Id-->" + eid + " | Name-->" + name+" | Current Role-->" + roleName +" | Role Id-->" + cRoleId +" | Salary-->" + salary;
	}
	
	
	
}
