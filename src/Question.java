import java.util.Scanner;


public class Question {

	String answer;
	Scanner scan = new Scanner(System.in);
	
	public Question() {
		
	}
	
	public String getAnswer() {
		System.out.println("What would like to do? (create, delete, login, or quit)");
		answer = scan.nextLine();
		if (answer.equalsIgnoreCase("create")) {
			return answer;
		}
		else if (answer.equalsIgnoreCase("delete")) {
			return answer;
		}
		else if (answer.equalsIgnoreCase("login")) {
			return answer;
		}
		else if (answer.equalsIgnoreCase("quit")) {
			return answer;
		}
		else {
			return "You can only (create, delete, login, or quit) for now..";
		}
	}

	
}
