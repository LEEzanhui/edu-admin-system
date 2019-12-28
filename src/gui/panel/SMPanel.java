package gui.panel;

import java.awt.*;
import javax.swing.*;

import gui.UIConst;

public class SMPanel extends JPanel {
	private JTextField textField;
	private JButton buttonOn;
//	private static tex
	
	public SMPanel() {
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
		textField = new JTextField(13);
		buttonOn = new JButton("-_-");
		gridbag.setConstraints(textField, c);
		panel.add(textField);
		gridbag.setConstraints(buttonOn, c);
		panel.add(buttonOn);

		this.add(panel, BorderLayout.CENTER);
	}
	
	public static void setContent() {
		// TODO Auto-generated method stub
		
	}

}
