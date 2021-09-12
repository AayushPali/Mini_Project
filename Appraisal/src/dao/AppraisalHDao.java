package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import configuration.DB_Connector;
import model.Employee;

public class AppraisalHDao implements AppraisalHInterfaceDao {

	@Override
	public boolean appraisalWithIncrement(Employee employee, int newRoleId) {
		//Enter Appraisal data for Employees who got promotion(role change+increment)
		try (Connection connection = DB_Connector.connect();
				PreparedStatement pst = connection.prepareStatement(
						"insert into appraisal_history(eid,appraisal_date,old_role_id,new_role_id,increment)"
								+ " values (?,curdate(),?,?,?);");) {
			
			//System.out.println("Inside insertAppraisalInfo");
			pst.setInt(1, employee.getEid());
			pst.setInt(2, employee.getcRoleId());
			pst.setInt(3, newRoleId);
			int increment = (int) (employee.getSalary() * 0.1);
			pst.setInt(4, increment);
			int c = pst.executeUpdate();
			if(c>0)
				return true;
		
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
			//classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
			//sqlException.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean appraisalWithBonus(Employee employee, int bonus) {
		//Enters Appraisal data for employees who got bonus
		try (Connection connection = DB_Connector.connect();
				PreparedStatement pst = connection.prepareStatement(
						"insert into appraisal_history(eid,appraisal_date,old_role_id,new_role_id,bonus)"
								+ " values (?,curdate(),?,?,?);");) {

			pst.setInt(1, employee.getEid());
			pst.setInt(2, employee.getcRoleId());
			pst.setInt(3, employee.getcRoleId());
			pst.setInt(4, bonus);
			pst.executeUpdate();
			return true;
			
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		}
		return false;
	}
}
