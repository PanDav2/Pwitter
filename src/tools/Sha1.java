package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1 {

	public static String generate(String text)
	        throws NoSuchAlgorithmException, UnsupportedEncodingException  {
 
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
 
        byte[] sha1hash = new byte[40];
             
        md.update(text.getBytes("utf-8"), 0, text.length());
        sha1hash = md.digest();
     
        return Sha1.convertToHex(sha1hash);
    }
	
	private static String convertToHex(byte[] data) {
	      StringBuffer buffer = new StringBuffer();
	      for (int i=0; i<data.length; i++)
	      {
	          if (i % 4 == 0 && i != 0)
	              buffer.append("");
	          int x = (int) data[i];
	          if (x<0)
	              x+=256;
	          if (x<16)
	              buffer.append("0");
	          buffer.append(Integer.toString(x,16));
	      }
	      return buffer.toString();
	 }
}
