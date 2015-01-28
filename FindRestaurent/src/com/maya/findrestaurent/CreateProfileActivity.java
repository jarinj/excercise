package com.maya.findrestaurent;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateProfileActivity extends ActionBarActivity {
	
	EditText etName, etDescription, etAddress, etLatitude, etLongitude, etMenu, etSpecialMenu, etClose, etOpen;
	String name, description, address, latitude, longitude, menu, specialMenu, close, open;
	Button save;
	
	RestaurantDataSource dataSource = null;
	RestaurantModel restaurant;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_list);
		
		dataSource = new RestaurantDataSource(this);
		
		//initialization
		etName = (EditText)findViewById(R.id.etName);
		etDescription = (EditText)findViewById(R.id.etDescription);
		etAddress = (EditText)findViewById(R.id.etAddress);
		etLatitude = (EditText)findViewById(R.id.etLatitude);
		etLongitude = (EditText)findViewById(R.id.etLongitude);
		etMenu = (EditText)findViewById(R.id.etMenu);
		etSpecialMenu = (EditText)findViewById(R.id.etSpecialMenu);
		etClose = (EditText)findViewById(R.id.etClose);
		etOpen = (EditText)findViewById(R.id.etOpen);
		save = (Button) findViewById(R.id.bSaveCP);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name = etName.getText().toString();
				description = etDescription.getText().toString();
				address = etAddress.getText().toString();
				latitude = etLatitude.getText().toString();
				longitude = etLongitude.getText().toString();
				menu = etMenu.getText().toString();
				specialMenu = etSpecialMenu.getText().toString();
				close = etClose.getText().toString();
				open = etOpen.getText().toString();
				
				restaurant = new RestaurantModel(name, description, address, latitude, longitude, menu, specialMenu, close, open);
				
				long inserted = dataSource.addRestaurant(restaurant);
				if (inserted >= 0) {
					Toast.makeText(getApplicationContext(), "data inserted", Toast.LENGTH_LONG).show();
					Intent i = new Intent(getApplicationContext(),ViewProfileActivity.class);
					startActivity(i);
					finish();
				}else{
					Toast.makeText(getApplicationContext(), "Data insertion failed!", Toast.LENGTH_LONG).show();
				}
			}
		});
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
