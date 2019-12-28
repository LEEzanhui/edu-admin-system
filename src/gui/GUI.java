package gui;

import java.awt.*;

import javax.swing.*;

import gui.panel.SignInPanel;
import gui.panel.SMPanel;
import gui.panel.ToolBarPanel;

public class GUI {

	private JFrame frame;

	public static JPanel mainPanelpart;
	public static SMPanel smPanel;
	public static SignInPanel signInPanel;
	public static ToolBarPanel toolBarPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GUI window = new GUI();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GUI() {
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

        smPanel = new SMPanel();
        signInPanel = new SignInPanel();

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
