package User.ui.panel;

import javax.swing.*;
import java.awt.*;

import User.App;
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
		JPanel panelU = new JPanel();	//工具栏上端
		panelU.setBackground(UIConst.TOOL_BAR_BACK_COLOR);
		panelU.setLayout(new FlowLayout(-2, -2, 4));
		JPanel panelD = new JPanel();	//工具栏下端
		panelD.setBackground(UIConst.TOOL_BAR_BACK_COLOR);
		panelD.setLayout(new BorderLayout(0, 0));
		
		buttonSM = new IconButton(UIConst.ICON_XXX, UIConst.ICON_XXX, UIConst.ICON_XXX, "tip");
		buttonTimetable = null;
//		buttonSetting = null;
		
		panelU.add(buttonSM);
//		panelU.add(buttonTimetable);
//		panelD.add(buttonSetting, BorderLayout.SOUTH);
		
		this.add(panelU);
		this.add(panelD);
	}
	
	public void addListener() {
		buttonSM.addActionListener(e -> {
//			buttonSM.setIcon(UIConst.ICON_XXX_ENABLE);	//be chose
//			buttonTimetable.setIcon(UIConst.ICON_XXX);	//other not chose
//			buttonSetting.setIcon(UIConst.ICON_set);
			
			App.mainPanelpart.removeAll();
			SMPanel.setContent();
			App.mainPanelpart.add(App.smPanel, BorderLayout.CENTER);
			
			App.mainPanelpart.updateUI();
		});
		
//		...
	}
}
