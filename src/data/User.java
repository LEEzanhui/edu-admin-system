package data;

import java.util.Vector;

import data.users.Authority;

public class User {
	protected String id="";
	protected String name="";
	protected Vector<Authority> authority = new Vector<Authority>();
	protected String other="";
	protected Vector<String> courses = new Vector<String>(); // store courses' id

	public User() {}
	public User(String id, String name, String other) {
		this.id = id;
		this.name = name;
		this.other = other;
	}
	public User(String id, String name,
				String other, Vector<String> courses) {

		this.id = id;
		this.name = name;
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

//	public static void main(String[] args) {
		/* case 1 */
		// User user = new User();
		// User newUser = new User("123", "asd", new Vector<Authority>(), "asd");
		// System.out.println(newUser.courses == null);

		// Test
//		String str = "ADMIN";
//		System.out.println((Authority)str);
//	}
}
