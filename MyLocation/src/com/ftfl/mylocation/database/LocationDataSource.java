package com.ftfl.mylocation.database;

import java.util.ArrayList;

import com.ftfl.mylocation.model.LocationModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LocationDataSource {
	private SQLiteDatabase db;
	private LocationDBHelper dbHelper;

	public LocationDataSource(Context context) {
		dbHelper = new LocationDBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// Adding new
	public long addNewLocation(LocationModel place) {
		open();
		ContentValues values = new ContentValues();
		values.put(LocationDBHelper.KEY_LATITUDE, place.getmLatitude());
		values.put(LocationDBHelper.KEY_LOGITUDE, place.getmLongitude());
		values.put(LocationDBHelper.KEY_REMARKS, place.getmRemarks());
		values.put(LocationDBHelper.KEY_PHOTO, place.getmImage());
		values.put(LocationDBHelper.KEY_DATE, place.getmDate());
		values.put(LocationDBHelper.KEY_PHOTO, place.getmTime());


		long inserted = db.insert(LocationDBHelper.TABLE_NAME, null, values);
		close();
		return inserted;
	}

	// delete data form database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			db.delete(LocationDBHelper.TABLE_NAME, LocationDBHelper.KEY_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// update database by Id
	public long updateData(Integer id, LocationModel place) {
		open();
		ContentValues valuesUpdate = new ContentValues();
		valuesUpdate.put(LocationDBHelper.KEY_LATITUDE, place.getmLatitude());
		valuesUpdate.put(LocationDBHelper.KEY_LOGITUDE, place.getmLongitude());
		valuesUpdate.put(LocationDBHelper.KEY_REMARKS, place.getmRemarks());
		valuesUpdate.put(LocationDBHelper.KEY_PHOTO, place.getmImage());
		valuesUpdate.put(LocationDBHelper.KEY_DATE, place.getmDate());
		valuesUpdate.put(LocationDBHelper.KEY_PHOTO, place.getmTime());

		long updated = 0;
		try {
			updated = db.update(LocationDBHelper.TABLE_NAME, valuesUpdate, LocationDBHelper.KEY_ID
					+ "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// Getting All place list
	public ArrayList<LocationModel> getLocationList() {
		ArrayList<LocationModel> locationList = new ArrayList<LocationModel>();
		open();
		Cursor cursor = db.query(LocationDBHelper.TABLE_NAME, null, null, null, null,
				null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor.getColumnIndex(LocationDBHelper.KEY_ID));
				String latitude = cursor.getString(cursor
						.getColumnIndex(LocationDBHelper.KEY_LATITUDE));
				String longitude = cursor.getString(cursor
						.getColumnIndex(LocationDBHelper.KEY_LOGITUDE));
				String remarks = cursor.getString(cursor
						.getColumnIndex(LocationDBHelper.KEY_REMARKS));
				String photo = cursor.getString(cursor
						.getColumnIndex(LocationDBHelper.KEY_PHOTO));
				String date = cursor.getString(cursor
						.getColumnIndex(LocationDBHelper.KEY_DATE));
				String time = cursor.getString(cursor
						.getColumnIndex(LocationDBHelper.KEY_TIME));

				LocationModel location = new LocationModel(id, latitude, longitude, remarks, photo, date, time, null);
				locationList.add(location);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return place list
		return locationList;
	}

	// Getting All place detail
	public LocationModel getDetail(int id) {
		LocationModel locationDetail;
		open();

		String selectQuery = "SELECT  * FROM " + LocationDBHelper.TABLE_NAME
				+ " WHERE " + LocationDBHelper.KEY_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String latitude = cursor.getString(cursor
				.getColumnIndex(LocationDBHelper.KEY_LATITUDE));
		String longitude = cursor.getString(cursor
				.getColumnIndex(LocationDBHelper.KEY_LOGITUDE));
		String remarks = cursor.getString(cursor
				.getColumnIndex(LocationDBHelper.KEY_REMARKS));
		String photo = cursor.getString(cursor
				.getColumnIndex(LocationDBHelper.KEY_PHOTO));
		String date = cursor.getString(cursor
				.getColumnIndex(LocationDBHelper.KEY_DATE));
		String time = cursor.getString(cursor
				.getColumnIndex(LocationDBHelper.KEY_TIME));

		locationDetail = new LocationModel(id, latitude, longitude, remarks, photo, date, time, time);
		cursor.moveToNext();

		cursor.close();
		db.close();

		// return place detail
		return locationDetail;
	}
}
