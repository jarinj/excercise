package com.maya.findrestaurent;

import java.util.HashMap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RestaurentDBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "findRestaurant.db";
	private static final int DATABASE_VERSION = 1;

	public static final String RESTAURENTS_TABLE_NAME = "restaurents";
	public static final String RESTAURENTS_COLUMN_ID = "id";

	public static final String PROFILES_COLUMN_NAME = "name";
	public static final String PROFILES_COLUMN_DESCRIPTION = "description";
	public static final String PROFILES_COLUMN_ADDRESS = "address";
	public static final String PROFILES_COLUMN_LATITUDE = "latitude";
	public static final String PROFILES_COLUMN_LONGITUDE = "longitude";
	public static final String PROFILES_COLUMN_MENU = "menu";
	public static final String PROFILES_COLUMN_SPECIALMENU = "specialmenu";
	public static final String PROFILES_COLUMN_CLOSE = "close";
	public static final String PROFILES_COLUMN_OPEN = "open";

	private HashMap hp;

	public RestaurentDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_ACTIVITY);

	}

	// create table
	private static final String DATABASE_CREATE_ACTIVITY = "create table "
			+ RESTAURENTS_TABLE_NAME + "(" + RESTAURENTS_COLUMN_ID
			+ " integer primary key autoincrement, " 
			+ PROFILES_COLUMN_NAME + " text not null," 
			+ PROFILES_COLUMN_DESCRIPTION + " text not null," 
			+ PROFILES_COLUMN_ADDRESS + " text not null,"
			+ PROFILES_COLUMN_LATITUDE + " text not null,"
			+ PROFILES_COLUMN_LONGITUDE + " text not null,"
			+ PROFILES_COLUMN_MENU + " text not null,"
			+ PROFILES_COLUMN_SPECIALMENU + " text not null,"
			+ PROFILES_COLUMN_CLOSE + " text not null," 
			+ PROFILES_COLUMN_OPEN + " text not null);";

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(RestaurentDBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + RESTAURENTS_TABLE_NAME);
		onCreate(db);
	}

}
