package net.matmas.smstohttp.network;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import net.matmas.smstohttp.App;
import android.widget.Toast;

public class Tools {
	public static String convertStreamToString(java.io.InputStream is) {
	    try {
	        return new java.util.Scanner(is).useDelimiter("\\A").next();
	    } catch (java.util.NoSuchElementException e) {
	        return "";
	    }
	}
	
	public static void tryRegex(String regex) {
		try {
        	Pattern.compile(regex);
        }
		catch (PatternSyntaxException e) {
			Toast.makeText(App.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		} 
	}
	
	// http://www.supermind.org/blog/1102/generating-hmac-md5-sha1-sha256-etc-in-java#comments
	public static String hmacDigest(String msg, String keyString, String algo) {
	    String digest = null;
	    try {
	      SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
	      Mac mac = Mac.getInstance(algo);
	      mac.init(key);

	      byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

	      StringBuffer hash = new StringBuffer();
	      for (int i = 0; i < bytes.length; i++) {
	        String hex = Integer.toHexString(0xFF & bytes[i]);
	        if (hex.length() == 1) {
	          hash.append('0');
	        }
	        hash.append(hex);
	      }
	      digest = hash.toString();
	    } catch (UnsupportedEncodingException e) {
	    } catch (InvalidKeyException e) {
	    } catch (NoSuchAlgorithmException e) {
	    }
	    return digest;
	  }
}
