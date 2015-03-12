package com.ftfl.icare.model;

public class DoctorProfileModel {
	Integer mID = 0;
	String mDrName = null;
	String mDrPhone = null;
	String mDrMail = null;
	String mDrAddress = null;
	String mAppointmentDate = null;
	String mAppointmentTime = null;
	String mAppointmentRemainder = null;
	String mImagePrescription = null;
	
	public DoctorProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorProfileModel(Integer mID, String mDrName, String mDrPhone,
			String mDrMail, String mDrAddress, String mAppointmentDate,
			String mAppointmentTime, String mAppointmentRemainder,
			String mImagePrescription) {
		super();
		this.mID = mID;
		this.mDrName = mDrName;
		this.mDrPhone = mDrPhone;
		this.mDrMail = mDrMail;
		this.mDrAddress = mDrAddress;
		this.mAppointmentDate = mAppointmentDate;
		this.mAppointmentTime = mAppointmentTime;
		this.mAppointmentRemainder = mAppointmentRemainder;
		this.mImagePrescription = mImagePrescription;
	}

	public Integer getmID() {
		return mID;
	}

	public void setmID(Integer mID) {
		this.mID = mID;
	}

	public String getmDrName() {
		return mDrName;
	}

	public void setmDrName(String mDrName) {
		this.mDrName = mDrName;
	}

	public String getmDrPhone() {
		return mDrPhone;
	}

	public void setmDrPhone(String mDrPhone) {
		this.mDrPhone = mDrPhone;
	}

	public String getmDrMail() {
		return mDrMail;
	}

	public void setmDrMail(String mDrMail) {
		this.mDrMail = mDrMail;
	}

	public String getmDrAddress() {
		return mDrAddress;
	}

	public void setmDrAddress(String mDrAddress) {
		this.mDrAddress = mDrAddress;
	}

	public String getmAppointmentDate() {
		return mAppointmentDate;
	}

	public void setmAppointmentDate(String mAppointmentDate) {
		this.mAppointmentDate = mAppointmentDate;
	}

	public String getmAppointmentTime() {
		return mAppointmentTime;
	}

	public void setmAppointmentTime(String mAppointmentTime) {
		this.mAppointmentTime = mAppointmentTime;
	}

	public String getmAppointmentRemainder() {
		return mAppointmentRemainder;
	}

	public void setmAppointmentRemainder(String mAppointmentRemainder) {
		this.mAppointmentRemainder = mAppointmentRemainder;
	}

	public String getmImagePrescription() {
		return mImagePrescription;
	}

	public void setmImagePrescription(String mImagePrescription) {
		this.mImagePrescription = mImagePrescription;
	}
}