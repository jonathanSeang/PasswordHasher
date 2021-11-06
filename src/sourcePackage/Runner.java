package sourcePackage;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

public class Runner {
  public static void main(String args[]) 
  {
    String salt = getSalt();
    System.out.println(salt);
    CombinedPassword combinedPW = new CombinedPassword("password", salt);
    System.out.print("password: " + combinedPW.getPassword());
    System.out.println(" | salt: " + combinedPW.getSalt());
    System.out.println("hashed password: " + combinedPW.getSecuredPassword());
    
    System.out.println("--------------");
    HashMap<String, CombinedPassword> database = new HashMap<>();
    String inputUsername = "testUsername";
    String inputPassword = "testPassword";
    storeInput(database, inputUsername, inputPassword);
    //database.put(inputUsername, new CombinedPassword(inputPassword, getSalt())); //might want to just use this instead of storeInput() that we discussed together
    System.out.println("map values: " + database.entrySet());
    System.out.println("Converted hashed password to ascii: " + convertToASCII(combinedPW.getSecuredPassword()));
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
   * Hashes password combined with salt into database
   * 
   * @param database = database to store into
   * @param username = username just entered from user
   * @param password = plaintext password entered from user
   */
  private static void storeInput(HashMap<String, CombinedPassword> database, String username, String password) {

	  CombinedPassword hashedPassword = new CombinedPassword(password, getSalt());
	  database.put(username, hashedPassword);

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

  public void getUserInput(HashMap database){

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
        CombinedPassword newUser = new CombinedPassword(convertToASCII(password), getSalt());
        database.put(username,newUser.getSecuredPassword());



    }
    else if(input.toLowerCase().equals("login")){
        System.out.println("Please enter your username.");
        String username = in.nextLine();
        if(database.containsKey(username)){
            System.out.println("Please enter your password.");
            String password = in.nextLine();

            

        }

    }
    else{
        
    }
    
}

  
}
