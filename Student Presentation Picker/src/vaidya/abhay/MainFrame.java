package vaidya.abhay;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class to define main window
 * @author Abhay Vaidya
 * @version 1.0
 */
public class MainFrame extends JFrame {
	public MainFrame(String title){
		super(title);
		
		// Set layout manager
		getContentPane().setLayout(new GridLayout(4,4));
		getContentPane().setBackground(Color.decode("#ecf0f1"));
		
        // Padding for labels
        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        Border authorPadding = BorderFactory.createEmptyBorder(2,0,2,0);
        
		// Load file button
		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.setFont(new Font("Gill Sans MT", Font.PLAIN, 17));
		btnLoadFile.setForeground(Color.WHITE);
		btnLoadFile.setBackground(Color.decode("#27ae60"));
		btnLoadFile.setFocusPainted(false);
		btnLoadFile.setBorderPainted(false);
		
		// Load file button action
		btnLoadFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				// Create file chooser component and set default location to desktop
				String userhome = System.getProperty("user.home");
				JFileChooser fileChooser = new JFileChooser(userhome + "\\Desktop");
				
				// File type filter
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);		
				fileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File","csv");
				fileChooser.setFileFilter(filter);
				
				// Create new window to enter student info and delete current window
				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					FileHandler presentation = new FileHandler(fileChooser.getSelectedFile());
					btnLoadFile.setVisible(false);
					Section section = new Section();
					presentation.loadStudents(section);
					if(section != null){
						JFrame frame = new StudentFrame("Add Marks");
						ImageIcon img = new ImageIcon("src/icon.png");
						frame.setIconImage(img.getImage());
						frame.setSize(350, 400);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						dispose();
					}
				}
			}
			
		});

		// Title label
		JLabel lblTitle = new JLabel("Welcome to the Student Presentation Picker!");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setForeground(Color.decode("#27ae60"));
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		lblTitle.setBorder(BorderFactory.createCompoundBorder(paddingBorder,paddingBorder));
		
		// Author label
		JLabel lblAuthor = new JLabel("By Abhay Vaidya");
		lblAuthor.setVerticalAlignment(SwingConstants.TOP);
		lblAuthor.setForeground(Color.decode("#95a5a6"));
		lblAuthor.setBackground(new Color(255, 255, 255));
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblAuthor.setBorder(BorderFactory.createCompoundBorder(authorPadding,authorPadding));
		
        // Instructions label
		JLabel lblBodyinfo = new JLabel("To begin, load a new .csv file of your students");
		lblBodyinfo.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblBodyinfo.setHorizontalAlignment(SwingConstants.CENTER);
			
		// Add components
		getContentPane().add(lblTitle, BorderLayout.NORTH);
		getContentPane().add(lblAuthor, BorderLayout.CENTER);
		getContentPane().add(lblBodyinfo, BorderLayout.CENTER);
		getContentPane().add(btnLoadFile, BorderLayout.SOUTH);	
	}
}
