package data.users;

import data.User;

public class Administrator extends User {
	public Administrator(String id, String name, String other) {
		super(id, name, other);
		authority.add(Authority.ADMIN);
	}

}
