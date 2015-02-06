package com.ftfl.mylocation.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LocationDBHelper extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "MyLocation";

	//table name
	public static final String TABLE_NAME = "LocationInfo";

	// Table Columns names
	public static final String KEY_ID = "id";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_LOGITUDE = "longitude";
	public static final String KEY_REMARKS = "remarks";
	public static final String KEY_DATE = "date";
	public static final String KEY_TIME = "time";
	public static final String KEY_PHOTO = "photo";

	// table information
	public String CREATE_PLACE_TABLE = "create table " + TABLE_NAME + "("
			                          + KEY_ID + " integer primary key autoincrement, " 
			                          + KEY_LATITUDE + " text not null, "
			                          + KEY_LOGITUDE + " text not null, "
			                          + KEY_REMARKS + " text not null, "
			                          + KEY_DATE + " text not null, "
			                          + KEY_TIME + " text not null, "
	                                  + KEY_PHOTO + " text not null);";

   // Create Database
	public LocationDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

   // Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PLACE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LocationDBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
	
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_PLACE_TABLE);
		onCreate(db);
	}

}