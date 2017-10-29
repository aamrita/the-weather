package com.tcs.weather.simulation.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tcs.weather.simulation.WeatherForecast;

public class WeatherForecastTest {

	WeatherForecast weatherforecast = new WeatherForecast();
	private double[] tempList;
	private double finalList;

	@Before
	public void initializeValue() {
		tempList = new double[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		finalList =10;
	}

	@Test
	public void getForecastDataTest() {
		double forcastResult = weatherforecast.getForecastData(tempList);
		assertEquals(forcastResult,finalList,0);
		
	}
	/*@Test
	public void getForecastDataTest() {
		double[] forcastResult = weatherforecast.getForecastData(tempList);
		assertArrayEquals(forcastResult, finalList, 0);
	}*/
}
