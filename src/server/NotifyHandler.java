package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import User.*;
import data.*;
import jdk.nashorn.internal.ir.BreakableNode;

public class NotifyHandler extends Thread {
	private Socket socket = null;
	private InputStream in = null;
	private CourseDB courseDataBase;
	private UserDB userDataBase;

	public NotifyHandler(Socket socket, UserDB userDataBase, CourseDB courseDataBase) {
		this.socket = socket;
		this.userDataBase = userDataBase;
		this.courseDataBase = courseDataBase;
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

		switch (msg.getOpcode()) {		//decide opcode
		case 0:
			exit(msg);
		case 1:
			newMsg = register(msg);
			break;
		case 2:
			newMsg = searchCourseById((Message<String>) msg);
			break;
		case 3:
			newMsg = searchCourseByName((Message<String>) msg);
			break;
		case 4:
			newMsg = modifyCourse((Message<Course>)msg);
			break;
		case 5:
			newMsg = deleteCourse((Message<String>) msg);
			break;
		case 6:
			newMsg = searchUserById((Message<String>) msg);
			break;
		case 7:
			newMsg = searchUserByName((Message<String>) msg);
			break;
		case 8:
			newMsg = modifyUser((Message<User>) msg);
			break;
		case 9:
			newMsg = deleteUser((Message<String>) msg);
			break;
		default:
			other();
			break;
		}
		return newMsg;
	}


	public Message<?> exit(Message<?> msg) {

		return msg;
	}
	public Message<?> register(Message<?> msg) {

		return msg;
	}
	public Message<Course> searchCourseById(Message<String> msg) {
		Message<Course> newMsg = new Message<Course>();
		newMsg.setId(msg.getId());

		Vector<Course> course = courseDataBase.searchByCourseId(msg.getVec().get(0));
		newMsg.setVec(course);
		return newMsg;
	}
	public Message<Course> searchCourseByName(Message<String> msg) {
		Message<Course> newMsg = new Message<Course>();
		newMsg.setId(msg.getId());

		Vector<Course> course = courseDataBase.searchByName(msg.getVec().get(0));
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
			courseDataBase.modify(revision);
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
			courseDataBase.delete(msg.getVec());
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
			userDataBase.delete(msg.getVec());
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
			userDataBase.modify(revision);
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

		Vector<User> user = userDataBase.searchByName(msg.getVec().get(0));
		newMsg.setVec(user);
		return newMsg;
	}

	private Message<User> searchUserById(Message<String> msg) {
		Message<User> newMsg = new Message<User>();
		newMsg.setId(msg.getId());

		Vector<User> user = userDataBase.searchById(msg.getVec().get(0));
		newMsg.setVec(user);
		return newMsg;
	}

	public void other() {}

}
