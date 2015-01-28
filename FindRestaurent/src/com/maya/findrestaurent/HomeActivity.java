package com.maya.findrestaurent;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class HomeActivity extends ActionBarActivity{

	ListView mRestaurantList;
	RestaurantModel restaurant;
	RestaurantDataSource dataSource;
	ArrayList<RestaurantModel> restaurantName;
	RestaurantAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//fav restaurant list
		mRestaurantList = (ListView) findViewById(R.id.listView1);
		
		dataSource = new RestaurantDataSource(this);
		restaurantName = dataSource.getRestaurantList();
		
		adapter = new RestaurantAdapter(this, restaurantName);
		mRestaurantList.setAdapter(adapter);
		
		mRestaurantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// creating bundle obj
				Bundle b = new Bundle();
				
				//put id into bundle
				b.putLong("id", id);
				Intent i = new Intent(getApplicationContext(), ViewProfileActivity.class);
				
				// storing bundle obj into intent
				i.putExtras(b);
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		inflater.inflate(R.menu.view_profile, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.addRestaurant:
			// adding diet chart
			addRestaurant();
			return true;
			
		case R.id.delete:
			delete();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void addRestaurant() {
		Intent i = new Intent(getBaseContext(), CreateProfileActivity.class);
		startActivity(i);
	}
	
	private void delete() {
		// TODO Auto-generated method stub
		dataSource = new RestaurantDataSource(this);
		long eId = 0;
		dataSource.delete(eId);
	}
}
