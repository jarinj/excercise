package com.ftfl.icare;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class UserProfileDashboardActivity extends ActionBarActivity {
	
	Button mBuserProfile;
	Button mBdiet;
	Button mBcall;
	Button mBdrProfile;
	Button mBhelp;
	Button mBvaccination;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile_dashboard);
		
		mBuserProfile = (Button) findViewById(R.id.bProfile);
		mBdiet = (Button) findViewById(R.id.bDiet);
		mBcall = (Button) findViewById(R.id.bCall);
		mBdrProfile = (Button) findViewById(R.id.bDr);
		mBhelp = (Button) findViewById(R.id.bHelp);
		mBvaccination = (Button) findViewById(R.id.bVaccine);
		
		mBuserProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentViewProfile = new Intent(UserProfileDashboardActivity.this, UserProfileViewActivity.class);
				startActivity(intentViewProfile);
			}
		});
		
		mBdiet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentDietAdd = new Intent(UserProfileDashboardActivity.this, DietAddActivity.class);
				startActivity(intentDietAdd);
			}
		});
		
		mBcall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentCall = new Intent(UserProfileDashboardActivity.this, EmergencyCallActivity.class);
				startActivity(intentCall);
			}
		});
		
		mBdrProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentDrProfile = new Intent(UserProfileDashboardActivity.this, DrProfileCreateActivity.class);
				startActivity(intentDrProfile);
			}
		});
		
		mBhelp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentHelp = new Intent(UserProfileDashboardActivity.this, ICareInfoActivity.class);
				startActivity(intentHelp);
			}
		});
		
		mBvaccination.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentVaccination = new Intent(UserProfileDashboardActivity.this, VaccinationAddActivity.class);
				startActivity(intentVaccination);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile_dashboard, menu);
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
