
/**
 * ListTask.java
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
public class ListTask {

	// 1. Attributes
	private ArrayList<Task> listTask;

	// 2.Getter - Setter
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

	public ListTask() {
		listTask = new ArrayList<Task>();
	}

	// 3. Input - Output method
	/**
	 * Output method
	 */
	public void showInformation() {
		for (Task task : this.listTask) {
			task.showInformation();
			System.out.println();
		}
	}

	/**
	 * Input method
	 * 
	 * @param scan
	 */
	public void inputInformation(Scanner scan) {
		for (Task task : this.listTask) {
			task.inputData(scan);
			this.listTask.add(task);
		}
	}

	// 4. Read - Write file
	/**
	 * Phương thức load dữ liệu từ file.
	 * 
	 * @return
	 */
	public boolean createData() {
		boolean flag = false;
		try {
			// Đọc file theo từng dòng và tách dữ liệu được ngăn cách bởi dấu "#"
			FileReader reader = new FileReader("src/DanhSachTask.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Task task;
				String[] listInfo = line.split(" # ");
				// Tạo dữ liệu cho mỗi Task
				task = new Task();
				task.setTaskID(listInfo[0]);
				task.setTaskName(listInfo[1]);
				task.setTaskHours(Float.parseFloat(listInfo[2]));
				task.setPersonID(listInfo[3]);
				// Lưu vào trong list
				this.listTask.add(task);
			}
			reader.close();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Phương thức ghi dữ liệu xuống file.
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
			for (Task p : this.listTask) {
				String line = p.getTaskID() + " # " + p.getTaskName() + " # " + p.getTaskHours() + " # " + p.getPersonID();
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
