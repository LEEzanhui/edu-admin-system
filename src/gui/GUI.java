package gui;

import java.awt.*;
import java.net.Socket;

import javax.swing.*;

import gui.panel.SignInPanel;
import gui.panel.SelectCoursePanel;
import gui.panel.ToolBarPanel;

public class GUI {

	public JFrame frame;

	public static JPanel mainPanelpart;
	public static SelectCoursePanel smPanel;
	public static SignInPanel signInPanel;
	public static ToolBarPanel toolBarPanel;
	
	private Socket socket = null;

	public GUI(Socket socket) {
		this.socket = socket;
		initial();
//		loginPanel.buttonStartSchedule.doClick();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void initial() {
		frame = new JFrame();
		frame.setTitle(UIConst.APP_NAME);
		frame.setBounds(UIConst.MAIN_WINDOW_X, UIConst.MAIN_WINDOW_Y,
				UIConst.MAIN_WINDOW_WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
//		frame.setMaximumSize(
//			new Dimension(UIConst.MAIN_WINDOW_MAX_WIDTH, UIConst.MAIN_WINDOW_MAX_HEIGHT));
//		frame.setMinimumSize(
//			new Dimension(UIConst.MAIN_WINDOW_MIN_WIDTH, UIConst.MAIN_WINDOW_MIN_HEIGHT));
//        frame.setIconImage(UIConst.IMAGE_ICON);
		frame.setResizable(false);
        frame.setBackground(UIConst.MAIN_BACK_COLOR);

        JPanel mainPanel = new JPanel(true);

        smPanel = new SelectCoursePanel(socket);
        signInPanel = new SignInPanel(socket);
        toolBarPanel = new ToolBarPanel();

		mainPanelpart = new JPanel(true);
		mainPanelpart.setLayout(new BorderLayout());
		mainPanelpart.add(signInPanel, BorderLayout.CENTER);
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(toolBarPanel, BorderLayout.WEST);
		mainPanel.add(mainPanelpart, BorderLayout.CENTER);

		frame.add(mainPanel);
	}
}
