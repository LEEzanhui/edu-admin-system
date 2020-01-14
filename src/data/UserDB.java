package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import data.users.Authority;

public class UserDB {
	private static Map<String, User> users;	// <Userid, User>
	private static String pathname = "data/userDB.txt";

	// readData from file
	public UserDB() {
		users = new HashMap<String, User>();
		try {
			File filename = new File(pathname);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String id = br.readLine();
			while (id != null) {
				importUser(id, br);
				br.readLine();	// read a empty line
				id = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void importUser(String id, BufferedReader br) {
		try {
			String password = br.readLine();
			String name = br.readLine();

			Vector<Authority> authority = new Vector<Authority>();
			String line = br.readLine();
			if (!line.equals("")) {
				String []authStrs = line.split(", ");
				for (String authStr : authStrs)
				  authority.add(Authority.valueOf(authStr));
			}

			String other = br.readLine();

			Vector<String> courses = new Vector<String>();
			line = br.readLine();
			if (!line.equals("")) {
				String []coursesStr = line.split(", ");
				for (String courseStr : coursesStr)
				  courses.add(courseStr);
			}

			User newUser = new User(id, password, name, other, courses);
			users.put(id, newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeBack() {
		File file = new File(pathname);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			for(Map.Entry<String, User> entry : users.entrySet()) {
				out.write(entry.getKey()+"\n");
				out.write(entry.getValue().password()+"\n");
				out.write(entry.getValue().name()+"\n");

				Vector<Authority> authority = entry.getValue().authority();
				for (int i=0; i<authority.size(); i++)
					if (i == 0) out.write(authority.get(i).toString());
					else out.write(", "+authority.get(i).toString());
				out.write("\n");

				out.write(entry.getValue().other()+"\n");

				Vector<String> courses = entry.getValue().courses();
				for (int i=0; i<courses.size(); i++)
				  if (i == 0) out.write(courses.get(i).toString());
				  else out.write(", "+courses.get(i).toString());
				out.write("\n\n");
				out.flush();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized Vector<User> searchById(String id) {
		Vector<User> result = new Vector<User>();
		for(Map.Entry<String, User> entry : users.entrySet())
		  if (isMatched(entry.getKey(), id)) {
//			  System.out.println("!");
			  result.add(entry.getValue());			  
		  }
		return result;
	}

	public synchronized Vector<User> searchByName(String name) {
		Vector<User> result = new Vector<User>();
		for(Map.Entry<String, User> entry : users.entrySet())
		  if (isMatched(entry.getValue().name(), name))
			result.add(entry.getValue());
		return result;
	}

	// create new users or modify user's info
	public synchronized boolean modify(Map<String, User> newUsers) {
		for (Map.Entry<String, User> entry : newUsers.entrySet())
			users.put(entry.getKey(), entry.getValue());
		return true;
	}

	public synchronized boolean delete(Vector<String> usersId) {
		for (String userId : usersId)
			users.remove(userId);
		return true;
	}

	private boolean isMatched(String userKey, String inputKey) {
		int len = inputKey.length();
		if(userKey.length() < len) {
			return false;
		}
//		System.out.println(userKey.substring(0, len));
		return len >= 3 && userKey.substring(0, len).equals(inputKey);
	}

	public boolean pwdMatched(String id, String inputPwd) {
		if(users.containsKey(id))
			return users.get(id).password().equals(inputPwd);
		else
			return false;
	}

	public String getNewId() {
		if (users.keySet().isEmpty())
		  return new String("00000001");
		String maxId = "00000000";
		for (String id : users.keySet()) {
			if (maxId.compareTo(id) < 0)
			  maxId = id;
		}
		String newId = String.format("%08d", Integer.parseInt(maxId)+1);
		return newId;
	}

//	public void print() {
//		for (Map.Entry<String, User> entry : users.entrySet()) {
//			System.out.println(entry.getKey());
//			System.out.println(entry.getValue().name());
//			System.out.println(entry.getValue().authority());
//			System.out.println(entry.getValue().other());
//			System.out.println(entry.getValue().courses());
//		}
//	}
//
	public static void main(String[] args) {
		UserDB userDB = new UserDB();
		String newId = userDB.getNewId();
		System.out.println(newId);
	}
}
