package prj.src.rateit;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class ViewBusiness extends Activity {
	
	private EditText nameView;
	private EditText addressView;
	private EditText websiteView;
	
	private String name="Ishan";
	private String address;
	private String website;
	
	private Spinner spinner1, spinner2, spinner3, spinner4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_business);
		// Show the Up button in the action bar.
		setupActionBar();
		addItemsOnSpinner();
		LinearLayout layout = (LinearLayout)findViewById(R.id.LinearLayout01);
		setSettings(layout);
		setFieldsView(layout);
		
	}
	
	public void setSettings(LinearLayout layout){
		((EditText)(layout.getChildAt(1))).setText(name);
		((EditText)(layout.getChildAt(3))).setText(address);
		((EditText)(layout.getChildAt(5))).setText(website);
	}
	
	public void setFieldsView(LinearLayout layout){
		nameView = (EditText)layout.getChildAt(1);
		addressView = (EditText)layout.getChildAt(3);
		websiteView = (EditText)layout.getChildAt(5);
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
	
	public void addItemsOnSpinner() {
		spinner1 = (Spinner) findViewById(R.id.Spinner01);
		spinner2 = (Spinner) findViewById(R.id.Spinner02);
		spinner3 = (Spinner) findViewById(R.id.Spinner03);
		spinner4 = (Spinner) findViewById(R.id.Spinner04);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> hours_list = new ArrayList<String>();
		list.add("Monday");list.add("Tuesday");list.add("Wednesday");list.add("Thursday");list.add("Friday");
		list.add("Saturday");list.add("Sunday");
		hours_list.add("6am");hours_list.add("7am");hours_list.add("8am");hours_list.add("9am");hours_list.add("10am");
		hours_list.add("3pm");hours_list.add("4pm");hours_list.add("5pm");hours_list.add("6pm");hours_list.add("7pm");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, hours_list);
		spinner1.setAdapter(dataAdapter);
		spinner2.setAdapter(dataAdapter);
		spinner3.setAdapter(dataAdapter2);
		spinner4.setAdapter(dataAdapter2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_business, menu);
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
	
	public void viewBusiness(View view){
		boolean dataCheck=true;
		 
		 name = nameView.getText().toString();
		 address = addressView.getText().toString();
		 website = websiteView.getText().toString();	
		 
		 dataCheck=verifyData();
			if(dataCheck==true){
				 finish();
			 }
			 
	}
	
	public boolean verifyData(){
		View focusView = null;
		boolean dataCheck=true;
			
		nameView.setError(null);
		addressView.setError(null);		 
		
		if (TextUtils.isEmpty(name)){
			nameView.setError(getString(R.string.error_field_required));
			focusView = nameView;
			dataCheck=false;
		}
		
		if (TextUtils.isEmpty(address)){
			addressView.setError(getString(R.string.error_field_required));
			focusView = addressView;
			dataCheck=false;
		}
	
	return dataCheck;	
	}
		 
	public void cancelViewBussiness(View view){
		finish();
	}
	
	

}
