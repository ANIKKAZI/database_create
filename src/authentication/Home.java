/**
 * 
 */
package authentication;

import java.io.IOException;

/**
 * @author lib-user
 *
 */
public class Home {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Connection connectionObj = new Connection();
		try {
			connectionObj.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
