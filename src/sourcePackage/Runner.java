package sourcePackage;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;

public class Runner {
  public static void main(String args[]) 
  {
	  
	  HashMap<String, CombinedPassword> database = new HashMap<>();
	  getUserInput(database);
	  
  }


  private static String getSalt() 
  {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return Byte_To_String(salt);    
  }


  private static String Byte_To_String(byte[] temp) 
  {
    StringBuilder sb = new StringBuilder();
    for(byte a: temp) {
      sb.append(String.format("%02x", a));
    }
    return sb.toString();
  }


  /**
   * @param input
   * @return converted input as its ASCII value
   */
  private static String convertToASCII(String input) {

	  StringBuilder sb = new StringBuilder();
	  char[] inputArr = input.toCharArray();

	  for (char ch: inputArr) {
		  sb.append((int) ch);
	  }

	  return sb.toString();
  }
	
  public static boolean compareInput(HashMap<String, CombinedPassword> database,String username, String inputPassword) {
	String hashedDatabasePassword = database.get(username).getPassword();
		
	String saltyInputPassword = new CombinedPassword(inputPassword, database.get(username).getSalt()).getPassword();
	return hashedDatabasePassword.equals(saltyInputPassword);
  }
  
  // Check for special character, length, and upper case letter with regex class
  public static boolean checkValidPassword(String password) {
	  
	  /*
	   * Check for
	   * 1. numbers
	   * 2. uppercase letter
	   * 3. special character
	   * 3. length between 8 and 20
	   */
	  String regex = "^(?=.*[0-9])"
              + "(?=.*[A-Z])"
              + "(?=.*[!@#$%^&*()-_=+\\\\|[{]};:'\\\",<.>/?])"
              + "(?=\\S+$).{8,20}$";
	  
	  // Compile regex
      Pattern p = Pattern.compile(regex);

	  if (password == null) {
          return false;
      }
	  
	  // matcher() checks if the param password contains conditions
	  Matcher m = p.matcher(password);
	  
	  return m.matches();
  }

  public static void getUserInput(HashMap<String, CombinedPassword> database){

	  while  (true) {

		  Scanner in = new Scanner(System.in);
		  System.out.println("Would you like to login or register?");
		  String input = in.nextLine();
		  if(input.toLowerCase().equals("register")){
			  System.out.println("Please enter your desired username.");
			  String username = in.nextLine();
			  while(database.containsKey(username)){
				  System.out.println("Username Taken. Please enter another username.");
				  username = in.nextLine();
			  }
			  System.out.println("Please enter your desired password.");
			  String password = in.nextLine();
			  while(!checkValidPassword(password)) {
				  System.out.println("Your desired password needs to contain: ");
				  System.out.println("1. A special character. ");
				  System.out.println("2. An uppercase letter. ");
				  System.out.println("3. A number. ");
				  System.out.println("4. And between 8 and 20 characters.");
				  System.out.println("Please reenter your desired password.");
				  password = in.nextLine();
				  
				  
			  }
			
			  CombinedPassword newUser = new CombinedPassword(convertToASCII(password), getSalt());
			  database.put(username,newUser);
			  System.out.println("Successfully registered: " + username);

		  }
		  else if(input.toLowerCase().equals("login")){
			  System.out.println("Please enter your username.");
			  String username = in.nextLine();
			  while(!database.containsKey(username)){
				  System.out.println("Username not recognized. Please reenter.");
				  username = in.nextLine();
			  }
			  System.out.println("Please enter your password.");
			  String password = in.nextLine();
			  if(compareInput(database,username,convertToASCII(password))){
				  System.out.println("Welcome back!" + username);


			  }
			  else{
				  System.out.println("Invalid Password");
			  }

		  }
		  else{
			  //do something when they don't want to login or register

		  }

		  System.out.println(database);
		  
	  }


  }

  
}
