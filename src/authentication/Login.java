package authentication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import commands.Query;
import logging.LoggingInfo;

public class Login {
	/**
	 * @throws Exception
	 * @return boolean 
	 */
  public boolean userValidation() throws Exception {
	 String username,password, answer;
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
	 FileCreation fileReadObj = new FileCreation();
	 ArrayList<String> fileData = fileReadObj.fileRead();
	 System.out.println("Enter the username");
	 username=br.readLine();
	 System.out.println("Enter the password");
	 password=br.readLine();
	 String matchedDetails="";
	 for(String userData : fileData) {
		 if(userData.contains(username)) {
			 matchedDetails = userData;
			 break;
		 }
	  }
	 try {
		 if(matchedDetails.length()>0) {
			 String[] userDetails = matchedDetails.split("k0l42");
			 String encryptedPass = PasswordConfig.passwordEncryption(password);
			 System.out.println(userDetails[2]);
			 answer = br.readLine();
			 if((userDetails[0].compareTo(username) ==0) && (userDetails[1]).trim().compareTo(encryptedPass)==0 && (answer.compareTo(userDetails[3])==0)) {
				 LoggingInfo.logInfo(username + "login succesful");
				 System.out.println("login succesful");
				 Query queryTab = new Query();
				 queryTab.writeQueries();
			 }
			 else
			 {
				 LoggingInfo.logInfo(username + "Incorrect Credential");
				 throw new Error("Incorrect Credential");
				 
			 }
		 }
		 else {
			 throw new Error("User not found");
		 }
		 
	 }
	 catch(Error e) {
		 System.out.println(e.getMessage());
	 }
	 
	  return true;
  }
}
