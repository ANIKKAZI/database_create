package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggingInfo {
	 /**
		 * @param message
		 * @throws SecurityException, IOException 
		 */
	public static void logInfo(String message) throws SecurityException, IOException {
		//boolean appedToFile = true;
		FileHandler logHandler  = new FileHandler("assignment.log",true);
	//	logHandler.
		Logger logObj = Logger.getLogger("createlog");
		try {
			logObj.addHandler(logHandler);
			logObj.info(message);
		}
		catch(SecurityException e ) {
		 e.printStackTrace();	
		}
		
		
		
	}
}
