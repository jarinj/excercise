package com.ftfl.mylocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ftfl.mylocation.googlemap.GoogleMapActivity;

public class RetrieveActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.retrieve_row);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.retrieve, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.googleMap:
			// add new
			Intent intentGoogleMap = new Intent(getBaseContext(), GoogleMapActivity.class);
			startActivity(intentGoogleMap);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	
	}
}
