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
		Dimension preferredSize = new Dimension(UIConst.MAIN_WINDOW_WIDTH-ToolBarPanel.WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setBackground(UIConst.MAIN_BACK_COLOR);

		GridBagLayout gbLayout = new GridBagLayout();
		this.setLayout(gbLayout);
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel title = new JLabel("Sign in to Educational Administration System");
		gbc.gridheight = 0;
		gbc.gridwidth = 0;
		gbLayout.setConstraints(title, gbc);


		username = new JTextField(13);
		password = new JTextField(13);
		loginBtn = new JButton("Sign in");
		gbLayout.setConstraints(username, gbc);
		this.add(username);
		gbLayout.setConstraints(password, gbc);
		this.add(password);
		gbLayout.setConstraints(loginBtn, gbc);
		this.add(loginBtn);

	}


}
