/**
 * 
 */
package commands;

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
/**
 * @author User
 *
 */
public class Update {
	 /**
		 * @param query
		 * @throws SecurityException, IOException 
		 */
	public void updateOperation(String query) throws SecurityException, IOException
	{
		//System.out.println(query);
		 String tableName;
		 try {
			 String[] tableDefination = query.split(" ");
			   
			 //checking if the correct syntax ie DELETE FROM is adhered
			 if(tableDefination[0].compareToIgnoreCase("UPDATE")==0) {
				 tableName = tableDefination[1];
				 File fileObj = new File(tableName+".txt");
			     if(!fileObj.exists())
			     {
			    	throw new Error("Table does not exist");
			     }
			     else if(!(query.endsWith(";"))) {
			    	 throw new Error("Incorrect syntax");
			     }
			     else
			     {
			    	 if(tableDefination[2].compareTo("SET")==0)
			    	 {
			    		 //delete all the elements from the table 
			    		 updateAllRecords(tableName,query);
			    	 }
			    	 else
			    	 {
			    		 throw new Error("Syntax error"); 
			    	 }
			     }
			 }
			 else {
				 throw new Error("Incorrect format");
			 }
				
		 }
		 catch(Error e)
		 {
			 LoggingInfo.logInfo(e.getMessage());
			 System.out.println(e.getMessage());
		 }
		
	}

	/**
	 * @param tableName
	 * @param query
	 * @throws SecurityException
	 * @throws IOException
	 */
	private void updateAllRecords(String tableName, String query) throws SecurityException, IOException {
		// TODO Auto-generated method stub
		int setIndex = query.indexOf("SET");
		int whereIndex = query.indexOf("WHERE");
		String fileData="";
		//Boolean isUnique = true;
		ArrayList <String> lineList = new ArrayList<String>();
	    String attributeSet = query.substring(setIndex+3,whereIndex).trim();
	    //System.out.println(attributeSet);
	    String wherePart = query.substring(whereIndex+6,query.length()-1).trim();
	    //System.out.println(wherePart);
	    String[] SETPARAM = attributeSet.split("="); 
	    String[] WHEREPARAM = wherePart.split("=");
	    
	    
	    
	    try {
			 File fileExistObj = new File(tableName+".txt");
			  FileReader fileObj = new FileReader(tableName.concat(".txt"));
			  BufferedReader br = new BufferedReader(fileObj);
			  if(!fileExistObj.exists())
			     {
			    	throw new Error("Table does not exist");
			     }
			  else {
				  while((fileData = br.readLine())!=null) {
				       //System.out.println(fileData);
						 lineList.add(fileData); 
					  }
					  fileObj.close();
					  String tableAttr = lineList.get(0);
					  //System.out.println(tableAttr);
					  String[] attrCol= tableAttr.split("EOC");
					  int isContained = -1;
					  for(int k=0;k<attrCol.length-1;k++)
						  {
							  if(attrCol[k].contains(SETPARAM[0]))
							  {
								  isContained = k;
								  break;
							  }
							  
						  }
						  if(isContained<0)
						  {
								  throw new Error("Column doesnt exist"); // checking if the column exist
							  
						  }
						  
						  
						  if(lineList.size()>1)
						  {
							  File filePrevObj = new File(tableName.concat(".txt"));
						      if(filePrevObj.delete())
							  {
								  
								  File fileWriteObj = new File(tableName+".txt");
								     if(!fileWriteObj.exists())
								     {
								    	 fileWriteObj.createNewFile();
								    	 FileWriter fileWrite = new FileWriter(fileWriteObj,true);
										 BufferedWriter filerWriterObj = new BufferedWriter(fileWrite);
										 //Integer attrSize = columnList.size();
										 filerWriterObj.write(lineList.get(0));
										 filerWriterObj.newLine();
										 filerWriterObj.close();
										 LoggingInfo.logInfo(tableName + " updated succesfully");
										 System.out.println("Table Updated Succesfully");
										 
								     }
								     else
								     {
								    	 // checking if the table exists
								    	 throw new Error("Table does not exist");
								     }
								  for(int i=1;i<lineList.size();i++)
								  {
									  String lineItem = lineList.get(i);
									  if(lineItem.contains(WHEREPARAM[1])) {
										  String[] lineItemVal = lineItem.split(" ");
										  
										  lineItemVal[isContained] = SETPARAM[1];
										  String newUpdatedEntry = "";
										  for(int s =0;s<lineItemVal.length;s++)
										  {
											  newUpdatedEntry= newUpdatedEntry.concat(lineItemVal[s]).concat(" ");
											  
										  }
										  lineList.remove(i);
										 // System.out.println(newUpdatedEntry);
										  lineList.add(i, newUpdatedEntry.trim());
									  }
									  
									  
										  FileWriter fileWrite = new FileWriter(fileWriteObj,true);
										  BufferedWriter filerWriterObj = new BufferedWriter(fileWrite);
										  filerWriterObj.write(lineList.get(i));
										  filerWriterObj.newLine();
										  filerWriterObj.close();
									  
									

								  }
							  }
							  else
							  {
								  throw new Error("Update not possible");
							  }
						  }
						  else {
							  throw new Error("Tuple doesnt exist");  //when there is no entry in the table  
						  }
			  }


					  
				
		  }
		  catch(IOException file) {
			  LoggingInfo.logInfo(file.getMessage());
			  System.out.println("File was not found");
		  }
	    
	}

}
