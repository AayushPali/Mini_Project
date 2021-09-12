package service;

import model.Employee;

public interface EmpInterfaceService {
	
	public boolean validateEid(int eid);
	public Employee returnEmpData(int eid);
	public boolean updateRole(Employee e,String nRole);
}
