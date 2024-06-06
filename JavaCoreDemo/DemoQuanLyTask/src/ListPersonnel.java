
/**
 * ListPersonnel.java
 * 29-05-2024
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 *
 * @author: Nguyễn Hoàn
 */
public class ListPersonnel implements DataInputOutput {

	// 1. Attributes
	private ArrayList<Personnel> listPerson;

	// 2. Getter - Setter
	/**
	 * @return the listPerson
	 */
	public ArrayList<Personnel> getListPerson() {
		return listPerson;
	}

	/**
	 * @param listPerson the listPerson to set
	 */
	public void setListPerson(ArrayList<Personnel> listPerson) {
		this.listPerson = listPerson;
	}

	// 3. Constructor
	public ListPersonnel() {
		listPerson = new ArrayList<Personnel>();
	}

	// 4. Input - Output method.
	@Override
	public void showInformation() {
		System.out.println("List Information Personnel:");
		for (Personnel p : this.listPerson) {
			p.showInformation();
			System.out.println();
		}
	}

	/**
	 * Output method.
	 * 
	 * @param scan
	 */
	public void inputInformation(Scanner scan) {
		boolean flag = true;
		Personnel person;
		while (flag) {
			inMenu();
			int chose = Integer.parseInt(scan.nextLine());
			switch (chose) {
			case 1:
				person = new Staff();
				inputData(scan, person);
				break;
			case 2:
				person = new Manager();
				inputData(scan, person);
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("Chỉ nhập 0 - 2.");
			}
		}
	}

	/**
	 * Phương thức nhập thông tin nhân viên thường và trưởng phòng.
	 * 
	 * @param scan
	 */
	private void inputData(Scanner scan, Personnel person) {
		String id;
		boolean check = true;
		do {
			System.out.println("Mã nhân viên:");
			id = scan.nextLine();
			check = checkIdDuplicate(id);
			if (check) {
				System.out.println("ID đã tồn tại:");
			}
		} while (check);
		System.out.println("Tên nhân viên: ");
		String name = scan.nextLine();
		System.out.println("SĐT:");
		String phone = scan.nextLine();
		System.out.println("Email:");
		String mail = scan.nextLine();
		System.out.println("Ngày sinh:");
		int birthday = Integer.parseInt(scan.nextLine());
		System.out.println("Số ngày làm việc:");
		float workDay = Float.parseFloat(scan.nextLine());
		if (person instanceof Staff) {
			person = new Staff(id, name, birthday, mail, phone, workDay);
		}
		if (person instanceof Manager) {
			person = new Manager(id, name, birthday, mail, phone, workDay);
		}
		person.payroll();
		this.listPerson.add(person);
	}

	/**
	 * In menu nhập
	 */
	private void inMenu() {
		System.out.println("Nhập thông tin: ");
		System.out.println("1. Nhân viên thường.");
		System.out.println("2. Trưởng phòng.");
		System.out.println("0. Thoát.");
	}

	/**
	 * Phương thức kiểm tra xem mã ID đã tồn tại hay chưa
	 * 
	 * @param id
	 * @return
	 */
	private boolean checkIdDuplicate(String id) {
		boolean flag = false;
		for (Personnel p : this.listPerson) {
			if (p.getPersonID().equalsIgnoreCase(id)) {
				flag = true;
			}
		}
		return flag;
	}

	// 5. Read - Write File Method.
	/**
	 * Read File Method.
	 * 
	 * @return
	 */
	public boolean createData(Scanner scan) {
		boolean flag = false;
		try {
			File file = null;
			do {
				System.out.println("Chọn file muốn lấy dữ liệu: ");
				String fileLoader = scan.nextLine();
				file = new File(fileLoader);
				// Đọc file theo từng dòng và tách dữ liệu được ngăn cách bởi dấu "#"
				FileReader reader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					Personnel person;
					String[] listInfo = line.split(" # ");
					// Xét cuối mỗi dòng
					if (listInfo[listInfo.length - 1].equalsIgnoreCase("true")) {
						// Nếu là true, thì khởi tạo trưởng phòng
						person = new Manager();
					} else {
						// Nếu là false thì khởi tạo nhân viên thường
						person = new Staff();
					}
					// Tạo dữ liệu cho mỗi nhân sự
					person.setPersonID(listInfo[0]);
					person.setFullName(listInfo[1]);
					person.setBirthday(Integer.parseInt(listInfo[2]));
					person.setEmail(listInfo[3]);
					person.setPhoneNumber(listInfo[4]);
					person.setWorkDay(Float.parseFloat(listInfo[5]));
					person.setDepartmentID(listInfo[7]);

					// Lưu vào trong list
					this.listPerson.add(person);
				}
				reader.close();
				flag = true;
			} while (!file.exists());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Write File Method.
	 * 
	 * @param scan
	 * @return
	 */
	public boolean saveData(Scanner scan) {
		boolean flag = false;
		try {
			String fileSave;
			System.out.println("Chọn nơi lưu data:");
			fileSave = scan.nextLine();
			File file = new File(fileSave);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter write = new FileWriter(file);
			BufferedWriter bwrite = new BufferedWriter(write);
			for (Personnel p : this.listPerson) {
				String isManager = "";
				if (p instanceof Manager) {
					isManager = "true";
				} else {
					isManager = "false";
				}
				String line = p.getPersonID() + " # " + p.getFullName() + " # " + p.getBirthday() + " # " + p.getEmail()
						+ " # " + p.getPhoneNumber() + " # " + p.getWorkDay() + " # " + p.getSalaryPerson() + " # "
						+ p.getDepartmentID() + " # " + isManager;
				;
				bwrite.write(line);
				bwrite.newLine();
			}
			bwrite.close();
			flag = true;
		} catch (IOException exp) {
			System.out.println(exp);
		}
		return flag;
	}

	// 5. Business method.
	/**
	 * Phương thức tính lương cho nhân sự công ty.
	 */
	public void payroll() {
		for (Personnel p : this.listPerson) {
			p.payroll();
		}
	}
}
