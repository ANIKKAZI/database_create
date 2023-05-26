/**
 * 
 */
package commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import logging.LoggingInfo;

/**
 * @author lib-user
 *
 */
public class Read {
	 /**
		 * @param query
		 * @throws IOException 
		 */
	public void readOperation(String query) throws IOException {
		String tableName;
		 try {
			 String[] tableDefination = query.split(" ");
			 int size = tableDefination.length;
			   
			 //checking if the correct syntax ie DELETE FROM is adhered
			 if(tableDefination[0].compareToIgnoreCase("SELECT")==0 && tableDefination[size-2].compareToIgnoreCase("FROM")==0 && query.endsWith(";")) {
				 
				 tableName = tableDefination[size-1].replace(";", "");
				 File fileObj = new File(tableName+".txt");
			     if(!fileObj.exists())
			     {
			    	throw new Error("Table does not exist");
			     }
			     else
			     {
			    	 if(query.contains("*"))
			    	 {
			    		 //delete all the elements from the table 
			    		// deleteAllRecords(tableName);
			    		 
			    		 //display all records;
			    		 simpleSelect(query,tableName);
			    	 }
			    	 else
			    	 {
			    		 if(!query.contains("*")) {
			    			 //String deleteParam =  tableDefination[4];
			    			 int selectIndex = query.indexOf("SELECT");
			    			 int whereIndex = query.indexOf("FROM");
			    			 //String [] selectParam =  ;
			    			String []attrParam = query.substring(selectIndex+6, whereIndex).split(",");
			    			selectAttrOperation(tableName, attrParam,query);
			    			 // deleteOperation(tableName,deleteParamDef);
			    		 }
			    		 else
			    		 {
			    			 throw new Error("Incorrect syntax");
			    		 }
			    	 }
			     }
			 }
			 else {
				 throw new Error("Incorrect format");
			 }
				
		 }
		 catch(Error e)
		 {
			 System.out.println(e.getMessage());
		 }

	}
    
	 /**
		 * @param query,tableName
		 * @throws IOException 
		 */
	private void simpleSelect(String query, String tableName) throws IOException {
		// TODO Auto-generated method stub
		String fileData="";
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
					  if(lineList.size()>1)
					  {
						  
							  for(int i=1;i<lineList.size();i++)
							  {
								  String lineItem = lineList.get(i);
								  
								  System.out.println(lineItem);
							  }
						   }
						  else
						  {
							  throw new Error("Select not possible");
						  }
					 	  	  
			  }
		 }
		 catch(Error e)
		 {
			 System.out.println(e.getMessage());
		 }
		
		
	}

	 /**
		 
		 * @param tableName, attrParam,query
		 * @throws IOException 
		 */
	private void selectAttrOperation(String tableName, String[] attrParam, String query) throws IOException {
		// TODO Auto-generated method stub
		
		String fileData="";
		String attrSelected = "";
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
					  for(Integer k=0;k<attrCol.length-1;k++)
						  {
						   for(int p=0;p<attrParam.length;p++) {
							   if(attrCol[k].contains(attrParam[p].trim()))
								  {
								   attrSelected= attrSelected.concat( k.toString()).concat(" ");
									 
								  } 
						   }
							  
						  }
					  if(attrSelected.length()<=0)
					   {
								  throw new Error("Column doesnt exist"); // checking if the column exist
							  
					   }
					  else
					   {
						  
						  if(lineList.size()>1)
						  {
							  
								  for(int i=1;i<lineList.size();i++)
								  {
									  String lineItem = lineList.get(i);
									  String[] lineItemVal = lineItem.split(" ");
									  //int lineItemFoundIndex =-1;
									  for(Integer p=0;p<lineItemVal.length;p++)
										  {
											  if(attrSelected.trim().contains(p.toString()))
											  {
												  String lineItemFound = lineItemVal[p];
												  System.out.print(lineItemFound+" ");
											  }
										 }
									  System.out.println();
								  }
							   }
							  else
							  {
								  throw new Error("Select not possible");
							  }
						 	  
					   }
			  }
		 }
		 catch(Error e)
		 {
			 LoggingInfo.logInfo(e.getMessage());
			 System.out.println(e.getMessage());
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 LoggingInfo.logInfo(e.getMessage());
			 System.out.println(e);
		}
		
	}
}
