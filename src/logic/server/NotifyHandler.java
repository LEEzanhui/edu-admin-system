package logic.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
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
			ObjectInputStream ois = new ObjectInputStream(in);

			while(true) {
					Message<?> msg = (Message<?>) ois.readObject();

					Message<?> newMsg = decode(msg);
					OutputStream out = socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(out);
					oos.writeObject(newMsg);
			}

		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public Message<?> decode(Message<?> msg) {
		Message<?> newMsg = null;

		switch (msg.getOpcode()) {
		case "login":
			newMsg = login(msg);
			break;
		case "exit":
			exit(msg);
			break;
		case "regi":
			newMsg = register((Message<User>)msg);
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
		default:
			other();
			break;
		}
		newMsg.setOpcode(msg.getOpcode());
		return newMsg;
	}

	public Message<Integer> login(Message<?>msg) {
		Message<Integer> newMsg = new Message<Integer>();
		if (userDB.pwdMatched(msg.getId(), msg.getPassword()))
		  newMsg.getVec().add(1);
		else newMsg.getVec().add(0);
		return newMsg;
	}
	public Message<?> exit(Message<?> msg) {

		return msg;
	}
	public Message<Integer> register(Message<User> msg) {
		Map<String, User> newUser = new HashMap<String, User>();
		String newId = userDB.getNewId();
		newUser.put(newId, msg.getVec().firstElement());
		Message<Integer> newMsg = new Message<Integer>();
		if (userDB.modify(newUser))
		  newMsg.getVec().add(1);
		else newMsg.getVec().add(0);
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
