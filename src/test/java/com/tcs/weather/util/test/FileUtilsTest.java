package com.tcs.weather.util.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static org.junit.Assert.*;

import org.junit.Test;

import com.tcs.weather.exception.WeatherException;
import com.tcs.weather.util.FileUtils;

public class FileUtilsTest {
	private String inpDateRaw = "12/30/2017";
	private String inpDate = "12_30_2017_results";
	
	@Test
	public void createOutputFileTest() throws WeatherException, FileNotFoundException{
//		assertEquals(new PrintWriter(inpDate), FileUtils.createOutputFile(inpDateRaw));
		PrintWriter testWriter = FileUtils.createOutputFile(inpDateRaw);
		testWriter.write("testing file creation. . .");
		File file =new File(inpDate);
		assertTrue(file.exists());
	}

}
