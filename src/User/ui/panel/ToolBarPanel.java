package User.ui.panel;

import javax.swing.*;
import java.awt.*;
import User.ui.IconButton;
import User.ui.UIConst;

public class ToolBarPanel extends JPanel {
	
	private static IconButton buttonSM;
	private static IconButton buttonTimetable;
	private static IconButton buttonSetting;
	
	public ToolBarPanel() {
		initial();
		addButton();
		addListener();
	}
	
	public void initial() {
        Dimension preferredSize = new Dimension(48, UIConst.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setBackground(UIConst.TOOL_BAR_BACK_COLOR);
        this.setLayout(new GridLayout(2, 1));
	}
	
	public void addButton() {
		JPanel panelUp = 
	}
}
