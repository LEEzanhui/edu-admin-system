package User.ui;

//import javax.imageio.ImageIO;
//import com.sun.prism.Image;
import javax.swing.*;
import java.awt.*;
//import java.io.File;

public class UIConst {
//	app name
	public final static String APP_NAME = "Yunkai";
//	�����ڴ�С
	public final static int MAIN_WINDOW_X = 240;
	public final static int MAIN_WINDOW_Y = 100;
	public final static int MAIN_WINDOW_WIDTH = 885;
	public final static int MAIN_WINDOW_HEIGHT = 636;
	
//	������ͼ��
	public final static String filepath = "";
	public final static Image IMAGE_ICON = null;	// = ImageIO.read(new File(filepath));
//	������backg color
	public final static Color MAIN_BACK_COLOR = Color.WHITE;
//	������backg color
	public final static Color TOOL_BAR_BACK_COLOR = new Color(37, 174, 96);
//	�����backg color
	public final static Color TABLE_LINE_COLOR = new Color(229, 229, 229);
	
//	����	
	public final static Font FONT = null;	// = new Font(attributes)
	
	public final static ImageIcon ICON_DATA_SYNC = null;
	
//	������ͼ��
//	Ĭ��
	public static ImageIcon ICON_XXX = null;
//	����
	public static ImageIcon ICON_XXX_ENABLE = null;
	
	public static ImageIcon ICON_XXX_DISABLE = null;
	
	public static void initial() {
		ICON_XXX = new ImageIcon("src//User//ui//image//setting.png");
		ICON_XXX_ENABLE = new ImageIcon("src//User//ui//image//atom.png");
		ICON_XXX_DISABLE = new ImageIcon("src//User//ui//image//angel.png");
	}
	
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
