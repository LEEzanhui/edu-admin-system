package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import gui.GUI;
import gui.UIConst;
import logic.client.Client;
import logic.server.Message;

public class searchStuPanel extends JPanel{
	private JLabel title;
	private JTextField filterText;
	private JButton search;
	private JButton searchname;
	public JTable table;
	
	private String column_names[]= {"id","name","other"};
	
	public TableModel tableModel = new DefaultTableModel(column_names, 18) {
		public boolean isCellEditable(int rowIndex,int columnIndex) {
			if(columnIndex!=4) return false;//这个是可以编辑的列
			//if(rowIndex!=0) return false;
			return true;
		}
	};
	
	public searchStuPanel(GUI window) {
		initial();
		addListener();
	}
	
	public void initial() {
		JPanel titlePanel = new JPanel();
		title = new JLabel("searchUser");
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.TITLE_FONT);
		titlePanel.add(title);
	
		table = new JTable();
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setForeground(UIConst.TOOL_BAR_BACK_COLOR);
        
		table.setModel(tableModel);
//		table.setEnabled(false);
		table.setRowHeight(18);// 设置表格行宽
		table.getColumnModel().getColumn(0).setPreferredWidth(50);	//设置列宽
		table.getTableHeader().setReorderingAllowed(false);		//不让JTABLE中的列任意换位置
	
        Dimension preferredSize = new Dimension(UIConst.MAIN_WINDOW_WIDTH-ToolBarPanel.WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);
        
        this.setLayout(new BorderLayout());        
        
		JPanel panel = new JPanel();
		
		GridBagLayout gridbag = new GridBagLayout();
		panel.setLayout(gridbag);
		panel.setBackground(UIConst.MAIN_BACK_COLOR);
		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 1;
//		c.gridx = 0;
		c.gridy = 0;
//		c.weightx = 0.6;
		filterText = new JTextField(30);
		gridbag.setConstraints(filterText, c);
		panel.add(filterText);
//		c.gridx = 1;
		c.gridy = 0;
//		c.weightx = 0.2;
		search = new JButton("searchById");
		gridbag.setConstraints(search, c);
		panel.add(search);
		
//		c.gridx = 2;
		searchname = new JButton("searchByName");
		gridbag.setConstraints(searchname, c);
		panel.add(searchname);
		
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		JScrollPane scrollPane = new JScrollPane(table);		//JTable
		scrollPane.setBackground(UIConst.MAIN_BACK_COLOR);
		gridbag.setConstraints(scrollPane, c);
		panel.add(scrollPane);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
	}
	
	public void addListener() {
		search.addActionListener(e -> {
			String text = filterText.getText();
			Vector<String> vec = new Vector<String>();
			vec.add(text);
			Message<String> message = new Message<String>("selecourse", vec);
			message.setOpcode("seauserid");
			Client.send(message);
		});
		searchname.addActionListener(e -> {
			String text = filterText.getText();
			Vector<String> vec = new Vector<String>();
			vec.add(text);
			Message<String> message = new Message<String>("selecourse", vec);
			message.setOpcode("seausername");
			Client.send(message);
		});
	}
}
