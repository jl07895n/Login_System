import java.util.Scanner;

public class Username {
	
	String username;
	Scanner scan = new Scanner(System.in);
	
	public Username(String ID) {
		username = ID;
	}
	
	public String getName() {
		return username;
	}
	
	public String CreateID() {
		System.out.println("New username: ");
		username = scan.nextLine();
		return username;
	}
	
}