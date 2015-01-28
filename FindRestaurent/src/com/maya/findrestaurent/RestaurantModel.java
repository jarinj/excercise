package com.maya.findrestaurent;

public class RestaurantModel {
	long id;
	String name;
	String description;
	String address;
	String latitude;
	String longitude;
	String menu;
	String specialMenu;
	String close;
	String open;

	public RestaurantModel(String name, String description, String address,
			String latitude, String longitude, String menu, String specialMenu,
			String close, String open) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.menu = menu;
		this.specialMenu = specialMenu;
		this.close = close;
		this.open = open;
	}

	public RestaurantModel(long id, String name, String description,
			String address, String latitude, String longitude, String menu,
			String specialMenu, String close, String open) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.menu = menu;
		this.specialMenu = specialMenu;
		this.close = close;
		this.open = open;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getSpecialMenu() {
		return specialMenu;
	}

	public void setSpecialMenu(String specialMenu) {
		this.specialMenu = specialMenu;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "RestaurantModel [id=" + id + ", name=" + name
				+ ", description=" + description + ", address=" + address
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", menu=" + menu + ", specialMenu=" + specialMenu
				+ ", close=" + close + ", open=" + open + "]";
	}

}
