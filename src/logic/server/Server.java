package logic.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import data.*;
import logic.Configuration;

public class Server {
	private static ServerSocket server;
	private CourseDB courseDataBase;
	private UserDB userDataBase;
	
	static Map<Integer, Socket> sessionMap = new HashMap<Integer, Socket>();
	
	public Server() {
		courseDataBase = new CourseDB();		//maybe be restructure
		userDataBase = new UserDB();
	}
	
	public void socketStart() {
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
