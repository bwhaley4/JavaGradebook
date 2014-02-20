import java.util.ArrayList;

/**
 * Holds all of the data that needs to be recorded about a semester.
 * 
 * @author Blake Whaley
 * 
 */
public class Semester {
	private String name;
	private ArrayList<Class> class_list;
	public double gpa;

	/**
	 * Constructor.
	 * 
	 * @param name
	 * 			The name of this semester.
	 */
	public Semester(String name) {
		this.name = name;
		class_list = new ArrayList<Class>();
	}

	/**
	 * Add a class to the list of classes for this semester.
	 * 
	 * @param c
	 * 			A valid class object to be added.
	 */
	public void addClass(Class c) {
		class_list.add(c);
	}

	/**
	 * Calculates the gpa for this semester based on all of the
	 * letter grades from every class.
	 */
	public void calculateGpa() {
		int semCredits = 0;
		double gpaSum = 0;
		for (Class c : class_list) {
			semCredits += c.getCredits();
			gpaSum += letterToGpa(c.getLetterGrade());
		}
		setGpa(gpaSum / semCredits);
	}

	/**
	 * Converts a valid letter grade to an equivalent gpa.
	 * (Converts to a standard 4.0 scale)
	 * 
	 * @param letter
	 * 			the letter to be converted to a gpa value.
	 * @return
	 * 			a gpa value on a 4.0 scale.
	 */
	public double letterToGpa(String letter) {
		if (letter.equals("A+") || letter.equals("A")) {
			return 4.0;
		} else if (letter.equals("A-")) {
			return 3 + 2 / 3;
		} else if (letter.equals("B+")) {
			return 3 + 1 / 3;
		} else if (letter.equals("B")) {
			return 3.0;
		} else if (letter.equals("B-")) {
			return 2 + 2 / 3;
		} else if (letter.equals("C+")) {
			return 2 + 1 / 3;
		} else if (letter.equals("C")) {
			return 2;
		} else if (letter.equals("C-")) {
			return 1 + 2 / 3;
		} else if (letter.equals("D+")) {
			return 1 + 1 / 3;
		} else if (letter.equals("D")) {
			return 1;
		} else if (letter.equals("D-")) {
			return 2 / 3;
		}
		return 0;
	}

	/** Getters and Setters */
	/**
	 * Returns the gpa for the semester.
	 * 
	 * @return
	 * 		the gpa.
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Sets the gpa for the semester.
	 * 
	 * @param gpa
	 * 			the gpa value to set.
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Looks for a specific class by name.
	 * 
	 * @param classname
	 * 			the name of class being searched for.
	 * @return
	 * 		If found, returns the class object that corresponds
	 * 		to the name given. Otherwise returns null.
	 */
	public Class getClass(String classname) {
		for (Class c : class_list) {
			if (classname.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Returns the list of classes.
	 * 
	 * @return
	 * 		the list of classes.
	 */
	public ArrayList<Class> getClasses() {
		return class_list;
	}

	/**
	 * Sets the name of the semester.
	 * 
	 * @param n
	 * 		the new name.
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Returns the name of the semester.
	 * 
	 * @return
	 * 		the name of the semester.
	 */
	public String getName() {
		return name;
	}

}
