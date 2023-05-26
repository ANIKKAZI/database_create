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
public class Delete {
	 /**
		 * @param query
		 * @throws IOException 
		 */
	public void deleteOperation(String query) throws IOException {
		 String tableName;
		 try {
			 String[] tableDefination = query.split(" ");
			   
			 //checking if the correct syntax ie DELETE FROM is adhered
			 if(tableDefination[0].compareToIgnoreCase("DELETE")==0 && tableDefination[1].compareToIgnoreCase("FROM")==0) {
				 tableName = tableDefination[2];
				 File fileObj = new File(tableName+".txt");
			     if(!fileObj.exists())
			     {
			    	throw new Error("Table does not exist");
			     }
			     else
			     {
			    	 if(tableDefination.length==3)
			    	 {
			    		 //delete all the elements from the table 
			    		 deleteAllRecords(tableName);
			    	 }
			    	 else
			    	 {
			    		 if(query.contains("WHERE") && query.contains("FROM")) {
			    			 //String deleteParam =  tableDefination[4];
			    			 String [] deleteParamDef = tableDefination[4].trim().split("=");
			    			 deleteOperation(tableName,deleteParamDef);
			    		 }
			    		 else
			    		 {
			    			 throw new Error("Incorrect syntax");
			    		 }
			    	 }
			     }
			 }
			 else {
				 throw new Error("Incorrect syntax");
			 }
				
		 }
		 catch(Error e)
		 {
			 System.out.println(e.getMessage());
		 }
	}
	
	 /**
		 * @param tableName
		 * @throws IOException 
		 */
	public void deleteAllRecords(String tableName) throws IOException {
		String fileData="";
		//Boolean isUnique = true;
		ArrayList <String> lineList = new ArrayList<String>();
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
								 LoggingInfo.logInfo(tableName  + " Delete Succesful");
								 System.out.println("Delete Succesful");
						     }
						     else
						     {
						    	 // checking if the table exists
						    	 throw new Error("Table already exist");
						     }
					  }
				      else
				      {
				    	  throw new Error("Delete cannot happen");
				      }
				 	
				}
			  }
			
		catch(Error e) {
			LoggingInfo.logInfo(e.getMessage());
			System.out.println(e.getMessage());
		}

		
	}
	 /**
		 * @param tableName, deleteParamDef
		 * @throws SecurityException, IOException 
		 */
 	public void deleteOperation(String tableName,String[]deleteParamDef) throws SecurityException, IOException {
		String fileData="";
		//Boolean isUnique = true;
		ArrayList <String> lineList = new ArrayList<String>();
		//ArrayList <String> updatelineList = new ArrayList<String>();
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
					  String[] attrCol= tableAttr.split("EOC");
					  int isContained = -1;
					  for(int k=0;k<attrCol.length-1;k++)
						  {
							  if(attrCol[k].contains(deleteParamDef[0]))
							  {
								  isContained = k;
								  break;
							  }
							  
						  }
						  if(isContained<0)
						  {
								  throw new Error("Column doesnt exist"); // checking if the column exist
							  
						  }
						  
						  
						  if(lineList.size()>=2)
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
										 
								     }
								     else
								     {
								    	 // checking if the table exists
								    	 throw new Error("Table does not exist");
								     }
								  for(int i=1;i<lineList.size();i++)
								  {
									  System.out.println("Delete succesful");
									  String lineItem = lineList.get(i);
									  int lineItemFoundIndex =-1;
									  if(lineItem.contains(deleteParamDef[1])) {
										  String[] lineItemVal = lineItem.split(" ");
										  for(int p=0;p<lineItemVal.length;p++)
										  {
											  if(lineItemVal[p].contains(deleteParamDef[1]) && p ==isContained)
											  {
												  lineItemFoundIndex = i;
												 
//												  System.out.println(lineItemFound);
											  }
										  }
									  }
									  if(lineItemFoundIndex<0) {
										  FileWriter fileWrite = new FileWriter(fileWriteObj,true);
										  BufferedWriter filerWriterObj = new BufferedWriter(fileWrite);
											 //Integer attrSize = columnList.size();
										  filerWriterObj.write(lineList.get(i));
										  filerWriterObj.newLine();
										  filerWriterObj.close();
									  }
									

								  }
							  }
							  else
							  {
								  throw new Error("Delete not possible");
							  }
						  }
			  }
 

					  
				
		  }
		  catch(IOException file) {
			  LoggingInfo.logInfo(file.getMessage()); 	
			  System.out.println("File was not found");
		  }
		  //return lineList;
		  //System.out.println("is this unqiue" + isUnique);
		//return isUnique;
	}

}
