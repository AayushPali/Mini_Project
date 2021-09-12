package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import configuration.DB_Connector;
import model.Role;

public class RoleDao implements RoleInterfaceDao {

	Role robj = new Role();

	@Override
	public ArrayList<String> getValidRoles(String rName) {
		//rName = rName.toLowerCase();
		try (Connection con = DB_Connector.connect(); Statement st = con.createStatement();) {
			ResultSet rs = st.executeQuery("select * from role order by role_id;");
			ArrayList<String> validRoles = new ArrayList<>();
			while (rs.next()) {
				if (rName.equals(rs.getString(2))) {
					if (rs.next())
						validRoles.add(rs.getString(2));
					if (rs.next())
						validRoles.add(rs.getString(2));
					return validRoles;
				}
			}
		}
			catch (ClassNotFoundException classNotFoundException) {
				System.out.println(classNotFoundException);
				//classNotFoundException.printStackTrace();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
				//sqlException.printStackTrace();
			}
		return null;
	}

	@Override
	public boolean isRoleValid(String rName) {
		rName = rName.toLowerCase();
		try (Connection con = DB_Connector.connect(); Statement st = con.createStatement();) {
			ResultSet rs = st.executeQuery("select * from roles where role_name='" + rName + "' ;");
			rs.next();
			int i = 0;
			i = rs.getInt(1);
			if (i == 0)
				return false;
			else
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
	public int getRoleId(String rname) {
		try (Connection con = DB_Connector.connect(); Statement st = con.createStatement();) {
			ResultSet rs = st.executeQuery("select role_id from role where role_name='" + rname + "' ;");

			rs.next();
			return rs.getInt(1);
		}catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
			//classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
			//sqlException.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<String> getRoleSet() {

		try (Connection con = DB_Connector.connect(); Statement st = con.createStatement();) {
			ResultSet resultSet = st.executeQuery("select * from role order by role_id;");
			ArrayList<String> resultSetList = new ArrayList<>();
			while (resultSet.next()) {
				resultSetList.add("Role Id --> " + resultSet.getInt(1) + " |Role Name -->" + resultSet.getString(2));
			}
			return resultSetList;
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
			//classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
			//sqlException.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addNewRole(int newRoleId, String newRoleName) {
		try (Connection connection = DB_Connector.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"select * from role order by role_id;", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				Statement statement = connection.createStatement();
				Statement statement2 = connection.createStatement();
				ResultSet resultSet = preparedStatement.executeQuery();) {
			

			if (resultSet.last()) {
				while (resultSet.getInt(1) >= newRoleId) {
					statement.executeUpdate(
							"update role set role_id=(role_id+1) where role_id=" + resultSet.getInt(1) + " ;");
					resultSet.previous();
				}
				statement2.executeUpdate("insert into role values(" + newRoleId + ",'" + newRoleName + "');");
			}
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
	public boolean modifyRole(int currentRoleId, String modifiedRoleName) {
		try (Connection connection = DB_Connector.connect(); Statement statement = connection.createStatement();) {
			modifiedRoleName = modifiedRoleName.toLowerCase();
			statement.executeUpdate(
					"update role set role_name='" + modifiedRoleName + "' where role_id= " + currentRoleId + " ;");
			return true;
		}

		catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
			//classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
			//sqlException.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteRole(int roleId) {
		try (	Connection connection = DB_Connector.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"select * from role order by role_id;", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				Statement statement = connection.createStatement();
				Statement statement2 = connection.createStatement();
				ResultSet resultSet = preparedStatement.executeQuery();
				) {
			
			
			if (resultSet.next()) {
				while (resultSet.getInt(1) != roleId) {
					resultSet.next();
				}
				statement.executeUpdate("delete from role where role_id = " + roleId + ";");
				resultSet.next();
				do {
					statement2.executeUpdate(
							"update role set role_id=(role_id-1) where role_id=" + resultSet.getInt(1) + " ;");
				} while (resultSet.next());

			}
			return true;
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException);
			//classNotFoundException.printStackTrace();
		} catch (SQLException sqlException) {
			System.out.println("Can't delete a active role, " + roleId + ".");
			System.out.println(sqlException);
			//sqlException.printStackTrace();
		}
		

		return false;

	}
}
