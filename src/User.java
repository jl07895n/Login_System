public class User {
	
	protected String username = "";
	protected String password = "";
	
	protected User(String ID, String PW) {
		username = ID;
		password = PW;
	}
	
	protected String getName() {
		return username;
	}
	
	protected String getPass() {
		return password;
	}
	
	public String toString(){
		String result = username + " " + password + "\n";
		return result;
	}

}
