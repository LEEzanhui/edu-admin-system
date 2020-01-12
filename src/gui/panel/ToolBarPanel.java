package gui.panel;

import javax.swing.*;

import gui.GUI;
import gui.UIConst;
import gui.component.IconButton;

import java.awt.*;

public class ToolBarPanel extends JPanel {
	
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

		buttonStatus = new IconButton(UIConst.ICON_STATUS, UIConst.ICON_STATUS_ENABLE, UIConst.ICON_STATUS_DISABLE, "info");
		buttonTimetable = new IconButton(UIConst.ICON_SCHEDULE, UIConst.ICON_SCHEDULE_ENABLE, UIConst.ICON_SCHEDULE, "selectCourse");
		buttonSetting = new IconButton(UIConst.ICON_ABOUT, UIConst.ICON_SETTING_ENABLE, UIConst.ICON_ABOUT, "searchUser");

		panelU.add(buttonStatus);
		panelU.add(buttonSetting);
		panelD.add(buttonTimetable, BorderLayout.SOUTH);

		this.add(panelU);
		this.add(panelD);
	}

	public void addListener() {
		buttonStatus.addActionListener(e -> {
			window.mainPanel.removeAll();
			window.mainPanel.add(window.infoPanel, BorderLayout.CENTER);
			window.mainPanel.updateUI();
		});

		buttonTimetable.addActionListener(e -> {
			window.mainPanel.removeAll();
			window.mainPanel.add(window.scPanel, BorderLayout.CENTER);
//			在这边实现update数据
			window.mainPanel.updateUI();
		});
		
		buttonSetting.addActionListener(e -> {
			window.mainPanel.removeAll();
			window.mainPanel.add(window.ssPanel, BorderLayout.CENTER);
			window.mainPanel.updateUI();
		});
//		...
	}
}
