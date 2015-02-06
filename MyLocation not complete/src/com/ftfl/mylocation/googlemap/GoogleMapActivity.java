package com.ftfl.mylocation.googlemap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.ftfl.mylocation.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends FragmentActivity{
	private GoogleMap googleMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.google_map);
		
		
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
}
