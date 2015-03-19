import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class User {
	
	// fields for initializing variables and objects
	BST<String, User> LoginDataBase = new BST<String, User>();
	String filename;
	String input;
	String name, pass;
	boolean i;
	Username username;
	Password password;
	User user;
	Scanner scan = new Scanner(System.in);
	// -->

	// object User that takes the objects Username and Password as parameters
	public User(Username ID, Password PW) {
		username = ID;
		password = PW;
	}
	// -->
	
	// returns the String of the object's Username object
	public String getName() {
		return username.getName();
	}
	// -->
	
	// returns the String of the object's Password object
	public String getPass() {
		return password.getPass();
	}
	
	// returns the String representation of the object
	public String toString() {
		String result = username.getName() + ": " + password.getPass() + "\n";
		return result;
	}
	// -->
	
	// this method takes data from a textfile and organizes them into a binary
	public void Extract() {
		filename = "database.txt";
		i = true;
		while (i){
			File file = new File(filename);
			try {
				i = false;
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					String[] parts = line.split(" ");
					username = new Username(parts[0]); // username
					password = new Password(parts[1]); // password
					user = new User(username, password);
					LoginDataBase.insert(user.getName(), user);
				}
				fileReader.close();
			}
			catch (FileNotFoundException e) {
					System.out.println("No file found");
			}
			catch (IOException e) {
				e.printStackTrace();
			    i = true;
			}
		}
		System.out.println(LoginDataBase);
	}
	// -->
	
	// this method adds new User(Username, Password) into BST and writes to file
	public void Add() {
		filename = "database.txt";
		i = true;
		while (i) {
			String try_username = username.CreateID();
			try {
				if (LoginDataBase.find(try_username).getName().equalsIgnoreCase(try_username)) {
					System.out.println("Username is already taken");
					boolean n = true;
					while (n) {
						System.out.println("Try again? (yes, no)");
						String input = scan.nextLine();
						if (input.equalsIgnoreCase("yes")) {
							n = false;
						}
						else if (input.equalsIgnoreCase("no")) {
							System.out.println("System didn't create a new user.");
							n = false;
							i = false;
						}
						else {
							System.out.println("You can only input (yes, no)!");
						}
					}
				}
			}
			catch (NullPointerException e) {
				name = try_username;
				System.out.println("Successful!");
				password.CreateID();
				Username username = new Username(name);
				Password password = new Password(pass);
				user = new User(username, password);
				LoginDataBase.insert(user.getName(), user);
				try {
					PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filename), true /* append = true */)); 
					pw.append("\n" + user.getName() + " " + user.getPass());
					pw.close();
					i = false;
				}
				catch (FileNotFoundException f) {
					System.out.println("database does not exist, cannot save to file");
				}	
			}
		}
	}
	// -->
	
	// this method takes the input username and deletes the user after the password is confirmed
	public void destroy() {
		i = true;
		while (i) {
			System.out.println("Which user would you like to delete?");
			String delete_user = scan.nextLine();
			try {
				if (LoginDataBase.find(delete_user).getName().equalsIgnoreCase(delete_user)) {
					for (int j = 0; j < 3; j++) {
						System.out.println("Confirm password: ");
						String pass_confrm = scan.nextLine();
						if (LoginDataBase.find(delete_user).getPass().equals(pass_confrm)) {
							LoginDataBase.remove(delete_user);
							System.out.println(delete_user + " has been deleted.");
							j = 4;
							i = false;
						}
						else {
							System.out.println("Incorrect password!");
						}
					}
				}
			} 
			catch (NullPointerException e) {
				System.out.println("Incorrect username!");
				boolean n = true;
				while (n) {
					System.out.println("Try again? (yes, no)");
					input = scan.nextLine();
					if (input.equalsIgnoreCase("yes")) {
						i = true;
						n = false;
					}
					else if (input.equalsIgnoreCase("no")) {
						i = false;
						n = false;
					}
					else {
						System.out.println("You can only input (yes, no)!");
					}
				}
			}
		}
	}
	// -->
	
	// this method ensures the login of the user and does something after logged in
	public void login() {
		i = true;
		while (i) {
			System.out.println("Enter username");
			String delete_user = scan.nextLine();
			try {
				if (LoginDataBase.find(delete_user).getName().equalsIgnoreCase(delete_user)) {
					for (int j = 0; j < 3; j++) {
						System.out.println("Confirm password: ");
						String pass_confrm = scan.nextLine();
						if (LoginDataBase.find(delete_user).getPass().equals(pass_confrm)) {
							//do something
							System.out.println("Logged in!");
							j = 4;
							i = false;
						}
						else {
							System.out.println("Incorrect password!");
						}
					}
				}
			} 
			catch (NullPointerException e) {
				System.out.println("Incorrect username!");
				boolean n = true;
				while (n) {
					System.out.println("Try again? (yes, no)");
					input = scan.nextLine();
					if (input.equalsIgnoreCase("yes")) {
						i = true;
						n = false;
					}
					else if (input.equalsIgnoreCase("no")) {
						i = false;
						n = false;
					}
					else {
						System.out.println("You can only input (yes, no)!");
					}
				}
			}
		}
	}
	// -->

	
}
