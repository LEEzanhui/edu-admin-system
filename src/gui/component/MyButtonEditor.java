package gui.component;

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
public class MyButtonEditor extends DefaultCellEditor
{
 
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6546334664166791132L;
 
    private JPanel panel;
 
    private JButton button;
    
    private JTable table;
 
    public MyButtonEditor(JTable table)
    {
        // DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。
        super(new JTextField());
 
        this.table = table;
        
        // 设置点击几次激活编辑。
        this.setClickCountToStart(1);
 
        this.initButton();
 
        this.initPanel();
 
        // 添加按钮。
        this.panel.add(this.button);
    }
 
    private void initButton()
    {
        this.button = new JButton();
 
        // 设置按钮的大小及位置。
        this.button.setBounds(0, 0, 50, 15);
 
        // 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。
        this.button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // 触发取消编辑的事件，不会调用tableModel的setValue方法。
                MyButtonEditor.this.fireEditingCanceled();		//???

                // 这里可以做其它操作。
                // 可以将table传入，通过getSelectedRow,getSelectColumn方法获取到当前选择的行和列及其它操作等。
				int count=table.getSelectedRow();//获取你选中的行号（记录）
				String getname= table.getValueAt(count, 0).toString();//读取你获取行号的某一列的值（也就是字段）
				Message<String> message = new Message<String>();
				
				message.setOpcode("choose this course");
				Vector<String> out = new Vector<String>();
				out.add(getname);				//将课程名传走（暂定）
				message.setVec(out);
				//未完成，想法是将整个课程都传走，这样修改课程和选课可以共用这个函数，只要设置表格可否改动即可
				Client.output(message);
            }
        });
 
    }
 
    private void initPanel()
    {
        this.panel = new JPanel();
 
        // panel使用绝对定位，这样button就不会充满整个单元格。
        this.panel.setLayout(null);
    }
 
 
    /**
     * 这里重写父类的编辑方法，返回一个JPanel对象即可（也可以直接返回一个Button对象，但是那样会填充满整个单元格）
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        // 只为按钮赋值即可。也可以作其它操作。
        this.button.setText(value == null ? "" : String.valueOf(value));
 
        return this.panel;
    }
 
    /**
     * 重写编辑单元格时获取的值。如果不重写，这里可能会为按钮设置错误的值。
     */
    @Override
    public Object getCellEditorValue()
    {
        return this.button.getText();
    }
 }
