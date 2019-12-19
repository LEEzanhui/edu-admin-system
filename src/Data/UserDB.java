package Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class UserDB {
	private Map<String, User> users;	//<Userid, User>

	public UserDB() {
		users = new HashMap<String, User>();
//		readData from outside
	}
	
	public synchronized Vector<User> search(String id) {
		Vector<User> result = new Vector<User>();
		for(Map.Entry<String, User> entry : users.entrySet())
		  if (isMatched(entry.getKey(), id))
			result.add(entry.getValue());
		return result;
	}

	// create new user and modify user's info
	public synchronized void modify(Map<String, User> users) {
		for (Map.Entry<String, User> entry : users.entrySet()) {
			String userId = entry.getKey();
			this.users.put(userId, entry.getValue());
		}
	}

	public synchronized void delete(Vector<String> usersId) {
		for (String userId : usersId)
			users.remove(userId);
	}

	private boolean isMatched(String userKey, String inputKey) {
		int len = inputKey.length();
		return len >= 3 && userKey.substring(0, len).equals(inputKey);
	}
	
	private void print() {
		for (Map.Entry<String, User> entry : users.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue().name());
			System.out.println(entry.getValue().authority());
			System.out.println(entry.getValue().other());
			System.out.println(entry.getValue().courses());
		}
	}


	public static void main(String[] args) {
		UserDB userDB = new UserDB();
//		userDB.users.put("1234", new User());
		Map<String, User> newUsers = new HashMap<String, User>();
		newUsers.put("1234", new User("1234", "name", new Vector<Authority>(), "other"));
		userDB.modify(newUsers);
		userDB.print();
		userDB.users.put("1235", new User());
		userDB.print();
		System.out.println("----\n"+userDB.search("123"));
		Vector<String> delUsers = new Vector<String>();
		delUsers.add("1234");
		userDB.delete(delUsers);
		System.out.println("----\n"+userDB.search("123"));
	}
}
