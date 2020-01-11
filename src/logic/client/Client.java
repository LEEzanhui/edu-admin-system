package logic.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import gui.GUI;
import logic.Configuration;
import logic.server.Message;

public class Client {
	private static Socket socket = null;
	private InputStreamReader input = null;
	private InputStream in = null;
	private OutputStream out = null;
	private int clientId;

	private GUI window = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Client client = new Client();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Client() {
		socketStart();
		System.out.println(socket);
		window = new GUI();
		window.frame.setVisible(true);
	}

	public static void output(Message<?> message) {
		try {
			OutputStream out = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);

			oos.writeObject(message);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void socketStart() {
		try {
			socket = new Socket(Configuration.host, Configuration.port);

			in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in);
			clientId = (int)ois.readObject();

			System.out.println("clientId: "+clientId);

			new Thread() {
				public void run() {
					try {
						while(true) {
							in = socket.getInputStream();
							ObjectInputStream ois = new ObjectInputStream(in);
							Message<?> msg = (Message<?>) ois.readObject();
							System.out.println("Receive: " + msg.getOpcode());

//							guess
							handleInput(msg);
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleInput(Message<?> msg) {
		switch (msg.getOpcode()) {
		case "reg":
			window.toolBarPanel.buttonStatus.setEnabled(true);		//用于启用其它功能
			window.toolBarPanel.buttonTimetable.setEnabled(true);//用于启用其它功能
			
			if(true || false) {
				window.mainPanel.removeAll();
				window.mainPanel.add(window.infoPanel, BorderLayout.CENTER);
				window.mainPanel.updateUI();
			}
			
			break;

		default:
			break;
		}
	}
}
