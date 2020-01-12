package gui;

import java.awt.*;
import java.net.Socket;

import javax.swing.*;

import gui.panel.*;

public class GUI{

	public JFrame frame;

	public JPanel mainPanel;
	public SelectCoursePanel scPanel;
	public SignInPanel signInPanel;
	public ToolBarPanel toolBarPanel;
	public InfoPanel infoPanel;
	public searchStuPanel ssPanel;

//	private Socket socket = null;

	public GUI() {
//		this.socket = socket;
		initial();
//		loginPanel.buttonStartSchedule.doClick();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void initial() {
		frame = new JFrame();
		frame.setTitle(UIConst.APP_NAME);
		frame.setBounds(UIConst.MAIN_WINDOW_X, UIConst.MAIN_WINDOW_Y,
				UIConst.MAIN_WINDOW_WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
        frame.setIconImage(UIConst.IMAGE_ICON);
		frame.setResizable(false);
        frame.setBackground(UIConst.MAIN_BACK_COLOR);

        JPanel panel = new JPanel(true);

		mainPanel = new JPanel(true);
		mainPanel.setLayout(new BorderLayout());
        
        scPanel = new SelectCoursePanel(this);
        signInPanel = new SignInPanel(this);
        toolBarPanel = new ToolBarPanel(this);
        infoPanel = new InfoPanel(this);
        ssPanel = new searchStuPanel(this);

        toolBarPanel.buttonStatus.setEnabled(false);
        toolBarPanel.buttonTimetable.setEnabled(false);        
        toolBarPanel.buttonSetting.setEnabled(false);
        
        mainPanel.add(signInPanel, BorderLayout.CENTER);
//        mainPanel.add(infoPanel, BorderLayout.CENTER);

		panel.setLayout(new BorderLayout());
		panel.add(toolBarPanel, BorderLayout.WEST);
		panel.add(mainPanel, BorderLayout.CENTER);

		frame.add(panel);
	}
}
