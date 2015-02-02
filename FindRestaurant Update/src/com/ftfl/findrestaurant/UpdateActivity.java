package com.ftfl.findrestaurant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ftfl.findrestaurant.database.RestaurantDataSource;
import com.ftfl.findrestaurant.utils.RestaurantConstants;
import com.ftfl.findrestaurant.utils.RestaurantModel;

public class UpdateActivity extends ActionBarActivity {
	private EditText mEtname;
	private EditText mEtDescrption;
	private EditText mEtAddress;
	private EditText mEtLatitude;
	private EditText mEtLongitude;
	private EditText mEtMenu;
	private EditText mEtSpecialMenu;
	private EditText mEtClose;
	private EditText mEtOpen;
	

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
	private Button buttonEditText;
	private RestaurantModel restaurantModel;
	private RestaurantDataSource dataSource;

	private int mSelectedId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.update);
		initialize();

		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getInt(
				RestaurantConstants.SELECTED_ID);
		/* create instance of Data Source class */
		dataSource = new RestaurantDataSource(this);
		/*
		 * receive restrant information object by calling a method from Data
		 * Source class and sentd selected id aas parameter
		 */
		restaurantModel = dataSource.getRestaurantById(mSelectedId);
		/* SHow existing data */
		setData();

		Toast.makeText(getApplicationContext(), String.valueOf(mSelectedId),Toast.LENGTH_SHORT).show();
	}

	public void updateButtonClicked(View view) {
		getData();
		RestaurantModel res_Info_obj = new RestaurantModel(mName,
				mAddress, mLatitude, mLongitude, mMenus, mSpecialMenu,
				mCloseDay, mDailyOpenTime, mDescription);
		dataSource.editRestaurant(mSelectedId, res_Info_obj);
	}

	public void initialize() {
		mEtname = (EditText) findViewById(R.id.etName);
		mEtDescrption = (EditText) findViewById(R.id.etDescription);
		mEtAddress = (EditText) findViewById(R.id.etAddress);
		mEtLatitude = (EditText) findViewById(R.id.etLatitude);
		mEtLongitude = (EditText) findViewById(R.id.etLongitude);
		mEtMenu = (EditText) findViewById(R.id.etMenu);
		mEtSpecialMenu = (EditText) findViewById(R.id.etSpecialMenu);
		mEtClose = (EditText) findViewById(R.id.etClose);
		mEtOpen = (EditText) findViewById(R.id.etOpen);
		
		// buttonEditText=(Button) findViewById(R.id.buttonUpdate);
	}

	public void setData() {
		mEtname.setText(restaurantModel.getName());
		mEtAddress.setText(restaurantModel.getAddrsss());
		mEtLatitude.setText(restaurantModel.getLatitude());
		mEtLongitude.setText(restaurantModel.getLongitude());
		mEtMenu.setText(restaurantModel.getMenus());
		mEtSpecialMenu.setText(restaurantModel.getSpecialMenu());
		mEtClose.setText(restaurantModel.getCloseDay());
		mEtOpen.setText(restaurantModel.getDailyOpenTime());
		mEtDescrption.setText(restaurantModel.getDescription());
	}

	public void getData() {

		mName = mEtname.getText().toString();
		mAddress = mEtAddress.getText().toString();
		mLatitude = mEtLatitude.getText().toString();
		mLongitude = mEtLongitude.getText().toString();
		mMenus = mEtMenu.getText().toString();
		mSpecialMenu = mEtSpecialMenu.getText().toString();
		mCloseDay = mEtClose.getText().toString();
		mDailyOpenTime = mEtOpen.getText().toString();
		mDescription = mEtDescrption.getText().toString();
	}

}
