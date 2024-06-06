/**
 * Staff.java
 * 29-05-2024
 */

/**
 * 
 *
 * @author: Nguyễn Hoàn
 */
public class Staff extends Personnel implements DataInputOutput {

	// Hằng số
	private final static float FRINGE_BENEFITS = 300;
	private final static float BONUS = 1000;
	private final static float ONE_DAY = 200;

	// Constructor
	public Staff() {
		super();
	}

	public Staff(String personId, String personName) {
		super(personId, personName);
	}

	public Staff(String id, String fullName, int birthday, String mail, String phone, float workDay) {
		super(id, fullName, birthday, mail, phone, workDay);
	}

	// Output - Input method
	@Override
	public void showInformation() {
		super.showInformation();
	}

	// Business method
	@Override
	public void payroll() {
		this.salaryPerson = this.workDay * ONE_DAY + FRINGE_BENEFITS;
	}

}
