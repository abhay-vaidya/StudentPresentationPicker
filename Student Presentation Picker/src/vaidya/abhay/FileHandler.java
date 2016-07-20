package vaidya.abhay;

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Class to handle source file
 * @author Abhay Vaidya
 * @version 1.0
 */
public class FileHandler {
		
	// File object, name, and path variables
	public static String fileName;
	public static String filePath;
	public static File sourceFile;
	public Section section = new Section();
		
	/**
	 * Empty constructor
	 */
	public FileHandler() {
	}
	
	/**
	 * Constructor for use with file object
	 * @param inFile - Source file
	 */
	public FileHandler(File inFile){
		sourceFile = inFile;
	}
	
	/**
	 * Gets the file name
	 * @return fileName - Name of file without extension
	 */
	public static String getFileName(){
		fileName = sourceFile.getName().substring(0,6);
		return fileName;
	}
	
	/**
	 * Gets the file path
	 * @return filePath - Path of source file
	 */
	public static String getFilePath(){
		filePath = sourceFile.getAbsolutePath().substring(0,sourceFile.getAbsolutePath().length()-10);
		return filePath;
	}

	/**
	 * Loads students into section object
	 * @param sect - Section for which students will be loaded into
	 */
	public void loadStudents(Section sect){
		section = sect;
		Scanner in;
		int MAX_COMMAS = 5;
		try{
			in = new Scanner(sourceFile);
			String line = "";
			String[] studentInfo;
			in.nextLine();
			while (in.hasNext()){
				line = in.nextLine();
				studentInfo = line.split(",");
				if(studentInfo.length == MAX_COMMAS){
					section.addStudent (new Student(studentInfo[0],studentInfo[1],
							Integer.valueOf(studentInfo[2]),
							Integer.valueOf(studentInfo[3]),
							Integer.valueOf(studentInfo[4])));
				} else {
					throw new Exception(
						"Invalid file format. \nExpected: First Name, Last Name, ID, Content Mark, Delivery Mark");
				}
			}
		}	catch (Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	}
}


		
	

