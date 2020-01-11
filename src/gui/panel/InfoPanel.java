package gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import gui.GUI;
import gui.UIConst;

public class InfoPanel extends JPanel{
	private JLabel title;
	
	public InfoPanel(GUI window) {
		super();
		initial();
//		addListener();
	}
	
	public void initial() {
		Dimension preferredSize = new Dimension(
				UIConst.MAIN_WINDOW_WIDTH - ToolBarPanel.WIDTH,
				UIConst.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
//		this.setBackground(UIConst.MAIN_BACK_COLOR);
		this.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		title = new JLabel("学业挂科警告");
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.TITLE_FONT);
		titlePanel.add(title);
		
		this.add(titlePanel, BorderLayout.NORTH);
	}
}
