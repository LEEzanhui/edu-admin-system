package data;

import java.io.Serializable;
import java.util.Vector;

import data.users.Authority;

public class User implements Serializable {
	protected String id="";
	protected String password="password";
	protected String name="";
	protected Vector<Authority> authority = new Vector<Authority>();
	protected String other="";
	protected Vector<String> courses = new Vector<String>(); // store courses' id

	public User() {}
	public User(String id, String password, String name, String other) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.other = other;
	}
	public User(String id, String password, String name,
				String other, Vector<String> courses) {

		this.id = id;
		this.password = password;
		this.name = name;
		this.other = other;
		this.courses = courses;
	}

	public String id() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String password() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String name() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<Authority> authority() {
		return authority;
	}
	public String other() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
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
