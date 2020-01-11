package gui.panel;

import javax.swing.*;

import gui.GUI;
import gui.UIConst;
import gui.component.IconButton;

import java.awt.*;

public class ToolBarPanel extends JPanel {

	private static IconButton buttonSM;
	private static IconButton buttonTimetable;
	private static IconButton buttonSetting;

	public static int WIDTH = 48;

	public ToolBarPanel() {
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

		buttonSM = new IconButton(UIConst.ICON_XXX, UIConst.ICON_XXX_ENABLE, UIConst.ICON_XXX_DISABLE, "tip");
		buttonTimetable = new IconButton(UIConst.ICON_XXX_ENABLE, UIConst.ICON_XXX_DISABLE, UIConst.ICON_XXX, "???");
//		buttonSetting = null;

		panelU.add(buttonSM);
//		panelU.add(buttonTimetable);
		panelD.add(buttonTimetable, BorderLayout.SOUTH);

		this.add(panelU);
		this.add(panelD);
	}

	public void addListener() {
		buttonSM.addActionListener(e -> {
//			buttonSM.setIcon(UIConst.ICON_XXX_ENABLE);	//be chose
//			buttonTimetable.setIcon(UIConst.ICON_XXX);	//other not chose
//			buttonSetting.setIcon(UIConst.ICON_set);

			GUI.mainPanel.removeAll();
//			SMPanel.setContent();
			GUI.mainPanel.add(GUI.signInPanel, BorderLayout.CENTER);

			GUI.mainPanel.updateUI();
		});

		buttonTimetable.addActionListener(e -> {
			GUI.mainPanel.removeAll();
			GUI.mainPanel.add(GUI.smPanel, BorderLayout.CENTER);

			GUI.mainPanel.updateUI();
		});

//		...
	}
}
