package com.ftfl.icare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ftfl.icare.database.UserProfileDatabase;
import com.ftfl.icare.model.UserProfileModel;

public class UserProfileViewActivity extends ActionBarActivity {

	private TextView mShowName;
	private TextView mShowDOB;
	private TextView mShowHeight;
	private TextView mShowWeight;
	private TextView mShowGender;

	String mName;
	String mDOB;
	String mHeight;
	String mWeight;
	String mGender;

	UserProfileModel mUserModel;
	UserProfileDatabase mUserDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile_view);
		
		
		// initialize
		mShowName = (TextView) findViewById(R.id.showName);
		mShowDOB = (TextView) findViewById(R.id.showDOB);
		mShowHeight = (TextView) findViewById(R.id.showHeight);
		mShowWeight = (TextView) findViewById(R.id.showWeight);
		mShowGender = (TextView) findViewById(R.id.showGender);

			
			mUserDatabase = new UserProfileDatabase(this);
			mUserModel = mUserDatabase.getDetail(1);

			// get data
			mName = mUserModel.getmName();
			mDOB = mUserModel.getmDOB();
			mHeight = mUserModel.getmHeight();
			mWeight = mUserModel.getmWeight();
			mGender = mUserModel.getmGender();

			// set data
			mShowName.setText(mName);
			mShowDOB.setText(mDOB);
			mShowHeight.setText(mHeight);
			mShowWeight.setText(mWeight);
			mShowGender.setText(mGender);

		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_user_profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.edit:
			// profile found
			edit();
			return true;

		case R.id.delete:
			// adding diet chart
			delete();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void delete() {
		// TODO Auto-generated method stub
		mUserDatabase = new UserProfileDatabase(this);
		mUserDatabase.deleteData(1);
		Intent i = new Intent(getBaseContext(), ICareInfoActivity.class);
		startActivity(i);
	}

	private void edit() {
		// TODO Auto-generated method stub
		Bundle b = new Bundle();
		// put id into bundle
		b.putInt("id", 1);
		Intent mIntent = new Intent(getBaseContext(),
				UserProfileCreateActivity.class);
		mIntent.putExtras(b);
		startActivity(mIntent);
	}
}
