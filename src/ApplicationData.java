import java.util.ArrayList;

/**
 * For now, this is the model for the program. 
 * It holds the highest level of the hierarchy.
 * 
 * Hierarchy is as follows:
 * 
 * List of semesters
 * Each semester holds a list of classes
 * Each class holds a list of gradeables
 * 
 * 
 * @author Blake Whaley
 * 
 */
public class ApplicationData {
	private ArrayList<Semester> semesters;

	/**
	 * Constructor.
	 */
	public ApplicationData() {
		// Set up
		semesters = new ArrayList<Semester>();
	}

	/**
	 * Returns the list of semesters for the program.
	 * 
	 * @return the semesters
	 */
	public ArrayList<Semester> getSemesters() {
		return semesters;
	}

	/**
	 * Allows for a new list of semesters to be set
	 * as the data for the program.
	 * 
	 * @param semesters
	 *            the semesters to set
	 */
	public void setSemesters(ArrayList<Semester> semesters) {
		this.semesters = semesters;
	}

	/**
	 * Looks for a semester in the list that has the title <name>.
	 * 
	 * NOTE: This is an inefficient way to do this, look for a better
	 * way to either organize the semesters or search by name.
	 * 
	 * @param name 
	 * 			a string that corresponds to the title being searched for.
	 * 
	 * @return the semester object if found, null if not found.
	 */
	public Semester getSemesterByName(String name) {
		int i = 0;
		while (i < semesters.size()) {
			if (name.equals(semesters.get(i).getName())) {
				return semesters.get(i);
			}
		}
		return null;
	}

	/**
	 * Adds a new semester to the semester list.
	 * @param s 
	 * 			a valid semester object to be added.
	 */
	public void addNewSemester(Semester s) {
		semesters.add(s);
	}

	/**
	 * Removes a semester object from the list.
	 * 
	 * @param s
	 * 			a valid semester object to be removed.
	 * @return
	 * 		true if removal was successful.
	 */
	public boolean removeSemester(Semester s) {
		return semesters.remove(s);
	}

}
