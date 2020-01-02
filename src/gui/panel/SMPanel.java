package gui.panel;

import java.awt.*;
import java.net.Socket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import gui.UIConst;
import gui.component.MyButtonEditor;
import gui.component.MyButtonRender;
import sun.tools.jar.resources.jar;

public class SMPanel extends JPanel {
	private JTextField textField;
	private JButton search;
	private JTable table;
	
//	test
	Object[][] object;
//	test
	
	private Socket socket = null;
	
	TableModel tableModel = new DefaultTableModel(8, 8);

	public SMPanel(Socket socket) {
		this.socket = socket;
		initial();
		addListener();
	}
	
	public void initial() {
//		test
		table = new JTable();
		table.setModel(tableModel);
		table.getColumnModel().getColumn(2).setCellRenderer(new MyButtonRender());	//button in table
		table.getColumnModel().getColumn(2).setCellEditor(new MyButtonEditor());
		tableModel.setValueAt(1, 0, 0);
//		test
		
        Dimension preferredSize = new Dimension(UIConst.MAIN_WINDOW_WIDTH-ToolBarPanel.WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);

        this.setLayout(new GridLayout(1, 1));        
        
		JPanel panel = new JPanel();
		
		GridBagLayout gridbag = new GridBagLayout();
		panel.setLayout(gridbag);
		panel.setBackground(UIConst.MAIN_BACK_COLOR);
		GridBagConstraints c = new GridBagConstraints();
		
//		c.gridheight = 1;
//		c.gridwidth = 1;
		c.gridy = 0;
		textField = new JTextField(13);
		search = new JButton("press me");
//		table = new JTable();
		
		gridbag.setConstraints(textField, c);
		panel.add(textField);
		gridbag.setConstraints(search, c);
		panel.add(search);
		c.gridy = 1;
		gridbag.setConstraints(table, c);
		panel.add(table);
		
		this.add(panel, BorderLayout.CENTER);
	}
	
	public static void setContent() {
		// TODO Auto-generated method stub
		
	}

	public void addListener() {
		search.addActionListener(e -> {
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					tableModel.setValueAt(i*j, i, j);
				}
			}
		});
	}
}
