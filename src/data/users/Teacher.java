package data.users;

import data.User;

public class Teacher extends User {
	public Teacher(String id, String name, String other) {
		super(id, name, other);
		authority.add(Authority.TEACHER);
	}
}
