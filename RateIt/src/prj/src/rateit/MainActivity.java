package prj.src.rateit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import java.sql.*;

import prj.src.rateit.util.DatabaseAPI;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void ownersFrame(View view){
    Intent intent = new Intent(this, OwnersLogin.class);	
    startActivity(intent);	
    }
    
    public void customersFrame(View view){
        DatabaseAPI.test();    	
    }
        
}
