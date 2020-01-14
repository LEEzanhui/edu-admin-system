package logic.server;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.*;
import gui.UIConst;
import logic.Configuration;

public class Server {
	private static ServerSocket server;
	static Map<Integer, Socket> sessionMap = new HashMap<Integer, Socket>();
	
	private CourseDB courseDataBase;
	private UserDB userDataBase;
	
	public JFrame serverFrame;
	
	public Server() {
		courseDataBase = new CourseDB();
		userDataBase = new UserDB();
	}
	
	public void socketStart() {
		serverFrame = new JFrame();
		serverFrame.setBounds(UIConst.MAIN_WINDOW_X, UIConst.MAIN_WINDOW_Y,
				400, 300);
		serverFrame.setResizable(false);
        serverFrame.setBackground(UIConst.MAIN_BACK_COLOR);
		serverFrame.setVisible(true);
		serverFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				courseDataBase.writeBack();
				userDataBase.writeBack();
				System.exit(0);
			}
		});
		
		try {
			server = new ServerSocket(Configuration.port);
			System.out.println("Server->java Server");
			System.out.println("Initializing Port...");
			System.out.println("Listening");
			
			int i = 1;
			
			while (true) {
				Socket socket = server.accept();
				if(socket != null) {
					System.out.println("Connect to client!");
					sessionMap.put(i, socket);
					
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(i);
					
					Thread thread = new Thread(new NotifyHandler(socket, userDataBase, courseDataBase));
					thread.setDaemon(true);
					thread.start();
					i++;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new Server().socketStart();
	}

}
