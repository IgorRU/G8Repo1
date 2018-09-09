import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.bind.DatatypeConverter;
//import org.apache.commons.codec.binary.Base64;
//import java.io.IOException;

public class AQ {

	    public static void main( String[] args )  {   
	    	
	        System.out.println("qBittorrent connect (HTML).");     
	    	URL url = null;
			try {
				url = new URL(" http://192.168.88.254:8088");
			} catch (MalformedURLException e3) {
				e3.printStackTrace();
			}
	        System.out.println("URL = "+url); 
	    	HttpURLConnection conn = null;
			try {
				conn = (HttpURLConnection) url.openConnection();
			} catch (IOException e2) {
				e2.printStackTrace();
			} 	    	 
	    	try {
				conn.setRequestMethod("POST");
			} catch (ProtocolException e1) {
				e1.printStackTrace();
			}
	    	conn.setDoOutput(true);
	    	conn.setDoInput(true);
	    	conn.setUseCaches(false);
	    	 
	    	//Set basic authentication in the header
	        String teststr = "admin:123456";
	        String b64 = DatatypeConverter.printBase64Binary(teststr.getBytes());
	        System.out.println("HASH: "+teststr + " is " + b64); 
	    	conn.setRequestProperty("Authorization", "Basic " + b64);
	    	 
	    	//Submit user and password.  A JSON object will return that contains session name and session id for this user
	    	String formParameters = null;
			try {
				formParameters = "username=" + URLEncoder.encode("user1", "UTF-8") + "&password=" + URLEncoder.encode("user1password", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}	    	                  
	    	conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
	    	conn.setRequestProperty("Content-Length", Integer.toString(formParameters.length()));
	    	conn.setRequestProperty("Accept", "application/json");
	    	 
	    	DataOutputStream out = null;
			try {
				out = new DataOutputStream(conn.getOutputStream());
				out.writeBytes(formParameters);
				out.flush();
				out.close();
				System.out.println("code="+conn.getResponseCode()+" is "+ conn.getResponseMessage());
				System.out.println(conn.getURL());				

				InputStream is = conn.getInputStream();
				String string_out = "";
				DataInputStream input = new DataInputStream(is);
				if (input != null)
					for( int c = input.read(); c != -1; c = input.read() ) 
						string_out = string_out + (char)c;
				input.close();				
				System.out.println(UTF8ToCP1251(string_out));
				
			} catch (IOException e) { 
				e.printStackTrace();
			}			 
	}
	    
	static String UTF8ToCP1251(String s) throws UnsupportedEncodingException {
		byte[] b1 = s.getBytes("ISO-8859-1");
		String szUT8 = new String(b1, "UTF-8");
		return szUT8;
	}
}
