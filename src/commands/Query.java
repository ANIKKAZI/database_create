/**
 * 
 */
package commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import logging.LoggingInfo;

/**
 * @author lib-user
 *
 */

public class Query {
	 /**
		 * @throws Exception 
		 */
 public void writeQueries() throws Exception {
	 String query;
	 String[] crud = {"create","delete","insert","update","select"};
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
	 query = br.readLine();
	 try {
		 if(query.toLowerCase().startsWith(crud[0])) {
			 Create createObj = new Create();
			 createObj.performCreate(query);
		 }
		 else if(query.toLowerCase().startsWith(crud[1])) {
			 Delete deleteObj = new Delete();
			 deleteObj.deleteOperation(query);
		 }
		 else if(query.toLowerCase().startsWith(crud[2])) {
			 Insert insertObj = new Insert();
			 insertObj.insertOperation(query);
		 }
		 else if(query.toLowerCase().startsWith(crud[3])) {
			 Update updateObj = new Update();
			 updateObj.updateOperation(query);
		 }
		 else if(query.toLowerCase().startsWith(crud[4])) {
			 Read readObj = new Read();
			 readObj.readOperation(query);
		 }
		 else
		 {
			 throw new Error("Inccorect Operation Selected");
		 }
	 }
	 catch (Error e) {
		LoggingInfo.logInfo(e.getMessage());
		System.out.println(e.getMessage());
	}
	 
	 
 }
}
