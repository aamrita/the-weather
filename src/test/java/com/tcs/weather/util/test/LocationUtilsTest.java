package com.tcs.weather.util.test;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tcs.weather.bean.Cordinate;
import com.tcs.weather.exception.WeatherException;
import com.tcs.weather.util.LocationUtils;

public class LocationUtilsTest {

	private static List<String> locationlist,
			locations = new ArrayList<String>();
	private static Map<String, Cordinate> cordMap = new HashMap<String, Cordinate>();
	Cordinate cordinate = new Cordinate();

	@Before
	public void initializeValues() {
		locationlist = Arrays.asList("Sydney","Melbourne", "Perth","Goldcoast","Adelaide");
		cordinate.setLat(-33.86);
		cordinate.setLon(151.2);
		cordinate.setEle(20);
	}

	@Test
	public void getDistinctLocationsTest() throws WeatherException {
		locations = new LocationUtils().getDistinctLocations();
		assertEquals(locationlist, locations);
	}

	@Test
	public void getFilePathTest() {
		String filepath = LocationUtils.getFilePath("Sydney", 02);
		Path path = Paths.get(filepath);
		assertTrue(path.endsWith("SYDNEY/02.csv"));

	}

	@Test
	public void getLatLonEleTest() throws WeatherException {
		cordMap.put("Sydney", cordinate);
		Map<String, Cordinate> resultMap = new HashMap<String, Cordinate>();
		resultMap = new LocationUtils().getLatLonEle("Sydney");
		assertEquals(resultMap.get("Sydney").getLat(), cordMap.get("Sydney")
				.getLat(), 0);
		assertEquals(resultMap.get("Sydney").getLon(), cordMap.get("Sydney")
				.getLon(), 0);
		assertEquals(resultMap.get("Sydney").getEle(), cordMap.get("Sydney")
				.getEle(), 0);
	}
}