package vaidya.abhay;

/**
 * Student class with marks
 * @author Abhay Vaidya
 * @version 1.0
 */
public class Student {

	// Student variables
	private String firstName;
	private String lastName;
	private int studentID;
	private int contentMark;
	private int deliveryMark;
	
	/**
	 * Student constructor default
	 */
	public Student() {
		this.firstName = "First Name";
		this.lastName = "Last Name";
		this.studentID = 0;
		this.contentMark = 0;
		this.deliveryMark = 0;
	}
	
	/**
	 * Student constructor with values
	 * @param firstName - Student's first name
	 * @param lastName - Student's last name
	 * @param studentID - Student's unique ID
	 * @param content - Student's content mark
	 * @param delivery - Student's delivery mark
	 */
	public Student(String firstName, String lastName, Integer studentID, Integer contentMark, Integer deliveryMark) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.contentMark = contentMark;
		this.deliveryMark = deliveryMark;
	}
	
	/**
	 * Obtain first name
	 * @return firstName
	 */
	public String getFirstName(){
	return firstName;
	}
	
	/**
	 * Obtain last name
	 * @return lastName
	 */
	public String getLastName(){
		return lastName;
	}
	
	/**
	 * Obtain student ID
	 * @return studentID
	 */
	public int getID(){
		return studentID;
	}
	
	/**
	 * Obtain content mark
	 * @return content
	 */
	public int getContentMark(){
		return contentMark;
	}
	
	/**
	 * Obtain delivery mark
	 * @return deliveryMark
	 */
	public int getDeliveryMark(){
		return deliveryMark;
	}
	
	/**
	 * Enter new content mark
	 * @param contentMark
	 */
	public void inputContentMark(int contentMark){
		this.contentMark = contentMark;
	}
	
	/**
	 * Enter new delivery mark
	 * @param deliveryMark
	 */
	public void inputDeliveryMark(int deliveryMark){
		this.deliveryMark = deliveryMark;
	}
	
	/**
	 * Return string value
	 * @Override default method
	 */
	public String toString(){
		return firstName + " " + lastName;
	}
}


