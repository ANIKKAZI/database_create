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

import authentication.FileCreation;
import logging.LoggingInfo;

/**
 * @author lib-user
 *
 */
public class Insert {
	 /**
		 * @param query
		 * @throws Exception 
		 */
	public void insertOperation(String query) throws Exception{
		int first,last;
		 String valueParams,tableDef, tableName;
		 first = query.indexOf("(");
		 last = query.lastIndexOf(")");
		 try
		 {
			 if(first == -1 || last == -1)
			 {
				 throw new Error("Incorrect syntax");
			 }
			 else
			 {
				 tableDef = query.substring(0, first);
				 String[] columnVal;
				 valueParams = query.substring(first+1, last);
				 try {
					    String[] tableDefination = tableDef.split(" ");
					    //checking if the correct syntax ie CREATE TABLE TABLENAME is adhered
						if(tableDefination[0].compareToIgnoreCase("INSERT")==0 && tableDefination[1].compareToIgnoreCase("INTO")==0 && query.contains("VALUES")){
							tableName=tableDefination[2];
							//checking if the table exist
							File fileObj = new File(tableName+".txt");
						     if(!fileObj.exists())
						     {
						    	throw new Error("Table does not exist");
						     }
						     else
						     {
						    	columnVal = valueParams.split(",");
						    	//System.out.println(columnVal.length);
						    	FileCreation fileReadObj = new FileCreation();
						   	 	//System.out.println(tableName);
						   	 	ArrayList<String> fileData = fileReadObj.fileReadForInsert(tableName);
//						   	 System.out.println("Enter the username");
//						   	 username=br.readLine();
//						   	 System.out.println("Enter the password");
//						   	 password=br.readLine();
						   	// System.out.println(fileData.get(0));	
						   	 checkValues(fileData,columnVal,query,tableName);
						   	// String matchedDetails="";
//						   	 
						     }

						}
						else
						{
							throw new Error("Incorrect syntax");
						}
				 }
				 catch(Error e) {
					 LoggingInfo.logInfo(e.getMessage());
					 System.out.println(e.getMessage());
				 } 
			 }
		 }
		 catch(Error e)
		 {
			 LoggingInfo.logInfo(e.getMessage());
			 System.out.println(e.getMessage());
		 }
		 
	}
	 /**
		 * @param {@link ArrayList}, coulmnValues,query,tableName
		 * @throws Exception 
		 */
	public void checkValues(ArrayList<String> valuesList,String[] columnValues, String query, String tableName)throws Exception
	{
		//System.out.println(valuesList.get(0));
		String wordGen="";
		String[] attrList = valuesList.get(0).split("EOC");
		Boolean isApt = true;
		//System.err.println(attrList.length);
		ArrayList<String> attributeList = new ArrayList<String>();
		int lastIndex = attrList.length-1;//Integer.parseInt(attrList[attrList.length-1]);
		if(columnValues.length > lastIndex)
		{
			throw new Error("Number of values to be inserted is more than the attribute defined");
		}
		else
		{
			for(int i =0;i<columnValues.length;i++)
			{
				try{
					String[] attributeMeta = attrList[i].split("TRM"); 
					String colValue = columnValues[i].trim();
					if(attributeMeta[1].toUpperCase().contains("IN"))
					{
						attributeList.add(colValue);
					}
					//checking for VARCHAR with the length precssion
					else if(attributeMeta[1].contains("VC#")) {
						String isVarchar =  columnValues[i].trim();
						//System.out.println(isVarchar);
						if(isVarchar.endsWith("'") && isVarchar.startsWith("'"))
						{
							wordGen = isVarchar.substring(isVarchar.indexOf("'")+1, isVarchar.lastIndexOf("'"));
							String[] varcharDetails = attributeMeta[1].split("#");
							if(wordGen.length() > Integer.parseInt(varcharDetails[1])){
								throw new Error("Size is exceeded");
							}
							else
							{
								attributeList.add(wordGen);
							}
							
						}
						else
						{
							throw new Error("Incorrect datatype for varchar");
						}
						
					}
					//validating boolean datatype
					else if(attributeMeta[1].compareTo("BL")==0)
					{
						String isBoolean = columnValues[i].trim().toLowerCase();
						if(isBoolean.compareTo("true") !=0 || isBoolean.compareTo("false") !=0)
						{
							throw new Error("incorrect datatype for boolean");
						}
						else
						{
							attributeList.add(columnValues[i].trim());
						}
					}
					
					else
					{
						throw new Error("Incorrect datatype in general");
					}
					
					if(attrList[i].contains("PK"))
					{
						Boolean isUnique= constrainValidation(attrList[i],columnValues[i],tableName);
						//System.out.println(isUnique);
						if(isUnique == false || columnValues[i].isBlank())
						{
							throw new Error("Primary key violation");
						}
					}
					else if(attrList[i].contains("UK"))
					{
						
						Boolean isUnique= constrainValidation(attrList[i],columnValues[i],tableName);
						//System.out.println(isUnique);
						if(isUnique == false || columnValues[i].isEmpty())
						{
							throw new Error("Unique key violation");
						}
					}
					
				}
				catch(Exception e)
				{
					isApt = false;
					LoggingInfo.logInfo(e.getMessage());
					System.out.println(e);
					break;
				}
				catch(Error e)
				{
				  isApt = false;
				  LoggingInfo.logInfo(e.getMessage());
				  System.out.println(e.getMessage());
				  break;
				}
			}
					
		}
		
		if(isApt)
		{
			try {
				  String totalAttribute = ""; 
			      File fileObj = new File(tableName.concat(".txt"));
			     // System.out.println(tableName);
			      FileWriter fileWrite = new FileWriter(fileObj,true);
				  BufferedWriter filerWriterObj = new BufferedWriter(fileWrite);
				  for(int i=0;i<attributeList.size();i++)
				  {
					 totalAttribute =totalAttribute.concat(attributeList.get(i)).concat(" ");
				  }
				  String created = totalAttribute;//regObj.userID+"k0l42"+regObj.password+"k0l42"+regObj.question+"k0l42"+regObj.answer;
				//  System.out.println(created);
				  filerWriterObj.write(created);
			      filerWriterObj.newLine();
				  filerWriterObj.close();
				  LoggingInfo.logInfo(tableName + "Insert Succesful");
				  System.out.println("Insert Succesful");
			      
			    } catch (IOException e) {
			      LoggingInfo.logInfo(e.getMessage());	
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		
		//if()
	}
	 /**
		 * @return boolean
		 * @param attrList,columVal,tableName
		 * @throws SecurityException, IOException 
		 */
	public boolean constrainValidation(String attrList,String columVal,String tableName) throws SecurityException, IOException
	{
		String fileData="";
		Boolean isUnique = true;
		  ArrayList <String> lineList = new ArrayList<String>();
		  try {
			  FileReader fileObj = new FileReader(tableName.concat(".txt"));
			  BufferedReader br = new BufferedReader(fileObj);
			  while((fileData = br.readLine())!=null) {
		       //System.out.println(fileData);
				 lineList.add(fileData); 
			  }
			  fileObj.close();
			  if(lineList.size()>1)
			  {
				  for(int i=1;i<lineList.size();i++)
				  {
					  
					if(lineList.get(i).contains(columVal))
					{
						isUnique=false;
						break;
					}
				  }  
			  }
			   
		  }
		  catch(IOException file) {
			  LoggingInfo.logInfo(file.getMessage());
			  System.out.println("Table does not exists");
		  }
		  //return lineList;
		  //System.out.println("is this unqiue" + isUnique);
		return isUnique;
	}
}
