package vaidya.abhay;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * Class to define secondary student window
 * @author Abhay Vaidya
 * @version 1.0
 */
public class StudentFrame extends JFrame {
	private JTextField textFieldContentMark;
	private JTextField textFieldDeliveryMark;
	private int studentID;
	public Section section = new Section();
	
	public StudentFrame(String title){
		super(title);
		
		// Set layout manager
		getContentPane().setLayout(new GridLayout(9,1));
		getContentPane().setBackground(Color.decode("#ecf0f1"));
		
		// Padding for labels and text fields
        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        Border texrFieldPadding = BorderFactory.createEmptyBorder(0,10,0,10);
		
		//  Section information label
		JLabel lblSectioninformation = new JLabel("SectionInformation");
		lblSectioninformation.setFont(new Font("Gill Sans MT", Font.BOLD, 12));
		lblSectioninformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSectioninformation.setText("Class of " + FileHandler.getFileName() + " of " + section.getNumberStudents() + " students. " + section.numberUnmarked() + " unmarked students.");
		
        // Student name label
		JLabel lblStudentName = new JLabel("Student Name: ");
		lblStudentName.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lblStudentName.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
		
		// Content mark label
		JLabel lblContentMark = new JLabel("Content Mark: ");
		lblContentMark.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblContentMark.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
		
		// Content mark text field
		textFieldContentMark = new JTextField();
		textFieldContentMark.setColumns(10);
		textFieldContentMark.setBorder(BorderFactory.createCompoundBorder(texrFieldPadding,texrFieldPadding));
		
		// Delivery mark label
		JLabel lblDeliveryMark = new JLabel("Delivery Mark: ");
		lblDeliveryMark.setFont(new Font("Gill Sans MT", Font.PLAIN, 13));
		lblDeliveryMark.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
		
		// Delivery mark text field
		textFieldDeliveryMark = new JTextField();
		textFieldDeliveryMark.setColumns(10);
		textFieldDeliveryMark.setBorder(BorderFactory.createCompoundBorder(texrFieldPadding,texrFieldPadding));
		
		// Button to export final file
		JButton btnExportFile = new JButton("Export File");
		btnExportFile.setEnabled(false);
		btnExportFile.setForeground(Color.WHITE);
		btnExportFile.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
		btnExportFile.setBackground(Color.decode("#27ae60"));
		btnExportFile.setFocusPainted(false);
		btnExportFile.setBorderPainted(false);
		
		// Export button action
		btnExportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int saveDialog = JOptionPane.showConfirmDialog(null, ("Would you like to save to " + FileHandler.getFilePath().substring(0, (FileHandler.getFilePath().length()-1))+ "?"), "Save File", JOptionPane.YES_NO_OPTION);
				if (saveDialog == JOptionPane.YES_OPTION){
					section.exportSectionFile();
					int exitDialog = JOptionPane.showConfirmDialog(null, "Would you like to exit?", "Exit?", JOptionPane.YES_NO_OPTION);
					if (exitDialog == JOptionPane.YES_OPTION){
						System.exit(0);
					}
				}
			}
		});
		
		// Button to save student information
		JButton btnStudentSave = new JButton("Save Student");
		btnStudentSave.setEnabled(false);
		btnStudentSave.setForeground(Color.WHITE);
		btnStudentSave.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
		btnStudentSave.setBackground(Color.decode("#34495e"));
		btnStudentSave.setFocusPainted(false);
		btnStudentSave.setBorderPainted(false);
		
		// Save student button action
		btnStudentSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (textFieldContentMark.getText().equals("") || textFieldDeliveryMark.getText().equals("")){
						JOptionPane.showMessageDialog(null, "You did not enter any/all grades!");
					}
					
					else if (Integer.parseInt(textFieldContentMark.getText())> 100 
							|| Integer.parseInt(textFieldContentMark.getText())< 0
							|| Integer.parseInt(textFieldDeliveryMark.getText())> 100
							|| Integer.parseInt(textFieldDeliveryMark.getText())< 0){
						JOptionPane.showMessageDialog(null, "You did not enter grades between 0-100!");
					}
				
					else if(Integer.parseInt(textFieldContentMark.getText())>=0 &&
							Integer.parseInt(textFieldContentMark.getText())<= 100
							&& Integer.parseInt(textFieldDeliveryMark.getText())>=0
							&& Integer.parseInt(textFieldDeliveryMark.getText())<=100){
								section.enterMarks(studentID, Integer.parseInt(textFieldContentMark.getText()), Integer.parseInt(textFieldDeliveryMark.getText()));
								textFieldContentMark.setText(null);
								textFieldDeliveryMark.setText(null);
								lblStudentName.setText("Student Name: ");
								btnStudentSave.setEnabled(false);
					}
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "You did not enter valid numerical grades!");
				}
				if (section.numberUnmarked()==0){
					btnExportFile.setEnabled(true);
				}
				lblSectioninformation.setText("Class of " + FileHandler.getFileName() + " of " + section.getNumberStudents() + " students. " + section.numberUnmarked() + " unmarked students.");
			} 
		});
		
		// Button to pick random student
		JButton btnPickRandomStudent = new JButton("Pick Random Student");
		btnPickRandomStudent.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
		btnPickRandomStudent.setForeground(Color.WHITE);
		btnPickRandomStudent.setBackground(Color.decode("#3498db"));
		btnPickRandomStudent.setPreferredSize(new Dimension(40,40));
		btnPickRandomStudent.setBorderPainted(false);
		btnPickRandomStudent.setFocusPainted(false);
		
		// Random pick button action
		btnPickRandomStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (section.areAllMarked()== true){
					btnStudentSave.setEnabled(false);
					JOptionPane.showMessageDialog(null, "All students have been marked!");
				}
				else if (section.areAllMarked()==false){
				studentID = section.getRandomStudent();
				lblStudentName.setText("Student Name: " + section.getStudentName(studentID));
				btnStudentSave.setEnabled(true);
				}
			}
		});
					
		// Add components
		getContentPane().add(btnPickRandomStudent);
		getContentPane().add(lblSectioninformation);
		getContentPane().add(lblStudentName);
		getContentPane().add(lblContentMark);
		getContentPane().add(textFieldContentMark);
		getContentPane().add(lblDeliveryMark);
		getContentPane().add(textFieldDeliveryMark);
		getContentPane().add(btnStudentSave);
		getContentPane().add(btnExportFile);
	}
}
