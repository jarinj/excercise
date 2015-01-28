package com.maya.findrestaurent;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewProfileActivity extends ActionBarActivity {
	
	RestaurantModel restaurant;
	RestaurantDataSource dataSource;
	
	TextView showName, showDescription, showAddress, showLatitude, showLongitude, showMenu, showSpecialMenu, showClose, showOpen;
	String mName, mDescription, mAddress, mLatitude, mLongitude, mMenu, mSpecialMenu, mClose, mOpen;
	
	String mID = "";
	Long mId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_profile);
		
		//get the intent from home activity to start this activity
		Intent intent = getIntent();
		
		// get the bundle that stores the data of this activity
		Bundle b = intent.getExtras();
		
		mId = b.getLong("id");
		
		showName = (TextView)findViewById(R.id.tvNameVP);
		showDescription = (TextView)findViewById(R.id.tvDescriptionVP);
		showAddress = (TextView)findViewById(R.id.tvAddressVP);
		showLatitude = (TextView)findViewById(R.id.tvLatitudeVP);
		showLongitude = (TextView)findViewById(R.id.tvLongitudeVP);
		showMenu = (TextView)findViewById(R.id.tvMenuVP);
		showSpecialMenu = (TextView)findViewById(R.id.tvSpecialMenuVP);
		showClose = (TextView)findViewById(R.id.tvCloseVP);
		showOpen = (TextView)findViewById(R.id.tvOpenVP);
		
		dataSource = new RestaurantDataSource(this);
		restaurant = dataSource.getRestaurantDetail(mId);
		
		mName = restaurant.getName();
		mDescription = restaurant.getDescription();
		mAddress = restaurant.getAddress();
		mLatitude = restaurant.getLatitude();
		mLongitude = restaurant.getLongitude();
		mMenu = restaurant.getMenu();
		mSpecialMenu = restaurant.getSpecialMenu();
		mClose = restaurant.getClose();
		mOpen = restaurant.getOpen();
		
		showName.setText("Name: " + mName);
		showDescription.setText("Description: " + mDescription);
		showAddress.setText("Address: " + mAddress);
		showLatitude.setText("Latitude: " + mLatitude);
		showLongitude.setText("Longitute: " + mLongitude);
		showMenu.setText("Menu: " + mMenu);
		showSpecialMenu.setText("Special Menu: " + mSpecialMenu);
		showClose.setText("Closing Day: " + mClose);
		showOpen.setText("Opening Time: " + mOpen);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		inflater.inflate(R.menu.google_map, menu);
		inflater.inflate(R.menu.view_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// take appropriate action for each item click.
		switch(item.getItemId()){
		case R.id.home:
			home();
			return true;
			
		case R.id.addRestaurant:
			// adding diet chart
			addRestaurant();
			return true;
			
		case R.id.googleMap:
			googleMap();
			return true;
			
		case R.id.edit:
			edit();
			return true;
			
		case R.id.delete:
			delete();
			return true;
			
			default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	private void home() {
		Intent i = new Intent(getBaseContext(),HomeActivity.class);
		startActivity(i);
	}
	
	private void addRestaurant() {
		Intent i = new Intent(getBaseContext(), CreateProfileActivity.class);
		startActivity(i);
	}
	
	private void googleMap() {
		// TODO Auto-generated method stub
		Intent i = new Intent(getBaseContext(), GoogleMapActivity.class);
		startActivity(i);
	}

	private void delete() {
		// TODO Auto-generated method stub
		dataSource = new RestaurantDataSource(this);
		dataSource.delete(mId);
		Intent i = new Intent(getBaseContext(), HomeActivity.class);
		startActivity(i);
	}

	private void edit() {
		// TODO Auto-generated method stub
		Intent i = new Intent(getBaseContext(), CreateProfileActivity.class);
		startActivity(i);
	}
}
