package Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import User.*;

public class NotifyHandler extends Thread {
	Socket socket = null;
	InputStream in = null;
	
	public NotifyHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			in = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(in); 
			
			while(true) {
				try {
					Instruction ins = (Instruction) ois.readObject();
					try {
					
						Instruction newIns = decode(ins);
						
						OutputStream out = socket.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(out);
						oos.writeObject(newIns);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}	
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public Instruction decode(Instruction ins) {
		switch (1) {
		case 1:
			register();
			break;

		default:
			other();
			break;
		}
		
		return new Instruction();
	}
	
	public void register() {
		
	}
	public void other() {}
		
}
