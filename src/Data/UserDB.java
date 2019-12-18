package Data;

import java.util.Map;

public class UserDB {
	private Map<String, User> userDB;	//<Userid, User>

	public UserDB() {
//		readData from outside
	}
	public synchronized Map<String, User> search() {
		Map<String, User> result;
		for(Map.Entry<String, User> entry : userDB.entrySet()){
			;
		}
		return result;
	}

	public synchronized Map<String, User> modify() {

	}

}
