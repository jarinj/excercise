package com.ftfl.findrestaurant.model;

public class RestaurantModel {
	Integer mID = 0;
	String mName = "";
	String mDescription = "";
	String mAddress = "";
	String mLatitude = "";
	String mLongitude = "";
	String mMenu = "";
	String mSpecialMenu = "";
	String mClose = "";
	String mOpen = "";
	String mImage = "";

	public RestaurantModel() {

	}

	public RestaurantModel(String eName, String eDescription, String eAddress,
			String eLatitude, String eLongitude,
			String eMenu, String eSpecialMenu, String eClose,String eOpen, String eImage) {
		super();
		this.mName = eName;
		this.mDescription = eDescription;
		this.mAddress = eAddress;
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		this.mMenu = eMenu;
		this.mSpecialMenu = eSpecialMenu;
		this.mClose = eClose;
		this.mOpen = eOpen;
		this.mImage = eImage;
	}

	public RestaurantModel(Integer eId, String eName, String eDescription, String eAddress,
			String eLatitude, String eLongitude,
			String eMenu, String eSpecialMenu, String eClose,String eOpen, String eImage) {
		super();
		this.mID = eId;
		this.mName = eName;
		this.mDescription = eDescription;
		this.mAddress = eAddress;
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		this.mMenu = eMenu;
		this.mSpecialMenu = eSpecialMenu;
		this.mClose = eClose;
		this.mOpen = eOpen;
		this.mImage = eImage;
	}

	public Integer getId() {
		return mID;
	}

	public void setId(Integer id) {
		this.mID = id;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String eName) {
		this.mName = eName;
	}

	public String getmPurpose() {
		return mDescription;
	}

	public void setmPurpose(String ePurpose) {
		this.mDescription = ePurpose;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String eAddress) {
		this.mAddress = eAddress;
	}

	public String getmDistrict() {
		return mMenu;
	}

	public void setmDistrict(String eDistrict) {
		this.mMenu = eDistrict;
	}

	public String getmLatitude() {
		return mLatitude;
	}

	public void setmLatitude(String eLatitude) {
		this.mLatitude = eLatitude;
	}

	public String getmLongitude() {
		return mLongitude;
	}

	public void setmLongitude(String eLongitude) {
		this.mLongitude = eLongitude;
	}

	public String getmStartDate() {
		return mSpecialMenu;
	}

	public void setmStartDate(String eStartDate) {
		this.mSpecialMenu = eStartDate;
	}

	public String getmEndDate() {
		return mClose;
	}

	public void setmEndDate(String eEndDate) {
		this.mClose = eEndDate;
	}

	public String getmNote() {
		return mOpen;
	}

	public void setmNote(String eNote) {
		this.mOpen = eNote;
	}

	public String getmImage() {
		return mImage;
	}

	public void setmImage(String mImage) {
		this.mImage = mImage;
	}

}
