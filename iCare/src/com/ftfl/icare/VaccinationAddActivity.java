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

import com.ftfl.icare.database.VaccinationDataSource;
import com.ftfl.icare.model.VaccinationModel;

public class VaccinationAddActivity extends ActionBarActivity {

	// Variable Declaration
	Button mSaveVaccine = null;
	EditText mEtVaccineName = null;
	EditText mEtVaccineFor = null;
	Button mBvaccineDate = null;
	Button mBvaccineTime = null;
	
	EditText mEtVaccineAddress = null;

	String mVaccineName = "";
	String mVaccineFor = "";
	String mVaccineDate = "";
	String mVaccineTime = "";
	String mVaccineAddress = "";
	String mAlarm = null;
	Bundle mBundle = null;
	int mVaccineID = 0;

	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	int mHour = 0;
	int mMinute = 0;
	int mYear = 0;
	int mDay = 0;
	int mMonth = 0;
	Integer mAlarmHour = 0;
	Integer mAlamrMinute = 0;
	final Calendar mCalendar = Calendar.getInstance();
	CheckBox mVaccineAlarm = null;
	VaccinationModel mVaccinationModel = null;
	VaccinationDataSource mVaccinationDataSource = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vaccine_add);

		mVaccinationDataSource = new VaccinationDataSource(this);

		// definition - gives variable a reference
		mEtVaccineName = (EditText) findViewById(R.id.etVaccineName);
		mEtVaccineFor = (EditText) findViewById(R.id.etVaccineFor);
		mBvaccineDate = (Button) findViewById(R.id.bVaccineDate);
		mBvaccineTime = (Button) findViewById(R.id.bVaccineTime);
		
		mEtVaccineAddress = (EditText) findViewById(R.id.etVaccineAddress);
		mVaccineAlarm = (CheckBox) findViewById(R.id.etVaccineAlarm);
		mSaveVaccine = (Button) findViewById(R.id.addToVaccineList);
		

		// get the Intent that started update Activity
		Intent mIntent = getIntent();

		// get the Bundle that stores the data of update Activity
		mBundle = mIntent.getExtras();

		if (mBundle != null) {
			mVaccineID = mBundle.getInt("id");

			if (mVaccineID > 0) {
				mVaccinationModel = mVaccinationDataSource
						.getVaccineDetail(mVaccineID);

				mEtVaccineName.setText(mVaccinationModel.getmVacName());
				mBvaccineDate.setText(mVaccinationModel.getmVacDate());
				mBvaccineTime.setText(mVaccinationModel.getmVacTime());
				mEtVaccineFor.setText(mVaccinationModel.getmVacReason());
				mEtVaccineAddress.setText(mVaccinationModel.getmVacAddress());
			}
		}

		// OnClick Activity of date field
		mBvaccineDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mYear = mCalendar.get(Calendar.YEAR);
				mMonth = mCalendar.get(Calendar.MONTH);
				mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog dialog = new DatePickerDialog(
						VaccinationAddActivity.this, mDateSetListener, mYear,
						mMonth, mDay);
				dialog.show();
			}
		});

		// OnClick Activity of time field
		mBvaccineTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
				mMinute = mCalendar.get(Calendar.MINUTE);

				TimePickerDialog timeDialog = new TimePickerDialog(
						VaccinationAddActivity.this, timePickerListener, mHour,
						mMinute, false);
				timeDialog.show();
			}
		});

		// OnClick Activity of save button
		mSaveVaccine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetDataFromView();
				if (mVaccineAlarm.isChecked()) {
					mAlarm = "1";
					Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
					alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, mAlarmHour);
					alarmIntent
							.putExtra(AlarmClock.EXTRA_MINUTES, mAlamrMinute);
					alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
					alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(alarmIntent);
				} else {
					mAlarm = "0";
				}

				if (mBundle != null && mVaccineID > 0) {
					if (mVaccineName.length() == 0
							|| mVaccineDate.length() == 0
							|| mVaccineTime.length() == 0
							|| mVaccineAddress.length() == 0
							|| mVaccineFor.length() == 0) {
						Toast.makeText(VaccinationAddActivity.this,
								"complete first!", Toast.LENGTH_SHORT).show();
					} else {
						mVaccinationModel = new VaccinationModel(mVaccineName,
								mVaccineTime, mVaccineDate, mVaccineFor,
								mVaccineAddress, mAlarm);
						long updated = mVaccinationDataSource
								.updateVaccineData(mVaccineID,
										mVaccinationModel);
						if (updated >= 0) {
							Toast.makeText(VaccinationAddActivity.this,
									"updated", Toast.LENGTH_LONG).show();
							Intent iIntent = new Intent(
									VaccinationAddActivity.this,
									VaccinationListActivity.class);
							startActivity(iIntent);

							finish();
						}
					}
				} else if (mVaccineName.length() == 0
						|| mVaccineDate.length() == 0
						|| mVaccineTime.length() == 0
						|| mVaccineAddress.length() == 0
						|| mVaccineFor.length() == 0) {
					Toast.makeText(VaccinationAddActivity.this,
							"complete first", Toast.LENGTH_SHORT).show();
				} else {
					mVaccinationModel = new VaccinationModel(mVaccineName,
							mVaccineTime, mVaccineDate, mVaccineFor,
							mVaccineAddress, mAlarm);
					long inserted = mVaccinationDataSource
							.addNewVaccine(mVaccinationModel);
					if (inserted >= 0) {
						Toast.makeText(VaccinationAddActivity.this, "inserted",
								Toast.LENGTH_LONG).show();
						Intent iIntent = new Intent(
								VaccinationAddActivity.this,
								VaccinationListActivity.class);
						startActivity(iIntent);

						finish();
					}
				}
			}
		});
	}

	public void GetDataFromView() {
		mVaccineName = mEtVaccineName.getText().toString();
		mVaccineDate = mBvaccineDate.getText().toString();
		mVaccineTime = mBvaccineTime.getText().toString();
		mVaccineFor = mEtVaccineFor.getText().toString();
		mVaccineAddress = mEtVaccineAddress.getText().toString();
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;

			mBvaccineDate.setText(new StringBuilder()
					.append(new DecimalFormat("00").format(mDay)).append("-")
					.append(new DecimalFormat("00").format(mMonth + 1))
					.append("-").append(mYear));
		}

	};

	// timepicker in dialogbox

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
			mBvaccineTime.setText(new StringBuilder().append(hour)
					.append(" : ").append(minute).append(" ").append(st));
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vaccination_add, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
			case R.id.VaccinationList:
			// profile found
			vaccinationChart();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void vaccinationChart() {
		// TODO Auto-generated method stub
		Intent i = new Intent(getBaseContext(), VaccinationListActivity.class);
		startActivity(i);
	}

}