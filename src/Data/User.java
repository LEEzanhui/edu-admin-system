package Data;

import java.util.Vector;

enum Authority{ADMIN, TEACHER, STUDENT};

public class User {
	private String id="";
	private String name="";
	private Vector<Authority> authority = new Vector<Authority>();
	private String other="";
	private Vector<String> courses = new Vector<String>(); // store courses' id

	public User() {}
	public User(String id, String name, Vector<Authority> authority, String other) {
		this.id = id;
		this.name = name;
		this.authority = authority;
		this.other = other;
	}
	public User(String id, String name, Vector<Authority> authority,
				String other, Vector<String> courses) {

		this.id = id;
		this.name = name;
		this.authority = authority;
		this.other = other;
		this.courses = courses;
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
	public Vector<String> courses() {
		return courses;
	}

	public static void main(String[] args) {
		User user = new User();
		User newUser = new User("123", "asd", new Vector<Authority>(), "asd");
		System.out.println(newUser.courses == null);
	}
}
