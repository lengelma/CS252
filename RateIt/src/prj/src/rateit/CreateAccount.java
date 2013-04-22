package prj.src.rateit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CreateAccount extends Activity {

	private EditText firstNameView;
	private EditText lastNameView;
	private EditText emailAddressView;
	private EditText passwordView;
	private EditText confirmPasswordView;
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	private String confirmPassword;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//Set the required fields
		LinearLayout layout=(LinearLayout)findViewById(R.id.LinearLayout_createAccount);
		setFields(layout);
	}
	
	public void setFields(LinearLayout layout){
		firstNameView = (EditText)layout.getChildAt(1);
		lastNameView = (EditText)layout.getChildAt(3);
		emailAddressView = (EditText)layout.getChildAt(5);
		passwordView = (EditText)layout.getChildAt(7);
		confirmPasswordView = (EditText)layout.getChildAt(9);
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

	public void saveUserData(View view){
		
		boolean checkData=true;
		
		firstName = firstNameView.getText().toString();
		lastName = lastNameView.getText().toString();
		emailAddress = emailAddressView.getText().toString();
		password = passwordView.getText().toString();
		confirmPassword= confirmPasswordView.getText().toString();
		
		checkData=verifyData();
		
		//if(checkData==true){
		Intent intent = new Intent(this, OwnersHome.class);	
		startActivity(intent);
		//}
	}
	
	public boolean verifyData(){
		View focusView = null;
		boolean dataCheck=true;
		
		firstNameView.setError(null);
		lastNameView.setError(null);
		emailAddressView.setError(null);
		passwordView.setError(null);
		confirmPasswordView.setError(null);
		
		if (TextUtils.isEmpty(firstName)){
			firstNameView.setError(getString(R.string.error_field_required));
			focusView = firstNameView;
			dataCheck=false;
		}
		
		if (TextUtils.isEmpty(lastName)){
			lastNameView.setError(getString(R.string.error_field_required));
			focusView = lastNameView;
			dataCheck=false;
		}
		
		if (TextUtils.isEmpty(emailAddress)){
			emailAddressView.setError(getString(R.string.error_field_required));
			focusView = emailAddressView;
			dataCheck=false;
		}
		
		if (TextUtils.isEmpty(password)){
			passwordView.setError(getString(R.string.error_field_required));
			focusView = passwordView;
			dataCheck=false;
		}else if (password.length() < 4) {
			passwordView.setError(getString(R.string.error_invalid_password));
			focusView = passwordView;
			dataCheck = false;
		}
		
		if (TextUtils.isEmpty(confirmPassword)){
			confirmPasswordView.setError(getString(R.string.error_field_required));
			focusView = confirmPasswordView;
			dataCheck=false;
		}else
		if(password.equals(confirmPassword)==false){
			confirmPasswordView.setError(getString(R.string.passwordMatchError));
			focusView = confirmPasswordView;
			dataCheck=false;
		}
				
	return dataCheck;	
	}
	
}
	
	
	

