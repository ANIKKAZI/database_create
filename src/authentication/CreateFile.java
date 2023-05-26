package authentication;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreateFile {
	/**
	 * @param password
	 * @return hashPassWord
	 *  @throws Exception 
	 */
  public static void main(String[] args) {
    try {
      File fileObj = new File("filename.txt");
      
      if (fileObj.createNewFile()) {
        System.out.println("File created: " + fileObj.getName());
      } else {
        System.out.println("File already exists.");
      }
      //fileObj.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
