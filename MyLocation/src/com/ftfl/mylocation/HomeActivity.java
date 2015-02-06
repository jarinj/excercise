package com.ftfl.mylocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends ActionBarActivity {
	Button mRegisterHome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		mRegisterHome = (Button)findViewById(R.id.bRegisterHome);
	}
	
	public void registerButtonClicked(View view){
		Intent intentRegister = new Intent(HomeActivity.this, TakePhotoActivity.class);
		startActivity(intentRegister);
	}
	
	public void retrieveButtonClicked(View view){
		Intent intentRetrieve = new Intent(HomeActivity.this, RetrieveActivity.class);
		startActivity(intentRetrieve);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
