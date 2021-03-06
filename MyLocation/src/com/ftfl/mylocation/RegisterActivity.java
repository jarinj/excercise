package com.ftfl.mylocation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.mylocation.model.LocationModel;
import com.ftfl.mylocation.utils.GPSTracker;
import com.google.android.gms.fitness.data.DataSource;

public class RegisterActivity extends ActionBarActivity {
	// Variable Declaration
	ImageView mImageView = null;
	TextView mtvLatitude = null;
	TextView mtvLongitude = null;
	EditText metRemarks = null;
	Button mButtonSave = null;

	// String Values
	String mImage = null;
	String mLatitude = null;
	String mLongitude = null;
	String mRemarks = null;
	String mLocalTime = null;

	// Intent Object
	Intent mIntent = null;

	// Profile Object
	LocationModel mLocation = null;

	// GPSTracker class
	GPSTracker mGPS = null;

	// DataSource Object
	DataSource mDataSource = null;

	// Photo Object
	String mPhotoPath = "";
	Bitmap bitmap = null;


	Button mRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		mRegister = (Button)findViewById(R.id.bRegisterTakePhoto);
	}
	
	public void save(View view){
		Intent intentHome = new Intent(RegisterActivity.this, HomeActivity.class);
		startActivity(intentHome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save, menu);
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
