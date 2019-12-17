package User;

import java.util.ArrayList;
import java.util.List;

public class Instruction {
	int opcode;
	int id;
	int password;
	List<Integer> info;		//Store various input information (such as the number of a series of courses)
	
	public Instruction() {
		info = new ArrayList<Integer>();
	}
}
