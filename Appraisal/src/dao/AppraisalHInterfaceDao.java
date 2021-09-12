package dao;

import model.Employee;

public interface AppraisalHInterfaceDao {

	boolean appraisalWithIncrement(Employee e,int nRoleId);
	boolean appraisalWithBonus(Employee e,int bonus);
}
