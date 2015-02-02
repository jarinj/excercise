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
	private EditText nameEditText;
	private EditText addressEditText;
	private EditText latitudeEditText;
	private EditText longitudeEditText;
	private EditText menusEditText;
	private EditText specialMenuEditText;
	private EditText closeDayEditText;
	private EditText dailyOpenTimeEditText;
	private EditText descrptionEditText;

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
		nameEditText = (EditText) findViewById(R.id.etName);
		descrptionEditText = (EditText) findViewById(R.id.etDescription);
		addressEditText = (EditText) findViewById(R.id.etAddress);
		latitudeEditText = (EditText) findViewById(R.id.etLatitude);
		longitudeEditText = (EditText) findViewById(R.id.etLongitude);
		menusEditText = (EditText) findViewById(R.id.etMenu);
		specialMenuEditText = (EditText) findViewById(R.id.etSpecialMenu);
		closeDayEditText = (EditText) findViewById(R.id.etClose);
		dailyOpenTimeEditText = (EditText) findViewById(R.id.etOpen);
		
		// buttonEditText=(Button) findViewById(R.id.buttonUpdate);
	}

	public void setData() {
		nameEditText.setText(restaurantModel.getName());
		addressEditText.setText(restaurantModel.getAddrsss());
		latitudeEditText.setText(restaurantModel.getLatitude());
		longitudeEditText.setText(restaurantModel.getLongitude());
		menusEditText.setText(restaurantModel.getMenus());
		specialMenuEditText.setText(restaurantModel.getSpecialMenu());
		closeDayEditText.setText(restaurantModel.getCloseDay());
		dailyOpenTimeEditText.setText(restaurantModel.getDailyOpenTime());
		descrptionEditText.setText(restaurantModel.getDescription());
	}

	public void getData() {

		mName = nameEditText.getText().toString();
		mAddress = addressEditText.getText().toString();
		mLatitude = latitudeEditText.getText().toString();
		mLongitude = longitudeEditText.getText().toString();
		mMenus = menusEditText.getText().toString();
		mSpecialMenu = specialMenuEditText.getText().toString();
		mCloseDay = closeDayEditText.getText().toString();
		mDailyOpenTime = dailyOpenTimeEditText.getText().toString();
		mDescription = descrptionEditText.getText().toString();
	}

}
