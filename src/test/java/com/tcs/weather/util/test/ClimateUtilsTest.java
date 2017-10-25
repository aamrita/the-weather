package com.tcs.weather.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tcs.weather.util.ClimateUtils;

public class ClimateUtilsTest {
 private static Double value;
	@Test
	public void getMeanTest(){
		value = (double) 20;
		assertEquals(value,ClimateUtils.getMean("30", "10"));
	}

}
