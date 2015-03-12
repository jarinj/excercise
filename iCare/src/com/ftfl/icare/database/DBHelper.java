package com.ftfl.icare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "iCare";

	// Create Database
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	// Creating Tables
	
	/*''''''''''''''''''''''''''''''''''''''''''''user profile table'''''''''''''''''''''''''''''''''''''''''''''''''	*/
	
	// user profile table names
	public static final String TABLE_USER = "user";

	// Table Columns names
	public static final String KEY_USER_ID = "id";
	//public static final String KEY_IMAGE_USER = "image";
	public static final String KEY_NAME = "name";
	public static final String KEY_DOB = "dob";
	public static final String KEY_HEIGHT = "height";
	public static final String KEY_WEIGHT = "weight";
	public static final String KEY_GENDER = "gender";

	// table information
	public String CREATE_USER_TABLE = "create table " + TABLE_USER + "("
			+ KEY_USER_ID + " integer primary key autoincrement, " 
			 
			+ KEY_NAME + " text not null, "
			+ KEY_DOB + " text not null, "
			+ KEY_HEIGHT + " text not null, "
			+ KEY_WEIGHT + " text not null, "
			+ KEY_GENDER + " text not null);";
	
/*''''''''''''''''''''''''''''''''''''''''''''Dr. profile table'''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	// Dr. profile table names
	public static final String TABLE_DOCTOR = "doctor";
	
	// Table Columns names
	public static final String KEY_DR_PROFILE_ID = "dr_id";
	public static final String KEY_DR_NAME = "dr_name";
	public static final String KEY_DR_PHONE = "dr_phone";
	public static final String KEY_DR_MAIL = "dr_mail";
	public static final String KEY_DR_ADDRESS = "dr_address";
	public static final String KEY_APPOINTMENT_DATE = "appointment_date";
	public static final String KEY_APPOINTMENT_TIME = "appointment_time";
	public static final String KEY_APPOINTMENT_REMINDER = "appointment_reminder";
	public static final String KEY_PRESCRIPTION_IMAGE = "prescription_image";
	
	// table information
	public String CREATE_DOCTOR_TABLE = "create table " + TABLE_DOCTOR + "("
			+ KEY_DR_PROFILE_ID + " integer primary key autoincrement, "  
			+ KEY_DR_NAME + " text not null, " 
			+ KEY_DR_PHONE + " text not null, "
			+ KEY_DR_MAIL + " text not null, "
			+ KEY_DR_ADDRESS + " text not null, "
			+ KEY_APPOINTMENT_DATE + " text not null, "
			+ KEY_APPOINTMENT_TIME + " text not null, "
			+ KEY_APPOINTMENT_REMINDER + " text not null, "
			+ KEY_PRESCRIPTION_IMAGE + " text not null);";
	
/*''''''''''''''''''''''''''''''''''''''''''''Diet table'''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	// Diet table names
	public static final String TABLE_DIET = "diet";
	
	// Table Columns names
	public static final String KEY_DIET_CHART_ID = "diet_id";
	public static final String KEY_FEAST = "feast";
	public static final String KEY_MENU = "menu";
	public static final String KEY_DIET_DATE = "diet_date";
	public static final String KEY_DIET_TIME = "diet_time";
	public static final String KEY_DIET_REMINDER = "diet_remainder";
	
	// table information
	public String CREATE_TABLE_DIET = "create table " + TABLE_DIET + "("
			+ KEY_DIET_CHART_ID + " integer primary key autoincrement, " 
			+ KEY_FEAST + " text not null, " 
			+ KEY_MENU + " text not null, "
			+ KEY_DIET_DATE + " text not null, "
			+ KEY_DIET_TIME + " text not null, "
			+ KEY_DIET_REMINDER + " text not null);";
	
	
/*''''''''''''''''''''''''''''''''''''''''''''Vaccination table'''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	// Dr. profile table names
	public static final String TABLE_VACCINATION = "vaccination";
	
	// Table Columns names
	public static final String KEY_VACCINATION_ID = "vaccination_id";
	//public static final String KEY_VACCINATION_USER = "vaccination_user";
	public static final String KEY_VACCINATION_NAME = "vaccination_name";
	public static final String KEY_VACCINATION_FOR = "vaccination_for";
	//public static final String KEY_VACCINATION_PHONE = "vaccination_phone";
	public static final String KEY_VACCINATION_ADDRESS = "vaccination_address";
	public static final String KEY_VACCINATION_DATE = "vaccination_date";
	public static final String KEY_VACCINATION_TIME = "vaccination_time";
	public static final String KEY_VACCINATION_REMINDER = "vaccination_remainder";
	
	// table information
	public String CREATE_TABLE_VACCINATION = "create table " + TABLE_VACCINATION + "("
			+ KEY_VACCINATION_ID + " integer primary key autoincrement, " 
			+ KEY_VACCINATION_NAME + " text not null, " 
			+ KEY_VACCINATION_FOR + " text not null, "
			
			+ KEY_VACCINATION_ADDRESS + " text not null, "
			+ KEY_VACCINATION_DATE + " text not null, "
			+ KEY_VACCINATION_TIME + " text not null, "
			+ KEY_VACCINATION_REMINDER + " text not null);";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_USER_TABLE);
		db.execSQL(CREATE_DOCTOR_TABLE);
		db.execSQL(CREATE_TABLE_DIET);
		db.execSQL(CREATE_TABLE_VACCINATION);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion + ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + CREATE_USER_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_DOCTOR_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_DIET);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_VACCINATION);
		onCreate(db);
	}

}