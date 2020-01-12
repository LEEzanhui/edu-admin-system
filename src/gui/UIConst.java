package gui;

import javax.imageio.ImageIO;
//import javax.imageio.ImageIO;
//import com.sun.prism.Image;
import javax.swing.*;
import java.awt.*;
//import java.io.File;
import java.io.File;

public class UIConst {

	public final static String APP_NAME = "Educational Administration System";

	public final static int MAIN_WINDOW_X = 100;
	public final static int MAIN_WINDOW_Y = 40;

	public final static int MAIN_WINDOW_WIDTH = 885;
	public final static int MAIN_WINDOW_HEIGHT = 636;

//	������ͼ��
	public final static String filepath = "";
	
	public final static Image IMAGE_ICON = null;
//	������backg color
	public final static Color MAIN_BACK_COLOR = Color.WHITE;
//	������backg color
	public final static Color TOOL_BAR_BACK_COLOR = new Color(37, 174, 96);
//	�����backg color
	public final static Color TABLE_LINE_COLOR = new Color(229, 229, 229);

//	����
	public final static Font TITLE_FONT = new Font("宋体", Font.BOLD, 30);	// = new Font(attributes)
	
	public final static Font INTRO_FONT = new Font("宋体", Font.BOLD, 18);
	public final static Font LABEL_FONT = new Font("宋体", Font.BOLD, 18);
	
//	public final static ImageIcon BACKGROND = new ImageIcon("resource//icon//background.png"); 
	public final static Color INTRO_BACKGROND = new Color(231, 231, 231);

//	public final static ImageIcon ICON_DATA_SYNC = null;

//	ToolBarIcon
//	default
	public final static ImageIcon ICON_STATUS = new ImageIcon("resource//icon//status.png");
//	enable
	public final static ImageIcon ICON_STATUS_ENABLE = new ImageIcon("resource//icon//statusEnable.png");
//	disable
	public final static ImageIcon ICON_STATUS_DISABLE = new ImageIcon("resource//icon//status.png");
	
	public final static ImageIcon ICON_SETTING = new ImageIcon("resource//icon//setting.png");
	public final static ImageIcon ICON_SETTING_ENABLE = new ImageIcon("resource//icon//settingEnable.png");
	
	public final static ImageIcon ICON_SCHEDULE = new ImageIcon("resource//icon//schedule.png");
	public final static ImageIcon ICON_SCHEDULE_ENABLE = new ImageIcon("resource//icon//scheduleEnable.png");	
	
	public final static ImageIcon ICON_ABOUT = new ImageIcon("resource//icon//about.png");
	
	public final static ImageIcon ICON_UPDATE = new ImageIcon("resource//icon//update.png");
	
//	signin
	public final static ImageIcon UNIVERSITY = new ImageIcon("resource//icon//university.jpg");

    // ��ʽ�������
    /**
     * �����ˮƽ���
     */
    public final static int MAIN_H_GAP = 25;
    /**
     * �����Label ��С
     */
    public final static Dimension LABLE_SIZE = new Dimension(1300, 30);
    /**
     * Item Label ��С
     */
    public final static Dimension LABLE_SIZE_ITEM = new Dimension(78, 30);
    /**
     * Item text field ��С
     */
    public final static Dimension TEXT_FIELD_SIZE_ITEM = new Dimension(400, 24);
    /**
     * radio ��С
     */
    public final static Dimension RADIO_SIZE = new Dimension(1300, 60);
    /**
     * �߼�ѡ�����Item ��С
     */
    public final static Dimension PANEL_ITEM_SIZE = new Dimension(1300, 40);
}
