package Service;

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
				try {
					Instruction ins = (Instruction) ois.readObject();
					try {
					
						Instruction newIns = decode(ins);
						
						OutputStream out = socket.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(out);
						oos.writeObject(newIns);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}	
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public Instruction decode(Instruction ins) {
		Instruction newIns = new Instruction();
		
		switch (ins.getOpcode()) {		//decide opcode
		case 1:
			newIns = register(ins);
			break;
		case 2:
			newIns = searchCourseById(ins);
			break;
		case 3:
			newIns = searchCourseByName(ins);
			break;
		case 4:
			newIns = modifyCourse(ins);
			break;
		case 5:
			newIns = deleteCourse(ins);
			break;
		case 6:
			newIns = searchUserById(ins);
			break;
		case 7:
			newIns = searchUserByName(ins);
			break;
		case 8:
			newIns = modifyId(ins);
			break;
		case 9:
			newIns = deleteUser(ins);
			break;
		default:
			other();
			break;
		}
		
		return ins;
	}
	
	public Instruction register(Instruction ins) {
		
		
		return ins;
	}
	public Instruction searchCourseById(Instruction ins) {
		Instruction newIns = new Instruction();
		newIns.setId(ins.getId());

		Vector<Course> course = courseDataBase.searchByCourseId(ins.getInfo().get(0));
		newIns.setCourses(course);
		return newIns;
	}
	public Instruction searchCourseByName(Instruction ins) {
		Instruction newIns = new Instruction();
		newIns.setId(ins.getId());

		Vector<Course> course = courseDataBase.searchByName(ins.getInfo().get(0));
		newIns.setCourses(course);
		return newIns;
	}
	public Instruction modifyCourse(Instruction ins) {
		Instruction newIns = new Instruction();
		newIns.setId(ins.getId());
		Vector<String> out = new Vector<String>();
		
		try {
			Map<String, Course> revision = new HashMap<String, Course>();
			for(Course temp : ins.getCourses()) {
				revision.put(temp.courseId(), temp);
			}
			courseDataBase.modify(revision);
		} catch (Exception e) {
			e.getMessage();
			out.add(e.getMessage());
		}
		newIns.setInfo(out);
		return newIns;
	}
	public Instruction deleteCourse(Instruction ins) {
		Instruction newIns = new Instruction();
		newIns.setId(ins.getId());
		Vector<String> out = new Vector<String>();
		
		try {
			courseDataBase.delete(ins.getInfo());
		} catch (Exception e) {
			e.getMessage();
			out.add(e.getMessage());
		}
		newIns.setInfo(out);
		return newIns;
	}
	public Instruction other() {}
		
}
