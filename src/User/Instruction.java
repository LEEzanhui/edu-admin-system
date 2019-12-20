package User;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import Data.*;

public class Instruction {
	int opcode;
	String id;
	String password;
	Vector<String> info;		//Store various input information (such as the number of a series of courses)
	Vector<Course> courses;
	Vector<User> users;
	
	public Instruction() {
		info = new Vector<String>();
	}
	
	public int getOpcode() {
		return opcode;
	}
	public String getId() {
		return id;
	}
	public String password() {
		return password;
	}
	public Vector<String> getInfo() {
		return info;
	}
	public Vector<Course> getCourses() {
		return courses;
	}
	public Vector<User> getUsers() {
		return users;
	}
	
	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setInfo(Vector<String> info) {
		this.info = info;
	}
	public void setCourses(Vector<Course> courses) {
		this.courses = courses;
	}
	public void setUsers(Vector<User> users) {
		this.users = users;
	}
}
