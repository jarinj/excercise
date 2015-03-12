package com.ftfl.icare.database;

import java.util.ArrayList;

import com.ftfl.icare.model.UserProfileModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserProfileDatabase {
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public UserProfileDatabase(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// Adding new
	public long addProfile(UserProfileModel eModel) {
		open();
		ContentValues values = new ContentValues();
		//values.put(DBHelper.KEY_IMAGE_USER, eModel.getmImageUser());
		values.put(DBHelper.KEY_NAME, eModel.getmName());
		values.put(DBHelper.KEY_DOB, eModel.getmDOB());
		values.put(DBHelper.KEY_HEIGHT, eModel.getmHeight());
		values.put(DBHelper.KEY_WEIGHT, eModel.getmWeight());
		values.put(DBHelper.KEY_GENDER, eModel.getmGender());
		
		long inserted = db.insert(DBHelper.TABLE_USER, null, values);
		close();
		return inserted;
	}

	// delete data form database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.TABLE_USER, DBHelper.KEY_USER_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// update database by Id
	public long updateProfile(Integer id, UserProfileModel eModel) {
		open();
		ContentValues values = new ContentValues();
		//values.put(DBHelper.KEY_IMAGE_USER, eModel.getmImageUser());
		values.put(DBHelper.KEY_NAME, eModel.getmName());
		values.put(DBHelper.KEY_DOB, eModel.getmDOB());
		values.put(DBHelper.KEY_HEIGHT, eModel.getmHeight());
		values.put(DBHelper.KEY_WEIGHT, eModel.getmWeight());
		values.put(DBHelper.KEY_GENDER, eModel.getmGender());

		long updated = 0;
		try {
			updated = db.update(DBHelper.TABLE_USER, values, DBHelper.KEY_USER_ID
					+ "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// Getting All Profile list
	public ArrayList<UserProfileModel> getProfileList() {
		ArrayList<UserProfileModel> modelList = new ArrayList<UserProfileModel>();
		open();
		Cursor cursor = db.query(DBHelper.TABLE_USER, null, null, null, null,
				null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				String id = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USER_ID));
				//String image = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_IMAGE_USER));
				String name = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_NAME));
				String dob = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DOB));
				String height = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_HEIGHT));
				String weight = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_WEIGHT));
				String gender = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_GENDER));

				UserProfileModel mModel = new UserProfileModel(id, name,
						dob, height, weight, gender);
				modelList.add(mModel);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return profile list
		return modelList;
	}

	// Getting All Profile list
	public UserProfileModel getDetail(int id) {
		UserProfileModel mModelDetail;
		String mId = null;
		open();

		String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_USER
				+ " WHERE " + DBHelper.KEY_USER_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		//String image = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_IMAGE_USER));
		mId = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USER_ID));
		String name = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_NAME));
		String dob = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DOB));
		String height = cursor.getString(cursor.
				getColumnIndex(DBHelper.KEY_HEIGHT));
		String weight = cursor.getString(cursor.
				getColumnIndex(DBHelper.KEY_WEIGHT));
		String gender = cursor.getString(cursor.
				getColumnIndex(DBHelper.KEY_GENDER));
		
		mModelDetail = new UserProfileModel(mId, name, dob, height,
				weight, gender);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return place detail
		return mModelDetail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.TABLE_USER, new String[] {
				DBHelper.KEY_USER_ID, 
				DBHelper.KEY_NAME, DBHelper.KEY_DOB,
				DBHelper.KEY_HEIGHT , DBHelper.KEY_WEIGHT,
				DBHelper.KEY_GENDER, }, null, null,
				null, null, null);

		if (cursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}
}
