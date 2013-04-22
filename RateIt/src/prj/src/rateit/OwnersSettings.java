package prj.src.rateit;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.widget.*;

public class OwnersSettings extends Activity {
	
	private EditText firstNameView;
	private EditText lastNameView;
	private EditText emailAddressView;
	private EditText passwordView;
	private EditText oldPasswordView;
	private EditText confirmPasswordView;
	
	private String first_Name;
	private String last_Name;
	private String Email_Address;
	private String Old_Password;
	private String New_Password;
	private String Confirm_Password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owners_settings);
		// Show the Up button in the action bar.
		setupActionBar();
		
		LinearLayout layout = (LinearLayout)findViewById(R.id.LinearLayout02);
		
		setSettings(layout);
		setFieldsView(layout);
	}
	
	public void setSettings(LinearLayout layout){
		((EditText)(layout.getChildAt(1))).setText(first_Name);
		((EditText)(layout.getChildAt(3))).setText(last_Name);
		((EditText)(layout.getChildAt(5))).setText(Email_Address);
		((EditText)(layout.getChildAt(7))).setText(Old_Password);
		((EditText)(layout.getChildAt(9))).setText(New_Password);
		((EditText)(layout.getChildAt(11))).setText(Confirm_Password);
	}
	
	
	
	public void setFieldsView(LinearLayout layout){
		firstNameView = (EditText)layout.getChildAt(1);
		lastNameView = (EditText)layout.getChildAt(3);
		emailAddressView = (EditText)layout.getChildAt(5);
		oldPasswordView = (EditText)layout.getChildAt(7);
		passwordView=(EditText)layout.getChildAt(9);
		confirmPasswordView = (EditText)layout.getChildAt(11);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.owners_settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void set_FirstName(String _first){
		first_Name = _first;
	}
	public void set_LastName(String _last){
		last_Name = _last;
	}
	public void set_Email(String _email){
		Email_Address = _email;
	}
	public void set_oldPsw(String _psw){
		Old_Password = _psw;
	}
	public void set_newPsw(String _psw){
		New_Password = _psw;
	}
	public void set_confirmPsw(String _psw){
		Confirm_Password = _psw;
	}
	
	 public void editUserData(View view){
		 
		 boolean dataCheck=true;
		 
		 first_Name = firstNameView.getText().toString();
		 last_Name = lastNameView.getText().toString();
		 Email_Address = emailAddressView.getText().toString();
		 Old_Password = oldPasswordView.getText().toString();
		 New_Password = passwordView.getText().toString();
		 Confirm_Password= confirmPasswordView.getText().toString();
		 
		 dataCheck=verifyData();
		 
		 if(dataCheck==true){
		 finish();
		 }
	}
	 
	 
	 public boolean verifyData(){
		View focusView = null;
		boolean dataCheck=true;
			
		firstNameView.setError(null);
		lastNameView.setError(null);
		emailAddressView.setError(null);
		passwordView.setError(null);
		confirmPasswordView.setError(null);
			
			if (TextUtils.isEmpty(first_Name)){
				firstNameView.setError(getString(R.string.error_field_required));
				focusView = firstNameView;
				dataCheck=false;
			}
			
			if (TextUtils.isEmpty(last_Name)){
				lastNameView.setError(getString(R.string.error_field_required));
				focusView = lastNameView;
				dataCheck=false;
			}
			
			if (TextUtils.isEmpty(Email_Address)){
				emailAddressView.setError(getString(R.string.error_field_required));
				focusView = emailAddressView;
				dataCheck=false;
			}
			
			if (TextUtils.isEmpty(Old_Password)){
				oldPasswordView.setError(getString(R.string.error_field_required));
				focusView = oldPasswordView;
				dataCheck=false;
			}
			
			if (TextUtils.isEmpty(New_Password)){
				passwordView.setError(getString(R.string.error_field_required));
				focusView = passwordView;
				dataCheck=false;
			}else if (New_Password.length() < 4) {
				passwordView.setError(getString(R.string.error_invalid_password));
				focusView = passwordView;
				dataCheck = false;
			}
			
			
			if (TextUtils.isEmpty(Confirm_Password)){
				confirmPasswordView.setError(getString(R.string.error_field_required));
				focusView = confirmPasswordView;
				dataCheck=false;
			}
			else
			if(New_Password.equals(Confirm_Password)==false){
				confirmPasswordView.setError(getString(R.string.passwordMatchError));
				focusView = confirmPasswordView;
				dataCheck=false;
			}			
		return dataCheck;	 
	 }
	 
	 public void cancelSetting(View view){
	 finish();		
	 }

}