package com.ftfl.findrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.ftfl.findrestaurant.database.RestaurantDataSource;
import com.ftfl.findrestaurant.googlemap.GoogleMapActivity;
import com.ftfl.findrestaurant.utils.RestaurantConstants;
import com.ftfl.findrestaurant.utils.RestaurantModel;

public class ViewActivity extends ActionBarActivity {
	private TextView nameTextView;
	private TextView addressTextView;
	private TextView latitudeTextView;
	private TextView longitudeTextView;
	private TextView menusTextView;
	private TextView specialMenuTextView;
	private TextView closeDayTextView;
	private TextView dailyOpenTimeTextView;
	private TextView descrptionTextView;

	private RestaurantModel res_info;
	private RestaurantDataSource data_source_obj;

	private int mSelectedId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.view);
		initialize();

		/* get Id from Intent */
		mSelectedId = getIntent().getExtras().getInt(
				RestaurantConstants.SELECTED_ID);
		/* create instance of Data Source class */
		data_source_obj = new RestaurantDataSource(this);
		/*
		 * receive restrant information object by calling a method from Data
		 * Source class and sentd selected id aas parameter
		 */
		res_info = data_source_obj.getRestaurantById(mSelectedId);
		/* SHow existing data */
		setData();

		/*
		 * Toast.makeText( getApplicationContext(), String.valueOf(mSelectedId),
		 * Toast.LENGTH_SHORT).show();
		 */
	}

	public void viewMap(View view) {
		Intent intent = new Intent(ViewActivity.this, GoogleMapActivity.class);
		Bundle bundle = new Bundle();

		bundle.putString(RestaurantConstants.LATITUDE, res_info.getLatitude());
		bundle.putString(RestaurantConstants.LONGITUDE, res_info.getLongitude());
		bundle.putString(RestaurantConstants.RESTAURANTNAME, res_info.getName());
		intent.putExtras(bundle);
		startActivity(intent);

		/*
		 * getData(); RestaurantInformation res_Info_obj=new
		 * RestaurantInformation(mName, mAddress, mLatitude, mLongitude, mMenus,
		 * mSpecialMenu, mCloseDay, mDailyOpenTime, mDescription);
		 * data_source_obj.editRestaurant(mSelectedId, res_Info_obj);
		 */
	}

	public void initialize() {
		nameTextView = (TextView) findViewById(R.id.tvNameVP);
		descrptionTextView = (TextView) findViewById(R.id.tvDescriptionVP);		
		addressTextView = (TextView) findViewById(R.id.tvAddressVP);
		latitudeTextView = (TextView) findViewById(R.id.tvLatitudeVP);
		longitudeTextView = (TextView) findViewById(R.id.tvLongitudeVP);
		menusTextView = (TextView) findViewById(R.id.tvMenuVP);
		specialMenuTextView = (TextView) findViewById(R.id.tvSpecialMenuVP);
		closeDayTextView = (TextView) findViewById(R.id.tvCloseVP);
		dailyOpenTimeTextView = (TextView) findViewById(R.id.tvOpenVP);

		// buttonTextView=(Button) findViewById(R.id.buttonUpdate);
	}

	public void setData() {
		nameTextView.setText(res_info.getName());
		addressTextView.setText(res_info.getAddrsss());
		latitudeTextView.setText(res_info.getLatitude());
		longitudeTextView.setText(res_info.getLongitude());
		menusTextView.setText(res_info.getMenus());
		specialMenuTextView.setText(res_info.getSpecialMenu());
		closeDayTextView.setText(res_info.getCloseDay());
		dailyOpenTimeTextView.setText(res_info.getDailyOpenTime());
		descrptionTextView.setText(res_info.getDescription());
	}

	public void getData() {

		nameTextView.getText().toString();
		addressTextView.getText().toString();
		latitudeTextView.getText().toString();
		longitudeTextView.getText().toString();
		menusTextView.getText().toString();
		specialMenuTextView.getText().toString();
		closeDayTextView.getText().toString();
		dailyOpenTimeTextView.getText().toString();
		descrptionTextView.getText().toString();
	}

}
