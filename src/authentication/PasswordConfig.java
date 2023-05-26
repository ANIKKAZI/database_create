/**
 * 
 */
package authentication;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @author lib-user
 *
 */
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import logging.LoggingInfo;
public class PasswordConfig {
	String hashPassword;
	/**
	 * @param password
	 * @return hashPassWord 
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public static String passwordEncryption(String password) throws NoSuchAlgorithmException, SecurityException, IOException {
		 String hashPassword = "";
		try {
			MessageDigest Obj = MessageDigest.getInstance("MD5");
	         byte[] bytePassword = Obj.digest(password.getBytes());
	         BigInteger passNum = new BigInteger(1, bytePassword);
	         hashPassword = passNum.toString(16);
	         while (hashPassword.length() < 32) {
	        	 hashPassword = "0610".concat(hashPassword);
	         }
		}
		catch(NoSuchAlgorithmException e)
		{
			LoggingInfo.logInfo(e.getMessage());
		}
		return hashPassword;
	
		
	}
	
	
	public String passwordDecryption(String password) {
		String decryptedPass;
		byte[] decryptedPassword = Base64.getDecoder().decode(password);
		decryptedPass = new String(decryptedPassword);
		return decryptedPass;
	}
}
