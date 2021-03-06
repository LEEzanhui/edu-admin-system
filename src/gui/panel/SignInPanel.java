package gui.panel;

import javax.swing.*;

import logic.client.*;
import gui.GUI;
import gui.UIConst;
import logic.server.Message;

import java.awt.*;
import java.util.Vector;

public class SignInPanel extends JPanel {

	private JLabel title;
	private JLabel picture;
	private JLabel usernLabel;
	private JLabel pwdLabel;
	private JTextField username;
	private JTextField password;
	private JButton signinBtn;
	private JButton signupBtn;

	private GUI window;


	public SignInPanel(GUI window) {
		super();
		this.window = window;
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
		title = new JLabel("Welcome to Educational Administration System");
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.TITLE_FONT);
		titlePanel.add(title);

		JPanel contentPanel = new JPanel();
//		contentPanel.setBackground(UIConst.BACKGROND);
		contentPanel.setBackground(UIConst.MAIN_BACK_COLOR);

		GridBagLayout gbLayout = new GridBagLayout();
		contentPanel.setLayout(gbLayout);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		JTextArea intro = new JTextArea(
				"\n\n\n\n\n"
				+ "本教务系统旨在帮助学生更方便地管理信息，\n"
				+ "可以帮助你快速查询学校开设课程、并且查询自己地已选课程。\n"
				+ "\n"
				+ "请使用id进行登录。\n"
				+ "如果你是新用户，\n"
				+ "请直接填写用户名注册。");
		intro.setFont(UIConst.INTRO_FONT);
		intro.setEditable(false);
		intro.setLineWrap(true);
		intro.setOpaque(true);
		intro.setBackground(UIConst.INTRO_BACKGROND);
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridheight = 5;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 1;
		gbLayout.setConstraints(intro, gbc);
		contentPanel.add(intro);

		picture = new JLabel();
		ImageIcon university = UIConst.UNIVERSITY;
		int picWidth = (UIConst.MAIN_WINDOW_WIDTH - ToolBarPanel.WIDTH) / 3;
		university.setImage(
			university.getImage().getScaledInstance(picWidth,
					picWidth*university.getIconHeight()/university.getIconWidth(),
					Image.SCALE_DEFAULT));
		picture.setIcon(university);
//		gbc.insets = new Insets(10, 10, 80, 80);
		gbc.ipadx = 85;
		gbc.gridheight = 1;
		gbc.gridwidth = 5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbLayout.setConstraints(picture, gbc);
		contentPanel.add(picture);

		usernLabel = new JLabel("username / id", null, JLabel.CENTER);
		usernLabel.setFont(UIConst.LABEL_FONT);
		contentPanel.add(usernLabel);
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbLayout.setConstraints(usernLabel, gbc);

		username = new JTextField(40);
		username.setFont(new Font("宋体", Font.PLAIN, 22));
//		gbc.ipadx = 10;
//		gbc.ipady = 10;
		gbc.gridx = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 0.4;
		gbc.insets = new Insets(30, 30, 10, 10);
		gbLayout.setConstraints(username, gbc);
		contentPanel.add(username);

		pwdLabel = new JLabel("password", null, JLabel.CENTER);
		pwdLabel.setFont(UIConst.LABEL_FONT);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbLayout.setConstraints(pwdLabel, gbc);
		contentPanel.add(pwdLabel);

		password = new JTextField(40);
		password.setFont(new Font("宋体", Font.PLAIN, 22));
		gbc.gridwidth = 4;
		gbc.gridx = 2;
		gbc.weightx = 0.4;
		gbc.insets = new Insets(30, 30, 10, 10);
		gbLayout.setConstraints(password, gbc);
		contentPanel.add(password);

		signupBtn = new JButton("Sign up");
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.weightx = 0.2;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbLayout.setConstraints(signupBtn, gbc);
		contentPanel.add(signupBtn);

		signinBtn = new JButton("Sign in");
		gbc.gridwidth = 2;
		gbc.gridx = 4;
		gbc.weightx = 0.2;
		gbLayout.setConstraints(signinBtn, gbc);
		contentPanel.add(signinBtn);


		this.add(titlePanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
	}

	public void addListener() {
		signupBtn.addActionListener(e -> {
			Vector<String> vec = new Vector<String>();
			vec.add(username.getText());
			vec.add(password.getText());
			System.out.println("loginPanel: "+vec);
			Message<String> message = new Message<String>("regi", vec);
			Client.send(message);

		});

		signinBtn.addActionListener(e -> {
			Message<String> message = new Message<String>(
					"login", username.getText(), password.getText());
			System.out.println("signinPanel: "+message.getId()+" "+message.getPassword());
			Client.send(message);
		});
	}
}
