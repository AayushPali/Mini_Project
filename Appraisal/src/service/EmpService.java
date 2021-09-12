package service;

import dao.EmpDao;
import model.Employee;

public class EmpService implements EmpInterfaceService {

	EmpDao emoloyeeObj = new EmpDao();

	@Override
	public boolean validateEid(int eid) {
		return emoloyeeObj.validateEid(eid);

	}

	@Override
	public boolean updateRole(Employee e, String newRole) {

		return emoloyeeObj.updateEmployeeRole(e, newRole);
	}

	@Override
	public Employee returnEmpData(int eid) {
		Employee emp = emoloyeeObj.returnEmpData(eid);
		return emp;
	}

}
