package com.ftfl.icare.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.icare.model.VaccinationModel;

public class VaccinationDataSource {
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	public String mCurrentDate = "";
	List<String> mTakenVaccine = new ArrayList<String>();

	public VaccinationDataSource(Context context) {
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

	// Adding new vaccine
	public long addNewVaccine(VaccinationModel vaccine) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_VACCINATION_NAME, vaccine.getmVacName());
		values.put(DBHelper.KEY_VACCINATION_FOR,
				vaccine.getmVacReason());
		values.put(DBHelper.KEY_VACCINATION_DATE, vaccine.getmVacDate());
		values.put(DBHelper.KEY_VACCINATION_TIME, vaccine.getmVacTime());
		
		values.put(DBHelper.KEY_VACCINATION_ADDRESS,
				vaccine.getmVacAddress());
		values.put(DBHelper.KEY_VACCINATION_REMINDER, vaccine.getmVacAlarm());

		long inserted = db.insert(DBHelper.TABLE_VACCINATION, null,
				values);
		close();
		return inserted;
	}

	// delete data form database.
	public boolean deleteVaccineData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.TABLE_VACCINATION,
					DBHelper.KEY_VACCINATION_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// update database by Id
	public long updateVaccineData(Integer id, VaccinationModel vaccine) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_VACCINATION_NAME, vaccine.getmVacName());
		values.put(DBHelper.KEY_VACCINATION_FOR,
				vaccine.getmVacReason());
		values.put(DBHelper.KEY_VACCINATION_DATE, vaccine.getmVacDate());
		values.put(DBHelper.KEY_VACCINATION_TIME, vaccine.getmVacTime());
		
		values.put(DBHelper.KEY_VACCINATION_ADDRESS,
				vaccine.getmVacAddress());
		values.put(DBHelper.KEY_VACCINATION_REMINDER, vaccine.getmVacAlarm());
		
		long updated = 0;
		try {
			updated = db.update(DBHelper.TABLE_VACCINATION, values,
					DBHelper.KEY_VACCINATION_ID + "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// Getting All vaccination list
	public ArrayList<VaccinationModel> getVaccineList() {
		ArrayList<VaccinationModel> vaccine_list = new ArrayList<VaccinationModel>();
		open();
		Cursor cursor = db.query(DBHelper.TABLE_VACCINATION, null,
				null, null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_ID));
				String name = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_NAME));
				String reason = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_FOR));
				String date = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_DATE));
				String time = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_TIME));
				
				String address = cursor
						.getString(cursor
								.getColumnIndex(DBHelper.KEY_VACCINATION_ADDRESS));
				String alarm = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_REMINDER));

				VaccinationModel vaccine = new VaccinationModel(id, name, time,
						date, reason, address, alarm);
				vaccine_list.add(vaccine);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return vaccination list
		return vaccine_list;
	}

	/*******************************************************************************************/
	// Getting All vaccination list
	public ArrayList<VaccinationModel> getTakenVaccineList() {
		ArrayList<VaccinationModel> taken_vaccine_list = new ArrayList<VaccinationModel>();
		open();

		getCurrentDate();

		Cursor cursor = db.query(DBHelper.TABLE_VACCINATION, new String[] {
				DBHelper.KEY_VACCINATION_ID,
				DBHelper.KEY_VACCINATION_NAME,
				DBHelper.KEY_VACCINATION_FOR,
				DBHelper.KEY_VACCINATION_DATE,
				DBHelper.KEY_VACCINATION_TIME,
				
				DBHelper.KEY_VACCINATION_ADDRESS,
				DBHelper.KEY_VACCINATION_REMINDER,

		},
		DBHelper.KEY_VACCINATION_DATE + " < '" + mCurrentDate + "' ",
				null, null, null, null);
		
		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_ID));
				String name = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_NAME));
				String reason = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_FOR));
				String date = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_DATE));
				String time = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_TIME));
				
				String address = cursor
						.getString(cursor
								.getColumnIndex(DBHelper.KEY_VACCINATION_ADDRESS));
				String alarm = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_REMINDER));

				VaccinationModel vaccine = new VaccinationModel(id, name, time,
						date, reason, address, alarm);
				taken_vaccine_list.add(vaccine);
				cursor.moveToNext();
			}
		}
		cursor.close();
		this.db.close();

		// return vaccination list
		return taken_vaccine_list;
	}

	/*******************************************************************************************/
	
	public ArrayList<VaccinationModel> getRemainingVaccineList() {
		ArrayList<VaccinationModel> taken_vaccine_list = new ArrayList<VaccinationModel>();
		open();

		getCurrentDate();

		Cursor cursor = db.query(DBHelper.TABLE_VACCINATION, new String[] {
				DBHelper.KEY_VACCINATION_ID,
				DBHelper.KEY_VACCINATION_NAME,
				DBHelper.KEY_VACCINATION_FOR,
				DBHelper.KEY_VACCINATION_DATE,
				DBHelper.KEY_VACCINATION_TIME,
				
				DBHelper.KEY_VACCINATION_ADDRESS,
				DBHelper.KEY_VACCINATION_REMINDER,

		},
		DBHelper.KEY_VACCINATION_DATE + " >= '" + mCurrentDate + "' ",
				null, null, null, null);
		
		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_ID));
				String name = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_NAME));
				String reason = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_FOR));
				String date = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_DATE));
				String time = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_TIME));
				
				String address = cursor
						.getString(cursor
								.getColumnIndex(DBHelper.KEY_VACCINATION_ADDRESS));
				String alarm = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_VACCINATION_REMINDER));

				VaccinationModel vaccine = new VaccinationModel(id, name, time,
						date, reason, address, alarm);
				taken_vaccine_list.add(vaccine);
				cursor.moveToNext();
			}
		}
		cursor.close();
		this.db.close();

		// return vaccination list
		return taken_vaccine_list;
	}
	
	/********************************************************************************************/

	// Getting All vaccination detail
	public VaccinationModel getVaccineDetail(int id) {
		VaccinationModel vaccine_detail;
		open();

		String selectQuery = "SELECT  * FROM "
				+ DBHelper.TABLE_VACCINATION + " WHERE "
				+ DBHelper.KEY_VACCINATION_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String name = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINATION_NAME));
		String reason = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINATION_FOR));
		String date = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINATION_DATE));
		String time = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINATION_TIME));

		String address = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINATION_ADDRESS));
		String alarm = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_VACCINATION_REMINDER));

		vaccine_detail = new VaccinationModel(id, name, time, date, reason,
				address, alarm);

		cursor.moveToNext();

		cursor.close();
		this.db.close();

		// return vaccination detail
		return vaccine_detail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.TABLE_VACCINATION,
				new String[] { DBHelper.KEY_VACCINATION_ID,
						DBHelper.KEY_VACCINATION_NAME,
						DBHelper.KEY_VACCINATION_FOR,
						DBHelper.KEY_VACCINATION_DATE,
						DBHelper.KEY_VACCINATION_TIME,
						
						DBHelper.KEY_VACCINATION_ADDRESS,
						DBHelper.KEY_VACCINATION_REMINDER, }, null, null,
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
