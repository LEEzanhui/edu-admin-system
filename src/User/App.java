package User;

import Service.*;
import java.awt.*;
import javax.swing.*;

public class App {
//no idea
	//i think we can handle filtering and sorting here
	
	private JFrame frame;
	
	public static JPanel mainPanel;
	public static SMPanel smPanel;
	public static LoginPanel loginPanel;
//	...
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {		//???
			try {
				App window = new App();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		});
	}
	
	public App() {
		initial();
		loginPanel.buttonStartSchedule.doClick();
	}
	
	public void initial() {
		
	}
}
