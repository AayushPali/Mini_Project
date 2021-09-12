package dao;

import model.Employee;

public interface EmpInterfaceDao {
	public boolean validateEid(int eid);
	public Employee returnEmpData(int eid);
	public boolean updateEmployeeRole(Employee e, String newRole);
}
