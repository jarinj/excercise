package com.ftfl.icare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class DrProfileCreateActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dr_profile_create);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctor_profile_create, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.DrProfileList:
			// profile found
			drProfileList();
			return true;


		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void drProfileList() {
		// TODO Auto-generated method stub
		Intent intentDrProfileList = new Intent(getBaseContext(), DrProfileListActivity.class);
		startActivity(intentDrProfileList);
	}
}
