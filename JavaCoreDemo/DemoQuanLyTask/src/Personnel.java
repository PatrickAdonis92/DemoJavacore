
/**
 * Personnel.java
 * 29-05-2024
 */

import java.util.ArrayList;

/**
 * 
 *
 * @author: Nguyễn Hoàn
 */
public abstract class Personnel implements DataInputOutput {

	// 1. Attributes
	protected String personID;
	protected String fullName;
	protected int birthday;
	protected String email;
	protected String phoneNumber;
	protected String departmentID;
	protected float salaryPerson;
	protected float workDay;
	protected ArrayList<Task> listTask;

	// 2. Getter - Setter
	/**
	 * @return the personID
	 */
	public String getPersonID() {
		return personID;
	}

	/**
	 * @param personID the personID to set
	 */
	public void setPersonID(String personID) {
		this.personID = personID;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the birthday
	 */
	public int getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

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
	 * @return the workDay
	 */
	public float getWorkDay() {
		return workDay;
	}

	/**
	 * @param workDay the workDay to set
	 */
	public void setWorkDay(float workDay) {
		this.workDay = workDay;
	}

	/**
	 * @return the listTask
	 */
	public ArrayList<Task> getListTask() {
		return listTask;
	}

	/**
	 * @param listTask the listTask to set
	 */
	public void setListTask(ArrayList<Task> listTask) {
		this.listTask = listTask;
	}

	/**
	 * @return the salaryPerson
	 */
	public float getSalaryPerson() {
		return salaryPerson;
	}

	// 3. Constructor
	/**
	 * Constructor không tham số.
	 */
	public Personnel() {
		init();
	}

	public Personnel(String personId, String personName) {
		init();
		this.personID = personId;
		this.fullName = personName;
	}
	
	/**
	 * Constructor có tham số.
	 * 
	 * @param id
	 * @param fullName
	 * @param birthday
	 * @param mail
	 * @param phone
	 * @param workDay
	 */
	public Personnel(String id, String fullName, int birthday, String mail, String phone, float workDay) {
		init();
		this.personID = id;
		this.fullName = fullName;
		this.birthday = birthday;
		this.email = mail;
		this.phoneNumber = phone;
		this.workDay = workDay;
	}

	/**
	 * Phương thức cài đặt các thiết lập mặc định.
	 */
	private void init() {
		listTask = new ArrayList<Task>();
		this.departmentID = "-1";
	}

	// 4.Input - Output method
	@Override
	public void showInformation() {
		String temp = this.departmentID;
		if (this.departmentID.equalsIgnoreCase("-1")) {
			temp = "chưa phân bổ";
		}
		System.out.print("ID: " + this.personID + "\tFull Name: " + this.fullName + "\tBirthday: " + this.birthday
				+ "\tEmail: " + this.email + "\tPhone: " + this.phoneNumber + "\tWork Day:" + this.workDay
				+ "\tSalary: " + this.salaryPerson + "\tDepartment: " + temp);
	}

	/**
	 * Phương thức xuất ra mã và tên của nhân sự.
	 */
	public void showIdAndName() {
		System.out.println("Mã: " + this.personID + "\t Tên: " + this.fullName);
	}

	// 5.Business method.
	/**
	 * Phương thức tính lương một tháng cho một nhân sự
	 */
	public abstract void payroll();

}
