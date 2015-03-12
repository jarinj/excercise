package com.ftfl.icare;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class GoogleMapActivity extends Activity {
	// Google Map
	GoogleMap googleMap;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.google_map);try {
			// loading map
			initializeMap();
			
			//placing a marker
			//latitude and longitude
			double latitudeNICVD = 23.770652;
			double longitudeNICVD = 90.369833;
			
			double latitudeChildHospital = 23.773195;
			double longitudeChildHospital = 90.369013;
			
			double latitudeDhakaMedical = 23.803505;
			double longitudeDhakaMedical = 90.388492;
			//create marker
			MarkerOptions markerNICVD = new MarkerOptions().position(new LatLng(latitudeNICVD, longitudeNICVD)).title("NICVD");
			MarkerOptions markerChildHospital = new MarkerOptions().position(new LatLng(latitudeChildHospital, longitudeChildHospital)).title("Dhaka Child Hospital");
			MarkerOptions markerDMCH = new MarkerOptions().position(new LatLng(latitudeDhakaMedical, longitudeDhakaMedical)).title("Dhaka Medical");
			
			//adding marker
			googleMap.addMarker(markerNICVD);
			googleMap.addMarker(markerChildHospital);
			googleMap.addMarker(markerDMCH);
			
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
