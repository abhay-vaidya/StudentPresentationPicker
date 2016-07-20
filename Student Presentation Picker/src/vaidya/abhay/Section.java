package vaidya.abhay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Section class with students
 * @author Abhay Vaidya
 * @version 1.0
 */
public class Section {
	
	// List of all students
	private static ArrayList <Student> students = new ArrayList<Student>();

	/**
	 * Adds student to list
	 * @param student - New student
	 */
	public void addStudent(Student student) {
		students.add(student);
	}
	
	/**
	 * Determines if all students have been marked or not
	 * @return boolean - true or false
	 */
	public boolean areAllMarked(){
		int unmarked = 0;
		for (int x=0; x < students.size(); x++){
			if (students.get(x).getContentMark() == -1 && students.get(x).getDeliveryMark()== -1){
				unmarked++;
			}
		}
		if (unmarked > 0){
			return false;
		}
		else return true;
	}
	
	/**
	 * Determines how many students are unmarked
	 * @return
	 */
	public int numberUnmarked(){
		int unmarked = 0;
		for (int x=0; x < students.size(); x++){
			if (students.get(x).getContentMark() == -1 && students.get(x).getDeliveryMark()== -1){
				unmarked++;
			}
		}
		return unmarked;
	}
	
	/**
	 * Gets random student from list
	 * @return random - Random student
	 */
	public int getRandomStudent(){
		boolean done = false;
		Random randomGenerator = new Random();
		int random = 0;
			while (done == false){
				random = randomGenerator.nextInt(students.size());
				if (students.get(random).getContentMark() == -1 || students.get(random).getDeliveryMark()== -1){
					done = true;
				}
			}
			return random;
	}
	
	/**
	 * Gets student name
	 * @param index - Index of student in list
	 * @return A student
	 */
	public Student getStudentName(int index){
		return students.get(index);
	}
	
	/**
	 * Enters student's marks
	 * @param studentID - Student's ID
	 * @param contentMark - Student's content mark
	 * @param deliveryMark - Student's delivery mark
	 */
	public void enterMarks (int studentID, int contentMark, int deliveryMark){
		    students.get(studentID).inputContentMark(contentMark);
		    students.get(studentID).inputDeliveryMark(deliveryMark);
		}
	
	/**
	 * Gets number of students in the section
	 * @return Size of student list
	 */
	public int getNumberStudents(){
		return students.size();
	}
	
	/**
	 * Exports the student information into new .csv file
	 */
	public void exportSectionFile(){
		try{
		boolean fileExists = new File(FileHandler.getFilePath() + "/" + FileHandler.getFileName() + "-FINAL.csv").exists();
		
		// If file exists, ask to overwrite
		if (fileExists==true){
			int overwrite = JOptionPane.showConfirmDialog(null, "Would you like to overwrite existing file?", "Overwrite?", JOptionPane.YES_NO_OPTION);
			if (overwrite == JOptionPane.YES_OPTION){
				File outFile = new File (FileHandler.getFilePath() + "/" + FileHandler.getFileName() + "-FINAL.csv");	
				FileWriter out = new FileWriter(outFile);
				BufferedWriter writeFile = new BufferedWriter(out);	
				String firstName;
				String lastName;
				int contentMark;
				int deliveryMark;
				int studentID;
				
				outFile.createNewFile();
				writeFile.write("FIRST,LAST,ID,CONTENT,DELIVERY");
				writeFile.newLine();
				
				for (int x=0; x < students.size(); x++){
					firstName = students.get(x).getFirstName();
					lastName = students.get(x).getLastName();
					studentID = students.get(x).getID();
					contentMark = students.get(x).getContentMark();
					deliveryMark = students.get(x).getDeliveryMark();
					writeFile.write(firstName + "," + lastName + "," + studentID + "," + contentMark + "," + deliveryMark);
					writeFile.newLine();
				}
				writeFile.close();
				out.close();
				JOptionPane.showMessageDialog(null, "File saved.");
			}
			else JOptionPane.showMessageDialog(null, "File not saved.");
		}
		
		// If file does not exist, create and write new file
		else if (fileExists==false) {
			File outFile = new File (FileHandler.getFilePath() + "/" + FileHandler.getFileName() + "-FINAL.csv");	
			FileWriter out = new FileWriter(outFile);
			BufferedWriter writeFile = new BufferedWriter(out);
			String firstName;
			String lastName;
			int contentMark;
			int deliveryMark;
			int studentID;
			
			outFile.createNewFile();
			writeFile.write("FIRST,LAST,ID,CONTENT,DELIVERY");
			writeFile.newLine();
			
			for (int x=0; x < students.size(); x++){
				firstName = students.get(x).getFirstName();
				lastName = students.get(x).getLastName();
				studentID = students.get(x).getID();
				contentMark = students.get(x).getContentMark();
				deliveryMark = students.get(x).getDeliveryMark();
				writeFile.write(firstName + "," + lastName + "," + studentID + "," + contentMark + "," + deliveryMark);
				writeFile.newLine();
			}
			writeFile.close();
			out.close();
			JOptionPane.showMessageDialog(null, "File saved.");
		}
			
	} catch (Exception e){
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
	}
}
		
