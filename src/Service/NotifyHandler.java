package Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

import User.*;
import data.*;

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
			newIns = searchUserById(ins);
			break;
		case 5:
			newIns = searchUserByName(ins);
			break;
		case 6:
			newIns = modifyCourse(ins);
			break;
		case 7:
			newIns = modifyId(ins);
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
		Vector<Course> course = courseDataBase.searchByCourseId(ins.getInfo().get(0));
		Instruction newIns = new Instruction();
		newIns.setCourses(course);
		return newIns;
	}
	public Instruction searchCourseByName(Instruction ins) {
		Vector<Course> course = courseDataBase.searchByName(ins.getInfo().get(0));
		Instruction newIns = new Instruction();
		newIns.setCourses(course);
		return newIns;
	}
	public Instruction modifyCourse(Instruction ins) {
		
	}
	
	public Instruction other() {}
		
}
