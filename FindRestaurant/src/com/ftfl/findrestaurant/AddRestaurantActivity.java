package com.ftfl.findrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ftfl.findrestaurant.database.RestaurantDataSource;
import com.ftfl.findrestaurant.utils.RestaurantModel;

public class AddRestaurantActivity extends ActionBarActivity {

	private EditText mEtName;
	private EditText mEtDescrption;
	private EditText mEtAddress;
	private EditText mEtLatitude;
	private EditText mEtLongitude;
	private EditText mEtMenu;
	private EditText mEtSpecialMenu;
	private EditText mEtCloseDay;
	private EditText mEtOpenTime;

	private Button mBsubmit;

	private String mName = "";
	private int mId;
	private String mAddress = "";
	private String mLatitude = "";
	private String mLongitude = "";
	private String mMenus = "";
	private String mSpecialMenu = "";
	private String mCloseDay = "";
	private String mDailyOpenTime = "";
	private String mDescription = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_restaurant);
		initialize();
	}

	public void insertButtonClicked(View view) {
		getData();

		RestaurantModel res_Info_obj = new RestaurantModel(mName,
				mAddress, mLatitude, mLongitude, mMenus, mSpecialMenu,
				mCloseDay, mDailyOpenTime, mDescription);
		RestaurantDataSource res_data_source_obj = new RestaurantDataSource(
				this);
		res_data_source_obj.insertRestaurantInfo(res_Info_obj);
		Intent intent = new Intent(AddRestaurantActivity.this, HomeActivity.class);
		startActivity(intent);

	}

	public void initialize() {
		mEtName = (EditText) findViewById(R.id.etName);
		mEtDescrption = (EditText) findViewById(R.id.etDescription);
		mEtAddress = (EditText) findViewById(R.id.etAddress);
		mEtLatitude = (EditText) findViewById(R.id.etLatitude);
		mEtLongitude = (EditText) findViewById(R.id.etLongitude);
		mEtMenu = (EditText) findViewById(R.id.etMenu);
		mEtSpecialMenu = (EditText) findViewById(R.id.etSpecialMenu);
		mEtCloseDay = (EditText) findViewById(R.id.etClose);
		mEtOpenTime = (EditText) findViewById(R.id.etOpen);
		
		mBsubmit = (Button) findViewById(R.id.bSaveCP);
	}

	public void getData() {

		mName = mEtName.getText().toString();
		mAddress = mEtAddress.getText().toString();
		mLatitude = mEtLatitude.getText().toString();
		mLongitude = mEtLongitude.getText().toString();
		mMenus = mEtMenu.getText().toString();
		mSpecialMenu = mEtSpecialMenu.getText().toString();
		mCloseDay = mEtCloseDay.getText().toString();
		mDailyOpenTime = mEtOpenTime.getText().toString();
		mDescription = mEtDescrption.getText().toString();
	}

}
