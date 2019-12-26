package User.ui.panel;

import java.awt.*;
import javax.swing.*;

import User.ui.UIConst;

public class SMPanel extends JPanel {

	private JButton buttonOn;
//	private static tex
	
	public SMPanel() {
		initial();
		addButton();
	}
	
	public void initial() {
        Dimension preferredSize = new Dimension(UIConst.MAIN_WINDOW_WIDTH-48, UIConst.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setBackground(UIConst.MAIN_BACK_COLOR);
        this.setLayout(new GridLayout(2, 1));
        
	}
	
	public void addButton() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		buttonOn = new JButton("-_-");
		p.add(buttonOn);
		this.add(p, BorderLayout.CENTER);
	}
	
	public static void setContent() {
		// TODO Auto-generated method stub
		
	}

}
