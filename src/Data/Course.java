package Data;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class Course {
	private String id;
	private String name;
	private String teacherId;
	private Vector<String> student;	//<studentId>
	private String otherInfo;
	
	public Course() {
		id = null;
		name = null;
		teacherId = null;
		student = null;
		otherInfo = null;
	}
	
	public String courseId() {
		return id;
	}
	public String name() {
		return name;
	}
	public String teacherId() {
		return teacherId;
	}
	public Vector<String> student() {
		return student;
	}
	public String otherInfo() {
		return otherInfo;
	}
	
}
