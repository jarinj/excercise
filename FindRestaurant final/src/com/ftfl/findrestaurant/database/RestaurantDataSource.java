package com.ftfl.findrestaurant.database;

import java.util.ArrayList;

import com.ftfl.findrestaurant.model.RestaurantModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RestaurantDataSource {	
private SQLiteDatabase db;
private RestaurantDBHelper dbHelper;

public RestaurantDataSource(Context context) {
	dbHelper = new RestaurantDBHelper(context);
}

public void open() throws SQLException {
	db = dbHelper.getWritableDatabase();
}

public void close() {
	dbHelper.close();
}

// Adding new
public long addNewPlace(RestaurantModel restaurant) {
	open();
	ContentValues values = new ContentValues();
	values.put(RestaurantDBHelper.KEY_NAME, restaurant.getmName());
	values.put(RestaurantDBHelper.KEY_PURPOSE, restaurant.getmPurpose());
	values.put(RestaurantDBHelper.KEY_ADDRESS, restaurant.getmAddress());
	values.put(RestaurantDBHelper.KEY_DISTRICT, restaurant.getmDistrict());
	values.put(RestaurantDBHelper.KEY_LATITUDE, restaurant.getmLatitude());
	values.put(RestaurantDBHelper.KEY_LOGITUDE, restaurant.getmLongitude());
	values.put(RestaurantDBHelper.KEY_START_DATE, restaurant.getmStartDate());
	values.put(RestaurantDBHelper.KEY_END_DATE, restaurant.getmEndDate());
	values.put(RestaurantDBHelper.KEY_NOTE, restaurant.getmNote());
	values.put(RestaurantDBHelper.KEY_PHOTO, restaurant.getmImage());

	long inserted = db.insert(RestaurantDBHelper.TABLE_NAME, null, values);
	close();
	return inserted;
}

// delete data form database.
public boolean deleteData(Integer eId) {
	this.open();
	try {
		db.delete(RestaurantDBHelper.TABLE_NAME, RestaurantDBHelper.KEY_ID + "=" + eId, null);
	} catch (Exception ex) {
		Log.e("ERROR", "data not deleted");
		return false;
	}
	this.close();
	return true;
}

// update database by Id
public long updateData(Integer id, RestaurantModel restaurant) {
	open();
	ContentValues values = new ContentValues();
	values.put(RestaurantDBHelper.KEY_NAME, restaurant.getmName());
	values.put(RestaurantDBHelper.KEY_PURPOSE, restaurant.getmPurpose());
	values.put(RestaurantDBHelper.KEY_ADDRESS, restaurant.getmAddress());
	values.put(RestaurantDBHelper.KEY_DISTRICT, restaurant.getmDistrict());
	values.put(RestaurantDBHelper.KEY_LATITUDE, restaurant.getmLatitude());
	values.put(RestaurantDBHelper.KEY_LOGITUDE, restaurant.getmLongitude());
	values.put(RestaurantDBHelper.KEY_START_DATE, restaurant.getmStartDate());
	values.put(RestaurantDBHelper.KEY_END_DATE, restaurant.getmEndDate());
	values.put(RestaurantDBHelper.KEY_NOTE, restaurant.getmNote());

	long updated = 0;
	try {
		updated = db.update(RestaurantDBHelper.TABLE_NAME, values, RestaurantDBHelper.KEY_ID
				+ "=" + id, null);
	} catch (Exception ex) {
		Log.e("ERROR", "data upgraion problem");
	}
	close();
	return updated;
}

// Getting All place list
public ArrayList<RestaurantModel> getPlaceList() {
	ArrayList<RestaurantModel> place_list = new ArrayList<RestaurantModel>();
	open();
	Cursor cursor = db.query(RestaurantDBHelper.TABLE_NAME, null, null, null, null,
			null, null);

	// looping through all rows and adding to list
	if (cursor != null && cursor.getCount() > 0) {
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			int id = cursor.getInt(cursor.getColumnIndex(RestaurantDBHelper.KEY_ID));
			String name = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_NAME));
			String purpose = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_PURPOSE));
			String address = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_ADDRESS));
			String district = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_DISTRICT));
			String latitude = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_LATITUDE));
			String longitude = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_LOGITUDE));
			String startDate = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_START_DATE));
			String endDate = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_END_DATE));
			String note = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_NOTE));
			String photo = cursor.getString(cursor
					.getColumnIndex(RestaurantDBHelper.KEY_PHOTO));

			RestaurantModel place = new RestaurantModel(id, name, purpose,
					address, district, latitude, longitude, startDate,
					endDate, note, photo);
			place_list.add(place);
			cursor.moveToNext();
		}
	}
	cursor.close();
	db.close();

	// return place list
	return place_list;
}

// Getting All place detail
public RestaurantModel getDetail(int id) {
	RestaurantModel place_detail;
	open();

	String selectQuery = "SELECT  * FROM " + RestaurantDBHelper.TABLE_NAME
			+ " WHERE " + RestaurantDBHelper.KEY_ID + "=" + id;

	Cursor cursor = db.rawQuery(selectQuery, null);
	cursor.moveToFirst();
	String name = cursor
			.getString(cursor.getColumnIndex(RestaurantDBHelper.KEY_NAME));
	String purpose = cursor.getString(cursor
			.getColumnIndex(RestaurantDBHelper.KEY_PURPOSE));
	String address = cursor.getString(cursor
			.getColumnIndex(RestaurantDBHelper.KEY_ADDRESS));
	String district = cursor.getString(cursor
			.getColumnIndex(RestaurantDBHelper.KEY_DISTRICT));
	String latitude = cursor.getString(cursor
			.getColumnIndex(RestaurantDBHelper.KEY_LATITUDE));
	String longitude = cursor.getString(cursor
			.getColumnIndex(RestaurantDBHelper.KEY_LOGITUDE));
	String startDate = cursor.getString(cursor
			.getColumnIndex(RestaurantDBHelper.KEY_START_DATE));
	String endDate = cursor.getString(cursor
			.getColumnIndex(RestaurantDBHelper.KEY_END_DATE));
	String note = cursor
			.getString(cursor.getColumnIndex(RestaurantDBHelper.KEY_NOTE));
	String photo = cursor.getString(cursor
			.getColumnIndex(RestaurantDBHelper.KEY_PHOTO));

	place_detail = new RestaurantModel(id, name, purpose, address,
			district, latitude, longitude, startDate, endDate, note, photo);

	cursor.moveToNext();

	cursor.close();
	db.close();

	// return place detail
	return place_detail;
}

public boolean isEmpty() {
	this.open();
	Cursor cursor = db.query(RestaurantDBHelper.TABLE_NAME, new String[] {
			RestaurantDBHelper.KEY_ID, RestaurantDBHelper.KEY_NAME, RestaurantDBHelper.KEY_PURPOSE,
			RestaurantDBHelper.KEY_ADDRESS, RestaurantDBHelper.KEY_DISTRICT,
			RestaurantDBHelper.KEY_LATITUDE, RestaurantDBHelper.KEY_LOGITUDE,
			RestaurantDBHelper.KEY_START_DATE, RestaurantDBHelper.KEY_END_DATE,
			RestaurantDBHelper.KEY_NOTE, RestaurantDBHelper.KEY_PHOTO, }, null, null, null,
			null, null);

	if (cursor.getCount() == 0) {
		this.close();
		return true;
	} else {
		this.close();
		return false;
	}
}
}