package com.ftfl.findrestaurant;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ftfl.findrestaurant.adapter.RestaurantAdapter;
import com.ftfl.findrestaurant.database.RestaurantDataSource;
import com.ftfl.findrestaurant.utils.RestaurantConstants;
import com.ftfl.findrestaurant.utils.RestaurantModel;

public class HomeActivity extends ActionBarActivity {
	RestaurantDataSource restaurantDataSource;
	ListView allResturantList;
	ArrayList<RestaurantModel> restaurantList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		restaurantDataSource = new RestaurantDataSource(this);
		restaurantList = restaurantDataSource.getRestaurantList();

		RestaurantAdapter adapter = new RestaurantAdapter(HomeActivity.this, restaurantList);
		allResturantList = (ListView) findViewById(R.id.listView);
		allResturantList.setAdapter(adapter);
		allResturantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			private int selectedId;
			private int pos;

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
			// Setting Dialog Title

			final String[] menuList = { "view", "edit", "delete"};
			alertDialog.setTitle("Options");
			alertDialog.setIcon(R.drawable.ic_launcher);
			selectedId = restaurantList.get(position).getId();
			alertDialog.setItems(menuList, new DialogInterface.OnClickListener() {			
				public void onClick(DialogInterface dialog, int item) {
					switch (item) {
					case 0:
						Intent intent = new Intent(HomeActivity.this,ViewActivity.class);
						intent.putExtra(RestaurantConstants.SELECTED_ID,selectedId);
						startActivity(intent);

						break;
					case 1:
						// function 2 code here
						Intent secondIntent = new Intent(HomeActivity.this,	UpdateActivity.class);
						secondIntent.putExtra(RestaurantConstants.SELECTED_ID,selectedId);
						startActivity(secondIntent);
						break;
					case 2:
						Toast.makeText(getApplicationContext(),String.valueOf(selectedId),Toast.LENGTH_SHORT).show();

						AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);

						// Setting Dialog Title
						alertDialog.setTitle("Do You Want to delete?");
						// Setting Dialog Message
						alertDialog.setMessage("Are you sure you want delete this?");
						// Setting Icon to Dialog
						alertDialog.setIcon(R.drawable.ic_launcher);

						// Setting Positive "Yes" Button
						alertDialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						
							public void onClick(DialogInterface dialog,	int which) {

								// Write your code here to invoke YES event
								restaurantDataSource.deleteRestaurant(selectedId);
								finish();
								startActivity(getIntent());
							}
						});
						// Showing Alert Message
						// Setting Negative "NO" Button
						alertDialog.setNegativeButton("No",	new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								Toast.makeText(getApplicationContext(), "You clicked on No",Toast.LENGTH_SHORT).show();
								dialog.cancel();
							}

						});
						alertDialog.show();
						break;
						}
						}
						});
						AlertDialog menuDrop = alertDialog.create();

						menuDrop.show();
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.insert_menu) {
			Intent intent = new Intent(HomeActivity.this,
					AddRestaurantActivity.class);
			startActivity(intent);
			return true;
		}
		if (id == R.id.home_menu) {
			Intent intent = new Intent(this,HomeActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}