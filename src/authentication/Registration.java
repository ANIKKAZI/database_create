package authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

public class Registration {
	/**
	 * @throws IOException
	 * @throws SecurityException 
	 * @throws NoSuchAlgorithmException 
	 */
 public void createRegistration() throws IOException, NoSuchAlgorithmException, SecurityException{
	 String userID, password, question, answer;
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 RegistrationModel regObj = new RegistrationModel();
	 System.out.println("Enter the username");
	 userID = br.readLine();
	 regObj.userID = userID;
	 System.out.println("Enter the password");
	 password = br.readLine();
	 System.out.println("Enter the security question");
	 question = br.readLine();
	 regObj.question = question;
	 System.out.println("Enter the answer");
	 answer = br.readLine();
	 regObj.answer = answer;
	 regObj.password = PasswordConfig.passwordEncryption(password);
	 FileCreation fileObj = new FileCreation();
	 fileObj.fileCreation(regObj);
 }
}
