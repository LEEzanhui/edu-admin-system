package Data;

import java.util.Map;

public class CourseData {
	public public CourseData() {
//		readData from outside
	}
	
	public Map<Integer, Course> search(int id) {
//		id from course
//		id from teacher	- return the courses he taught
//		id from student - return the courses he took
//		how synchronize? jiao jiao me
	}
	
	public Map<Integer, Course> search(String courseName) {
//		fuzzy search
	}
	
	public void modify(Map<Integer, Course> in) {
//		courseId not exist - new
//		courseId exist - view Course's flag
//			update Course or delete it
//		Do we need batch operation?
	}
	
	private Map<Integer, Course> courseBase;	//<CourseId, Course>
}