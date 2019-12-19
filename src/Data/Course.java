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
	public void setCourseId(String id) {
		this.id = id;
	}
	public String name() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String teacherId() {
		return teacherId;
	}
	public void setTeacherId(String id) {
		this.teacherId = id;
	}
	public Vector<String> student() {
		return student;
	}
	public void setStudent(Vector<String> in) {
		this.student = in;
	}
	public String otherInfo() {
		return otherInfo;
	}
	public void setOtherIndo(String info) {
		this.otherInfo = info;
	}
	
}
