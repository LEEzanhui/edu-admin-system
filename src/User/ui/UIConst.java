package User.ui;

//import javax.imageio.ImageIO;
//import com.sun.prism.Image;
import javax.swing.*;
import java.awt.*;
//import java.io.File;

public class UIConst {
//	app name
	public final static String APP_NAME = "Yunkai";
//	主窗口大小
	public final static int MAIN_WINDOW_X = 240;
	public final static int MAIN_WINDOW_Y = 100;
	public final static int MAIN_WINDOW_WIDTH = 885;
	public final static int MAIN_WINDOW_HEIGHT = 636;
	
//	主窗口图标
	public final static String filepath = "";
	public final static Image IMAGE_ICON = null;	// = ImageIO.read(new File(filepath));
//	主窗口backg color
	public final static Color MAIN_BACK_COLOR = Color.WHITE;
//	工具栏backg color
	public final static Color TOOL_BAR_BACK_COLOR = new Color(37, 174, 96);
//	表格线backg color
	public final static Color TABLE_LINE_COLOR = new Color(229, 229, 229);
	
//	字体	
	public final static Font FONT = null;	// = new Font(attributes)
	
	public final static ImageIcon ICON_DATA_SYNC = null;
	
//	工具栏图标
//	默认
	public final static ImageIcon ICON_XXX = null;
//	激活
	public final static ImageIcon ICON_XXX_ENABLE = null;
	
	
    // 样式布局相关
    /**
     * 主面板水平间隔
     */
    public final static int MAIN_H_GAP = 25;
    /**
     * 主面板Label 大小
     */
    public final static Dimension LABLE_SIZE = new Dimension(1300, 30);
    /**
     * Item Label 大小
     */
    public final static Dimension LABLE_SIZE_ITEM = new Dimension(78, 30);
    /**
     * Item text field 大小
     */
    public final static Dimension TEXT_FIELD_SIZE_ITEM = new Dimension(400, 24);
    /**
     * radio 大小
     */
    public final static Dimension RADIO_SIZE = new Dimension(1300, 60);
    /**
     * 高级选项面板Item 大小
     */
    public final static Dimension PANEL_ITEM_SIZE = new Dimension(1300, 40);
}
