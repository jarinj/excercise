package com.ftfl.findrestaurant.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ftfl.findrestaurant.utils.RestaurantModel;

public class RestaurantDataSource {
	private RestaurantDBHelper dbHelper;

	public RestaurantDataSource(Context context) {
		dbHelper = new RestaurantDBHelper(context);
	}

	// Create diet(data insert)
	public long insertRestaurantInfo(RestaurantModel resInfoObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_NAME,
				resInfoObj.getName());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_ADDRESS,
				resInfoObj.getAddrsss());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_LATITUDE,
				resInfoObj.getLatitude());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_LONGITUDE,
				resInfoObj.getLongitude());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_MENUS,
				resInfoObj.getMenus());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_SPECIAL_MENU,
				resInfoObj.getSpecialMenu());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUNM_CLOSE_DAY,
				resInfoObj.getCloseDay());
		contentValues.put(
				RestaurantDBHelper.RESTAURANT_COLUMN_DAILY_OPEN_TIME,
				resInfoObj.getDailyOpenTime());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_DESCRIPTION,
				resInfoObj.getDescription());

		return db.insert(dbHelper.RESTAURANT_TABLE_NAME, null, contentValues);

	}
	
	public ArrayList<RestaurantModel> getRestaurantList() {
			ArrayList<RestaurantModel> res_list  = new ArrayList<RestaurantModel>();
			
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor result = db.rawQuery("select * from restaurant ", null);
			if (result.moveToFirst()) {
			do {
			int id = result.getInt(0);
			String name = result.getString(1);
			String address = result.getString(2);
			String latitude = result.getString(3);
			String longitude = result.getString(4);
			String menus = result.getString(5);
			String specialmenus = result.getString(6);
			String close_day = result.getString(7);
			String daily_open_time = result.getString(8);
			String description= result.getString(9);
			
			RestaurantModel resInfo = new RestaurantModel(name, address, latitude, longitude, menus, specialmenus, close_day, daily_open_time, description);
			resInfo.setId(id);
			
			res_list.add(resInfo);
			} while (result.moveToNext());
			}
			return res_list;
			}
	
	public RestaurantModel getRestaurantById(int eId) {
		RestaurantModel restaurantObj = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from restaurant where id='" + eId + "'",
		null);
		if (result.moveToFirst()) {
		do {
			int id = result.getInt(0);
			String name = result.getString(1);
			String address = result.getString(2);
			String latitude = result.getString(3);
			String longitude = result.getString(4);
			String menus = result.getString(5);
			String specialmenus = result.getString(6);
			String close_day = result.getString(7);
			String daily_open_time = result.getString(8);
			String description= result.getString(9);
			
			restaurantObj = new RestaurantModel(name, address, latitude, longitude, menus, specialmenus, close_day, daily_open_time, description);
			restaurantObj.setId(id);
			
		} while (result.moveToNext());
		}
		return restaurantObj;
		}
	
	public long editRestaurant(Integer id, RestaurantModel resInfoObj) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_NAME,
				resInfoObj.getName());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_ADDRESS,
				resInfoObj.getAddrsss());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_LATITUDE,
				resInfoObj.getLatitude());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_LONGITUDE,
				resInfoObj.getLongitude());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_MENUS,
				resInfoObj.getMenus());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_SPECIAL_MENU,
				resInfoObj.getSpecialMenu());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUNM_CLOSE_DAY,
				resInfoObj.getCloseDay());
		contentValues.put(
				RestaurantDBHelper.RESTAURANT_COLUMN_DAILY_OPEN_TIME,
				resInfoObj.getDailyOpenTime());
		contentValues.put(RestaurantDBHelper.RESTAURANT_COLUMN_DESCRIPTION,
				resInfoObj.getDescription());
		return db.update("restaurant", contentValues, "id = ? ",
		new String[] { Integer.toString(id) });
		// return true;
		}
	 public void deleteRestaurant (Integer id)
	   {
	      SQLiteDatabase db = dbHelper.getWritableDatabase();
	      db.delete("restaurant", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }

}
