package User;

import server.Server;
import java.awt.*;
import javax.swing.*;

import User.ui.UIConst;
import User.ui.panel.*;

public class App {
//no idea
	//i think we can handle filtering and sorting here

	private JFrame frame;

	public static JPanel mainPanelpart;
	public static SMPanel smPanel;
	public static ToolBarPanel toolBarPanel;
//	public static LoginPanel loginPanel;
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
//		loginPanel.buttonStartSchedule.doClick();
	}

	public void initial() {
		UIConst.initial();
		
		frame = new JFrame();
		frame.setBounds(UIConst.MAIN_WINDOW_X, UIConst.MAIN_WINDOW_Y, UIConst.MAIN_WINDOW_WIDTH,
                UIConst.MAIN_WINDOW_HEIGHT);
//        frame.setTitle(UIConst.APP_NAME);
//        frame.setIconImage(UIConst.IMAGE_ICON);
        frame.setBackground(UIConst.MAIN_BACK_COLOR);
        
        JPanel mainPanel = new JPanel(true);
        
        smPanel = new SMPanel();
        
		mainPanelpart = new JPanel(true);
		mainPanelpart.setLayout(new BorderLayout());
		mainPanelpart.add(smPanel, BorderLayout.CENTER);
		
		toolBarPanel = new ToolBarPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(toolBarPanel, BorderLayout.WEST);
		mainPanel.add(mainPanelpart, BorderLayout.CENTER);
		
		frame.add(mainPanel);
		
	}
}
