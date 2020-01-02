package logic.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import logic.Configuration;
import logic.server.Message;

public class Client {
	private Socket socket = null;
	private InputStreamReader input = null;
	private InputStream in = null;
	private OutputStream out = null;
	private int clientId;
	
	public void socketStart() {
		try {
			socket = new Socket(Configuration.host, Configuration.port);
			
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
				input = new InputStreamReader(System.in);
				String msg = new BufferedReader(input).readLine();
//				Message message = new Message();
				
//				oos.writeObject(message);									
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
}
