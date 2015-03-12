package com.ftfl.icare;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ftfl.icare.adapter.ProfileAdapter;
import com.ftfl.icare.database.UserProfileDatabase;
import com.ftfl.icare.model.UserProfileModel;

public class UserProfileListActivity extends ActionBarActivity {
	

	
	// Declaration of ListView
	ListView mListView = null;

	// Profile Object
	UserProfileModel mModel = null;

	// DataSource Object
	UserProfileDatabase mDataSource = null;

	// Profile Type ArrayList
	ArrayList<UserProfileModel> mModelArrayList = null;
	ArrayList<String> mStringList = new ArrayList<String>();
	ArrayList<String> mIdList = new ArrayList<String>();

	// CustomAdapter
	ProfileAdapter mCustomAdapter = null;
	
	Integer mPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile_list);

		setListData();
		// Initialization ListView
				mListView = (ListView) findViewById(R.id.listView1);

				// Get Data From DataSource
				mDataSource = new UserProfileDatabase(this);
				mModelArrayList = mDataSource.getProfileList();

				// Set Adapter
				mCustomAdapter = new ProfileAdapter(UserProfileListActivity.this, mModelArrayList);
				mListView.setAdapter(mCustomAdapter);
				
			
				
				final String[] option = new String[] { "Edit Data", "Delete Data" };
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						android.R.layout.select_dialog_item, option);
				AlertDialog.Builder builder = new AlertDialog.Builder(this);

				builder.setTitle("Select Option");
				builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Log.e("Selected Item", String.valueOf(which));
						
						if (which == 0) {
							editData(mPosition);
						}
						
						if (which == 1) {
							deleteData(mPosition);
						}
					}

				});
				final AlertDialog dialog = builder.create();
				mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						mPosition = position;
						dialog.show();
					}
				});
				
				

	}
				
	private void setListData() {
		UserProfileDatabase profiledatabase = new UserProfileDatabase(this);
		mModelArrayList = profiledatabase.getProfileList();
		for (int i = 0; i < mModelArrayList.size(); i++) {
			UserProfileModel mProfile = mModelArrayList.get(i);
			mIdList.add(mProfile.getmID());
			mStringList.add(mProfile.getmName());
		}
	}
			
			public void editData(Integer ePosition){
				Intent mEditIntent = new Intent(getApplicationContext(),
						UserProfileCreateActivity.class);
				Long id = Long.parseLong(mIdList.get(ePosition));
				mEditIntent.putExtra("id", id.toString());
				startActivity(mEditIntent);
				
			}
	
			public void deleteData(Integer ePosition){
				mDataSource = new UserProfileDatabase(this);
				Integer id = Integer.parseInt(mIdList.get(ePosition));
				mDataSource.deleteData(id);
				Intent mDeleteIntent = new Intent(getApplicationContext(),
						UserProfileListActivity.class);
				startActivity(mDeleteIntent);
			}
				
	}
