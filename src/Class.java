import java.util.ArrayList;

/**
 * 
 * A class holds all of the data for one class in a semester.
 * 
 * @author Blake Whaley
 * 
 */
public class Class {

	private String name;
	private double grade;
	private String letterGrade;
	private ArrayList<Gradeable> gradeables;
	private int credits;

	/**
	 * Constructor.
	 * 
	 * @param n
	 * 		The title or name to be given to the class.
	 */
	public Class(String n) {
		name = n;
		gradeables = new ArrayList<Gradeable>();
	}

	/**
	 * Calculates the grade in the class on a scale of 1-100.
	 */
	public void calculateClassGrade() {
		double tempGrade = 0;
		double sumWeights = 0;
		for (Gradeable g : gradeables) {
			tempGrade += g.getGrade() * g.getWeight();
			sumWeights += g.getWeight();
		}
		setGrade(tempGrade / (10 * sumWeights));
	}

	/**
	 * Adds a gradeable object to the list of gradeables for
	 * the class.
	 * 
	 * @param g
	 * 		A valid gradeable object to be added.
	 */
	public void addGrade(Gradeable g) {
		gradeables.add(g);
	}

	/**
	 * Based on the grade out of 100 returns a string
	 * representing the letter grade in the class.
	 * 
	 * @param grade
	 * 			Grade to be converted to a letter.
	 * @return
	 * 			The letter grade corresponding to the numerical grade.
	 */
	public String gradeToLetter(double grade) {
		if (grade >= 93) {
			return "A";
		} else if (grade >= 90) {
			return "A-";
		} else if (grade >= 87) {
			return "B+";
		} else if (grade >= 83) {
			return "B";
		} else if (grade >= 80) {
			return "B-";
		} else if (grade >= 77) {
			return "C+";
		} else if (grade >= 73) {
			return "C";
		} else if (grade >= 70) {
			return "C-";
		} else if (grade >= 67) {
			return "D+";
		} else if (grade >= 63) {
			return "D";
		} else if (grade >= 60) {
			return "D-";
		}
		return "F";
	}

	/* Getters and Setters */

	/**
	 * Returns the name/title of class
	 * 
	 * @return
	 * 		the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name/title of the class.
	 * 
	 * @param n
	 * 			The new name for the class.
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Returns the number of credits the class counted for.
	 * 
	 * @return
	 * 		the number of credits.
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the number of credits a class counts for.
	 * 
	 * @param credits
	 * 			the number of credits to set.
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}

	/**
	 * Returns the grade stored for the class.
	 * 
	 * @return the grade
	 */
	public double getGrade() {
		return grade;
	}

	/**
	 * Sets the grade for the class.
	 * 
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}

	/**
	 * The letter grade for the class.
	 * 
	 * @return the letterGrade
	 */
	public String getLetterGrade() {
		return letterGrade;
	}

	/**
	 * Sets the letter grade for the class.
	 * 
	 * @param letterGrade
	 *            the letterGrade to set
	 */
	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	/**
	 * Returns the list of gradeables for the class.
	 * 
	 * @return the gradeables
	 */
	public ArrayList<Gradeable> getGradeables() {
		return gradeables;
	}

	/**
	 * Sets the list of gradeables used for the class.
	 * 
	 * @param gradeables
	 *            the gradeables to set
	 */
	public void setGradeables(ArrayList<Gradeable> gradeables) {
		this.gradeables = gradeables;
	}

}
