/**
 * 
 */
package authentication;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logging.LoggingInfo;
/**
 * @author lib-user
 *
 */
public class FileCreation {
	/**
	 * @param Registration Model
	 * @throws IOException 
	 */
	  public void fileCreation(RegistrationModel regObj)throws IOException {
	    try {
	      File fileObj = new File("users.txt");
	      if(!fileObj.exists())
	      {
	    	  fileObj.createNewFile();
	      }
	      System.out.println(fileObj.getAbsolutePath());
	      FileWriter fileWrite = new FileWriter(fileObj,true);
		  BufferedWriter filerWriterObj = new BufferedWriter(fileWrite);
		  String created = regObj.userID+"k0l42"+regObj.password+"k0l42"+regObj.question+"k0l42"+regObj.answer;
	      filerWriterObj.write(created);
	      filerWriterObj.newLine();
		  filerWriterObj.close();
		  System.out.println("Registration succesfull");
		  LoggingInfo.logInfo(regObj.userID + "Registration succesfull");
	      
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      LoggingInfo.logInfo(e.getMessage());
	      e.printStackTrace();
	    }
	  }
	  
	  /**
		 * @return {@link ArrayList} 
		 * @throws IOException 
		 */
	  public ArrayList<String> fileRead()throws IOException {
		  String fileData="";
		  ArrayList <String> lineList = new ArrayList<String>();
		  try {
			  FileReader fileObj = new FileReader("users.txt");
			  BufferedReader br = new BufferedReader(fileObj);
			  while((fileData = br.readLine())!=null) {
		       //System.out.println(fileData);
				 lineList.add(fileData); 
			  }
			  fileObj.close();
		  }
		  catch(IOException file) {
		
			  System.out.println("File was not found");
		  }
		  return lineList;
	  }
	  /**
		 * @param fileName
		 * @return {@link ArrayList} 
		 * @throws IOException  
		 */
	  public ArrayList<String> fileReadForInsert(String fileName)throws IOException {
		  String fileData="";
		  ArrayList <String> lineList = new ArrayList<String>();
		  try {
			  FileReader fileObj = new FileReader(fileName.concat(".txt"));
			  BufferedReader br = new BufferedReader(fileObj);
			  while((fileData = br.readLine())!=null) {
		       //System.out.println(fileData);
				 lineList.add(fileData); 
			  }
			  fileObj.close();
		  }
		  catch(IOException file) {
		
			  System.out.println("File was not found");
		  }
		  return lineList;
	  }
}
	
		
