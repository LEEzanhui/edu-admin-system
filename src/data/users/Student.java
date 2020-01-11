package data.users;

import java.util.Map;

import data.User;
import data.users.Authority;

public class Student extends User {
    private int grade;
    private int credit;
    private Map<String, Integer> scores;

    public Student(String id, String password, String name, String other,
    				int grade, int credit, Map<String, Integer> scores) {
    	super(id, password, name, other);
    	authority.add(Authority.STUDENT);
    	this.grade = grade;
    	this.credit = credit;
    	this.scores = scores;
    }

	public int grade() {
		return grade;
	}

	public int credit() {
		return credit;
	}

	public Map<String, Integer> scores() {
		return scores;
	}

}
