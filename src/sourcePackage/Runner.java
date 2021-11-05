package sourcePackage;

import java.security.SecureRandom;

public class Runner {
  public static void main(String args[]) 
  {
    String salt = getSalt();
    System.out.println(salt);
    CombinedPassword combinedPW = new CombinedPassword("password", salt);
    System.out.print("password: " + combinedPW.getPassword());
    System.out.println(" | salt: " + combinedPW.getSalt());
    System.out.println("hashed password: " + combinedPW.getSecuredPassword());
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
}
