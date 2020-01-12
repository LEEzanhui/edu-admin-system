package data;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import logic.server.Server;

public class CourseDB {

	private Map<String, Course> courses = new HashMap<String, Course>();	//<CourseId, Course>

	// readData from outside
	public CourseDB() {
		try {

			String pathname = "./data/courseDB.txt";
			File filename = new File(pathname);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			while (line != null) {
				readIn(line, br);
				line = br.readLine();
			}
			reader.close();
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
			System.out.println(line);
			line = br.readLine();
			ans.setTeacherId(line);
			System.out.println(line);

			line = br.readLine();
			System.out.println(line);
			
			Vector<String> student = new Vector<String>();
			while(line.equals("end-student") != true) {
				student.add(line);
//				System.out.println(line);
				line = br.readLine();
//				System.out.println(line);
			}
			ans.setStudent(student);

			line = br.readLine();
//			line = br.readLine();
			ans.setOtherIndo(line);
			
			courses.put(ans.courseId(), ans);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void writeBack() {	//unchecked!
		File file = new File("./data/courseDB.txt");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			for(Map.Entry<String, Course> entry : courses.entrySet()) {
				out.write(entry.getKey()+"\n");
				out.write(entry.getValue().name()+"\n");
//				System.out.println(entry.getValue().name());
				out.write(entry.getValue().teacherId()+"\n");
				out.flush();

				Vector<String> stud = entry.getValue().student();
				System.out.println(stud == null);
				for(int i=0; i<stud.size(); i++) {
					out.write(stud.get(i)+"\n");
				}
				out.write("end-student\n");
				out.write(entry.getValue().other()+"\n");
				out.flush();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized Vector<Course> searchByCourseId(String id) {
		int length = id.length();
		Vector<Course> ans = new Vector<Course>();
//		if(length <= 3) {
//			return ans;
//		}

		for (Map.Entry<String, Course> entry : courses.entrySet()) {
			if(entry.getValue().courseId().substring(0, length).equals(id)) {
				ans.add(entry.getValue());
			}
		}

		return ans;
//		return new Course();
		//		id from course
	}
//		id from teacher	- return the courses he taught
//		id from student - return the courses he took

	public synchronized Vector<Course> searchByName(String courseName) {
		int length = courseName.length();
		Vector<Course> ans = new Vector<Course>();
		if(length <= 3) {
			return ans;
		}

		for (Map.Entry<String, Course> entry : courses.entrySet()) {
			if(entry.getValue().name().substring(0, length).equals(courseName)) {
				ans.add(entry.getValue());
			}
		}
		return ans;
//		fuzzy search
	}

	public synchronized void modify(Map<String, Course> in) throws Exception {	//think about throw exception(unchecked)
		Iterator<Map.Entry<String, Course>> en = in.entrySet().iterator();

		while(en.hasNext()) {
			Map.Entry<String, Course> entry1 = en.next();
			Iterator<Map.Entry<String, Course>> index = courses.entrySet().iterator();
			boolean flag = false;
			while(index.hasNext()) {
				Map.Entry<String, Course> entry2 = index.next();

				if(entry1.getKey() == entry2.getKey()) {
					flag = true;
					courses.put(entry1.getKey(), entry1.getValue());
				}
			}

			if(flag == false)
				throw new Exception("courseId: "+entry1.getKey()+" not exist!");
		}

//		courseId not exist - new
//		courseId exist - view Course's flag
//			update Course or delete it
//		Do we need batch operation?
	}

	public synchronized void delete(Vector<String> courseId) throws Exception {	//think about throw exception
		for (String course : courseId)
			if(courses.remove(course) == null)
				throw new Exception("courseId: "+course+" not exist!");
	}


//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		CourseDB tempCourseDB = new CourseDB();
//		Vector<Course> m = tempCourseDB.searchByName("llll");
//
//		String n = m.firstElement().name();
//		System.out.println(n);
//	}
}
