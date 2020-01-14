package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
		title = new JLabel("用户查询");
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.TITLE_FONT);
		titlePanel.add(title);
	
		table = new JTable();
        table.getTableHeader().setBackground(Color.WHITE);
        table.getTableHeader().setForeground(UIConst.TOOL_BAR_BACK_COLOR);
        
		table.setModel(tableModel);
//		table.setEnabled(false);
		table.setRowHeight(36);// 设置表格行宽
		table.getColumnModel().getColumn(0).setPreferredWidth(50);	//设置列宽
		table.getTableHeader().setReorderingAllowed(false);		//不让JTABLE中的列任意换位置
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setPreferredSize(new Dimension(600, 380));
		
        Dimension preferredSize = new Dimension(UIConst.MAIN_WINDOW_WIDTH-ToolBarPanel.WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
        this.setPreferredSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setMinimumSize(preferredSize);
        
        this.setLayout(new BorderLayout());        
        
		JPanel panel = new JPanel();
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
		panel.setBackground(UIConst.MAIN_BACK_COLOR);
		filterText = new JTextField();
		filterText.setPreferredSize(new Dimension(320, 25));
		filterText.setFont(new Font("宋体", Font.PLAIN, 22));

		panel.add(filterText);

		search = new JButton("ID查询");

		panel.add(search);
		

		searchname = new JButton("名字查询");

		panel.add(searchname);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(550, 400));//JTable
		scrollPane.setBackground(UIConst.MAIN_BACK_COLOR);

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
