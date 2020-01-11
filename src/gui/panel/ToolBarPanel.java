package gui.panel;

import javax.swing.*;

import gui.GUI;
import gui.UIConst;
import gui.component.IconButton;

import java.awt.*;

public class ToolBarPanel extends JPanel {

	public boolean logIn = false;		//未登录-》登录界面；已登录-》信息界面
	
	public IconButton buttonStatus;
	public IconButton buttonTimetable;
	public IconButton buttonSetting;

	private GUI window;
	
	public static int WIDTH = 48;

	public ToolBarPanel(GUI window) {
		this.window = window;
		initial();
		addButton();
		addListener();
	}

	public void initial() {
        Dimension preferredSize = new Dimension(WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setBackground(UIConst.TOOL_BAR_BACK_COLOR);
        this.setLayout(new GridLayout(2, 1));
	}

	public void addButton() {
		JPanel panelU = new JPanel();	//�������϶�
		panelU.setBackground(UIConst.TOOL_BAR_BACK_COLOR);
		panelU.setLayout(new FlowLayout(-2, -2, 4));
		JPanel panelD = new JPanel();	//�������¶�
		panelD.setBackground(UIConst.TOOL_BAR_BACK_COLOR);
		panelD.setLayout(new BorderLayout(0, 0));

		buttonStatus = new IconButton(UIConst.ICON_STATUS, UIConst.ICON_STATUS_ENABLE, UIConst.ICON_STATUS_DISABLE, "tip");
		buttonTimetable = new IconButton(UIConst.ICON_STATUS, UIConst.ICON_STATUS_ENABLE, UIConst.ICON_STATUS_DISABLE, "???");
//		buttonSetting = null;

		panelU.add(buttonStatus);
//		panelU.add(buttonTimetable);
		panelD.add(buttonTimetable, BorderLayout.SOUTH);

		this.add(panelU);
		this.add(panelD);
	}

	public void addListener() {
		buttonStatus.addActionListener(e -> {
			window.mainPanel.removeAll();
			if(logIn == false)
				window.mainPanel.add(window.signInPanel, BorderLayout.CENTER);
			else
				window.mainPanel.add(window.infoPanel, BorderLayout.CENTER);
			
			window.mainPanel.updateUI();
		});

		buttonTimetable.addActionListener(e -> {
			window.mainPanel.removeAll();
			window.mainPanel.add(window.scPanel, BorderLayout.CENTER);
//			在这边实现update数据
			window.mainPanel.updateUI();
		});

//		...
	}
}
