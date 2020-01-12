package logic.server;

import java.awt.BorderLayout;
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
	private CourseDB courseDataBase;
	private UserDB userDataBase;
	
	public JFrame serverFrame;
	
	static Map<Integer, Socket> sessionMap = new HashMap<Integer, Socket>();
	
	public Server() {
		courseDataBase = new CourseDB();		//maybe be restructure
		userDataBase = new UserDB();
	}
	
	public void socketStart() {
		serverFrame = new JFrame();
		serverFrame.setBounds(UIConst.MAIN_WINDOW_X, UIConst.MAIN_WINDOW_Y,
				UIConst.MAIN_WINDOW_WIDTH, UIConst.MAIN_WINDOW_HEIGHT);
		serverFrame.setResizable(false);
        serverFrame.setBackground(UIConst.MAIN_BACK_COLOR);
		serverFrame.setVisible(true);
		serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel serverPanel = new JPanel(true);
		serverPanel.setLayout(new BorderLayout());
		
		JButton closeButton = new JButton("close server");
		serverPanel.add(closeButton, BorderLayout.CENTER);
		serverFrame.add(serverPanel);
		closeButton.addActionListener(e -> {
			courseDataBase.writeBack();
			userDataBase.writeBack();
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
