public class Menu {

	BST<String, User> LoginDataBase = new BST<String, User>();
	String answer, name, pass;
	Question question = new Question();
	Username username = new Username(name);
	Password password = new Password(pass);
	User user = new User(username, password);
	
	public Menu() {
	}

	public void Simulate_Menu() {
		user.Extract();
		answer = question.getAnswer();
		while (!answer.equalsIgnoreCase("quit")) {
			
			if (answer.equalsIgnoreCase("create")) {
				user.Add();
				answer = question.getAnswer();
			}
			else if (answer.equalsIgnoreCase("delete")) {
				user.destroy();
				answer = question.getAnswer();
			}
			else if (answer.equalsIgnoreCase("login")) {
				user.login();
				answer = question.getAnswer();
			}
			else {
				System.out.println("You can only enter (create, delete, login, or quit)");
				answer = question.getAnswer();
			}
		}
		if (answer.equalsIgnoreCase("quit")) {
			System.out.println("Program terminated.");
		}
	}

	
}
