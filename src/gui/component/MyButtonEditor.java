package gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import logic.client.*;
import logic.server.Message;
 
/**
 * 自定义一个往列里边添加按钮的单元格编辑器。最好继承DefaultCellEditor，不然要实现的方法就太多了。
 * 
 */
public class MyButtonEditor extends DefaultCellEditor {
 
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6546334664166791132L;
 
    private JPanel panel;
 
    private JButton button;
    
    private JTable table;
 
    public MyButtonEditor(JTable table) {
        // DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
        super(new JTextField());
 
        this.table = table;
        
        this.setClickCountToStart(1);
 
        this.initButton();
 
        this.initPanel();
 
        this.panel.add(this.button);
    }
 
    private void initButton() {
        this.button = new JButton("已发送");
 
        this.button.setBounds(0, 0, 130, 36);
 
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyButtonEditor.this.fireEditingCanceled();

				int count=table.getSelectedRow();
				String getname= table.getValueAt(count, 0).toString();//读取你获取行号的某一列的值（也就是字段）
				Message<String> message = new Message<String>();
				
				message.setOpcode("choosecourse");
				Vector<String> out = new Vector<String>();
				out.add(getname);
				message.setVec(out);
				Client.send(message);
            }
        });
 
    }
 
    private void initPanel() {
        this.panel = new JPanel();
        this.panel.setLayout(null);
    }
 
 
    /**
     * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.button.setText(value == null ? "" : String.valueOf(value));
        return this.panel;
    }
 
    /**
     * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
     */
    @Override
    public Object getCellEditorValue() {
        return this.button.getText();
    }
 }
