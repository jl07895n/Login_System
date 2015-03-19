import java.util.Scanner;

public class Password {

	String password;
	Scanner scan = new Scanner(System.in);
	
	public Password(String PW) {
		password = PW;
	}
	
	public String getPass() {
		return password;
	}
	
	public void CreateID() {
		System.out.println("New password: ");
		password = scan.nextLine();
	}
	
	
}
