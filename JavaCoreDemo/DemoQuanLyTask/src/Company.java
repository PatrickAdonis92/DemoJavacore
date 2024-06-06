
/**
 * Company.java
 * 29-05-2024
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 *
 * @author: Nguyễn Hoàn
 */
public class Company {

	// 1. Khai báo hằng số
	final static String COMPANY_NAME = "CyberSoft";

	// 2. Attributes
	private float salaryOfCompany;
	private int staffSize;
	private ListPersonnel objListPerson;
	private ListTask objListTask;
	private ListDepartment objListDepartment;
	private ArrayList<Manager> listManagers;
	private ArrayList<Staff> listStaffs;

	// 3. Getter - Setter
	/**
	 * @return the objListPerson
	 */
	public ListPersonnel getObjListPerson() {
		return objListPerson;
	}

	/**
	 * @param objListPerson the objListPerson to set
	 */
	public void setObjListPerson(ListPersonnel objListPerson) {
		this.objListPerson = objListPerson;
	}

	/**
	 * @return the objListTask
	 */
	public ListTask getObjListTask() {
		return objListTask;
	}

	/**
	 * @param objListTask the objListTask to set
	 */
	public void setObjListTask(ListTask objListTask) {
		this.objListTask = objListTask;
	}

	/**
	 * @return the objListDepartment
	 */
	public ListDepartment getObjListDepartment() {
		return objListDepartment;
	}

	/**
	 * @param objListDepartment the objListDepartment to set
	 */
	public void setObjListDepartment(ListDepartment objListDepartment) {
		this.objListDepartment = objListDepartment;
	}

	/**
	 * @return the salaryOfCompany
	 */
	public float getSalaryOfCompany() {
		return salaryOfCompany;
	}

	/**
	 * @return the staffSize
	 */
	public int getStaffSize() {
		return staffSize;
	}

	// 3. Constructor
	public Company() {
		init();
	}

	/**
	 * Hàm khởi tạo các giá trị mặc định.
	 */
	private void init() {
		objListPerson = new ListPersonnel();
		objListTask = new ListTask();
		objListDepartment = new ListDepartment();
		listManagers = new ArrayList<Manager>();
		listStaffs = new ArrayList<Staff>();
	}

	public void creatData(Scanner scan) {
		this.objListPerson.createData(scan);
		this.objListDepartment.createData();
		this.objListTask.createData();
	}

	// 4. Input - Output method.
	public void inputInformation(Scanner scan) {
		boolean flag = true;
		do {
			inMenu();
			int chose = Integer.parseInt(scan.nextLine());
			switch (chose) {
			case 1:
				objListPerson.inputInformation(scan);
				break;
			case 2:
				objListDepartment.inputInformation(scan);
				break;
			case 3:
				objListTask.inputInformation(scan);
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("Vui lòng chỉ chọn từ 0 - 3.");
			}
		} while (flag);
	}

	/**
	 * 
	 */
	private void inMenu() {
		System.out.println("Chức năng nhập thông tin:");
		System.out.println("1. Nhập nhân sự.");
		System.out.println("2. Nhập phòng ban.");
		System.out.println("3. Nhập Task.");
		System.out.println("0. Thoát nhập.");
		System.out.println("Mời chọn:");
	}

	public void showInformation() {
		System.out.println("<=============== COMPANY ===============>");
		System.out.println("Company Name: " + COMPANY_NAME);
		System.out.println("Staff Size: " + this.staffSize);
		System.out.println("<=============== PERSONNEL ===============>");
		this.objListPerson.showInformation();
	}

	private ArrayList<Manager> getListManager() {
		ArrayList<Manager> list = new ArrayList<Manager>();
		for (Personnel person : this.objListPerson.getListPerson()) {
			if (person instanceof Manager) {
				list.add((Manager) person);
			}
		}
		return list;
	}

	private ArrayList<Staff> getListStaff() {
		ArrayList<Staff> list = new ArrayList<Staff>();
		for (Personnel person : this.objListPerson.getListPerson()) {
			if (person instanceof Staff) {
				list.add((Staff) person);
			}
		}
		return list;
	}

	/**
	 * Phương thức phân bổ nhân viên về các phòng ban.
	 * 
	 * @param scan
	 */
	public void phanBoNhanSu(Scanner scan) {
		boolean flag = true;
		this.listStaffs = getListStaff();
		if (!this.listStaffs.equals(null)) {
			for (Staff staff : this.listStaffs) {
				if (staff.getDepartmentID().equalsIgnoreCase("-1")) {
					System.out.println("Đang phân bổ cho nhân sự:");
					staff.showIdAndName();
					do {
						System.out.println("Chọn 1 để phân bổ, 2 để đi tiếp.");
						int chose = Integer.parseInt(scan.nextLine());
						switch (chose) {
						case 1:
							Department department = chonPhongTuMaID(scan, staff);
							department.getListStaff().add(staff);
							staff.setDepartmentID(department.getDepartmentID());
							System.out.println("Đã phân bổ xong.");
							flag = false;
							break;
						case 2:
							flag = false;
							break;
						default:
							System.out.println("Vui lòng chỉ chọn 1 hoặc 2.");
						}
					} while (flag);

				}
			}
		}
	}

	/**
	 * Phương thức chỉ định trưởng phòng cho các phòng ban.
	 * 
	 * @param scan
	 */
	public void chiDinhTruongPhong(Scanner scan) {
		boolean flag = true;
		this.listManagers = getListManager();
		if (this.listManagers.size() > 0) {
			for (Manager manager : this.listManagers) {
				if (manager.getDepartmentID().equalsIgnoreCase("-1")) {

					System.out.println("Đang chỉ định cho trưởng phòng:");
					manager.showIdAndName();
					do {
						System.out.println("Chọn 1 để chỉ định, 2 để đi tiếp.");
						int chose = Integer.parseInt(scan.nextLine());
						switch (chose) {
						case 1:
							Department department = chonPhongTuMaID(scan, manager);
							department.setObjManager(manager);
							manager.setDepartmentID(department.getDepartmentID());
							System.out.println("Đã chỉ định xong!");
							flag = false;
							break;
						case 2:
							flag = false;
							break;
						default:
							System.out.println("Vui lòng chỉ chọn 1 hoặc 2.");
						}
					} while (flag);
				}
			}
		} else {
			System.out.println("List null!!!");
		}
	}

	public void phanTask(Scanner scan) {
		boolean flag = true;
		if (!(this.objListTask.getListTask().equals(null))) {
			for (Task task : this.objListTask.getListTask()) {
				System.out.println("Task đang phân bổ: ");
				task.showIdAndName();
				Personnel person = timNhanSuTheoMa(scan);
				do {
					System.out.println("Chọn 1 để phân bổ, 2 để chọn lại, 3. đi tiếp.");
					int chose = Integer.parseInt(scan.nextLine());
					switch (chose) {
					case 1:
						task.setPersonID(person.getPersonID());
						person.getListTask().add(task);
						System.out.println("Phân Task thành công.");
						flag = false;
						break;
					case 2:
						person = timNhanSuTheoMa(scan);
						break;
					case 3:
						flag = false;
						break;
					default:
						System.out.println("Vui lòng chỉ nhập 1 hoặc 2.");
					}
				} while (flag);
			}
		}
	}

	/**
	 * @param scan
	 * @return
	 */
	private Personnel timNhanSuTheoMa(Scanner scan) {
		Personnel person = null;
		do {
			System.out.println("Nhập vào mã nhân sự:");
			String idPerson = scan.nextLine();
			for (Personnel p : this.objListPerson.getListPerson()) {
				if (p.getPersonID().equalsIgnoreCase(idPerson)) {
					person = p;
				}
			}
		} while (person == null);
		return person;
	}

	/**
	 * @param idTP
	 * @return
	 */
	private Department chonPhongTuMaID(Scanner scan, Personnel person) {
		Department department = null;
		do {
			ArrayList<Department> list = getListDepartmentNullManager();
			showListDepartment(person);
			System.out.println("Nhập vào mã phòng trong danh sách trên:");
			String idTP = scan.nextLine();
			if (person instanceof Staff) {
				for (Department depart : this.objListDepartment.getListDepartment()) {
					if (depart.getDepartmentID().equalsIgnoreCase(idTP)) {
						department = depart;
					}
				}
			}
			if (person instanceof Manager) {
				for (Department depart : list) {
					if (depart.getDepartmentID().equalsIgnoreCase(idTP)) {
						department = depart;
					}
				}
			}
		} while (department == null);

		return department;
	}

	/**
	 * 
	 */
	private void showListDepartment(Personnel person) {
		if (person instanceof Manager) {
			ArrayList<Department> list = getListDepartmentNullManager();
			for (Department depart : list) {
				depart.showIdAndName();
			}
		}
		if (person instanceof Staff) {
			for (Department depart : this.objListDepartment.getListDepartment()) {
				depart.showIdAndName();
			}
		}
	}

	private ArrayList<Department> getListDepartmentNullManager() {
		ArrayList<Department> list = new ArrayList<Department>();
		for (Department depart : this.objListDepartment.getListDepartment()) {
			if (depart.getObjManager().getPersonID().equalsIgnoreCase("null")) {
				list.add(depart);
			}
		}
		System.out.println(list.size());
		return list;
	}

	public void saveData(Scanner scan) {
		this.objListPerson.saveData(scan);
		this.objListDepartment.saveData(scan);
		this.objListTask.saveData(scan);
	}

	public int companySize() {
		int companySize = 0;
		for (Personnel person : this.objListPerson.getListPerson()) {
			companySize += 1;
		}
		this.staffSize = companySize;
		return this.staffSize;
	}

	public void showDepart() {
		this.objListDepartment.showInformation();
	}

	public void showTask() {
		this.objListTask.showInformation();
	}

	public void deletePesonnel(Scanner scan) {
		boolean deleted = false;
		Personnel person = timNhanSuTheoMa(scan);
		if (person instanceof Manager) {
			deleted = deleteManager((Manager) person);
		} else {
			deleted = deleteStaff((Staff) person);
		}
	}

	private boolean deleteManager(Manager manager) {
		boolean deleted = false;
		// Xóa trưởng phòng quản lý trong phòng ban, reset null.
		for (Department depart : this.objListDepartment.getListDepartment()) {
			if (depart.getObjManager().getPersonID().equalsIgnoreCase(manager.getPersonID())) {
				depart.setObjManager(null);
			}
		}
		this.objListPerson.getListPerson().remove(manager);
		deleted = true;
		return deleted;
	}

	private boolean deleteStaff(Staff staff) {
		boolean deleted = false;
		// Xóa liên kết task (reset các mã nhân viên của task đang quản lý bởi Nhân sự
		// này là -1).
		for (Task task : this.objListTask.getListTask()) {
			if (task.getPersonID().equalsIgnoreCase(staff.getPersonID())) {
				task.setPersonID("-1");
			}
		}
		this.objListPerson.getListPerson().remove(staff);
		deleted = true;
		return deleted;
	}

	/**
	 * Phương thức xuất ra phòng ban có nhân viên trẻ tuổi nhất.
	 */
	public void phongCoNhanVienTreNhat() {
		int indexAge = 0;
		Personnel ageMin = null;
		ArrayList<Personnel> listHaveDepart = new ArrayList<Personnel>();
		for (Personnel p : this.objListPerson.getListPerson()) {
			if (!(p.getDepartmentID().equalsIgnoreCase("-1"))) {
				listHaveDepart.add(p);
			}
		}
		if (listHaveDepart.size() > 0) {
			ageMin = listHaveDepart.get(0);
			for (Personnel p : listHaveDepart) {
				if (ageMin.getBirthday() < p.getBirthday()) {
					ageMin = p;
					indexAge++;
				}
			}
			System.out.println("Phòng có nhân sự trẻ nhất: ");
			for (int i = indexAge; i < listHaveDepart.size(); i++) {
				Personnel p = listHaveDepart.get(i);
				if (ageMin.getBirthday() == p.getBirthday()) {
					p.showInformation();
					for (Department d : this.objListDepartment.getListDepartment()) {
						if (p.getDepartmentID().equalsIgnoreCase(d.getDepartmentID())) {
							System.out.println("\t" + d.getDepartmentName());
						}
					}
				}
			}
		}
	}

	/**
	 * Phương thức xuất ra thông tin nhân viên thực hiện nhiều task nhất.
	 */
	public void nhanVienCoTaskNhieuNhat() {
		Personnel personTaskMax = null;
		int indexMax = 0;
		ArrayList<Personnel> listPersonHaveTask = getListPersonHaveTask();
		ArrayList<Personnel> listTaskMax = new ArrayList<Personnel>();
		System.out.println("Nhaan vien cos task");
		for (Personnel e : listPersonHaveTask) {
			e.showIdAndName();
		}
		if (listPersonHaveTask.size() > 0) {
			personTaskMax = listPersonHaveTask.get(0);
			for (Personnel p : listPersonHaveTask) {
				if (personTaskMax.getListTask().size() < p.getListTask().size()) {
					personTaskMax = p;
					indexMax++;
					break;
				}
			}
			for (int i = indexMax; i < listPersonHaveTask.size(); i++) {
				Personnel p = listPersonHaveTask.get(i);
				if (personTaskMax.getListTask().size() == p.getListTask().size()) {
					listTaskMax.add(p);
				}
			}
		}

		System.out.println("Nhân viên đang thực hiện nhiều Task nhất: ");
		for (Personnel p : listTaskMax) {
			p.showIdAndName();
		}
	}

	/**
	 * @param listPersonHaveTask
	 */
	private ArrayList<Personnel> getListPersonHaveTask() {
		addTaskToPerson();
		ArrayList<Personnel> listPersonHaveTask = new ArrayList<Personnel>();
		for (Personnel person : this.objListPerson.getListPerson()) {
			if (person.getListTask().size() > 0) {
				listPersonHaveTask.add(person);
			}
		}
		return listPersonHaveTask;
	}

	/**
	 * 
	 */
	private void addTaskToPerson() {
		for (Task task : this.objListTask.getListTask()) {
			for (Personnel person : this.objListPerson.getListPerson()) {
				if (task.getPersonID() != "-1" && task.getPersonID() == person.getPersonID()) {
					person.getListTask().add(task);
				}
			}
		}
	}
}
