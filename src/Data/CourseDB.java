package Data;

import java.io.*;
import java.util.*;

public class CourseDB {
	public CourseDB() {
//		readData from outside
		try {
			
			String pathname = "course.txt";
			File filename = new File(pathname);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			while (line != null) {
				line = br.readLine();
				readLine(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readLine(String line) {
//		decode
//		?
	}
	
	public synchronized Course searchByCourseId(String id) {
		for (Map.Entry<String, Course> entry : courseBase.entrySet()) {
			if(id == entry.getKey()) {
				return entry.getValue();
			}
		}
		return null;
//		return new Course();
		//		id from course
	}
//		id from teacher	- return the courses he taught
//		id from student - return the courses he took
	
	public synchronized Vector<Course> searchByName(String courseName) {
		int length = courseName.length();
		if(length <= 3) {
			return null;
		}
		
		Vector<Course> ans = new Vector<Course>();
		for (Map.Entry<String, Course> entry : courseBase.entrySet()) {
			if(entry.getValue().name().substring(0, length) == courseName) {
				ans.add(entry.getValue());
			}
		}
		if(ans.size() == 0)
			return null;
		else
			return ans;
//		fuzzy search
	}
	
	public synchronized void modify(Map<String, Course> in) {
		Iterator<Map.Entry<String, Course>> en = in.entrySet().iterator();
		
		while(en.hasNext()) {
			Map.Entry<String, Course> entry1 = en.next();
			Iterator<Map.Entry<String, Course>> index = courseBase.entrySet().iterator();
			while(index.hasNext()) {
				Map.Entry<String, Course> entry2 = index.next();
				
				if(entry1.getKey() == entry2.getKey()) {
					if(entry1.getValue().name() == null) {
						en.remove();
					}
					else {
						courseBase.put(entry1.getKey(), entry1.getValue());
					}
				}
			}
			
		}
		
//		courseId not exist - new
//		courseId exist - view Course's flag
//			update Course or delete it
//		Do we need batch operation?
	}
	
	private Map<String, Course> courseBase;	//<CourseId, Course>
}