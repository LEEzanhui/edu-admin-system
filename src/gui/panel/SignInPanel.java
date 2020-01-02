package gui.panel;

import javax.swing.*;

import gui.UIConst;

import java.awt.*;

public class SignInPanel extends JPanel {

	private JTextField username;
	private JTextField password;
	private JButton loginBtn;

	public SignInPanel() {
		super();
		initial();
		addListener();
	}
	
	public void initial() {
		Dimension preferredSize = new Dimension(UIConst.MAIN_WINDOW_WIDTH-ToolBarPanel.WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setBackground(UIConst.MAIN_BACK_COLOR);
		this.setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel title = new JLabel("Sign in to ¿¬ÉñµÄEducational Administration System");
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.FONT_TITLE);
		upPanel.add(title);
		
		JPanel midPanel = new JPanel();
		GridBagLayout gbLayout = new GridBagLayout();
		midPanel.setLayout(gbLayout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		JLabel picture = new JLabel();
		ImageIcon university = UIConst.UNIVERSITY;
		university.setImage(university.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		picture.setIcon(university);
		
		username = new JTextField(40);
		username.setPreferredSize(new Dimension(900, 40));		//?
		password = new JTextField(40);
		password.setPreferredSize(new Dimension(900, 40));
		loginBtn = new JButton("Sign in");
		loginBtn.setPreferredSize(new Dimension(300, 40));
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

		this.add(upPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
	}

	public void addListener() {
		loginBtn.addActionListener(e -> {
			
		});
	}
}
