package com.tcs.weather.bean;

/**
 * @author amrutha
 * bean class for coordinates - Latitude, Longitude and Elevation along with getters and setters.
 */

public class Cordinate {
	private double lat;
	private double lon;
	private int ele;
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public int getEle() {
		return ele;
	}
	public void setEle(int ele) {
		this.ele = ele;
	}
	
	
}
