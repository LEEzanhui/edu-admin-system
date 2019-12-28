package gui.component;

import java.awt.Image;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton{
//	private static final long serialVersionUID = 1L;//???
	private ImageIcon iconEnable, iconDisable;
	private String tip;
	
//	默认图标、激活图标、失效图标
	public IconButton(ImageIcon iconNormal, ImageIcon iconEnable, ImageIcon iconDisable, String tip) {
		super(iconNormal);
		this.iconEnable = iconEnable;
		this.iconEnable.setImage(iconEnable.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT));;
		this.iconDisable = iconDisable;
		this.tip = tip;
		
		initial();
		setup();
	}
	
	public void initial() {
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusable(true);
        this.setMargin(new Insets(0, 0, 0, 0));
	}
	
	public void setup() {
        this.setRolloverIcon(iconEnable);
        // this.setSelectedIcon(iconEnable);
        this.setPressedIcon(iconEnable);
        this.setDisabledIcon(iconDisable);

        if (!tip.equals("")) {
            this.setToolTipText(tip);
        }
	}
}
