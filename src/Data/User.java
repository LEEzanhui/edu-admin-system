package Data;

import java.util.Vector;

enum Authority{ADMIN, TEACHER, STUDENT};

public class User {
	private String id;
	private String name;
	private Vector<Authority> authority;
	private String other;
	private Vector<String> coursesId = null;


	public User(String id, String name, Vector<Authority> authority,
				String other, Vector<String> coursesId) {

		this.id = id;
		this.name = name;
		this.authority = authority;
		this.other = other;
		this.coursesId = coursesId;
	}

	public String id() {
		return id;
	}
	public String name() {
		return name;
	}
	public Vector<Authority> authority() {
		return authority;
	}
	public String other() {
		return other;
	}
	public Vector<String> coursesId() {
		return coursesId;
	}

	public static void main() {

	}
}
