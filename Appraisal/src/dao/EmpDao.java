package dao;

import configuration.DB_Connector;
import model.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmpDao implements EmpInterfaceDao {
	Employee emp = new Employee();

	@Override
	public boolean validateEid(int employeeId) {
		//Returns true if  employee id(eid) is present in table 
		try (Connection connection = DB_Connector.connect();
				PreparedStatement preparedStatement = connection.prepareStatement("Select eid from employee_details where eid=?;");) {
			preparedStatement.setInt(1, employeeId);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			if (rs.getInt(1) == employeeId)
				return true;
			else
				return false;

		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
			//classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
			//sqlException.printStackTrace();
		}
		catch (Exception exception){
			return false;
		}
		return false;
		
	}

	@Override
	public Employee returnEmpData(int employeeId) {
		// sets values in Container employee and returns the same
		Employee employee = new Employee();
		employee.setEid(employeeId);
		try (Connection connection = DB_Connector.connect(); Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement
					.executeQuery("select current_role_id,name,salary from employee_details where eid=" + employeeId + ";");
			resultSet.next();
			int currentRoleId = resultSet.getInt(1);
			employee.setName(resultSet.getString(2));
			employee.setSalary(resultSet.getInt(3));
			employee.setcRoleId(currentRoleId);
			resultSet = statement.executeQuery("select role_name from role where role_id =" + currentRoleId + ";");
			resultSet.next();
			employee.setRoleName(resultSet.getString(1));

			return employee;

		}catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
			//classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
			//sqlException.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateEmployeeRole(Employee employee, String newRole) {
		//updates current role and salary of employee & creates appraisal history.
		try (Connection connection = DB_Connector.connect();
				PreparedStatement preparedStatement = connection
						.prepareStatement("update employee_details set current_role_id = ?,salary=? where eid= ? ;");) {
			RoleDao rcobj = new RoleDao();
			//System.out.println(newRole);
			int roleId = rcobj.getRoleId(newRole);
			int salary = employee.getSalary();
			int updatedSalary = (int) (salary + (salary * 0.1));
			AppraisalHDao ahobj = new AppraisalHDao();
			if (ahobj.appraisalWithIncrement(employee, roleId)) {	
				preparedStatement.setInt(1, roleId);
				preparedStatement.setInt(2, updatedSalary);
				preparedStatement.setInt(3, employee.getEid());
				int c = preparedStatement.executeUpdate();
				if (c > 0) {
					employee.setRoleName(newRole);
					employee.setSalary(updatedSalary);
					employee.setcRoleId(roleId);
					return true;
				}
				else
					return false;
			}

		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
			//classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
			//sqlException.printStackTrace();
		}
		return false;
	}

}
