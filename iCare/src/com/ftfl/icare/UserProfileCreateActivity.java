package com.ftfl.icare;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ftfl.icare.database.UserProfileDatabase;
import com.ftfl.icare.model.UserProfileModel;

public class UserProfileCreateActivity extends ActionBarActivity implements OnClickListener, OnDateSetListener{

	// Variable Declaration
	EditText mEtName = null;
	EditText mEtHeight = null;
	EditText mEtWeight = null;
	Button mBirthDate;
	RadioGroup mRGgender;
	RadioButton mRadioButton;
	Button mBsave;
	
	// date
	public String mCurrentDate = "";

	int mYear = 0;
	int mDay = 0;
	int mMonth = 0;
	
	String mActivityCurrentDate = "";
	String mActivityProfileId = "";

	final Calendar mCalendar = Calendar.getInstance();

	// String Values
	//String mImagePath = null;
	String mDOB = null;
	String mName = null;
	String mHeight = null;
	String mWeight = null;
	String mGender = null;
	String mGenderShow;


	// Profile Object
	UserProfileModel mModel = null;

	// DataSource Object
	UserProfileDatabase mDataSource = null;

	String mID = "";
	int mId = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile_create);
		
		// initialize
		mEtName = (EditText) findViewById(R.id.etName);
		mBirthDate = (Button) findViewById(R.id.bBirthDate);
		mEtHeight = (EditText) findViewById(R.id.etHeight);
		mEtWeight = (EditText) findViewById(R.id.etWeight);
		mBsave = (Button) findViewById(R.id.bSave);
		
		/*if (mID != null) {
			//mLID = Long.parseLong(mID);

			
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 
			mDataSource = new UserProfileDatabase(this);
			mModel = mDataSource.getDetail(mId);

			String mName = mModel.getmName();
			String mDOB = mModel.getmDOB();
			String mHeight = mModel.getmHeight();
			String mWeight = mModel.getmWeight();
			String mGender = mModel.getmGender();


			// set the value of database to the text field.

			mEtName.setText(mName);
			mBirthDate.setText(mDOB);
			mEtHeight.setText(mHeight);
			mEtWeight.setText(mWeight);
			

			
			 * change button name
			 
			mBsave.setText("Update");
		}
*/		/*
		 * Radio button click event
		 */
		
		/*mRGgender = (RadioGroup) findViewById(R.id.radioGroup1);
		int selectedId = mRGgender.getCheckedRadioButtonId();
		mRadioButton = (RadioButton) findViewById(selectedId);
		mGender= mRadioButton.getText().toString();*/
			
		/*
		 * Birth Date
		 */
		mBirthDate.setOnClickListener(this);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
		mBirthDate.setText(mCurrentDate);


		mDataSource = new UserProfileDatabase(this);

		mBsave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getData();
				mRGgender = (RadioGroup) findViewById(R.id.radioGroup1);
				int selectedId = mRGgender.getCheckedRadioButtonId();
				mRadioButton = (RadioButton) findViewById(selectedId);
				mGender= mRadioButton.getText().toString();
				mModel = new UserProfileModel(mName, mDOB, mHeight, mWeight, mGender);
				//mDataSource = new UserProfileDatabase(this);
				long inserted = mDataSource.addProfile(mModel);
				if (inserted >= 0) {
					Toast.makeText(getApplicationContext(),
							"Successfully Saved!", Toast.LENGTH_LONG).show();

					Intent i = new Intent(getApplicationContext(),
							ICareInfoActivity.class);
					startActivity(i);
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Data insertion failed...", Toast.LENGTH_LONG)
							.show();
				}
			}

			private void getData() {
				// TODO Auto-generated method stub

				mName = mEtName.getText().toString();
				mHeight = mEtHeight.getText().toString();
				mWeight = mEtWeight.getText().toString();
				mDOB = mBirthDate.getText().toString();
				//mImagePath = fileUri.getPath();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_user_profile, menu);
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
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
		mBirthDate.setText(new StringBuilder()
		// Month is 0 based so add 1
		.append(dayOfMonth).append("/").append(monthOfYear + 1)
		.append("/").append(year));
	}	

	@Override
	public void onClick(View v) {
		mYear = mCalendar.get(Calendar.YEAR);
		mMonth = mCalendar.get(Calendar.MONTH);
		mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog dialog = new DatePickerDialog(this, this, mYear,mMonth, mDay);
		dialog.show();

}
	}

