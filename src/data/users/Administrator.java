package data.users;

import data.User;

public class Administrator extends User {
	public Administrator(String id, String password, String name, String other) {
		super(id, password, name, other);
		authority.add(Authority.ADMIN);
	}

}
