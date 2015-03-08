import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// implementing binary search tree -->
		BST<String, User> LoginDataBase = new BST<String, User>();
		//----------------------------------->
		
		// instantiating variables -->
		String username = "", password = "";
		String question = "", create = "create", delete = "delete", login = "login", quit = "quit";
		String yes = "yes", no = "no";
		boolean i;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		//--------------------------->
		
		// call for database information-->
		i = true;	
		while (i == true){
			try {
				File file = new File("database.txt");
				i = false;
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					String[] parts = line.split(" ");
					String part1 = parts[0]; // username
					String part2 = parts[1]; // password
					
					User data = new User(part1, part2);
					LoginDataBase.insert(data.getName(), data);
				}
				fileReader.close();
				System.out.println(LoginDataBase);
				
			}
			catch (FileNotFoundException e)
			{
					System.out.println("No file found");
			}
			catch (IOException e) {
				e.printStackTrace();
			    i = true;
			}
		}
		// -------------------------------->
		
		// first question to start everything off-->
		System.out.println("What would like to do? (create, delete, login, or quit)");
		question = scan.nextLine();
		//----------------------------------------->
		
		
		// if the answer is not quit, it will either create, delete, or login -->
		while (!question.equalsIgnoreCase(quit)){
			
			if (question.equalsIgnoreCase(create)) {
				System.out.println("create your username:");
				username = scan.nextLine();
				System.out.println("create your password:");
				password = scan.nextLine();
				User login_user = new User(username, password);
				LoginDataBase.insert(login_user.getName(), login_user);
				// append to the file database by adding the new information-->
				try {
					PrintWriter pw = new PrintWriter(new FileOutputStream(new File("database.txt"), true /* append = true */)); 
					pw.append("\n" + login_user.getName() + " " + login_user.getPass());
					pw.close();
				}
				catch (FileNotFoundException e) {
					System.out.println("database does not exist, cannot save to file");
				}
				// ----------------------------------------------------------->
				System.out.println("What would like to do? (create, delete, login, or quit)");
				question = scan.nextLine();
			}
			
			else if (question.equalsIgnoreCase(delete)) {

				try {
					System.out.println("Which user do you want to delete?");
					username = scan.nextLine();
					System.out.println("Enter password:");
					password = scan.nextLine();
					if (LoginDataBase.find(username).getName().equalsIgnoreCase(username) &&
						LoginDataBase.find(username).getPass().equalsIgnoreCase(password))
					{
							LoginDataBase.remove(username);
							System.out.println("Deletion successful!");
					}
				}
				 catch (NullPointerException e) {
		            System.out.print("Incorrect username or password\n");
		            System.out.println("Try again?");
					question = scan.nextLine();	
					if (question.equalsIgnoreCase(yes)) {
						question.equalsIgnoreCase(delete);
			        }
					else if (question.equalsIgnoreCase(no)) {
						System.out.println("What would like to do? (create, delete, login, or quit)");
						question = scan.nextLine();
						}
		        }
				System.out.println("What would like to do? (create, delete, login, or quit)");
				question = scan.nextLine();
			}
			
			else if (question.equalsIgnoreCase(login)) {
				try {
					System.out.println("Enter username:");
					username = scan.nextLine();	
					if (LoginDataBase.find(username).getName().equalsIgnoreCase(username)) {
								System.out.println("Enter password:");
								password = scan.nextLine();
								if (LoginDataBase.find(username).getPass().equalsIgnoreCase(password)) {
									System.out.println("Login Successful!");
								}
								else if (!LoginDataBase.find(username).getPass().equalsIgnoreCase(password)) {
									System.out.println("Password mismatch!");
									System.out.println("Login failed!");
								}
					}
					System.out.println("What would like to do? (create, delete, login, or quit)");
					question = scan.nextLine();
				}
				catch (NullPointerException e) {
					System.out.println("Username not found!");
					System.out.println("Try again?");
					question = scan.nextLine();	
					if (question.equalsIgnoreCase(yes))
						question = login;
					else if (question.equalsIgnoreCase(no)) {
						System.out.println("What would like to do? (create, delete, login, or quit)");
						question = scan.nextLine();
					}
				}
			}
			
			else if (question.equalsIgnoreCase(quit))
				System.exit(0);
			else {
				System.out.println("You can only enter (create, delete, login, or quit)");
				System.out.println("What would like to do? (create, delete, login, or quit)");
				question = scan.nextLine();
			}
		}
		//----------------------------------------------------------------------->
		
	}
}
