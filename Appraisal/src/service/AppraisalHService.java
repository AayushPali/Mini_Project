package service;

import dao.AppraisalHDao;
import model.Employee;

public class AppraisalHService implements AppraisalHInterfaceService{

	private AppraisalHDao obj= new AppraisalHDao();
	@Override
	public boolean AppraisalWithBonus(Employee e, int bonus) {
		return obj.appraisalWithBonus(e, bonus);
	}

}
