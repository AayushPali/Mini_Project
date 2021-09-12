package controller;


import java.util.ArrayList;
import java.util.Scanner;

import model.Employee;
import report.DisplayReport;
import service.AppraisalHService;
import service.AppraisalHInterfaceService;
import service.EmpService;
import service.EmpInterfaceService;
import service.RoleService;
import service.RoleInterfaceService;

public class MainAppraisal {

	static Scanner scanner = new Scanner(System.in);
	 public void displayAllRoles() {
		 RoleInterfaceService roleInterfaceService = new RoleService();
		
		 ArrayList<String> roleList = roleInterfaceService.getAllRole();
		 for(String string: roleList) {
			 System.out.println(string);
		 }
		 System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
		
	}
	 public void addAppraisalInfo() {
			int i;
			RoleInterfaceService roleInterfaceService = new RoleService();
			EmpInterfaceService empInterfaceObj = new EmpService();

			System.out.print("\nEnter Employee Id-->");
			int employeeId = scanner.nextInt();
			
			if (empInterfaceObj.validateEid(employeeId) == true) {
				Employee employeeObj = empInterfaceObj.returnEmpData(employeeId);
				System.out.println(employeeObj);
				
				String roleName = employeeObj.getRoleName();

				System.out.print("Upgrade Role (y/n)?");
				if (scanner.next().charAt(0) == ('y' | 'Y')) {

					System.out.println("\nPromote " + employeeObj.getName() + " to :");

					ArrayList<String> validRoleArrayList = roleInterfaceService.getValidRoles(roleName);
					for (i = 0; i < validRoleArrayList.size(); i++) {
						System.out.println("\t\t" + (i + 1) + "--->" + validRoleArrayList.get(i));
					}
					if (validRoleArrayList.size() > 0) { // Enter if Role is not Highest
						System.out.print("\nEnter choice :");
						i = scanner.nextInt();
						if ((i - 1) < validRoleArrayList.size()) // Enter if choice is valid possible
						{

							if (empInterfaceObj.updateRole(employeeObj, validRoleArrayList.get(i - 1))) // Updates
																										// employee role
																										// & increments
																										// Salary by 10%
							{
								System.out.println("-----Appraisal Details Added------");
								
								System.out.println(employeeObj);
							} else {
								System.out.println("***Error in trying to update***");
							}

						} else {
							System.out.println("***Enter Valid Choice*** ");
						}
					} else {
						System.out.println("***Can't Upgrade Highest Role!***");
					}

				}

				else {

					/*
					 * Enter if Roles are not Updated Update appraisal_history
					 */

					System.out.print("Enter Bonus Amount: ");
					int bonusAmount = scanner.nextInt();
					AppraisalHInterfaceService obj = new AppraisalHService();
					if (obj.AppraisalWithBonus(employeeObj, bonusAmount))
						System.out.println("\n-----Appraisal Details Added------");

				}

			} else
				System.out.println("****Invalid Employee Id****");

			
}

	public static void main(String[] args) {
	
		Scanner sc2= new Scanner(System.in).useDelimiter("\n");
		byte choice=0;
		MainAppraisal mainAppraisalObj = new MainAppraisal(); 
		RoleInterfaceService roleInterfaceService = new RoleService();
		while (true) {
			
			boolean flag = false;
			
			System.out.println("\n__________________________________________________________________");
			System.out.println("1--> Add Appraisal Infromation ");
			System.out.println("2--> Add New Role ");
			System.out.println("3--> Modify Role");
			System.out.println("4--> Delete Role");
			System.out.println("5--> Generate Reports");
			System.out.print("6--> Exit\n\tEnter Choice-");
			
			choice = scanner.nextByte();
			
			System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");

			
			switch (choice) {
			case 1: mainAppraisalObj.addAppraisalInfo();				
					break;
				
			case 2: mainAppraisalObj.displayAllRoles();
					System.out.print("\nEnter Index of new role(in Order of new Role Priority): ");
					int newRoleId = scanner.nextInt();
					System.out.print("\nEnter new Role Name:");
					String newRoleName = sc2.next();
					if(roleInterfaceService.addNewRole(newRoleId, newRoleName))
						System.out.println("-----New Role added-----");
					 mainAppraisalObj.displayAllRoles();
					break;
					
					
			case 3: mainAppraisalObj.displayAllRoles();
					System.out.print("\nEnter Role id: ");
					int roleId = scanner.nextInt();
					System.out.print("\nChange Role Name to: ");
					String roleName = sc2.next();
					if(roleInterfaceService.modifyRole(roleId, roleName))
						System.out.println("-----Role Modified-----");
					mainAppraisalObj.displayAllRoles();
					break;
					
				
			case 4: mainAppraisalObj.displayAllRoles();
					System.out.print("\nEnter Role Id to delete: ");
					roleId = scanner.nextInt();
					if(roleInterfaceService.deleteRole(roleId))
						System.out.println("\n----Role Deleted----");
					mainAppraisalObj.displayAllRoles();
					break;

			case 5: DisplayReport.displayInternToManager();
				    DisplayReport.displayMaxAppraisal();
				    DisplayReport.displayNoAppraisal();
				    DisplayReport.displayRoleNotChanged();
				   break;
				
			case 6:	flag = true;
					scanner.close();
					sc2.close();
					break;

			default:
					System.out.println("Go Again\n");

			}
			if (flag == true)
				
				break;

		}
		
	}
}
