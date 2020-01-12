package gui.panel;

import java.awt.*;
import java.net.Socket;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

import logic.client.*;

import gui.GUI;
import gui.UIConst;
import gui.component.MyButtonEditor;
import gui.component.MyButtonRender;
import logic.server.Message;

public class SelectCoursePanel extends JPanel {
	private JTextField filterText;
	private JButton search;
	private JButton update;
	public JTable table;
	
	private TableRowSorter<TableModel> sorter ;
	
//	private Socket socket = null;
	
	private String column_names[]= {"id","name","teacher","other","choose"};	//table header
	
	public TableModel tableModel = new DefaultTableModel(column_names, 8) {
		public boolean isCellEditable(int rowIndex,int columnIndex) {
			if(columnIndex!=4) return false;//这个是可以编辑的列
			//if(rowIndex!=0) return false;
			return true;
		}
	};

	public SelectCoursePanel(GUI window) {
//		this.socket = socket;
		initial();
		addListener();
	}
	
	public void initial() {
		table = new JTable();
		
		table.setModel(tableModel);
//		table.setEnabled(false);
		table.getColumnModel().getColumn(4).setCellRenderer(new MyButtonRender());	//button in table第几列
		table.getColumnModel().getColumn(4).setCellEditor(new MyButtonEditor(table));	//我尝试在这里面实现按钮事件监听
		table.setRowHeight(18);// 设置表格行宽
		table.getColumnModel().getColumn(0).setPreferredWidth(50);	//设置列宽
		table.getTableHeader().setReorderingAllowed(false);		//不让JTABLE中的列任意换位置
		
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
		filterText = new JTextField(13);
		gridbag.setConstraints(filterText, c);
		panel.add(filterText);
		c.gridy = 1;
		search = new JButton("search");
		gridbag.setConstraints(search, c);
		panel.add(search);
		c.gridy = 2;
		update = new JButton("刷新");
		gridbag.setConstraints(update, c);
		panel.add(update);
		
		c.gridy = 3;
		sorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(sorter);
		JScrollPane scrollPane = new JScrollPane(table);		//JTable
		gridbag.setConstraints(scrollPane, c);
		panel.add(scrollPane);
		
		this.add(panel, BorderLayout.CENTER);
	}
	
	public static void setContent() {
		// TODO Auto-generated method stub
		
	}

	public void addListener() {
		search.addActionListener(e -> {
			String text = filterText.getText();
			if (text.length() == 0) {
				sorter.setRowFilter(null);
			}
			else {
				sorter.setRowFilter(RowFilter.regexFilter(text));
			}
		});
		
		update.addActionListener(e -> {
			Vector<String> vec = new Vector<String>();
			vec.add("1");
			Message<String> message = new Message<String>("selecourse", vec);
			message.setOpcode("seacourseid");
			Client.send(message);
			
			//			for test
//			for(int i=0; i<5; i++) {
//				for(int j=0; j<5; j++) {
//					tableModel.setValueAt(i*j, i, j);
//				}
//			}
//			test
		});
	}
}
