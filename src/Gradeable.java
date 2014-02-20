/**
 * A gradeable holds the data for all things that can be graded in a class.
 * 
 * @author Blake Whaley
 * 
 */
public class Gradeable {

	private double grade;
	private double maxPoints;
	private double recievedPoints;
	private String name;
	private double weight;

	/**
	 * Constructor.
	 * 
	 * @param n
	 * 			The name to be given to the gradeable.
	 */
	public Gradeable(String n) {
		name = n;
	}

	/* Getters and Setters */

	/**
	 * The numerical grade for the object.
	 * 
	 * @return the grade
	 */
	public double getGrade() {
		return grade;
	}

	/**
	 * Sets the numerical grade.
	 * 
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}

	/**
	 * The maximum points achievable for the assignment.
	 * 
	 * @return the maxPoints
	 */
	public double getMaxPoints() {
		return maxPoints;
	}

	/**
	 * Sets the maximum amount of achievable points.
	 * 
	 * @param maxPoints
	 *            the maxPoints to set
	 */
	public void setMaxPoints(double maxPoints) {
		this.maxPoints = maxPoints;
	}

	/**
	 * Returns the number of points awarded for the assignment.
	 * 
	 * @return the recievedPoints
	 */
	public double getRecievedPoints() {
		return recievedPoints;
	}

	/**
	 * Sets the number of points awarded for the assignment.
	 * 
	 * @param recievedPoints
	 *            the recievedPoints to set
	 */
	public void setRecievedPoints(double recievedPoints) {
		this.recievedPoints = recievedPoints;
	}

	/**
	 * The name/title of the gradeable.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name/title.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the wieght assigned to this assignment.
	 * 
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the weight assigned to this assignment.
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

}
