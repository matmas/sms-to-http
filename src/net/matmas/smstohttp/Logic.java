package net.matmas.smstohttp;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.matmas.smstohttp.network.SendTask;
import net.matmas.smstohttp.network.SuccessCallback;
import net.matmas.smstohttp.network.Tools;
import android.widget.Toast;

public class Logic {
	
	@SuppressWarnings("deprecation")
	public static void onSMSReceive(String from, String body) {
		String api_url = MainActivity.getPreferences().getString(MainActivity.API_URL, "");
        String from_matches = MainActivity.getPreferences().getString(MainActivity.FROM_MATCHES, "");
		String message_matches = MainActivity.getPreferences().getString(MainActivity.MESSAGE_MATCHES, "");
        String secret = MainActivity.getPreferences().getString(MainActivity.SECRET, "");
        String user = MainActivity.getPreferences().getString(MainActivity.USER, "");
        
        if (from.matches(from_matches)) {
        	Matcher m = Pattern.compile(message_matches).matcher(body);
        	if (m.find()) {
        		String message = m.group(1);
        		String digest = Tools.hmacDigest(from + body + user, secret, "HmacSHA256");
        		
        		new SendTask(api_url,
        				"from=" + URLEncoder.encode(from) +
        				"&message=" + URLEncoder.encode(message) +
        				"&user=" + URLEncoder.encode(user) +
        				"&digest=" + digest,
        				new SuccessCallback() {
    				public void onSuccess(String response) {
    					Toast.makeText(App.getContext(), response, Toast.LENGTH_LONG).show();
    				}
    			}, null).execute();
        	}
        }
	}
}
