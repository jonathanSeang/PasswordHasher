package sourcePackage;

import java.security.MessageDigest;

//stores password (after salting and hashing) with salt into single object
public class CombinedPassword {
	private String password;
	private String salt;

	public CombinedPassword(String password, String salt){
		this.password = getSecuredPassword(password, salt);
		this.salt = salt;
	}

	public String getPassword() {
		return this.password;
	}

	public String getSalt() {
		return this.salt;
	}

	/**
	 * Combines plaintext password and salt, then returns hashed value to be stored
	 * 
	 * @param inputPassword, inputSalt
	 * @return
	 */
	private String getSecuredPassword(String inputPassword, String inputSalt) {
		String hashedPassword;

		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(inputSalt.getBytes());
			byte[] bytes = md.digest(inputPassword.getBytes());

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
