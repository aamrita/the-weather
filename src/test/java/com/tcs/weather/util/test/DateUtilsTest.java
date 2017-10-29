package com.tcs.weather.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tcs.weather.constants.ConstantParam;
import com.tcs.weather.exception.WeatherException;
import com.tcs.weather.util.DateUtils;

public class DateUtilsTest {
	private static String inputDate, inputIsoDate,inphour;
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			ConstantParam.DATE_FORMAT_HR);
	private static SimpleDateFormat sdfIso = new SimpleDateFormat(
			ConstantParam.ISO_DATE);
	private static Date inputDateFormatted;
	private static List<Integer> myList = new ArrayList<Integer>();
	private static int hour = 10;

	@Before
	public void initializeValue() throws ParseException {
		inputDate = "10/20/2017 02:00:00";
		inputDateFormatted = sdf.parse(inputDate);
		inputIsoDate = sdfIso.format(inputDateFormatted);
		myList = Arrays.asList(3, 2, 1);
		inphour = "02:10:32";
	}

	@Test
	public void formatDateTest() throws WeatherException {
		assertEquals(inputDateFormatted, DateUtils.formatDate(inputDate,hour));
	}

	@Test
	public void checkIfFutureDateTest() throws WeatherException {
		assertTrue(DateUtils.checkIfFutureDate("11/15/2019"));
		assertTrue(DateUtils.checkIfFutureDate("10/22/2018"));
		assertFalse(DateUtils.checkIfFutureDate("11/15/2014"));
		assertFalse(DateUtils.checkIfFutureDate("10/22/2017"));
	}

	@Test
	public void sliceMonthTest() {
		assertEquals("10", DateUtils.sliceMonth(inputDate));
	}

	@Test
	public void getPreviousMonthsTest() {
		assertEquals(myList, DateUtils.getPreviousMonths(3));
	}

	@Test
	public void convertToIsoTest() {
		assertEquals(inputIsoDate, DateUtils.convertToIso(inputDateFormatted));
	}
	
	
	@Test
	public void validateHourTest(){
		assertEquals(2,DateUtils.validateHour(inphour));
	}
}
