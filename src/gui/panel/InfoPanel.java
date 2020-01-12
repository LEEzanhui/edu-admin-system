package gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import gui.GUI;
import gui.UIConst;

public class InfoPanel extends JPanel{
	private JLabel usernLabel;
	private JTextField username;
	private JLabel idLabel;
	private JTextField id;
	private JButton editBtn;
	private JLabel title;

	public InfoPanel(GUI window) {
		super();
		initial();
		addListener();
	}

	public void initial() {
		Dimension preferredSize = new Dimension(
				UIConst.MAIN_WINDOW_WIDTH - ToolBarPanel.WIDTH,
				UIConst.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setBackground(UIConst.MAIN_BACK_COLOR);
		this.setLayout(new BorderLayout());

		JPanel titlePanel = new JPanel();
		title = new JLabel("个人信息");
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.TITLE_FONT);
		titlePanel.add(title);
		this.add(titlePanel, BorderLayout.NORTH);

		JPanel contentPanel = new JPanel();
		this.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(
				new FlowLayout(FlowLayout.LEFT, 20, 40));
		usernLabel = new JLabel("username");
		username = new JTextField();
		username.setColumns(40);
		idLabel = new JLabel("userid");
		id = new JTextField();
		id.setColumns(40);
		editBtn = new JButton("Edit");
		contentPanel.add(usernLabel);
		contentPanel.add(username);
		contentPanel.add(idLabel);
		contentPanel.add(id);
		contentPanel.add(editBtn);
	}
	
	private void addListener() {
		editBtn.addActionListener(e -> {
			
		});
	}
}
