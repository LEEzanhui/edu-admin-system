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
		contentPanel.setBackground(UIConst.MAIN_BACK_COLOR);
		GridBagLayout gbLayout = new GridBagLayout();
		contentPanel.setLayout(gbLayout);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		JPanel introPanel = new JPanel();
		JTextArea intro = new JTextArea("introasd\nsadsadsdsadsad....");
//		intro.setEditable(false);
		introPanel.add(intro);
		gbc.gridheight = 5;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 1;
		gbLayout.setConstraints(introPanel, gbc);
		contentPanel.add(introPanel);

		picture = new JLabel();
		ImageIcon university = UIConst.UNIVERSITY;
		int picWidth = (UIConst.MAIN_WINDOW_WIDTH - ToolBarPanel.WIDTH) / 3;
		university.setImage(
			university.getImage().getScaledInstance(picWidth,
					picWidth*university.getIconHeight()/university.getIconWidth(),
					Image.SCALE_DEFAULT));
		picture.setIcon(university);
		gbc.gridheight = 1;
		gbc.gridwidth = 5;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbLayout.setConstraints(picture, gbc);
		contentPanel.add(picture);

		usernLabel = new JLabel("username", null, JLabel.CENTER);
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.2;
		gbLayout.setConstraints(usernLabel, gbc);
		contentPanel.add(usernLabel);

		username = new JTextField(40);
		username.setPreferredSize(new Dimension(100, 40));
		gbc.gridx = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 0.4;
		gbLayout.setConstraints(username, gbc);
		contentPanel.add(username);

		pwdLabel = new JLabel("password", null, JLabel.CENTER);
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.1;
		gbLayout.setConstraints(pwdLabel, gbc);
		contentPanel.add(pwdLabel);

		password = new JTextField(40);
		password.setPreferredSize(new Dimension(100, 40));
		gbc.gridwidth = 4;
		gbc.gridx = 2;
		gbc.weightx = 0.4;
		gbLayout.setConstraints(password, gbc);
		contentPanel.add(password);

		signupBtn = new JButton("Sign ip");
		signupBtn.setPreferredSize(new Dimension(200, 40));
		gbc.gridwidth = 2;
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.weightx = 0.2;
		gbc.weighty = 0.1;
		gbLayout.setConstraints(signupBtn, gbc);
		contentPanel.add(signupBtn);
		
		signinBtn = new JButton("Sign in");
		signinBtn.setPreferredSize(new Dimension(200, 40));
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
			Message<Integer> message = new Message<Integer>();		//type doesn't matter, right?

//				could we check the format here?

			message.setOpcode("reg");
			message.setId(username.getText());			//这里改改、将用户、密码发过去
			message.setPassword(password.getText());
//			Client.output(message);						//实际运行时启用

			window.toolBarPanel.buttonStatus.setEnabled(true);		//用于测试，实际运行时删除（点击后启用侧边栏按钮）
			window.toolBarPanel.buttonTimetable.setEnabled(true);//用于测试，实际运行时删除
			window.mainPanel.removeAll();//用于测试，实际运行时删除
			window.mainPanel.add(window.infoPanel, BorderLayout.CENTER);//用于测试，实际运行时删除
			window.mainPanel.updateUI();//用于测试，实际运行时删除
		});
	}
}
