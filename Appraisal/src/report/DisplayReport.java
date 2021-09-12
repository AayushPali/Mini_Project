package report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.DB_Connector;

public class DisplayReport {
	/*
	 * List of employees who joined as a intern and now are managers
	 */
	public static void displayInternToManager() {
		try (Connection connection = DB_Connector.connect(); Statement statement = connection.createStatement();) {

			System.out.println();
			System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
			System.out.println("List of employees who joined as a intern and now are managers: ");
			ResultSet resultSet = statement.executeQuery("select e.name from employee_details e inner join "
					+ "appraisal_history ah on e.eid=ah.eid inner join role r on e.current_role_id=r.role_id where e.current_role_id=5 and ah.old_role_id =1;");
			while (resultSet.next()) {
				System.out.println("-"+resultSet.getString(1));
			}
			

		} catch (ClassNotFoundException exception) {

			exception.printStackTrace();
		} catch (SQLException exception) {

			exception.printStackTrace();
		}
	}

	/*
	 * Employee with maximum appraisals
	 */
	public static void displayMaxAppraisal() {
		try (Connection connection = DB_Connector.connect(); Statement statement = connection.createStatement();) {

			System.out.println();
			System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
			System.out.print("\nEmployee with maximum appraisals:");
			ResultSet resultSet = statement.executeQuery("select e.name,count(*) c from appraisal_history ah "
					+ "join employee_details e on e.eid=ah.eid group by ah.eid order by c desc;");
			resultSet.next();
			
				System.out.println(resultSet.getString(1)+" got appraisal "+resultSet.getInt(2)+" times.");
			

		} catch (ClassNotFoundException exception) {

			exception.printStackTrace();
		} catch (SQLException exception) {

			exception.printStackTrace();
		}

	}

	/*
	 * Employees for who role was not changed after appraisal
	 */
	public static void displayRoleNotChanged() {
		try (Connection connection = DB_Connector.connect(); Statement statement = connection.createStatement();) {
			System.out.println();
			System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
			System.out.println("Employees for who role was not changed after appraisal : ");
			ResultSet resultSet = statement.executeQuery(
					"select e.eid,e.name from employee_details e inner join appraisal_history ah on e.eid=ah.eid "
							+ "where old_role_id = new_role_id; ");
			while(resultSet.next()) {
			System.out.println("\t|Id--> " + resultSet.getInt(1) + " | Name--> " + resultSet.getString(2));
			}

		} catch (ClassNotFoundException exception) {

			exception.printStackTrace();
		} catch (SQLException exception) {

			exception.printStackTrace();
		}
	}

	/*
	 * Employees who did not have appraisal
	 */

	public static void displayNoAppraisal() {
		try (Connection connection = DB_Connector.connect(); Statement statement = connection.createStatement();) {

			System.out.println();
			System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
			System.out.println("Employees who did not have appraisal");
			ResultSet resultSet = statement.executeQuery(
					"select * from employee_details e left join appraisal_history ah on "
					+ "e.eid=ah.eid where ah.eid is null;");
			
			/*	
			 * SELECT * FROM employee_details e
			 *	WHERE eid NOT IN
			 *  (SELECT eid FROM appraisal_history);
			 *	
			 */
			while(resultSet.next()) {
			System.out.println("\t|Id-->" + resultSet.getInt(1) + " | Name-->" + resultSet.getString(2));
			}

		} catch (ClassNotFoundException exception) {

			exception.printStackTrace();
		} catch (SQLException exception) {

			exception.printStackTrace();
		}

	}
}
