
/**
 * ListDepartment.java
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
public class ListDepartment {

	// 1. Attributes
	private ArrayList<Department> listDepartment;

	// 2. Getter - Setter
	/**
	 * @return the listDepartment
	 */
	public ArrayList<Department> getListDepartment() {
		return listDepartment;
	}

	/**
	 * @param listDepartment the listDepartment to set
	 */
	public void setListDepartment(ArrayList<Department> listDepartment) {
		this.listDepartment = listDepartment;
	}

	// 3. Constructor
	public ListDepartment() {
		listDepartment = new ArrayList<Department>();
	}

	// 4. Input - Output method
	/**
	 * Output method
	 */
	public void showInformation() {
		System.out.println("Department Infor:");
		for (Department department : this.listDepartment) {
			department.showInformation();
			System.out.println();
		}
	}

	/**
	 * Input method.
	 * 
	 * @param scan
	 */
	public void inputInformation(Scanner scan) {
		for (Department depart : this.listDepartment) {
			depart.inputData(scan);
			this.listDepartment.add(depart);
		}
	}

	// 5. Read - Write File Method
	/**
	 * Read File Method.
	 * 
	 * @return
	 */
	public boolean createData() {
		boolean flag = false;
		try {
			// Đọc file theo từng dòng và tách dữ liệu được ngăn cách bởi dấu "#"
			FileReader reader = new FileReader("src/PhongBan.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Department pb = new Department();
				Manager manager;
				String[] listInfo = line.split(" # ");
				// Tạo dữ liệu cho mỗi phòng banban
				pb.setDepartmentID(listInfo[0]);
				pb.setDepartmentName(listInfo[1]);
				manager = new Manager(listInfo[2], listInfo[3]);
				pb.setObjManager(manager);
				// Lưu vào trong list
				this.listDepartment.add(pb);
			}
			reader.close();
			flag = true;
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
			for (Department p : this.listDepartment) {
				String line = p.getDepartmentID() + " # " + p.getDepartmentName() + " # " + p.getObjManager().getPersonID()
						+ " # " + p.getObjManager().getFullName();
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
}
