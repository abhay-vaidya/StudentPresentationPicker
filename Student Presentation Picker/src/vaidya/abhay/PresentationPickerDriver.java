package vaidya.abhay;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Abhay Vaidya
 * @version 1.0
 * Date Created: 09/01/2016
 * Teacher: Mr. Hutchison
 * Class: ICS4UP
 */
public class PresentationPickerDriver {

	public static void main(String[] args) {
		// Create main window
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new MainFrame("Student Presentation Picker");
				ImageIcon img = new ImageIcon("src/icon.png");
				frame.setIconImage(img.getImage());
				frame.setSize(500, 225);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
	}
}


