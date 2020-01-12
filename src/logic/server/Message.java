package logic.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import data.*;

public class Message <T> {
	private String opcode;
	private String id;
	private String password;

	//Store various input information
	private Vector<T> vec;

	public Message() {
		vec = new Vector<T>();
	}
	public Message(String opcode, Vector<T> vec) {
		this.opcode = opcode;
		this.vec = vec;
	}
	public Message(String opcode, String id, String password) {
		this.opcode = opcode;
		this.id = id;
		this.password = password;
		vec = new Vector<T>();
	}
	public Message(String opcode, String id, String password, Vector<T> vec) {
		this.opcode = opcode;
		this.id = id;
		this.password = password;
		this.vec = vec;
	}

	public String getOpcode() {
		return opcode;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Vector<T> getVec() {
		return vec;
	}
	public void setVec(Vector<T> vec) {
		this.vec = vec;
	}
}
