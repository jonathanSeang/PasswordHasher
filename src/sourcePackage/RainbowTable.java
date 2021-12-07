package sourcePackage;

import java.security.MessageDigest;
import java.util.ArrayList;


/**
 * For a rainbow table attack, we will assume the attacker has:
 * 1. pre-hashed potential passwords
 * 2. knows the hash algorithm being used
 *
 */
public class RainbowTable {
	private ArrayList<String> plainPasswords = new ArrayList<String>();
	private ArrayList<String> rainbowTable = new ArrayList<String>();

	
	/**
	 * Set the password to be computed for hash function
	 * @param password
	 */
	public void addPassword(String password) {
		this.plainPasswords.add(password);
	}
	
	public ArrayList<String> getPlainPasswords() {
		return this.plainPasswords;
	}
	
	/**
	 * 
	 * @return get rainbowTable with hash outputs
	 */
	public ArrayList<String> getRainbowTable() {
		return this.rainbowTable;
	}
	
	
	/**
	 * Hash password without the salt in SHA-256
	 */
	public void createRainbowTable() {

	    try{
	    	for(int i = 0; i < this.plainPasswords.size(); i++) {
	    		MessageDigest md = MessageDigest.getInstance("SHA-256");
	    	      byte[] bytes = md.digest(this.plainPasswords.get(i).getBytes());

	    	      StringBuilder sb = new StringBuilder();
	    	      for (int j = 0; j < bytes.length; j++) {
	    	        sb.append(Integer.toString((bytes[j] & 0xff) + 0x100, 16).substring(1));
	    	      }
	    	      this.rainbowTable.add(sb.toString());

	    	}
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	      throw new RuntimeException(e);
	    }
	    
	  }}
