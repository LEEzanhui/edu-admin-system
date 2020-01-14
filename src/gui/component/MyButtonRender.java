package gui.component;


import java.awt.Component;
 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
 
public class MyButtonRender implements TableCellRenderer {
	
    private JPanel panel;
    private JButton button;
 
    public MyButtonRender() {
        this.initButton();
        this.initPanel();
        this.panel.add(this.button);
    }
 
    private void initButton() {
        this.button = new JButton("选择");
        this.button.setBounds(0, 0, 130, 36);
    }
 
    private void initPanel() {
        this.panel = new JPanel();
        this.panel.setLayout(null);
    }
 
    public Component getTableCellRendererComponent(
    		JTable table, Object value, boolean isSelected, 
    		boolean hasFocus, int row, int column) {
    	
        return this.panel;
    }
}