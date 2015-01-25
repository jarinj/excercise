package com.ftfl.googlelocationmap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity {
	// Google Map
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		try {
			// loading map
			initializeMap();
			
			//placing a marker
			//latitude and longitude
			double latitude = 23.709921;
			double longitude = 90.407143;
			
			//create marker
			MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Dhaka");
			
			//adding marker
			googleMap.addMarker(marker);
			
			//show current location
			googleMap.setMyLocationEnabled(true);
			
			//enable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initializeMap();
	}
	
	// function to load map. if map is not created it will create.
	private void initializeMap() {
		// TODO Auto-generated method stub
		if (googleMap == null) {
			googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
			
			//check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(), "Sorry! Unable to create maps", Toast.LENGTH_LONG).show();
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
