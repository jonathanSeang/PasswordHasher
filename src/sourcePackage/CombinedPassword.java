package sourcePackage;

import java.security.MessageDigest;

public class CombinedPassword {
  private String password;
  private String salt;

  public CombinedPassword(String password, String salt){
    this.password = password;
    this.salt = salt;
  }


  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return this.salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getSecuredPassword() {
    String hashedPassword;

    try{
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(salt.getBytes());
      byte[] bytes = md.digest(password.getBytes());

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      hashedPassword = sb.toString();
    
      return hashedPassword;
    } 
    catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    
  }
}
