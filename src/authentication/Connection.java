package authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Connection {
 String connectType ;
 /**
  * @return connectType

  */
 public String getCpnnectType() {
	return connectType;
}
 /**
  * @paramconnectType
    *@throws IOException 
  */
public void setCpnnectType(String cpnnectType)throws IOException {
	this.connectType = cpnnectType;
}

/**
 *  @throws Exception 
 */
public void connect() throws Exception {
	 //System.out.println("I am in the connect");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
	System.out.println("Do you want to login or register");
	try {
		connectType= br.readLine().toLowerCase();
		if(connectType.contains("register")) {
			Registration connection = new Registration();
			connection.createRegistration();
		}
		if(connectType.contains("login")) {
		 Login connectionObject = new Login();
		 connectionObject.userValidation();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	 
	 
 }
}
