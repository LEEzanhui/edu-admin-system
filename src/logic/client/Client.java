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
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data.Course;
import data.User;
import gui.GUI;
import gui.component.MyButtonEditor;
import gui.component.MyButtonRender;
import logic.Configuration;
import logic.server.Message;

public class Client implements java.io.Serializable{
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

	public static void send(Message<?> message) {
		try {
			System.out.println("Client"+message.getVec());
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

	@SuppressWarnings("unchecked")
	public void handleInput(Message<?> msg) {
		switch (msg.getOpcode()) {
		case "regi": case "login": 
			Vector<Integer> vec = (Vector<Integer>) msg.getVec();
			if(vec.firstElement() == 1) {
				window.toolBarPanel.buttonStatus.setEnabled(true);		//用于启用其它功能
				window.toolBarPanel.buttonTimetable.setEnabled(true);//用于启用其它功能
				window.toolBarPanel.buttonSetting.setEnabled(true);
				window.mainPanel.removeAll();
				window.mainPanel.add(window.infoPanel, BorderLayout.CENTER);
				window.mainPanel.updateUI();
			}
			break;
		case "seacourseid":
			Vector<Course> vec1 = (Vector<Course>)msg.getVec();

			for(int i=0; i<vec1.size(); i++) {
				window.scPanel.tableModel.setValueAt(vec1.get(i).courseId(), i, 0);
				window.scPanel.tableModel.setValueAt(vec1.get(i).name(), i, 1);
				window.scPanel.tableModel.setValueAt(vec1.get(i).teacherId(), i, 2);
				window.scPanel.tableModel.setValueAt(vec1.get(i).other(), i, 3);
			}
		case "seauserid":case "seausername":
			Vector<User> vec11 = (Vector<User>)msg.getVec();
//			System.out.println(vec11.size());
			for(int i=0; i<18; i++) {
				for(int j=0; j<3; j++) {
					window.ssPanel.tableModel.setValueAt("", i, j);
				}
			}
			for(int i=0; i<vec11.size(); i++) {
				window.ssPanel.tableModel.setValueAt(vec11.get(i).id(), i, 0);
				window.ssPanel.tableModel.setValueAt(vec11.get(i).name(), i, 1);
				window.ssPanel.tableModel.setValueAt(vec11.get(i).other(), i, 2);
			}
		default:
			break;
		}
	}
}
