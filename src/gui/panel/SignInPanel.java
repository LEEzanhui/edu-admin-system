package gui.panel;

import javax.swing.*;

import logic.client.*;
import gui.GUI;
import gui.UIConst;
import logic.server.Message;

import java.awt.*;
import java.net.Socket;

public class SignInPanel extends JPanel {

	private JLabel title;
	private JLabel picture;
	private JLabel usernLabel;
	private JLabel pwdLabel;
	private JTextField username;
	private JTextField password;
	private JButton loginBtn;
	// public JTextArea loginMes;

	private Socket socket = null;

	public SignInPanel(Socket socket) {
		super();
		this.socket = socket;
		initial();
		addListener();
	}

	public void initial() {
		Dimension preferredSize = new Dimension(
			UIConst.MAIN_WINDOW_WIDTH - ToolBarPanel.WIDTH,
			UIConst.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
//		this.setBackground(UIConst.MAIN_BACK_COLOR);
		this.setLayout(new BorderLayout());

		JPanel titlePanel = new JPanel();
		title = new JLabel("Welcome to Educational Administration System");
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.TITLE_FONT);
		titlePanel.add(title);

		JPanel midPanel = new JPanel();
		midPanel.setBackground(UIConst.MAIN_BACK_COLOR);
		GridBagLayout gbLayout = new GridBagLayout();
		midPanel.setLayout(gbLayout);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		picture = new JLabel();
		ImageIcon university = UIConst.UNIVERSITY;
		university.setImage(university.getImage().getScaledInstance(300, 215, Image.SCALE_DEFAULT));
		picture.setIcon(university);

		usernLabel = new JLabel("username", null, JLabel.LEFT);
		username = new JTextField(40);
		username.setPreferredSize(new Dimension(900, 40));
		pwdLabel = new JLabel("password", null, JLabel.LEFT);
		password = new JTextField(40);
		password.setPreferredSize(new Dimension(900, 40));

		loginBtn = new JButton("Sign in");
		loginBtn.setPreferredSize(new Dimension(300, 40));
		// loginMes = new JTextArea("???");						//tell if login fail

		gbLayout.setConstraints(picture, gbc);
		midPanel.add(picture);

		gbc.gridy = 2;
		gbLayout.setConstraints(username, gbc);
		midPanel.add(username);
		gbc.gridy = 3;
		gbLayout.setConstraints(password, gbc);
		midPanel.add(password);
		gbc.gridy = 4;
		gbLayout.setConstraints(loginBtn, gbc);
		midPanel.add(loginBtn);
		gbc.gridy = 5;
//		gbLayout.setConstraints(loginMes, gbc);
//		midPanel.add(loginMes);

		this.add(titlePanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
	}

	public void addListener() {
		loginBtn.addActionListener(e -> {
			Message<Integer> message = new Message<Integer>();		//type doesn't matter, right?

//				could we check the format here?

			message.setOpcode("reg");
			message.setId(username.getText());			//这里改改、将用户、密码发过去
			message.setPassword(password.getText());
//			Client.output(message);						//实际运行时启用
			
			GUI.toolBarPanel.buttonSM.setEnabled(true);		//用于测试，实际运行时删除（点击后启用侧边栏按钮）
			GUI.toolBarPanel.buttonTimetable.setEnabled(true);//用于测试，实际运行时删除
		});
	}
}
