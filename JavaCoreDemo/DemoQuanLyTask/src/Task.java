
/**
 * Task.java
 * 29-05-2024
 */

import java.util.Scanner;

/**
 * 
 *
 * @author: Nguyễn Hoàn
 */
public class Task implements DataInputOutput {

	// 1. Attributes
	private String taskID;
	private String taskName;
	private float taskHours;
	private String personID;

	// 2.Getter - Setter
	/**
	 * @return the taskID
	 */
	public String getTaskID() {
		return taskID;
	}

	/**
	 * @param taskID the taskID to set
	 */
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the taskHours
	 */
	public float getTaskHours() {
		return taskHours;
	}

	/**
	 * @param taskHours the taskHours to set
	 */
	public void setTaskHours(float taskHours) {
		this.taskHours = taskHours;
	}

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

	// 3. Constructor
	public Task() {
		init();
	}

	public Task(String id, String name, float hours) {
		this.taskID = id;
		this.taskName = name;
		this.taskHours = hours;
		init();
	}

	/**
	 * Phương thức khởi tạo các giá trị mặc định.
	 */
	private void init() {
		this.personID = "-1";
	}

	// 4. Input - Output method
	@Override
	public void showInformation() {
		String task = "ID: " + this.taskID + "\tName: " + this.taskName + "\tTime: " + this.taskHours;
		String temp = this.personID;
		if (this.personID.equalsIgnoreCase("-1")) {
			System.out.println(task);
		} else {
			System.out.println(task + "\tPeson: " + temp);
		}
	}

	/**
	 * Input method
	 * 
	 * @param scan
	 */
	public void inputData(Scanner scan) {
		System.out.println("Mã Task:");
		this.taskID = scan.nextLine();

		System.out.println("Tên Task:");
		this.taskName = scan.nextLine();

		System.out.println("Thời gian thực hiện Task:");
		this.taskHours = Float.parseFloat(scan.nextLine());
	}

	/**
	 * 
	 */
	public void showIdAndName() {
		System.out.println("ID: " + this.taskID + "\tName: " + this.taskName);
	}
}
