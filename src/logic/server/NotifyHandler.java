package logic.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//import User.*;
import data.*;

public class NotifyHandler extends Thread {

	private Socket socket = null;
	private InputStream in = null;
	private CourseDB courseDB;
	private UserDB userDB;


	public NotifyHandler(Socket socket, UserDB userDB, CourseDB courseDB) {
		this.socket = socket;
		this.userDB = userDB;
		this.courseDB = courseDB;
	}

	public void run() {
		try {
			in = socket.getInputStream();
			System.out.println(socket);
			ObjectInputStream ois = null;	
			
			while(true) {
				Message<?> msg = null;
				Message<?> newMsg = null;
//				try {
					ois = new ObjectInputStream(in);
					msg = (Message<?>) ois.readObject();
					newMsg = decode(msg);						
//				} catch (Exception e) {
//					System.out.println("Connect reset");
//					return;
//				}
				OutputStream out = socket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(out);
				oos.writeObject(newMsg);
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public Message<?> decode(Message<?> msg) {
		Message<?> newMsg = null;

		switch (msg.getOpcode()) {
		case "login":
			newMsg = login((Message<String>)msg);
			break;
		case "exit":
			exit(msg);
			break;
		case "regi":
			newMsg = register((Message<String>)msg);
			break;
		case "seacourseid":
			newMsg = searchCourseById((Message<String>) msg);
			break;
		case "seacoursen":
			newMsg = searchCourseByName((Message<String>) msg);
			break;
		case "modcourse":
			newMsg = modifyCourse((Message<Course>)msg);
			break;
		case "delcourse":
			newMsg = deleteCourse((Message<String>) msg);
			break;
		case "seauserid":
			newMsg = searchUserById((Message<String>) msg);
			break;
		case "seausername":
			newMsg = searchUserByName((Message<String>) msg);
			break;
		case "moduser":
			newMsg = modifyUser((Message<User>) msg);
			break;
		case "deluser":
			newMsg = deleteUser((Message<String>) msg);
			break;
		case "choosecourse":
//			newMsg = choose((Message<String>) msg);
			newMsg = (Message<String>) msg;
			break;
		case "showcouese":
//			newMsg = (Message<?>) 
			break;
		default:
			other();
			break;
		}
		newMsg.setOpcode(msg.getOpcode());
		return newMsg;
	}

	public Message<User> login(Message<String>msg) {
		Message<User> newMsg = new Message<User>();
		if (userDB.pwdMatched(msg.getId(), msg.getPassword())) {
			User user = userDB.searchById(msg.getId()).firstElement();
			newMsg.getVec().add(user);
		} else newMsg.getVec().clear();
		return newMsg;
	}
	public Message<?> exit(Message<?> msg) {

		return msg;
	}
	public Message<User> register(Message<String> msg) {
		Map<String, User> newUsers = new HashMap<String, User>();
		String newId = userDB.getNewId();
		String username = msg.getVec().get(0);
		System.out.println(username);
		String password = msg.getVec().get(1);
		String other = "";
		User newUser = new User(newId, password, username, other);
		
		newUsers.put(newId, newUser);
		Message<User> newMsg = new Message<User>();
		if (userDB.modify(newUsers))
		  newMsg.getVec().add(newUser);
		else newMsg.getVec().clear();;
		return newMsg;
	}
	public Message<Course> searchCourseById(Message<String> msg) {
		Message<Course> newMsg = new Message<Course>();
		newMsg.setId(msg.getId());
		Vector<Course> course = courseDB.searchByCourseId(msg.getVec().get(0));
		newMsg.setVec(course);
		return newMsg;
	}
	public Message<Course> searchCourseByName(Message<String> msg) {
		Message<Course> newMsg = new Message<Course>();
		newMsg.setId(msg.getId());

		Vector<Course> course = courseDB.searchByName(msg.getVec().get(0));
		newMsg.setVec(course);
		return newMsg;
	}
	public Message<String> modifyCourse(Message<Course> msg) {
		Message<String> newMsg = new Message<String>();
		newMsg.setId(msg.getId());
		Vector<String> out = new Vector<String>();

		try {
			Map<String, Course> revision = new HashMap<String, Course>();
			for(Course temp : msg.getVec()) {
				revision.put(temp.courseId(), temp);
			}
			courseDB.modify(revision);
		} catch (Exception e) {
			e.getMessage();
			out.add(e.getMessage());
		}
		newMsg.setVec(out);
		return newMsg;
	}
	public Message<String> deleteCourse(Message<String> msg) {
		Message<String> newMsg = new Message<String>();
		newMsg.setId(msg.getId());
		Vector<String> out = new Vector<String>();

		try {
			courseDB.delete(msg.getVec());
		} catch (Exception e) {
			e.getMessage();
			out.add(e.getMessage());
		}
		newMsg.setVec(out);
		return newMsg;
	}
	private Message<String> deleteUser(Message<String> msg) {
		Message<String> newMsg = new Message<String>();
		newMsg.setId(msg.getId());
		Vector<String> out = new Vector<String>();

		try {
			userDB.delete(msg.getVec());
		} catch (Exception e) {
			e.getMessage();
			out.add(e.getMessage());
		}
		newMsg.setVec(out);
		return newMsg;
	}
	private Message<String> modifyUser(Message<User> msg) {
		Message<String> newMsg = new Message<String>();
		newMsg.setId(msg.getId());
		Vector<String> out = new Vector<String>();

		try {
			Map<String, User> revision = new HashMap<String, User>();
			for(User temp : msg.getVec()) {
				revision.put(temp.id(), temp);
			}
			userDB.modify(revision);
		} catch (Exception e) {
			e.getMessage();
			out.add(e.getMessage());
		}
		newMsg.setVec(out);
		return newMsg;
	}
	private Message<User> searchUserByName(Message<String> msg) {
		Message<User> newMsg = new Message<User>();
		newMsg.setId(msg.getId());

		Vector<User> user = userDB.searchByName(msg.getVec().get(0));
		newMsg.setVec(user);
		return newMsg;
	}
	private Message<User> searchUserById(Message<String> msg) {
		Message<User> newMsg = new Message<User>();
		newMsg.setId(msg.getId());

		Vector<User> user = userDB.searchById(msg.getVec().get(0));
		newMsg.setVec(user);
		return newMsg;
	}

	public void other() {}

}
