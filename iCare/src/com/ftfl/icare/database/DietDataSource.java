package com.ftfl.icare.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.icare.model.DietModel;

public class DietDataSource {
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	public String mCurrentDate = "";

	public DietDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	@SuppressLint("SimpleDateFormat")
	public void getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
	}

	// Adding new diet
	public long addNewDiet(DietModel diet) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_FEAST, diet.getmFeast());
		values.put(DBHelper.KEY_MENU, diet.getmMenu());
		values.put(DBHelper.KEY_DIET_DATE, diet.getmDate());
		values.put(DBHelper.KEY_DIET_TIME, diet.getmTime());
		values.put(DBHelper.KEY_DIET_REMINDER, diet.getmAlarm());

		long inserted = db.insert(DBHelper.TABLE_DIET, null, values);
		close();
		return inserted;
	}

	// delete data form database.
	public boolean deleteDietData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.TABLE_DIET,
					DBHelper.KEY_DIET_CHART_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// update database by Id
	public long updateDietData(Integer id, DietModel diet) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_FEAST, diet.getmFeast());
		values.put(DBHelper.KEY_MENU, diet.getmMenu());
		values.put(DBHelper.KEY_DIET_DATE, diet.getmDate());
		values.put(DBHelper.KEY_DIET_TIME, diet.getmTime());
		values.put(DBHelper.KEY_DIET_REMINDER, diet.getmAlarm());

		long updated = 0;
		try {
			updated = db.update(DBHelper.TABLE_DIET, values,
					DBHelper.KEY_DIET_CHART_ID + "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// Getting All diet list
	public ArrayList<DietModel> getTodayDietList() {
		ArrayList<DietModel> diet_list = new ArrayList<DietModel>();
		open();
		getCurrentDate();

		Cursor cursor = db.query(DBHelper.TABLE_DIET, new String[] {
				DBHelper.KEY_DIET_CHART_ID,
				DBHelper.KEY_FEAST,
				DBHelper.KEY_MENU,
				DBHelper.KEY_DIET_DATE,
				DBHelper.KEY_DIET_TIME,
				DBHelper.KEY_DIET_REMINDER,

		},
		DBHelper.KEY_DIET_DATE + "= '" + mCurrentDate + "' ",
				null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_DIET_CHART_ID));
				String feast = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_FEAST));
				String menu = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_MENU));
				String date = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DIET_DATE));
				String time = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DIET_TIME));
				String alarm = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DIET_REMINDER));

				DietModel diet = new DietModel(id, feast, menu, time, date,
						alarm);
				diet_list.add(diet);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return diet list
		return diet_list;
	}
	
	// Getting All diet list
		public ArrayList<DietModel> getAllDietList() {
			ArrayList<DietModel> all_diet_list = new ArrayList<DietModel>();
			open();

			Cursor cursor = db.query(DBHelper.TABLE_DIET, new String[] {
					DBHelper.KEY_DIET_CHART_ID,
					DBHelper.KEY_FEAST,
					DBHelper.KEY_MENU,
					DBHelper.KEY_DIET_DATE,
					DBHelper.KEY_DIET_TIME,
					DBHelper.KEY_DIET_REMINDER,

			},null, null, null, null, null);

			// looping through all rows and adding to list
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				for (int i = 0; i < cursor.getCount(); i++) {
					int id = cursor.getInt(cursor
							.getColumnIndex(DBHelper.KEY_DIET_CHART_ID));
					String feast = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_FEAST));
					String menu = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_MENU));
					String date = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_DIET_DATE));
					String time = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_DIET_TIME));
					String alarm = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_DIET_REMINDER));

					DietModel diet = new DietModel(id, feast, menu, time, date,
							alarm);
					all_diet_list.add(diet);
					cursor.moveToNext();
				}
			}
			cursor.close();
			db.close();

			// return diet list
			return all_diet_list;
		}

	// Getting All diet detail
	public DietModel getDietDetail(int id) {
		DietModel diet_detail;
		open();

		String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_DIET
				+ " WHERE " + DBHelper.KEY_DIET_CHART_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String feast = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_FEAST));
		String menu = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_MENU));
		String date = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DIET_DATE));
		String time = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DIET_TIME));
		String alarm = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DIET_REMINDER));
		diet_detail = new DietModel(id, feast, menu, time, date, alarm);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return diet detail
		return diet_detail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.TABLE_DIET, new String[] {
				DBHelper.KEY_DIET_CHART_ID, DBHelper.KEY_FEAST,
				DBHelper.KEY_MENU, DBHelper.KEY_DIET_DATE,
				DBHelper.KEY_DIET_TIME, DBHelper.KEY_DIET_REMINDER, },
				null, null, null, null, null);

		if (cursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}

}
