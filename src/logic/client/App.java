package logic.client;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.*;

import User.ui.*;
import User.ui.panel.*;
import gui.UIConst;
import gui.panel.LoginPanel;
import gui.panel.SMPanel;
import gui.panel.ToolBarPanel;
import logic.server.Message;

public class App {

	private JFrame frame;

	public static JPanel mainPanelpart;
	public static SMPanel smPanel;
	public static LoginPanel loginPanel;
	public static ToolBarPanel toolBarPanel;
	
	private Socket socket = null;
	private InputStreamReader input = null;
	private InputStream in = null;
	private OutputStream out = null;
	private int clientId;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				App window = new App();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public App() {
		initial();
//		loginPanel.buttonStartSchedule.doClick();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        socketStart();
	}
	
	public void socketStart() {
		try {
			InetAddress host = InetAddress.getLocalHost();
			socket = new Socket(host.getHostName(), 6666);
			
			in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			clientId = (int)ois.readObject();
			
			System.out.println("clientId" + clientId);
			
			new Thread() {
				public void run() {
					try {
						while(true) {
							in = socket.getInputStream();
							ObjectInputStream ois = new ObjectInputStream(in);
							Message<?> msg = (Message<?>)ois.readObject();
							System.out.println("Receive: " + msg.getOpcode());
							
//							guess
							handleInput(msg);
						}
					}
					catch (Exception e) {
						e.printStackTrace();e.printStackTrace();
					}
				}
			}.start();
			
			out = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			while(true) {
//				input = new InputStreamReader(System.in);
//				String msg = new BufferedReader(input).readLine();
//				Message message = new Message();
				
				oos.writeObject(message);									
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void handleInput(Message<?> msg) {
		switch (msg.getOpcode()) {
		case 1:
			
			break;

		default:
			break;
		}
	}

	public void initial() {
		frame = new JFrame();
		frame.setBounds(UIConst.MAIN_WINDOW_X, UIConst.MAIN_WINDOW_Y,
				UIConst.MAIN_WINDOW_WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
		frame.setMaximumSize(
			new Dimension(UIConst.MAIN_WINDOW_MAX_WIDTH, UIConst.MAIN_WINDOW_MAX_HEIGHT));
		frame.setMinimumSize(
			new Dimension(UIConst.MAIN_WINDOW_MIN_WIDTH, UIConst.MAIN_WINDOW_MIN_HEIGHT));
        frame.setTitle(UIConst.APP_NAME);
//        frame.setIconImage(UIConst.IMAGE_ICON);
        frame.setBackground(UIConst.MAIN_BACK_COLOR);

        JPanel mainPanel = new JPanel(true);

        smPanel = new SMPanel();
        loginPanel = new LoginPanel();

		mainPanelpart = new JPanel(true);
		mainPanelpart.setLayout(new BorderLayout());
		mainPanelpart.add(smPanel, BorderLayout.CENTER);

		toolBarPanel = new ToolBarPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(toolBarPanel, BorderLayout.WEST);
		mainPanel.add(mainPanelpart, BorderLayout.CENTER);

		frame.add(mainPanel);
	}
}
