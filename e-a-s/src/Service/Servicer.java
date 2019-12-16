package Service;

public class Servicer {
	public Servicer() {}
	
	public void socketStart() {
//		while(true)
//		accept()
		new Thread(new NotifyHandler());
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		(new Servicer()).socketStart();
	}

}
