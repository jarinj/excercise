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

import com.ftfl.icare.model.DoctorProfileModel;

public class DoctorProfileDatabase {
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	public String mCurrentDate = "";

	public DoctorProfileDatabase(Context context) {
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
	public long addNewDr(DoctorProfileModel drModel) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_DR_NAME, drModel.getmDrName());
		values.put(DBHelper.KEY_DR_PHONE, drModel.getmDrPhone());
		values.put(DBHelper.KEY_DR_MAIL, drModel.getmDrMail());
		values.put(DBHelper.KEY_DR_ADDRESS, drModel.getmDrAddress());
		values.put(DBHelper.KEY_APPOINTMENT_DATE, drModel.getmAppointmentDate());
		values.put(DBHelper.KEY_APPOINTMENT_TIME, drModel.getmAppointmentTime());
		values.put(DBHelper.KEY_APPOINTMENT_REMINDER, drModel.getmAppointmentRemainder());
		values.put(DBHelper.KEY_PRESCRIPTION_IMAGE, drModel.getmImagePrescription());

		long inserted = db.insert(DBHelper.TABLE_DOCTOR, null, values);
		close();
		return inserted;
	}

	// delete data form database.
	public boolean deleteDietData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.TABLE_DOCTOR,
					DBHelper.KEY_DR_PROFILE_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// update database by Id
	public long updateDietData(Integer id, DoctorProfileModel drModel) {
		open();
		ContentValues values = new ContentValues();
		
		values.put(DBHelper.KEY_DR_NAME, drModel.getmDrName());
		values.put(DBHelper.KEY_DR_PHONE, drModel.getmDrPhone());
		values.put(DBHelper.KEY_DR_MAIL, drModel.getmDrMail());
		values.put(DBHelper.KEY_DR_ADDRESS, drModel.getmDrAddress());
		values.put(DBHelper.KEY_APPOINTMENT_DATE, drModel.getmAppointmentDate());
		values.put(DBHelper.KEY_APPOINTMENT_TIME, drModel.getmAppointmentTime());
		values.put(DBHelper.KEY_APPOINTMENT_REMINDER, drModel.getmAppointmentRemainder());
		values.put(DBHelper.KEY_PRESCRIPTION_IMAGE, drModel.getmImagePrescription());


		long updated = 0;
		try {
			updated = db.update(DBHelper.TABLE_DOCTOR, values,
					DBHelper.KEY_DR_PROFILE_ID + "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// Getting All diet list
	public ArrayList<DoctorProfileModel> getTodayDrProfileList() {
		ArrayList<DoctorProfileModel> dr_list = new ArrayList<DoctorProfileModel>();
		open();
		getCurrentDate();

		Cursor cursor = db.query(DBHelper.TABLE_DOCTOR, new String[] {
				DBHelper.KEY_DR_PROFILE_ID,
				DBHelper.KEY_DR_NAME,
				DBHelper.KEY_DR_PHONE,
				DBHelper.KEY_DR_MAIL,
				DBHelper.KEY_DR_ADDRESS,
				DBHelper.KEY_APPOINTMENT_DATE,
				DBHelper.KEY_APPOINTMENT_TIME,
				DBHelper.KEY_APPOINTMENT_REMINDER,
				DBHelper.KEY_PRESCRIPTION_IMAGE,

		},
		DBHelper.KEY_APPOINTMENT_DATE + "= '" + mCurrentDate + "' ",
				null, null, null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DBHelper.KEY_DR_PROFILE_ID));
				String mDrName = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DR_NAME));
				String mDrPhone = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DR_PHONE));
				String mDrMail = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DR_MAIL));
				String mDrAddress = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DR_ADDRESS));
				String mAppointmentDate = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_APPOINTMENT_DATE));
				String mAppointmentTime = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_APPOINTMENT_TIME));
				String mAppointmentReminder = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_APPOINTMENT_REMINDER));
				String mPrescriptionImage = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_PRESCRIPTION_IMAGE));
				

				DoctorProfileModel drModel = new DoctorProfileModel(id, mDrName, mDrPhone, mDrAddress, mDrMail,
						mAppointmentDate, mAppointmentTime, mAppointmentReminder, mPrescriptionImage);
				dr_list.add(drModel);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return diet list
		return dr_list;
	}
	
	// Getting All diet list
		public ArrayList<DoctorProfileModel> getAllDrList() {
			ArrayList<DoctorProfileModel> all_dr_list = new ArrayList<DoctorProfileModel>();
			open();

			Cursor cursor = db.query(DBHelper.TABLE_DIET, new String[] {
					DBHelper.KEY_DR_PROFILE_ID,
					DBHelper.KEY_DR_NAME,
					DBHelper.KEY_DR_PHONE,
					DBHelper.KEY_DR_MAIL,
					DBHelper.KEY_DR_ADDRESS,
					DBHelper.KEY_APPOINTMENT_DATE,
					DBHelper.KEY_APPOINTMENT_TIME,
					DBHelper.KEY_APPOINTMENT_REMINDER,
					DBHelper.KEY_PRESCRIPTION_IMAGE,

			},null, null, null, null, null);

			// looping through all rows and adding to list
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				for (int i = 0; i < cursor.getCount(); i++) {
					int id = cursor.getInt(cursor
							.getColumnIndex(DBHelper.KEY_DR_PROFILE_ID));
					String mDrName = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_DR_NAME));
					String mDrPhone = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_DR_PHONE));
					String mDrMail = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_DR_MAIL));
					String mDrAddress = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_DR_ADDRESS));
					String mAppointmentDate = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_APPOINTMENT_DATE));
					String mAppointmentTime = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_APPOINTMENT_TIME));
					String mAppointmentReminder = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_APPOINTMENT_REMINDER));
					String mPrescriptionImage = cursor.getString(cursor
							.getColumnIndex(DBHelper.KEY_PRESCRIPTION_IMAGE));
					

					DoctorProfileModel drModel = new DoctorProfileModel(id, mDrName, mDrPhone, mDrAddress, mDrMail,
							mAppointmentDate, mAppointmentTime, mAppointmentReminder, mPrescriptionImage);
					all_dr_list.add(drModel);
					cursor.moveToNext();
				}
			}
			cursor.close();
			db.close();

			// return diet list
			return all_dr_list;
		}

	// Getting All diet detail
	public DoctorProfileModel getDrDetail(int id) {
		DoctorProfileModel dr_detail;
		open();

		String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_DOCTOR
				+ " WHERE " + DBHelper.KEY_DR_PROFILE_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String mDrName = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DR_NAME));
		String mDrPhone = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DR_PHONE));
		String mDrMail = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DR_MAIL));
		String mDrAddress = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DR_ADDRESS));
		String mAppointmentDate = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_APPOINTMENT_DATE));
		String mAppointmentTime = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_APPOINTMENT_TIME));
		String mAppointmentReminder = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_APPOINTMENT_REMINDER));
		String mPrescriptionImage = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_PRESCRIPTION_IMAGE));
		
		dr_detail = new DoctorProfileModel(id, mDrName, mDrPhone, mDrAddress, mDrMail,
				mAppointmentDate, mAppointmentTime, mAppointmentReminder, mPrescriptionImage);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return diet detail
		return dr_detail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.TABLE_DOCTOR, new String[] {
				DBHelper.KEY_DR_PROFILE_ID, 
				DBHelper.KEY_DR_NAME,
				DBHelper.KEY_DR_PHONE,
				DBHelper.KEY_DR_MAIL,
				DBHelper.KEY_DR_ADDRESS,
				DBHelper.KEY_APPOINTMENT_DATE,
				DBHelper.KEY_APPOINTMENT_TIME,
				DBHelper.KEY_APPOINTMENT_REMINDER,
				DBHelper.KEY_PRESCRIPTION_IMAGE, },
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