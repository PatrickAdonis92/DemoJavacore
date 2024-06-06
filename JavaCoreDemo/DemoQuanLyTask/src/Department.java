
/**
 * Department.java
 * 29-05-2024
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 *
 * @author: Nguyễn Hoàn
 */
public class Department implements DataInputOutput {

	// 1. Attributes
	private String departmentID;
	private String departmentName;
	private Manager objManager;
	private ArrayList<Staff> listStaff;

	// 2. Getter - Setter
	/**
	 * @return the departmentID
	 */
	public String getDepartmentID() {
		return departmentID;
	}

	/**
	 * @param departmentID the departmentID to set
	 */
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the objManager
	 */
	public Manager getObjManager() {
		return objManager;
	}

	/**
	 * @param objManager the objManager to set
	 */
	public void setObjManager(Manager objManager) {
		this.objManager = objManager;
	}

	/**
	 * @return the listStaff
	 */
	public ArrayList<Staff> getListStaff() {
		return listStaff;
	}

	/**
	 * @param listStaff the listStaff to set
	 */
	public void setListStaff(ArrayList<Staff> listStaff) {
		this.listStaff = listStaff;
	}

	// 3. Constructor
	public Department() {
		init();
	}

	public Department(String id, String name) {
		init();
		this.departmentID = id;
		this.departmentName = name;
	}

	private void init() {
		objManager = new Manager();
		listStaff = new ArrayList<Staff>();
	}

	// 4. Input - Output method
	@Override
	public void showInformation() {
		System.out.print("ID: " + this.departmentID + "\tDepartment: " + this.departmentName);
		if (this.objManager != null) {
			if (!(this.objManager.getPersonID().equalsIgnoreCase("null"))) {
				System.out.println("\tManager ID: " + this.objManager.getPersonID() + "\tManager Name: "
						+ this.objManager.getFullName());
			}

		}

	}

	/**
	 * Input method.
	 * 
	 * @param scan
	 */
	public void inputData(Scanner scan) {
		System.out.println("Mã phòng: ");
		this.departmentID = scan.nextLine();

		System.out.println("Tên phòng:");
		this.departmentName = scan.nextLine();
	}

	/**
	 * 
	 */
	public void showIdAndName() {
		System.out.println("Mã Phòng: " + this.departmentID + "\tTên Phòng: " + this.departmentName);
	}
}
