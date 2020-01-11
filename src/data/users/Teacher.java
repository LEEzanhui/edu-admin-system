package data.users;

import data.User;

public class Teacher extends User {
	public Teacher(String id, String password, String name, String other) {
		super(id, password, name, other);
		authority.add(Authority.TEACHER);
	}
}
