package Service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import Data.*;

public class Servicer {
	private static ServerSocket server;
	private static int port = 6666;
	private CourseDB courseDataBase;
	private UserDB userDataBase;
	
	static Map<Integer, Socket> sessionMap = new HashMap<Integer, Socket>();
	
	public Servicer() {
		courseDataBase = new CourseDB();		//maybe be restructure
		userDataBase = new UserDB();
	}
	
	public void socketStart() {
		try {
			server = new ServerSocket(port);
			System.out.println("Server->java Server");
			System.out.println("Initializing Port...");
			System.out.println("Listening");
			
			int i = 1;
			
			while (true) {
				Socket socket = server.accept();
				System.out.println("Connect to client!");
				if(socket != null) {
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
		new Servicer().socketStart();
	}

}
