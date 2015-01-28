package com.maya.findrestaurent;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class RestaurantDataSource {
	private SQLiteDatabase db;
	private RestaurentDBHelper databaseHelper;
	
	public RestaurantDataSource(Context context){
		databaseHelper = new RestaurentDBHelper(context);
	}
	
	public void open()throws SQLException{
		db = databaseHelper.getWritableDatabase();
	}
	
	public void close(){
		databaseHelper.close();
	}
	
	//Adding new restaurant
	public long addRestaurant(RestaurantModel restaurant){
		open();
		ContentValues values = new ContentValues();
		values.put(RestaurentDBHelper.PROFILES_COLUMN_NAME, restaurant.getName()); //get name
		values.put(RestaurentDBHelper.PROFILES_COLUMN_DESCRIPTION, restaurant.getDescription()); //get description
		values.put(RestaurentDBHelper.PROFILES_COLUMN_ADDRESS, restaurant.getAddress()); // get address
		values.put(RestaurentDBHelper.PROFILES_COLUMN_LATITUDE, restaurant.getLatitude()); // get lat
		values.put(RestaurentDBHelper.PROFILES_COLUMN_LONGITUDE, restaurant.getLongitude()); //get long
		values.put(RestaurentDBHelper.PROFILES_COLUMN_MENU, restaurant.getMenu()); // get menu
		values.put(RestaurentDBHelper.PROFILES_COLUMN_SPECIALMENU, restaurant.getSpecialMenu()); //get spc menu
		values.put(RestaurentDBHelper.PROFILES_COLUMN_CLOSE, restaurant.getClose()); //get close
		values.put(RestaurentDBHelper.PROFILES_COLUMN_OPEN, restaurant.getOpen()); // get open
		long inserted = db.insert(RestaurentDBHelper.RESTAURENTS_TABLE_NAME, null, values);
		close();
		return inserted;
	}
	
	//delete data from database
	public boolean delete (long eId){
		this.open();
		try {
			db.delete(RestaurentDBHelper.RESTAURENTS_TABLE_NAME, RestaurentDBHelper.RESTAURENTS_COLUMN_ID + "=" + eId, null);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
		}
	
	//Getting All restaurant list
	public ArrayList<RestaurantModel> getRestaurantList(){
		ArrayList<RestaurantModel> RestaurantList = new ArrayList<RestaurantModel>();
		
		//select all query
		String selectQuery = "SELECT * FROM " + RestaurentDBHelper.RESTAURENTS_TABLE_NAME;
		open();
		Cursor cursor = db.query(RestaurentDBHelper.RESTAURENTS_TABLE_NAME, 
				null, null, null, null, null, null, null);
		
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for(int i = 0; i < cursor.getCount(); i++){
				int id = cursor.getInt(cursor.getColumnIndex(RestaurentDBHelper.RESTAURENTS_COLUMN_ID));
				String name = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_NAME));
				String description = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_DESCRIPTION));
				String address = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_ADDRESS));
				String latitude = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_LATITUDE));
				String longitude = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_LONGITUDE));
				String menu = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_MENU));
				String specialmenu = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_SPECIALMENU));
				String close = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_CLOSE));
				String open = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_OPEN));
				
				RestaurantModel restaurant = new RestaurantModel(id, name, description, address, latitude, longitude, menu, specialmenu, close, open);
				RestaurantList.add(restaurant);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();
		
		//return profile list
		return RestaurantList;
	}
	
	//Getting All restaurant detail.
	public RestaurantModel getRestaurantDetail (long id){
		RestaurantModel restaurantDetail;
		
		//Select All query
		String selectQuery = "SELECT * FROM " + RestaurentDBHelper.RESTAURENTS_TABLE_NAME + "WHERE" + RestaurentDBHelper.RESTAURENTS_COLUMN_ID + "=" + id;
		open();
		Cursor cursor = db.query(RestaurentDBHelper.RESTAURENTS_TABLE_NAME, null, null, null, null, null, null);
		
		cursor.moveToFirst();
		
		String name = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_NAME));
		String description = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_DESCRIPTION));
		String address = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_ADDRESS));
		String latitude = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_LATITUDE));
		String longitude = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_LONGITUDE));
		String menu = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_MENU));
		String specialmenu = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_SPECIALMENU));
		String close = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_CLOSE));
		String open = cursor.getString(cursor.getColumnIndex(RestaurentDBHelper.PROFILES_COLUMN_OPEN));		
		
		restaurantDetail = new RestaurantModel(id, name, description, address, latitude, longitude, menu, specialmenu, close, open);
		
		cursor.moveToNext();
		
		cursor.close();
		db.close();
		
		//return restaurant details.
		
		return restaurantDetail;
		
	}
}
