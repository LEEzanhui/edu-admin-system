package Data;

import java.io.*;
import java.util.*;

import Service.Servicer;

public class CourseDB {
	public CourseDB() {
//		readData from outside
		try {
			
			String pathname = "./course.txt";
			File filename = new File(pathname);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			while (line != null) {
				readIn(line, br);
				line = br.readLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readIn(String line, BufferedReader br) {
//		decode
		try {
			Course ans = new Course();
			ans.setCourseId(line);
			line = br.readLine();
			ans.setName(line);
			line = br.readLine();
			ans.setTeacherId(line);
			
			line = br.readLine();
			Vector<String> student = new Vector<String>();
			while(line.equals("end-student") != true) {
//			for(int i=0; i<2; i++) {
				System.out.println(line);
				student.add(line);
				line = br.readLine();
			}
			
			line = br.readLine();
			line = br.readLine();
			ans.setOtherIndo(line);
			
			courseBase.put(ans.courseId(), ans);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized Course searchByCourseId(String id) {
		for (Map.Entry<String, Course> entry : courseBase.entrySet()) {
			if(id.equals(entry.getKey())) {
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
			if(entry.getValue().name().substring(0, length).equals(courseName)) {
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
	
	public Map<String, Course> courseBase = new HashMap<String, Course>();	//<CourseId, Course>
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CourseDB tempCourseDB = new CourseDB();
		Vector<Course> m = tempCourseDB.searchByName("llll");
		
		String n = m.firstElement().name();
		System.out.println(n);
	}
}