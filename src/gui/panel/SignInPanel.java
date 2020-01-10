package gui.panel;

import javax.swing.*;

import logic.client.*;

import gui.UIConst;
import logic.server.Message;

import java.awt.*;
import java.net.Socket;

public class SignInPanel extends JPanel {

	private JTextField username;
	private JTextField password;
	private JButton loginBtn;
	private JTextArea loginMes;
	
	private Socket socket = null;

	public SignInPanel(Socket socket) {
		super();
		this.socket = socket;
		initial();
		addListener();
	}
	
	public void initial() {
		Dimension preferredSize = new Dimension(UIConst.MAIN_WINDOW_WIDTH-ToolBarPanel.WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
//		this.setBackground(UIConst.MAIN_BACK_COLOR);
		this.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel title = new JLabel("Sign in to 口口Educational Administration System");
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.FONT_TITLE);
		upPanel.add(title);
		
		JPanel midPanel = new JPanel();
		midPanel.setBackground(UIConst.MAIN_BACK_COLOR);
		GridBagLayout gbLayout = new GridBagLayout();
		midPanel.setLayout(gbLayout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		JLabel picture = new JLabel();					//set image size
		ImageIcon university = UIConst.UNIVERSITY;
		university.setImage(university.getImage().getScaledInstance(300, 215, Image.SCALE_DEFAULT));
		picture.setIcon(university);
		
		username = new JTextField(40);
		username.setPreferredSize(new Dimension(900, 40));		//?
		password = new JTextField(40);
		password.setPreferredSize(new Dimension(900, 40));
		loginBtn = new JButton("Sign in");
		loginBtn.setPreferredSize(new Dimension(300, 40));
		loginMes = new JTextArea("???");						//tell if login fail

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
		gbLayout.setConstraints(loginMes, gbc);
		midPanel.add(loginMes);

		this.add(upPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
	}

	public void addListener() {
		loginBtn.addActionListener(e -> {
				Message<Integer> message = new Message<Integer>();		//type doesn't matter, right?
				
//				could we check the format here?
				
				message.setOpcode("reg");
				message.setId(username.getText());
				message.setPassword(password.getText());
				Client.output(message);
		});
	}
}
