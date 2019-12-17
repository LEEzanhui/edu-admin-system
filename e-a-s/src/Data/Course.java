package Data;

import java.util.ArrayList;
import java.util.Map;

public class Course {
	private int id;
	private String name;
	private int teacherId;
	private Map<Integer, Double> student;	//<studentId, score>
	private String otherInfo;
	
	private int CourseId() {
		//so on
		return id;
	}
}
