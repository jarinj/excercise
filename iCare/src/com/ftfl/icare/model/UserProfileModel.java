package com.ftfl.icare.model;

public class UserProfileModel {
	// Variable Declaration
	String mID = null;
	//String mImageUser = null;
	String mName = null;
	String mDOB = null;
	String mHeight = null;
	String mWeight = null;
	String mGender = null;
	
	public UserProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfileModel(String mName, String mDOB,
			String mHeight, String mWeight, String mGender) {
		super();
		//this.mImageUser = mImageUser;
		this.mName = mName;
		this.mDOB = mDOB;
		this.mHeight = mHeight;
		this.mWeight = mWeight;
		this.mGender = mGender;
	}

	public UserProfileModel(String mID, String mName,
			String mDOB, String mHeight, String mWeight, String mGender) {
		super();
		this.mID = mID;
		//this.mImageUser = mImageUser;
		this.mName = mName;
		this.mDOB = mDOB;
		this.mHeight = mHeight;
		this.mWeight = mWeight;
		this.mGender = mGender;
	}

	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

/*	public String getmImageUser() {
		return mImageUser;
	}

	public void setmImageUser(String mImageUser) {
		this.mImageUser = mImageUser;
	}*/

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmDOB() {
		return mDOB;
	}

	public void setmDOB(String mDOB) {
		this.mDOB = mDOB;
	}

	public String getmHeight() {
		return mHeight;
	}

	public void setmHeight(String mHeight) {
		this.mHeight = mHeight;
	}

	public String getmWeight() {
		return mWeight;
	}

	public void setmWeight(String mWeight) {
		this.mWeight = mWeight;
	}

	public String getmGender() {
		return mGender;
	}

	public void setmGender(String mGender) {
		this.mGender = mGender;
	}
}