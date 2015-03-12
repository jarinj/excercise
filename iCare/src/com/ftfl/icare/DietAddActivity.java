package com.ftfl.icare;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ftfl.icare.database.DietDataSource;
import com.ftfl.icare.model.DietModel;

public class DietAddActivity extends ActionBarActivity {

	// Variable Declaration
	EditText mEtFeast = null;
	EditText mEtMenu = null;
	Button mBdietDate = null;
	Button mBdietTime = null;
	
	Button mAddToDietChart = null;

	String mFeast = "";
	String mMenu = "";
	String mDietDate = "";
	String mDietTime = "";
	String mAlarm = "";
	

	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	int mHour = 0;
	int mMinute = 0;
	int mYear = 0;
	int mDay = 0;
	int mMonth = 0;
	int mDietID = 0;

	final Calendar mCalendar = Calendar.getInstance();
	CheckBox mSetAlarm = null;
	DietModel mDietModel = null;
	DietDataSource mDietDataSource = null;
	Bundle mBundle = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diet_add);
		mDietDataSource = new DietDataSource(this);

		// definition - gives variable a reference
		mEtFeast = (EditText) findViewById(R.id.etFeast);
		mEtMenu = (EditText) findViewById(R.id.etMenu);
		mBdietDate = (Button) findViewById(R.id.bDietDate);
		mBdietTime = (Button) findViewById(R.id.bDietTime);
		//mSummeryET = (EditText) findViewById(R.id.summeryet);
		mSetAlarm = (CheckBox) findViewById(R.id.cbDietAlarm);
		mAddToDietChart = (Button) findViewById(R.id.bAddToDietChart);

		// get the Intent that started update Activity
		Intent mIntent = getIntent();

		// get the Bundle that stores the data of update Activity
		mBundle = mIntent.getExtras();

		if (mBundle != null) {
			mDietID = mBundle.getInt("id");

			if (mDietID > 0) {
				mDietModel = mDietDataSource.getDietDetail(mDietID);

				mEtFeast.setText(mDietModel.getmFeast());
				mEtMenu.setText(mDietModel.getmMenu());
				mBdietDate.setText(mDietModel.getmDate());
				mBdietTime.setText(mDietModel.getmTime());

			}
		}

		// OnClick Activity of date field
		mBdietDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mYear = mCalendar.get(Calendar.YEAR);
				mMonth = mCalendar.get(Calendar.MONTH);
				mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog dialog = new DatePickerDialog(
						DietAddActivity.this, mDateSetListener,
						mYear, mMonth, mDay);
				dialog.show();
			}
		});

		// OnClick Activity of time field
		mBdietTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
				mMinute = mCalendar.get(Calendar.MINUTE);

				TimePickerDialog timeDialog = new TimePickerDialog(
						DietAddActivity.this, timePickerListener,
						mHour, mMinute, false);
				timeDialog.show();
			}
		});

		// OnClick Activity of save field
		mAddToDietChart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetDataFromView();
				if (mSetAlarm.isChecked()) {
					mAlarm = "true";
					Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
					alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, mSetHour);
					alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES,mSetMinute);
					alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
					alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(alarmIntent);
				} else {
					mAlarm = "false";
				}

				if (mBundle != null && mDietID > 0) {
					if (mFeast.length() == 0 
							|| mMenu.length() == 0
							|| mDietDate.length() == 0
							|| mDietTime.length() == 0) {
						Toast.makeText(DietAddActivity.this,
								"complete first!", Toast.LENGTH_SHORT).show();
					} else {
						mDietModel = new DietModel(mFeast, mMenu, mDietTime,
								mDietDate, mAlarm);
						long updated = mDietDataSource.updateDietData(mDietID,
								mDietModel);
						if (updated >= 0) {
							Toast.makeText(DietAddActivity.this,
									"updated",
									Toast.LENGTH_LONG).show();
							Intent dietViewIntent = new Intent(
									DietAddActivity.this,
									DietViewActivity.class);
							startActivity(dietViewIntent);

							finish();
						}
					}
				} else if (mFeast.length() == 0 
						|| mMenu.length() == 0 
						|| mDietDate.length() == 0 
						|| mDietTime.length() == 0) {
					Toast.makeText(DietAddActivity.this,
							"complete first!", Toast.LENGTH_SHORT).show();
				} else {
					mDietModel = new DietModel(mFeast, mMenu, mDietTime,
							mDietDate, mAlarm);
					long inserted = mDietDataSource.addNewDiet(mDietModel);
					if (inserted >= 0) {
						Toast.makeText(DietAddActivity.this,
								"data Intserted!", Toast.LENGTH_LONG)
								.show();
						Intent dietListIntent = new Intent(
								DietAddActivity.this,
								DietListActivity.class);
						startActivity(dietListIntent);

						finish();
					}
				}
			}
		});
	}

	public void GetDataFromView() {
		mFeast = mEtFeast.getText().toString();
		mMenu = mEtMenu.getText().toString();
		mDietDate = mBdietDate.getText().toString();
		mDietTime = mBdietTime.getText().toString();
		// mSummery = mSummeryET.getText().toString();
	}

	// call DateSetListener for set selected date in edittext field
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			mBdietDate.setText(new StringBuilder()
					.append(new DecimalFormat("00").format(mDay)).append("-")
					.append(new DecimalFormat("00").format(mMonth + 1))
					.append("-").append(mYear));
		}
	};

	// timepicker in dialogbox
	// call TimeSetListener for set selected time in edittext field
	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mSetHour = hourOfDay;
			mSetMinute = minute;
			int hour = 0;
			String st = "";
			if (hourOfDay > 12) {
				hour = hourOfDay - 12;
				st = "PM";
			}

			else if (hourOfDay == 12) {
				hour = hourOfDay;
				st = "PM";
			}

			else if (hourOfDay == 0) {
				hour = hourOfDay + 12;
				st = "AM";
			} else {
				hour = hourOfDay;
				st = "AM";
			}
			mBdietTime.setText(new StringBuilder().append(hour).append(" : ")
					.append(minute).append(" ").append(st));
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diet_add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
			case R.id.DietChart:
			// profile found
			dietChart();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void dietChart() {
		// TODO Auto-generated method stub
		Intent i = new Intent(getBaseContext(), DietListActivity.class);
		startActivity(i);
	}

}