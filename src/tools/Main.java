package tools;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) {
		
		//System.out.println(Time.getCurrentTimeStamp());
		try {
			System.out.println(Sha1.generate("Remi"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
