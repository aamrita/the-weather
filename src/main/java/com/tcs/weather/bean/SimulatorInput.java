package com.tcs.weather.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author amrutha
 * 
 * bean class for input object along with getters and setters.
 */

public class SimulatorInput {

	private String location;
	private List<Double> inpTemp;
	private List<Double> inpPressure;
	private List<Double> inpHumidity;
	private Date inpRefDate;
	private int hour;
	private Map<String,Cordinate> cordMap;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Double> getInpTemp() {
		return inpTemp;
	}
	public void setInpTemp(List<Double> inpTemp) {
		this.inpTemp = inpTemp;
	}
	public List<Double> getInpPressure() {
		return inpPressure;
	}
	public void setInpPressure(List<Double> inpPressure) {
		this.inpPressure = inpPressure;
	}
	public List<Double> getInpHumidity() {
		return inpHumidity;
	}
	public void setInpHumidity(List<Double> inpHumidity) {
		this.inpHumidity = inpHumidity;
	}
	public Date getInpRefDate() {
		return inpRefDate;
	}
	public void setInpRefDate(Date inpRefDate) {
		this.inpRefDate = inpRefDate;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public Map<String, Cordinate> getCordMap() {
		return cordMap;
	}
	public void setCordMap(Map<String, Cordinate> cordMap) {
		this.cordMap = cordMap;
	}
	
	
}
