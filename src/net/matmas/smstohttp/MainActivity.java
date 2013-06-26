package net.matmas.smstohttp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText api_url = (EditText) findViewById(R.id.api_url);
		final EditText message_matches = (EditText) findViewById(R.id.message_matches);
		final EditText from_matches = (EditText) findViewById(R.id.from_matches);
		final EditText secret = (EditText) findViewById(R.id.secret);
		final EditText user = (EditText) findViewById(R.id.user);
		
		loadAll();
		
		for (EditText editText : new EditText[] {api_url, message_matches, from_matches, secret, user}) {
			editText.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence searchPattern, int arg1, int arg2, int arg3) {
					saveAll();
				}
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
				public void afterTextChanged(Editable arg0) {}
			});			
		} 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}
	
	public static SharedPreferences getPreferences() {
		return App.getContext().getSharedPreferences("", 0);
	}
	
	public final static String API_URL = "api_url";
	public final static String MESSAGE_MATCHES = "message_matches";
	public final static String FROM_MATCHES = "from_matches";
	public final static String SECRET = "secret";
	public final static String USER = "user";
	
	private void saveAll() {
		final EditText api_url = (EditText) findViewById(R.id.api_url);
		final EditText message_matches = (EditText) findViewById(R.id.message_matches);
		final EditText from_matches = (EditText) findViewById(R.id.from_matches);
		final EditText secret = (EditText) findViewById(R.id.secret);
		final EditText user = (EditText) findViewById(R.id.user);
		
		Editor editor = getPreferences().edit();
		editor.putString(API_URL, api_url.getText().toString());
		editor.putString(MESSAGE_MATCHES, message_matches.getText().toString());
		editor.putString(FROM_MATCHES, from_matches.getText().toString());
		editor.putString(SECRET, secret.getText().toString());
		editor.putString(USER, user.getText().toString());
		editor.commit();
	}
	
	private void loadAll() {
		final EditText api_url = (EditText) findViewById(R.id.api_url);
		final EditText message_matches = (EditText) findViewById(R.id.message_matches);
		final EditText from_matches = (EditText) findViewById(R.id.from_matches);
		final EditText secret = (EditText) findViewById(R.id.secret);
		final EditText user = (EditText) findViewById(R.id.user);
		
		api_url.setText(getPreferences().getString(API_URL, ""));
		message_matches.setText(getPreferences().getString(MESSAGE_MATCHES, ""));
		from_matches.setText(getPreferences().getString(FROM_MATCHES, ""));
		secret.setText(getPreferences().getString(SECRET, ""));
		user.setText(getPreferences().getString(USER, ""));
	}

}
