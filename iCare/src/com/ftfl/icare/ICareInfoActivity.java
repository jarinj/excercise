package com.ftfl.icare;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ftfl.icare.database.UserProfileDatabase;
import com.ftfl.icare.fragment.DietInfoFragment;
import com.ftfl.icare.fragment.GalleryFragment;
import com.ftfl.icare.fragment.GeneralHealthInfoFragment;
import com.ftfl.icare.fragment.VaccinationInfoFragment;

public class ICareInfoActivity extends ActionBarActivity {
	// for drawer
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mDrawarMenus;
	Fragment fragment;
	
	//for dashboard
	Button mBuserProfile;
	Button mBdiet;
	Button mBcall;
	Button mBdrProfile;
	Button mBmap;
	Button mBvaccination;
	UserProfileDatabase mUserDatabase;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.icare_info);
		
		// initialization for drawer
		mTitle = mDrawerTitle = getTitle();
		mDrawarMenus = getResources().getStringArray(R.array.drawer_menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		// initialization for dashboard
		mBuserProfile = (Button) findViewById(R.id.bProfile);
		mBdiet = (Button) findViewById(R.id.bDiet);
		mBcall = (Button) findViewById(R.id.bCall);
		mBdrProfile = (Button) findViewById(R.id.bDr);
		mBmap = (Button) findViewById(R.id.bMap);
		mBvaccination = (Button) findViewById(R.id.bVaccine);
		mUserDatabase = new UserProfileDatabase(this);
		
		// for button onClick
		mBuserProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mUserDatabase.isEmpty()) {
					
					Intent i = new Intent(getBaseContext(),	UserProfileCreateActivity.class);
					startActivity(i);

				} else {
					
					Intent i = new Intent(getBaseContext(),	UserProfileListActivity.class);
					
					startActivity(i);
				}
			}
		});
		
		mBdiet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentDietAdd = new Intent(ICareInfoActivity.this, DietAddActivity.class);
				startActivity(intentDietAdd);
			}
		});
		
		mBcall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentCall = new Intent(ICareInfoActivity.this, EmergencyCallActivity.class);
				startActivity(intentCall);
			}
		});
		
		mBdrProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentDrProfile = new Intent(ICareInfoActivity.this, DrProfileCreateActivity.class);
				startActivity(intentDrProfile);
			}
		});
		
		mBmap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentMap = new Intent(ICareInfoActivity.this, GoogleMapActivity.class);
				startActivity(intentMap);
			}
		});
		
		mBvaccination.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentVaccination = new Intent(ICareInfoActivity.this, VaccinationAddActivity.class);
				startActivity(intentVaccination);
			}
		});


		// set a custom shadow that overlays the main content when the drawer
		// opens

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.item_drawer_list, mDrawarMenus));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.navigation_drawer_open, /*
										 * "open drawer" description for
										 * accessibility
										 */
		R.string.navigation_drawer_close /*
										 * "close drawer" description for
										 * accessibility
										 */
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.icare_info, menu);
		return true;
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_settings:

			return true;
		
		case R.id.dashboard:
			// profile found
			dashboard();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void dashboard() {
		// TODO Auto-generated method stub
		Intent i = new Intent(getBaseContext(), ICareInfoActivity.class);
		startActivity(i);
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();

		switch (position) {
		
		case 0:
			fragment = new GeneralHealthInfoFragment();

			break;

		case 1:
			fragment = new VaccinationInfoFragment();

			break;

		case 2:
			fragment = new DietInfoFragment();
			break;
		case 3:
			fragment = new GalleryFragment();
			break;

		default:
			break;
		}

		fragmentManager.beginTransaction().replace(R.id.container, fragment)
				.commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawarMenus[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
