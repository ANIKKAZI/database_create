/**
 * 
 */
package commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logging.LoggingInfo;

/**
 * @author lib-user
 *
 */
public class Create {
	/**
	 * @param query
	 * @throws IOException, SecurityException 
	 */
 public void performCreate(String query) throws SecurityException, IOException {
	 int first,last;
	 String attributeParam,tableDef, tableName;
	// System.out.println("CREATE TABLE CUSTOMERS(ID INT NOT NULL,NAME VARCHAR (20) NOT NULL,AGE INT ADDRESS CHAR(25) SALARY DECIMAL (18, 2), PRIMARY KEY (ID),FOREIGN KEY (store_id) REFERENCES sales.stores (store_id));");
	 first = query.indexOf("(");
	 last = query.lastIndexOf(")");
	 tableDef = query.substring(0, first);
	 ArrayList<String> attrList = new ArrayList<String>();
	 ArrayList<String> columnList;
	 attributeParam = query.substring(first+1, last);
	 try {
		    String[] tableDefination = tableDef.split(" ");
		    //checking if the correct syntax ie CREATE TABLE TABLENAME is adhered
			if(tableDefination[0].compareToIgnoreCase("CREATE")==0 && tableDefination[1].compareToIgnoreCase("TABLE")==0){
				tableName=tableDefination[2];
				//extracting the attribute list
				if(query.contains(tableName+"(") && query.endsWith(");")) {
					try {
						int startIndex =0;
						for(int i =0;i<attributeParam.length();i++) {
							if(i>0) {
								
								if((attributeParam.charAt(i) == ',')){
									if(attributeParam.charAt(i-1)>48 && attributeParam.charAt(i-1)<57){
										continue;
									}
									else {
										String attributeGen = attributeParam.substring(startIndex,i);
										attrList.add(attributeGen.trim());
										startIndex=i+1;
									
									}
								
								}
								else if(i == attributeParam.length()-1)
								{	String attributeGen = attributeParam.substring(startIndex,i+1);
									startIndex=i+1;
									attrList.add(attributeGen.trim());
								}
								
							}
							
								
						} 
						//setting the attribute details and their constraints
						columnList= setColumns(attrList);
						String tableColum = "";
						for(String attrDef : columnList) {
							tableColum = tableColum.concat(attrDef).concat("EOC") ;
						}
						
						
						File fileObj = new File(tableName+".txt");
					     if(!fileObj.exists())
					     {
					    	 fileObj.createNewFile();
					    	 FileWriter fileWrite = new FileWriter(fileObj,true);
							 BufferedWriter filerWriterObj = new BufferedWriter(fileWrite);
							 Integer attrSize = columnList.size();
							 filerWriterObj.write(tableColum.concat(attrSize.toString()));
							 filerWriterObj.newLine();
							 filerWriterObj.close();
							 LoggingInfo.logInfo(tableName + "created succesful");
							 System.out.println("Table created succesful");
					     }
					     else
					     {
					    	 // checking if the table exists
					    	 LoggingInfo.logInfo(tableName + " already exist");
					    	 throw new Error("Table already exist");
					    	 
					     }
					}
					catch(IOException e) {
						 LoggingInfo.logInfo(e.getMessage()); 	
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
					}
				else
				{
					throw new Error("Syntax error");
				}
				
			}
		 else {
			 throw new Error("Syntax error again");
		 }
	 }
	 catch(Error e) {
		 LoggingInfo.logInfo(e.getMessage()); 	
		 System.out.println(e.getMessage());
	 }
 }
 /**
	 * @return {@link ArrayList} 
	 * @param {@link ArrayList}
	 * @throws SecurityException, IOException 
	 */
 public ArrayList<String> setColumns(ArrayList<String> attrList) throws SecurityException, IOException
 {
	ArrayList<String> attrCol = new ArrayList<String>();
	 try {
		 for(String userData : attrList) {
			 String attrName, type,isNotNull,keyName="";
			 int indexFound=-1;
			 if((userData.contains("PRIMARY KEY") || userData.contains("UNIQUE KEY") || userData.contains("FOREIGN KEY"))){
				 //matchedDetails = userData;
				 //break;
				 if(!attrCol.isEmpty())
				 {
					 int firstInd = userData.indexOf('(');
					 int closeInd = userData.indexOf(')');
					 String attr = userData.substring(firstInd+1, closeInd);
					 if(userData.contains("PRIMARY KEY"))
					 {
						 keyName="PK";
					 }
						 
					 else if(userData.contains("UNIQUE KEY"))
					 {
						 keyName="UK";
					 }
					 else if(userData.contains("FOREIGN KEY")) {
						 keyName="FK";
					 }
				  for(int i=0;i<attrCol.size();i++) {
					  if(attrCol.get(i).contains(attr)) {
						 indexFound=i;
						 break;
					  }
					 				 }
				 if(indexFound>-1)
				 {
					// System.out.println("Heyyyyy");
					 attrCol.set(indexFound, attrCol.get(indexFound).concat("TRM").concat(keyName));
					 indexFound = -1;
				 }
				 }
				 else
				 {    //constraint validation
					 throw new Error("Syntax error Attributes not defined");
				 }
			 }
			 else
			 {
				 String[] attrDef = userData.split(" ");
				 if(userData.contains("VARCHAR"))
				 {
					 int firstInd = userData.indexOf('(');
					 int closeInd = userData.indexOf(')');
					 String prec = userData.substring(firstInd+1, closeInd);
					 type = "VC#".concat(prec);
				 }
				 else if (userData.contains("INT")) {
					 	 type = "IN";
				 }
				 else if (userData.contains("BOOLEAN")) {
					 type="BL";
				 }
				 else
				 {
					 throw new Error("Inocrrect Syntax. Data type not mentioned");
				 }
				 
				 if(userData.contains("NOT NULL"))
				 {
					 isNotNull = "T";
				 }
				 else
				 {
					 isNotNull = "F";
				 }
				 
				 //checking if Attribute name is not a reserved name
				 if(attrDef[0].contains("VARCHAR") || attrDef[0].contains("BOOLEAN") || attrDef[0].contains("INT"))
				 {
					 throw new Error("Inocrrect Syntax. Reserved keyword cannot be used as attribute name not mentioned");
				 }
				 else
				 {
					 attrName=attrDef[0];  // setting column name
				 }
				 String attrConstruct = attrName.concat("TRM").concat(type).concat("TRM").concat(isNotNull);
				 attrCol.add(attrConstruct);
				 //attrCol.
				 
			 }
		  }
	 }
	 catch(Error e)
	 {
		 LoggingInfo.logInfo(e.getMessage()); 	
		 System.out.println(e.getMessage());
	 }
	 return attrCol;

 }
 
}
