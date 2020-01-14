package gui.panel;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data.User;
import gui.GUI;
import gui.UIConst;
import gui.component.MyButtonEditor;
import gui.component.MyButtonRender;
import logic.client.Client;
import logic.server.Message;

public class InfoPanel extends JPanel{
	private User user;
	
	private JLabel title;
	private JLabel usernLabel;
	private JTextField username;
	private JLabel idLabel;
	private JTextField id;
	private JLabel pwdLabel;
	private JTextField password;
	private JLabel othLabel;
	private JTextField other;
	private JButton editBtn;
	private JButton saveBtn;
	private JButton courseBtn;
	
	public JTable table;
	private String column_names[]= {"id","name","teacher","other"};	//table header
	
	public TableModel tableModel = new DefaultTableModel(column_names, 8) {
		public boolean isCellEditable(int rowIndex,int columnIndex) {
//			if(columnIndex!=4) return false;//这个是可以编辑的列
			//if(rowIndex!=0) return false;
			return true;
		}
	};

	public InfoPanel(GUI window) {
		super();
		initial();
		addListener();
	}

	public void initial() {
		Dimension preferredSize = new Dimension(
				UIConst.MAIN_WINDOW_WIDTH - ToolBarPanel.WIDTH,
				UIConst.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setLayout(new BorderLayout());
		this.setBackground(UIConst.MAIN_BACK_COLOR);

		JPanel titlePanel = new JPanel();
		title = new JLabel("个人信息");
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		title.setForeground(UIConst.TOOL_BAR_BACK_COLOR);
		title.setFont(UIConst.TITLE_FONT);
		titlePanel.add(title);
		this.add(titlePanel, BorderLayout.NORTH);

		
		JPanel contentPanel = new JPanel();
		this.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(
				new FlowLayout(FlowLayout.CENTER, 20, 40));
//		contentPanel.setLayout(new GridBagLayout());
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.fill = GridBagConstraints.BOTH;
		
		contentPanel.setBackground(UIConst.MAIN_BACK_COLOR);
		
		idLabel = new JLabel("user id: ");
		contentPanel.add(idLabel);
		
		id = new JTextField();
		id.setColumns(28);
		id.setEditable(false);
		contentPanel.add(id);
		
		usernLabel = new JLabel("username: ");
		contentPanel.add(usernLabel);
		
		username = new JTextField();
		username.setColumns(28);
		username.setEditable(false);
		contentPanel.add(username);
		
		pwdLabel = new JLabel("password: ");
		contentPanel.add(pwdLabel);
		
		password = new JTextField();
		password.setColumns(28);
		password.setEditable(false);
		contentPanel.add(password);
		
		othLabel = new JLabel("remark: ");
		contentPanel.add(othLabel);
		
		other = new JTextField();
		other.setColumns(28);
		other.setEditable(false);
		contentPanel.add(other);
		
		editBtn = new JButton("Edit");
		contentPanel.add(editBtn);
		
		saveBtn = new JButton("Save");
		saveBtn.setEnabled(false);
		contentPanel.add(saveBtn);
		
		courseBtn = new JButton("Selected Course");
		contentPanel.add(courseBtn);
		
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
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(UIConst.MAIN_BACK_COLOR);
		scrollPane.setPreferredSize(new Dimension(550, 400));
		contentPanel.add(scrollPane);
	}
	
	private void addListener() {
		editBtn.addActionListener(e -> {
			editBtn.setEnabled(false);
			saveBtn.setEnabled(true);
			
			username.setEditable(true);
			password.setText(user.password());
			password.setEditable(true);
			other.setEditable(true);
		});
		saveBtn.addActionListener(e -> {
			user.setName(username.getText());
			user.setPassword(password.getText());
			user.setOther(other.getText());
			
			username.setEditable(false);
			password.setEditable(false);
			setShieldPwd(password.getText());
			other.setEditable(false);
			
			saveBtn.setEnabled(false);
			editBtn.setEnabled(true);
			
			Vector<User> vec = new Vector<User>();
			vec.add(user);
			Message<User> message = new Message<User>(
					"moduser", vec);
			Client.send(message);
		});
		courseBtn.addActionListener(e -> {
			Message<String> message = new Message<String>(
					"showcourse", user.courses());
			System.out.println(user.courses());
			Client.send(message);
		});
	}
	
	public void setInfo(User user) {
		this.user = user;
		id.setText(user.id());
		username.setText(user.name());
		setShieldPwd(user.password());
		other.setText(user.other());
	}
	
	private void setShieldPwd(String pwd) {
		String str = "";
		for (int i=0; i<pwd.length(); i++)
			str += "*";
		password.setText(str);
	}
}
