package gui.panel;

import javax.swing.*;

import gui.UIConst;

import java.awt.*;

public class LoginPanel extends JPanel{
	private JTextField inName;
	private JTextField inPassword;
	private JButton buttonOn;
	
	public LoginPanel() {
		initial();
	}
	
	public void initial() {
        Dimension preferredSize = new Dimension(UIConst.MAIN_WINDOW_WIDTH-48, UIConst.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);
        
        this.setLayout(new GridLayout(1, 1));        
        
		JPanel panel = new JPanel();
		
		GridBagLayout gridbag = new GridBagLayout();
		panel.setLayout(gridbag);
		panel.setBackground(UIConst.MAIN_BACK_COLOR);
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridheight = 1;
		c.gridwidth = 1;
		inName = new JTextField(13);
		inPassword = new JTextField(13);
		buttonOn = new JButton("|^_^|");
		gridbag.setConstraints(inName, c);
		panel.add(inName);
		gridbag.setConstraints(inPassword, c);
		panel.add(inPassword);
		gridbag.setConstraints(buttonOn, c);
		panel.add(buttonOn);
		
		this.add(panel, BorderLayout.CENTER);
	}
	
}
